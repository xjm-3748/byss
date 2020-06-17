简单的springboot项目，导入时候添加maven项目，然后添加根目录中的infozilla-master.jar到library，并添加根目录文件夹lib作为library  
com.example.demo.DemoApplication运行  
controller都在issueController中  
model是用到的实体  
Repository是JPA  
service是一些方法，fileDownload是文件下载，UzipFile是解压，getIssue是从GitHub上面爬取issue，getTopTen是调用查找接口，其他是一些数据库接口  
添加了spring.jpa.properties.hibernate.hbm2ddl.auto=update，所以不用添加数据库了  
运行截图见 截图.pdf  


因为写了两遍，controller中有用thymeleaf的方法，返回 modelAndView的  
vue 的方法多是返回实体的