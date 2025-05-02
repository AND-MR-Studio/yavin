FROM openjdk:21

# 创建工作目录
WORKDIR /app

# 单独使用COPY指令复制构建产物
COPY yavin-agent/target/yavin-agent-1.0-SNAPSHOT.jar ./yavin-agent-1.0-SNAPSHOT.jar
COPY yavin-agent/src/main/resources/files/yishao-prompt-template.txt yavin-agent/src/main/resources/files/yishao-prompt-template.txt

# 暴露Spring Boot默认端口
EXPOSE 8081

# 设置启动命令
ENTRYPOINT ["java", "-jar", "./yavin-agent-1.0-SNAPSHOT.jar"]
