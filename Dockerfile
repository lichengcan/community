# 使用官方的OpenJDK 11作为基础镜像
FROM adoptopenjdk:17-jdk-hotspot

# 设置工作目录
WORKDIR /app

# 复制Spring Boot项目的构建产物到容器中
COPY target/community-0.0.1-SNAPSHOT.jar /app/community-0.0.1-SNAPSHOT.jar

# 暴露Spring Boot应用程序监听的端口号
EXPOSE 8080

# 启动Spring Boot应用程序
CMD ["java", "-jar", "community-0.0.1-SNAPSHOT.jar"]
