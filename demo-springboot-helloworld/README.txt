前提条件:
1.安装JDK1.8, 设置好JAVA_HOME环境变量
2.安装Maven3(我用的是3.5.0), , 设置好MAVEN_HOME环境变量
3.构建Project, 运行命令: mvn clean package
4.启动应用程序
进行target目录，　运行命令: java -jar demo-springboot-helloworld-1.0-SNAPSHOT.jar

5.访问URL, 将看到spring-boot输出"Hello World!"到浏览器上
http://localhost:8080