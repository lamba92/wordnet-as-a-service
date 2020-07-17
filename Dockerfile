FROM adoptopenjdk:8-jdk-hotspot-bionic
RUN apt-get update && apt-get install -y maven >/dev/null
ADD . /opt/wordnet_as_a_service
RUN cd /opt/wordnet_as_a_service && mvn compile
#this is the best way I found to download the plugin dependencies at build time
RUN cd /opt/wordnet_as_a_service && mvn exec:help
WORKDIR /opt/wordnet_as_a_service
CMD ["mvn","exec:java"]
