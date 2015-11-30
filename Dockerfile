#Start with tomcat 8 base
FROM tomcat:8.0.20-jre8
COPY war /usr/local/tomcat/webapps
EXPOSE 8080
RUN /usr/local/tomcat/bin/catalina.sh start
CMD tail -f /usr/local/tomcat/logs/catalina.out