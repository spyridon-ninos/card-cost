FROM openjdk:14-jdk-buster
RUN rm -f /usr/local/openjdk-14/bin/jar; \
 rm -f /usr/local/openjdk-14/bin/jarsigner; \
 rm -f /usr/local/openjdk-14/bin/javac; \
 rm -f /usr/local/openjdk-14/bin/javap; \
 rm -f /usr/local/openjdk-14/bin/jcmd; \
 rm -f /usr/local/openjdk-14/bin/jconsole; \
 rm -f /usr/local/openjdk-14/bin/jdb; \
 rm -f /usr/local/openjdk-14/bin/jdeprscan; \
 rm -f /usr/local/openjdk-14/bin/jdeps; \
 rm -f /usr/local/openjdk-14/bin/jfr; \
 rm -f /usr/local/openjdk-14/bin/jhsdb; \
 rm -f /usr/local/openjdk-14/bin/jimage; \
 rm -f /usr/local/openjdk-14/bin/jinfo; \
 rm -f /usr/local/openjdk-14/bin/jjs; \
 rm -f /usr/local/openjdk-14/bin/jlink; \
 rm -f /usr/local/openjdk-14/bin/jmap; \
 rm -f /usr/local/openjdk-14/bin/jmod; \
 rm -f /usr/local/openjdk-14/bin/jpackage; \
 rm -f /usr/local/openjdk-14/bin/jps; \
 rm -f /usr/local/openjdk-14/bin/jrunscript; \
 rm -f /usr/local/openjdk-14/bin/jshell; \
 rm -f /usr/local/openjdk-14/bin/jstack; \
 rm -f /usr/local/openjdk-14/bin/jstat; \
 rm -f /usr/local/openjdk-14/bin/jstatd; \
 rm -f /usr/local/openjdk-14/bin/keytool; \
 rm -f /usr/local/openjdk-14/bin/rmiregistry;
COPY target/card-cost-*.jar /card-cost.jar
RUN useradd -M -U card-cost
USER card-cost
CMD ["java", "-jar", "card-cost.jar"]
