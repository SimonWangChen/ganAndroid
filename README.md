# SQLite 嵌入式数据库，程序驱动 跨平台的磁盘文件
[imooc sqlite](www.imooc.com/video/13325)

[imooc_greendao](www.imooc.com/learn/749)
[imooc_greendao](www.imooc.com/learn/760)

## sql语句


1. 建表 create table person(_id Integer primary key, name varchar(10), age Integer not null);

2. 删表 drop table table_name;

3. 插入数据 insert into person (_id, age) values(1,20);

4. 更新 update person set name="ls", age=22 where _id=1;

5. 删除数据 delete from person where _id=1;

6. 查询语句 select * from person where _id=1;  select _id,name from person;

## sqlite 数据库创建

SQLiteOpenHelper 获取数据库对象，对数据进行操作的函数

## bean 查询之后的实体类

## Sqlite 适配器 将 cursor 数据直接展示到 ui SimpleCursorAdapter

## 数据库分页操作

# GreenDao

## 配置

## 使用

## 分析