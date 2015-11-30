FROM tomcat:8.0.20-jre8
COPY /war/Consumer.war /usr/local/tomcat/webapps
