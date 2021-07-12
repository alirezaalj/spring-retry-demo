###Spring boot retry demo

This program is written using the spring boot framework, which stores some basic data at the start of the program in the h2 database. and client application for retry logic

my website: [https://alirezaalijani.ir](https://alirezaalijani.ir "https://alirezaalijani.ir")
##### Goals
- Start Learning spring retry
- Using Spring h2 database
- Using Spring Multi Module Project

# How to use
##### Project dependencies
- maven  : [How to use or Download](https://maven.apache.org/ "How to use or Download")
- java 11 or higher

#### Run Web Service Module
1. go to project folder module "web-service"
2. start by [spring-boot-maven-plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/ "spring-boot-maven-plugin")
```shell
mvn spring-boot:run
```
#### Run Client Service Module
1. go to project folder module "client-service"
2. start by [spring-boot-maven-plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/ "spring-boot-maven-plugin")
```shell
mvn spring-boot:run
```
3. web service module start on port 8080 and client module start on port 8081. you can see the console for retry actions.
4. command to create jar file in each module
```shell
 mvn clean install
```
5. end
