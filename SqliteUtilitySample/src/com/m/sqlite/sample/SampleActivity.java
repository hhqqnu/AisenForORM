package com.m.sqlite.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.m.support.sqlite.SqliteUtility;
import com.m.support.sqlite.extra.Extra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SampleActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_main);

        List<TestBean> beanList = new ArrayList<TestBean>();
        for (int i = 0; i < 1; i++) {
            TestBean bean = new TestBean();
            bean.setStr("hello world");
            bean.setLongt(1234567890);
            bean.setIntt(123);
            bean.setId(UUID.randomUUID().toString());

            beanList.add(bean);
        }

//		SqliteUtility.getInstance().insert(null, beanList);

        String json = readAssetsFile("z_test.json", this);

        StatusContents contents = JSON.parseObject(json, StatusContents.class);

        StatusContent content = contents.getStatuses().get(0);

        Extra extra = new Extra();
        extra.setKey("haha");
        extra.setOwner("heihei");

        // 测试查询
        SqliteUtility.getInstance().deleteAll(null, StatusContent.class);
        for (int i = 0; i < 10; i++) {
            StatusContent newContent = JSON.parseObject(JSON.toJSONString(content), StatusContent.class);
            newContent.setId(i);
            newContent.setAttitudes_count(i);
            newContent.setComments_count(i);
            newContent.setReposts_count(i);
            SqliteUtility.getInstance().insert(null, newContent);
        }
//        List<StatusContent> contentList = SqliteUtility.getInstance().select(null, StatusContent.class);
        List<StatusContent> contentList = SqliteUtility.getInstance()
                .select(StatusContent.class, " attitudes_count > ? ", new String[]{ "5" },
                        null, null, null, null);
        for (StatusContent c : contentList)
            Log.d(SqliteUtility.TAG, c.getAttitudes_count() + "-" + c.getComments_count() + "-" + c.getReposts_count());

        Log.d(SqliteUtility.TAG, JSON.toJSONString(SqliteUtility.getInstance().selectById(null, StatusContent.class, "1")));

        // 新增一条数据

        if (true) return;

        SqliteUtility.getInstance().deleteAll(extra, StatusContent.class);
        SqliteUtility.getInstance().insert(extra, content);
        content.setText("哈哈");
        SqliteUtility.getInstance().insertOrReplace(null, content);
        content.setId(content.getId() + 1);
        SqliteUtility.getInstance().insert(extra, content);
        content.setText("嘿嘿");
        SqliteUtility.getInstance().update(extra, content);
        content.setId(content.getId() + 1);
        SqliteUtility.getInstance().insert(extra, content);
        SqliteUtility.getInstance().deleteById(extra, StatusContent.class, content.getId());

        String whereCaluse = " attitudes_count = ? ";
        String[] whereArgs = new String[]{ "1" };
        SqliteUtility.getInstance().delete(StatusContent.class, whereCaluse, whereArgs);

        if (true) return;

        StatusContent data = contents.getStatuses().get(0);
        contents.getStatuses().clear();
        for (int i = 0; i < 200; i++) {
            StatusContent randomData = JSON.parseObject(JSON.toJSONString(data), StatusContent.class);
            randomData.setId(i);
            contents.getStatuses().add(randomData);
        }

        SqliteUtility.getInstance().insertOrReplace(null, contents.getStatuses());

        if (true) return;

        List<WeiBoUser> users = new ArrayList<WeiBoUser>();
        WeiBoUser user = contents.getStatuses().get(0).getUser();

        for (int i = 0; i < 5; i++) {
            WeiBoUser newUser = JSON.parseObject(JSON.toJSONString(user), WeiBoUser.class);
            newUser.setIdstr(i + "");
            newUser.setStatus(null);
//            newUser.setAllow_all_comment(i % 2 == 0 ? false : true);
            newUser.setAllow_all_comment(true);
            users.add(newUser);
        }




        SqliteUtility.getInstance().insertOrReplace(null, users);
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
