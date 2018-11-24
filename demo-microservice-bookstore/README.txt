前提条件:
1.安装JDK1.8, 设置好JAVA_HOME环境变量
2.安装Maven3(我用的是3.5.0), 设置好MAVEN_HOME环境变量
３. 构建Project, 运行命令: mvn clean package
4. 启动应用程序
进行target目录，　运行命令: java -jar demo-microservice-bookstore-1.0-SNAPSHOT.jar

5. 访问以下URL
查看微服务book-service api文档
http://localhost:8090/v2/api-docs

使用Swagger测试Restful API
http://localhost:8090/swagger-ui.html

#列出所有书籍
http://localhost:8090/books

#根据bookId=2找书籍
http://localhost:8090/books/2

#Find books by keywork "Java"

#根据关键字"Java"找书籍
http://localhost:8090/books/search?keyword=Java

http://localhost:8090/v2/api-docs
http://localhost:8090/swagger-ui.html