商品管理项目总结












语言：java
小组：辛琪、陈辉、贾强、吕贵
工具：Eclipse、oracle、PL/SQL development
时间：2018-07-01
























转眼就到了7月，六月的尾声终于完结了第三个项目-商品管理系统，这个小项目主要包括的为添加编辑商品、添加编辑商品类别以及登陆。
第一：数据库表
Userquan： 用户权限表
Users：	用户表
Goods： 货物表
Typeinfo： 货物类型表

这里所有的表都是在pl/sql 里面写的，这点多少有点牵强

Users 用户表
Id 自动增长 （序列 + 触发器）number(10)
Username 用户名 varchar2(20)
Userpass 密码 varchar2(20)

Goods 货物表
Id 自动增长  （序列 + 触发器）number(10)
Gname  商品名字 varchar2(20)
Gtype  类别名 varchar2(20)
Gnum  数量 number(10)
Gprice 单价 number(10,2)
State 状态 1或者0 	1默认  1 上架 0 下架 number(2)

Typeinfo 类别表
Tid 自动增长 （序列 + 触发器）number(10)
Tname 类别名 varchae2(20)
State  1 或者0 	1使用 0 使用  1默认 number(2)

Userquan 用户权限表
Id 自动增长 （序列 + 触发器） number(2)
Username  外键users.username
Jurisdiction 权限 默认 0 number(10) 

第二： lib包
Oracle 数据库连接、dbutils数据库连接、pool连接池
建设路径

Src
Com.chinasoft
util包
数据库连接   
DBUtil
return DriverManager.getConnection(URL,USER,PASSWORD);
DBUtilPool
dataSourcePool = new BasicDataSource();
test包
主要将一些做的过程中所有的测试类都放在这里面。这些都是测试做的一些文件

Dao包
数据连接接口
四个表连接每个表需要的数据
Dao.impl包
实现dao包

entity包
实体包
Goods  Types  Users
每个实体
对应要读取的字符串，这个必须和数据库的对应

View包
视图包：所有视图都是用绘画画好，绝对布局所致：
Login登陆
AddGoods	添加货物
AddType		添加类别
GoodsMana	商品信息
GoodsMess	商品编辑
LeftMess		控制图层
Myedit		表格显示样式
Popup		浮动视图样式
SoftInfo		软件信息
TypeInfo		分类信息
TypeMana	分类编辑


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
