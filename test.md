# druid

[![Build Status](https://travis-ci.org/alibaba/druid.svg?branch=master)](https://travis-ci.org/alibaba/druid)
[![Coverage Status](https://img.shields.io/codecov/c/github/alibaba/druid/master.svg)](https://codecov.io/github/alibaba/druid?branch=master&view=all#sort=coverage&dir=asc)  
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.alibaba/druid/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.alibaba/druid/)
[![GitHub release](https://img.shields.io/github/release/alibaba/druid.svg)](https://github.com/alibaba/druid/releases)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

Introduction
---

- git clone https://github.com/alibaba/druid.git
- cd druid && mvn install
- have fun.

Documentation
---

- 中文 https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98
- English https://github.com/alibaba/druid/wiki/FAQ


MyBatis Spring Adapter
======================

[![Build Status](https://travis-ci.org/mybatis/spring.svg?branch=master)](https://travis-ci.org/mybatis/spring)
[![Coverage Status](https://coveralls.io/repos/mybatis/spring/badge.svg?branch=master&service=github)](https://coveralls.io/github/mybatis/spring?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/5619b698a193340f2f000520/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5619b698a193340f2f000520)
[![Maven central](https://maven-badges.herokuapp.com/maven-central/org.mybatis/mybatis-spring/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.mybatis/mybatis-spring)
[![License](http://img.shields.iocense-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)


![mybatis-spring](http://mybatis.github.io/images/mybatis-logo.png)

MyBatis-Spring adapter is an easy-to-use Spring3 bridge for MyBatis sql mapping framework.

Essentials
----------

* [See the docs](http://mybatis.github.io/spring/)


spring
======

[![Join the chat at https://gitter.im/cr2121/spring](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/cr2121/spring?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

spring3 + hibernate4 + struts2
spring3 + mybatis + struts2

when first run this,
shall change the database setting in applicationContext-database.xml

    <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/world" />
    <property name="user" value="root" />
    <property name="password" value="123456" />
as your database setting

and run the following command in your mysql

    create table student(
    id bigint NOT NULL auto_increment,
    name VARCHAR(30),
    age INT,
    primary key (id));
