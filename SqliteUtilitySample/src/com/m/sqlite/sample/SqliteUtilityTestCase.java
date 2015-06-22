package com.m.sqlite.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSON;
import com.m.sqlite.sample.bean.StatusContent;
import com.m.sqlite.sample.bean.StatusContents;
import com.m.support.sqlite.SqliteUtility;
import com.m.support.sqlite.SqliteUtilityBuilder;

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
