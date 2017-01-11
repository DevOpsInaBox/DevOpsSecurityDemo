FROM java:8

ENV JAR SampleProducts-0.0.1-SNAPSHOT.jar
ENV MAIN_CLASS com.newt.SampleProductsApplication

ADD "./target/SampleProducts-0.0.1-SNAPSHOT.jar" "SampleProducts-0.0.1-SNAPSHOT.jar"

CMD java  -jar SampleProducts-0.0.1-SNAPSHOT.jar
EXPOSE 8080