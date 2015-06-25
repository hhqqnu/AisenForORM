package org.aisen.orm.sample;

import java.io.File;

import android.app.Application;
import android.os.Environment;

public class MyApplication extends Application {
	
	public void onCreate() {
		super.onCreate();
		
//		new SqliteUtilityBuilder()
//					.configSdcardPath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "sqliteutility" + File.separator)
//					.configVersion(1)
//					.build(this);
	};

}
