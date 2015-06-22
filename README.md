# AisenForORM

## 说明

Android平台操作Sqlite的轻量级ORM库，部分代码来自GreenDAO，API参照实现Sqlite的基本API，追求使用极少的配置，面向对象操作数据库的ORUD操作;

SqliteUtility适合做数据的静态缓存，以及简单的排序、统计等DB操作，Aisen微博所有的数据缓存都使用该库;

## 功能

 * 支持程序内部、SD卡建库
 * 支持通过DBName同时操作多个数据库
 * 支持Object对象的字段扩展自动新增Table的数据库字段（删除字段不支持，Sqlite不支持Table的动态column删除操作）
 * API参照Sqlite的基本API，零学习成本
 * 支持自增主键的配置
 * Table支持多账户

## 

每个Object默认有3个组合主键（@AutoIncrementPrimaryKey除外，它只有一个）
1、@PrimaryKey配置的字段；
2、com_m_common_key字段
3、com_m_common_owner字段
每个Object有一个默认字段，标志该数据的最后更新时间
com_m_common_createat

## 初始化数据库

```java
	new SqliteUtilityBuilder()
		// 如果是SD卡的DB，配置SD卡路径	
		// 如果不配置这个方法，DB默认在/data/data/packagename/路径下
		.configSdcardPath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "sqliteutility" + File.separator)
		// 配置DB的版本号，如果高于历史版本号，数据库升级默认是Drop所有的Table
		.configVersion(1)
		.build(getContext());
```

## 简单的配置

使用注解配置实体的主键字段，必须配置，否则将crash



## API
 
 * insert(Extra extra, T... entities)

   根据一个数组存库，如果某实体已存在，则忽略该实体的存库

 * insertOrReplace(Extra extra, T... entities)

   根据一个数组存库，如果某实体已存在，则使用新的实体更新DB数据

 * insert(Extra extra, List<T> entityList)

   同上

 * insertOrReplace(Extra extra, List<T> entityList)

   同上

 * insert(Extra extra, T... entities)

 * insert(Extra extra, T... entities)

 * insert(Extra extra, T... entities)

 * insert(Extra extra, T... entities)

 * insert(Extra extra, T... entities)
   


## License

Copyright (c) 2014 Jeff Wang

Licensed under the [Apache License, Version 3.0](http://opensource.org/licenses/GPL-3.0)

