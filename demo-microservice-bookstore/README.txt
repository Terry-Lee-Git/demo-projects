ǰ������:
1.��װJDK1.8, ���ú�JAVA_HOME��������
2.��װMaven3(���õ���3.5.0), ���ú�MAVEN_HOME��������
��. ����Project, ��������: mvn clean package
4. ����Ӧ�ó���
����targetĿ¼������������: java -jar demo-microservice-bookstore-1.0-SNAPSHOT.jar

5. ��������URL
�鿴΢����book-service api�ĵ�
http://localhost:8090/v2/api-docs

ʹ��Swagger����Restful API
http://localhost:8090/swagger-ui.html

#�г������鼮
http://localhost:8090/books

#����bookId=2���鼮
http://localhost:8090/books/2

#Find books by keywork "Java"

#���ݹؼ���"Java"���鼮
http://localhost:8090/books/search?keyword=Java

http://localhost:8090/v2/api-docs
http://localhost:8090/swagger-ui.html