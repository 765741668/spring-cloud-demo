# spring-cloud-demo
分布式配置中心config、服务注册中心registry(eureka)、网关gateway(zuul)、监控通知monitor、
服务追踪分析zipkin、远程调用FeignClient(声明式http resful)
以上组件除了（远程调用功能）都有内置ui界面操作

# 启动
1. 修改 hosts 将主机名指向到本地 127.0.0.1	registry config rabbitmq monitor 
2. 顺序启基础服务 registry、config、monitor、zipkin、rabbitmq(没安装的话，在配置中将相关配置移除)
3. 启动 gateway
4. 启动微服务

# RESFUL 访问
1.各微服务基resful通过网关访问：一级路径为网关配置给user-service的别名，二级路径为具体服务本身的请求路径
user-service: localhost:8080/user/user/1
demo-service: localhost:8080/demo/users/1

2.内部微服务独立访问
user-service: localhost:8002/user/1
demo-service: localhost:8001/users/1
