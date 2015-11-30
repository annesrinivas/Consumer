FROM ubuntu:14.04

RUN apt-get update
RUN apt-get install software-properties-common -y
RUN add-apt-repository ppa:webupd8team/java -y
RUN apt-get update
RUN echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
RUN apt-get install oracle-java8-installer -y
RUN apt-get install oracle-java8-set-default


RUN apt-get -y install tomcat7
RUN echo "JAVA_HOME=/usr/lib/jvm/java-8-oracle" >> /etc/default/tomcat7

EXPOSE 9999

COPY war /var/lib/tomcat7/webapps

# CMD service tomcat7 start && tail -f /var/lib/tomcat7/logs/catalina.out

 
 
