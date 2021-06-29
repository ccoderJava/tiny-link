## tiny-link

> 一个看起来还不错的短链接服务

## 使用方法

+ 初始化[/document/tiny-link.sql](/document/tiny-link.sql)数据库脚本,并且注意修改`application.yml`中数据库配置
+ 启动`tiny-link-web`模块下的`TinyLinkApplication`
+ 访问`http://localhost:8080/swagger-ui/index.html` 打开Swagger测试页面



## 响应参数

```json
{
  "tinyLink": "http://localhost:8090/t/YNvAFb",
  "originLink": "https://www.baidu.com",
  "tinyType": "S",
  "timestamp": 1624945803647
}
```


