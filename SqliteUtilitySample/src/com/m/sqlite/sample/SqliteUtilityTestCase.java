package com.m.sqlite.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.m.sqlite.sample.bean.StatusContent;
import com.m.sqlite.sample.bean.StatusContents;
import com.m.support.sqlite.SqliteUtility;
import com.m.support.sqlite.SqliteUtilityBuilder;
import com.m.support.sqlite.utils.DBLogger;

import android.content.Context;
import android.os.Environment;
import android.test.AndroidTestCase;

public class SqliteUtilityTestCase extends AndroidTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		new SqliteUtilityBuilder()
				.configSdcardPath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "sqliteutility" + File.separator)
				.configVersion(1)
				.build(getContext());
	}
	
	public void testCreateTable() throws Throwable {
		SqliteUtility.getInstance().select(null, StatusContent.class);
	}
	
	public void testInsertObject() throws Throwable {
		// 清理数据
//		SqliteUtility.getInstance().deleteAll(null, StatusContent.class);
		// 读取复杂的JSON文件
		String json = readAssetsFile("z_test.json", getContext());
		// 转换成实体对象
		StatusContents contents = JSON.parseObject(json, StatusContents.class);
		// 取第一个对象
        StatusContent content = contents.getStatuses().get(0);
        content.setId(10000);
//        content.setText("没有被替换");
        content.setText("没有被替换，嘿嘿");
        // 插库
        SqliteUtility.getInstance().insert(null, content);
	}
	
	public void testInsertOrReplace() throws Throwable {
		// 清理数据
//		SqliteUtility.getInstance().deleteAll(null, StatusContent.class);
		// 读取复杂的JSON文件
		String json = readAssetsFile("z_test.json", getContext());
		// 转换成实体对象
		StatusContents contents = JSON.parseObject(json, StatusContents.class);
		// 取第一个对象
        StatusContent content = contents.getStatuses().get(0);
        content.setId(10000);
        content.setText("被替换掉了");
        // 插库，如果数据存在，则替换掉数据
        SqliteUtility.getInstance().insertOrReplace(null, content);
        content.setId(10001);
        content.setText("我是新的数据");
        SqliteUtility.getInstance().insertOrReplace(null, content);
	}
	
	public void testInsertList() throws Throwable {
		// 清理数据
		SqliteUtility.getInstance().deleteAll(null, StatusContent.class);
		// 读取复杂的JSON文件
		String json = readAssetsFile("z_test.json", getContext());
		// 转换成实体对象
		StatusContents contents = JSON.parseObject(json, StatusContents.class);
		// 取第一个对象
        StatusContent content = contents.getStatuses().get(0);
        // 创建一个集合
        List<StatusContent> list = new ArrayList<StatusContent>();
        for (int i = 0; i < 10000; i++) {
        	// copy一个新对象
        	StatusContent newBean = JSON.parseObject(JSON.toJSONString(content), StatusContent.class);
        	newBean.setId(i);
        	newBean.setAttitudes_count(0);
        	list.add(newBean);
        }
        // 插库
        SqliteUtility.getInstance().insert(null, list);
	}
	
	public void testInsertOrReplaceList() throws Throwable {
		// 清理数据
//		SqliteUtility.getInstance().deleteAll(null, StatusContent.class);
		// 读取复杂的JSON文件
		String json = readAssetsFile("z_test.json", getContext());
		// 转换成实体对象
		StatusContents contents = JSON.parseObject(json, StatusContents.class);
		// 取第一个对象
        StatusContent content = contents.getStatuses().get(0);
        // 创建一个集合
        List<StatusContent> list = new ArrayList<StatusContent>();
        for (int i = 0; i < 10000; i++) {
        	// copy一个新对象
        	StatusContent newBean = JSON.parseObject(JSON.toJSONString(content), StatusContent.class);
        	newBean.setId(i);
        	newBean.setAttitudes_count(new Random().nextInt(1000));
        	newBean.setBmiddle_pic(UUID.randomUUID().toString());
        	list.add(newBean);
        }
        // 插库
        SqliteUtility.getInstance().insertOrReplace(null, list);
	}
	
	public void testSelectById() throws Throwable {
		StatusContent bean = SqliteUtility.getInstance().selectById(null, StatusContent.class, 1);
		if (bean != null)
			DBLogger.d(SqliteUtility.TAG, JSON.toJSON(bean));
	}
	
	public void testSelectByArgs() throws Throwable {
		// select * from StatusContent where attitudes_count < 100
		String selection = " attitudes_count < ? ";
		String[] selectionArgs = new String[]{ "100" };
		List<StatusContent> list = SqliteUtility.getInstance().select(StatusContent.class, selection, selectionArgs);
		DBLogger.d(SqliteUtility.TAG, "list size = " + list.size());
		
		// select * from StatusContent where bmiddle_pic like '%abc%'
		selection = " bmiddle_pic like ? ";
		selectionArgs = new String[]{ "%abc%" };
		list = SqliteUtility.getInstance().select(StatusContent.class, selection, selectionArgs);
		DBLogger.d(SqliteUtility.TAG, "list size = " + list.size());
		
		selection = " bmiddle_pic like ? ";
		selectionArgs = new String[]{ "%abc%" };
		String groupBy = null;
		String having = null;
		String orderBy = null;
		String limit = null;
		list = SqliteUtility.getInstance().select(StatusContent.class, selection, selectionArgs, groupBy, having, orderBy, limit);
		DBLogger.d(SqliteUtility.TAG, "list size = " + list.size());
	}
	
	public void testDeleteById() throws Throwable {
		SqliteUtility.getInstance().deleteById(null, StatusContent.class, "1");
	}
	
	public void testDeleteByArgs() throws Throwable {
		String whereClause = " id < ? ";
		String[] whereArgs = new String[]{ "100" }; 
		SqliteUtility.getInstance().delete(StatusContent.class, whereClause, whereArgs);
	}
	
	public void testSum() throws Throwable {
		// select sum(attitudes_count) as _sum_ from StatusContent where  bmiddle_pic like '%abc%' 
		String column = "attitudes_count";
		String whereClause = null;// " bmiddle_pic like ? "
		String[] whereArgs = new String[]{ "%abc%" };
		long sum = SqliteUtility.getInstance().sum(StatusContent.class, column, whereClause, whereArgs);
		DBLogger.d(SqliteUtility.TAG, sum);
	}
	
	public void testCount() throws Throwable {
		// select count(*) as _count_ from StatusContent where  bmiddle_pic like '%abc%' 
		String whereClause = " bmiddle_pic like ? ";// " bmiddle_pic like ? "
		String[] whereArgs = new String[]{ "%abc%" };
		long count = SqliteUtility.getInstance().count(StatusContent.class, whereClause, whereArgs);
		DBLogger.d(SqliteUtility.TAG, count);
	}
	
	public static String readAssetsFile(String file, Context context) {
        StringBuffer sb = new StringBuffer();
        try {
            InputStream is = context.getResources().getAssets().open(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String readLine = null;
            while ((readLine = reader.readLine()) != null) {
                sb.append(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}
