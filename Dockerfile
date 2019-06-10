FROM java:8
COPY oss-automation.war app.war
CMD ["java", "-jar", "-Dspring.profiles.active=default", "/app.war"]