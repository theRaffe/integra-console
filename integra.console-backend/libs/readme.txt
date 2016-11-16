download ojdbc7 from http://www.oracle.com/technology/software/tech/java/sqlj_jdbc/index.html

mvn install:install-file -Dfile=ojdbc7.jar  -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar