FROM tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./estate-spring-boot-1.0.war /usr/local/tomcat/webapps/ROOT.war
COPY ./wait/wait-for-it.sh wait-for-it.sh
RUN chmod 755 wait-for-it.sh
EXPOSE 8080
ENTRYPOINT ["./wait-for-it.sh", "mysql-db:3306", "-t", "30", "--", "catalina.sh", "run"]