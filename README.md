# AisenForORM

Android平台Sqlite数据库的ORM库，API同步Sqlite的API，追求使用极少的配置实现面向对象的CRUD操作;

专门用做数据的静态缓存，以及sum、count等DB操作，Aisen微博所有的数据缓存都使用该库;性能和使用请参照Aisen微博的代码。

## 功能

 * 支持/data/data/packagename/databases/、/sdcard/路径建库
 * 支持操作多个数据库
 * Object和Tabel映射的自动维护（只支持Object的新增字段）
 * 支持自增主键的配置
 * Table支持多账户，key等扩展字段的配置

## 扩展字段

每个Object默认有3个组合主键（@AutoIncrementPrimaryKey除外，它只有一个）

 * @PrimaryKey配置的字段；
 * com_m_common_key字段
 * com_m_common_owner字段

>com_m_common_owner是标志数据的所有者，com_m_common_key是标志数据的类型。

每个Object有一个更新时间的字段

 * com_m_common_createat

>标志数据的最后更新时间

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

```java
// 配置表名（可选，不配置表名默认是Class的全路径）
@TableName(table = "StatusContent")
public class StatusContent implements Serializable {
...
}

// 配置主键（必须）
@PrimaryKey(column = "id")
private long id;

```



## API

 * Insert方法
 
  * insert(Extra extra, T... entities)

>根据一个数组存库，如果某实体已存在，则忽略该实体的存库

  * insertOrReplace(Extra extra, T... entities)

>根据一个数组存库，如果某实体已存在，则使用新的实体更新DB数据

  * insert(Extra extra, List<T> entityList)

  * insertOrReplace(Extra extra, List<T> entityList)

 * insert(Extra extra, T... entities)

 * insert(Extra extra, T... entities)

 * insert(Extra extra, T... entities)

 * insert(Extra extra, T... entities)

 * insert(Extra extra, T... entities)
   


## License

Copyright (c) 2014 Jeff Wang

Licensed under the [Apache License, Version 3.0](http://opensource.org/licenses/GPL-3.0)

