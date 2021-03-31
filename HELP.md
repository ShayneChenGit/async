# Spring Web 异步处理
## 适用场景
前端发来的请求，需要处理长事务，阻塞导致超时，导致前端页面报错。

## 四种方式
### 普通阻塞
URL： 127.0.0.1:8080/blockingRequest

日志：<br>
blockingRequest: start <br>
wording thread <br>
blockingRequest: end <br>

返回：
超时返回

### @Async注解
URL：127.0.0.1:8080/asyncMethod

日志：<br>
asyncMethod: start <br>
wording thread <br>
asyncMethod: end <br>

返回：
超时返回

### Callable
URL：127.0.0.1:8080/callable

日志：<br>
callable: start <br>
callable: end <br>
wording thread <br>
child task thread

返回：
超时返回

### DeferredResult
URL：127.0.0.1:8080/defferedResultRequest

日志：<br>
defferedResultRequest: start <br>
defferedResultRequest: end <br>
wording thread <br>

返回：
当即返回
