FROM adoptopenjdk:8-jdk-hotspot-bionic

ADD ./build/install /

CMD ["/wordnet-as-a-service/bin/wordnet-as-a-service"]
