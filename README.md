### 参考的开源项目：

项目的开源文档：http://www.macrozheng.com/#/README

项目地址：https://github.com/macrozheng/mall

会记录练习时的笔记，以及一些自己的思考。



### mall整合SpringBoot+Mybatis搭建基本骨架

参考书籍：MyBatis从入门到精通，书籍还没看完，看了下项目用到的技术。

<img src="README.assets/image-20210917111916615.png" alt="image-20210917111916615" style="zoom:50%;" />

遇到的问题：mybatis生成的mapper重复

解决：https://github.com/dlagez/java-note-mac/blob/master/javaweb/%E9%94%99%E8%AF%AF%E8%AE%B0%E5%BD%95/3.mybatisGenerater%E7%94%9F%E6%88%90mapper%E9%87%8D%E5%A4%8D.md



### mall整合Swagger-UI实现在线API文档

没有遇到问题



### mall整合Redis实现缓存功能

没有遇到问题



### mall整合SpringSecurity和JWT实现认证和授权

对于SpringSecurity和JWT不太了解。所以阅读了几篇参考文章。

参考文章：

阮一峰大佬的jwt讲解：https://www.ruanyifeng.com/blog/2018/07/json_web_token-tutorial.html

芋道大佬源码SpringSecurity解析：https://www.iocoder.cn/Spring-Boot/Spring-Security/?github



### mall整合Elasticsearch实现商品搜索

Elasticsearch以前了解过，但是没有具体学习过，这几天在学习Elasticsearch基本使用 2021-09-16。

### mall整合了日志记录

完成这个功能时记录了一些类的使用：

ThreadLocal：https://blog.csdn.net/zphuangtang/article/details/120421790

RequestContextHolder：https://blog.csdn.net/zphuangtang/article/details/120421823

这个功能并不是很难，使用到了传统的servlet技术，大多数操作都是HttpServletRequest request完成。

### 搁置：大概半个月到一个月之后会继续开发这个项目

由于这个项目集成的组件比较多，上面的组件具体使用还不是很清楚，所以目前在了解它的权限管理模块：

专门开了一个仓库来练习它的权限管理模块。

有一点不同的是本项目的持久层使用的mybatis，mall-tiny使用了mybatis+mybatisPlus组合完成。对于一些比较简单的crud，MybatisPlus已经提供了基本的BaseMapper，这点和SpringDataJPA类似。使用了rest风格的操作。节省了开发时间。个人其实对mybatis的代码生成不是很喜欢，尤其是配置比较繁琐。而且生成出来的mapper较多，很多都不会去使用。

链接🔗：https://github.com/dlagez/mall-tiny

对应的笔记链接🔗：[点我跳转](https://github.com/dlagez/java-note-mac/blob/master/javaweb/mall-tiny%E7%AC%94%E8%AE%B0/1.mybatis-plus%E7%94%9F%E6%88%90%E4%BB%A3%E7%A0%81.md)