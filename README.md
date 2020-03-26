# XXE 
## 项目说明
本项目为XXE测试demo。项目代码中包含禁止外部实体的代码。
## XXE使用的方法及对应的访问地址
| CODE | URL|
|  --- | ---|
| DocumentBuilderFactory | localhost:8080/xxe/xxe1|
| SAXBuilder|localhost:8080/xxe/xxe2|
|SAXParserFactory|localhost:8080/xxe/xxe3|
| SAXReader|localhost:8080/xxe/xxe4|
| SAXTransformerFactory|localhost:8080/xxe/xxe5|
| SchemaFactory|localhost:8080/xxe/xxe6|
| TransformerFactory|localhost:8080/xxe/xxe7|
| SchemaFactory|localhost:8080/xxe/xxe8|
| XMLInputFactory|localhost:8080/xxe/xxe9|
| XMLReader |localhost:8080/xxe/xxe10|
 
## 项目运行
`mvn clean package`
或者直接下载release jar包

## 内部poc
```xml
<?xml version="1.0" encoding="ISO-8859-1" ?>
        <!DOCTYPE example [
                <!ELEMENT example ANY >
                <!ENTITY file SYSTEM "http://localhost:10000" >
                ]>
<example>&file;</example>
```
需要本地监听10000端口


