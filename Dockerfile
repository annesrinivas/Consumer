#Start with ubuntu
FROM ubuntu:15.04

#install java
RUN apt-get update
RUN apt-get install software-properties-common -y
RUN add-apt-repository ppa:webupd8team/java -y
RUN apt-get update
RUN echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
RUN apt-get install oracle-java8-installer -y
RUN apt-get install oracle-java8-set-default

#install mongodb
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 7F0CEB10
RUN echo "deb http://repo.mongodb.org/apt/ubuntu "$(lsb_release -sc)"/mongodb-org/3.0 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-3.0.list
RUN apt-get update && apt-get install -y mongodb-org
RUN mkdir -p /data/db
EXPOSE 27017

#install tomcat8
RUN apt-get -y install tomcat8

#install consumer service
COPY war /usr/local/tomcat/webapps

#start tomcat
EXPOSE 8080
RUN /usr/local/tomcat/bin/catalina.sh start
CMD tail -f /usr/local/tomcat/logs/catalina.out