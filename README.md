Spring Boot个人博客系统
==========

作者 :jianghuan

qq :2959543063

开发工具
----------

IntelliJ IDEA + Navicat  + Git + Chrome

开发技术
----------

前端技术 ：`<Thymeleaf + semantic-UI + jQuery + ajax>`

后端技术 ：`<SpringBoot 2.0 + Mybatis + MySQL 5.5+>`

中间件技术 :`<Redis 6.0+>`

主要功能
---------

支持文章，页面，分类目录，标签的添加，删除，编辑等。文章及页面支持Markdown，支持代码高亮。

支持文章全文搜索。

支持评论功能，包括发表回复评论。

导航栏功能，最新文章，最新推荐，根据分类和标签查看博客，归档，日排行榜等

支持`<Redis>`缓存

项目演示地址
------------

http://120.55.160.231:8080/

让项目跑起来
-------------

**将项目克隆到本地**

```shell
git clone git@github.com:petr-i-chor/blog.git
```
**用IDEA导入项目**

修改`<resources>`文件下的`<application-pro.yml>`文件里的数据库配置信息和Redis配置信息（Redis需要先自行下载配置）

**导入blog.sql**

在命令行界面打开数据库输入`<source xxx/blog.sql>`

或者直接拖拽到Navicat中

**运行项目**

前端地址：localhost:8080

后端地址：localhost:8080/admin

问题相关
---------

有任何问题欢迎将问题描述发送至我邮箱`<2959543063@qq.com>`,我会尽快解答

博客也有对本项目有更进一步的讲解

博客地址:https://blog.csdn.net/weixin_45061732



<hr>
本项目是学习了b站李仁密老师的视频之后的个人理解和知识汇总，视频中持久层的技术用到JPA，本项目用的是Mybatis

视频地址：https://www.bilibili.com/video/BV1nE411r7TF


