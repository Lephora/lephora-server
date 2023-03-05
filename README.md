# lephora-server

Lephora server

### 核心原则

代码架构采用DDD领域驱动和CQRS命令查询职责分离模式进行开发，编写代码使用TDD模式，前后端对接以契约先行（openapi spec）为原则。

### 基础设施

database： postgres:14.5

### 配置hooks
    
```shell
make hooks
```

### 运行

+ 启动数据库和缓存服务

```shell
make env
```

+ 本地运行服务
```shell
make run.local
```

### 运行测试&代码检查

```shell
make check.local
```
+ 生成Jacoco报告

```shell
./gradlew jacocoTestReport
```

+ 代码风格检查

```shell
make check.style
```

### 构建
```shell
make jar
```

