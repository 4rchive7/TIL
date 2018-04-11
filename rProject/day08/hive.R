#-----------------------------------------------------#
#install.packages('RJDBC')                            |
#rjdbc는 모두 대문자로!                               |
#install.packages('DBI')                              |
#진행 순서                                            |
#1. Driver Loading                                    |
#2. Connection                                        |
#3. Statement                                         |
#4. ResultSet                                         |
#5. Close                                             |
#-----------------------------------------------------#

library(rJava)
library(RJDBC)
library(DBI)




drvName <- 'org.apache.hive.jdbc.HiveDriver';
id <- 'root';
pwd <- '111111';
url <- 'jdbc:hive2://192.168.111.100:10000';

hive_lib <- "c:\\java_hive_lib"
.jinit();
.jaddClassPath(dir(hive_lib, full.names = T));
.jclassPath();

#1. Driver Loading
drv <- JDBC(driverClass = drvName,
            classPath = 'hive-jdbc-1.0.1.jar');


#2. Connection
conn <- dbConnect(drv,url,id,pwd);
#3. Statement
sqlstr <- 'SELECT * FROM airline_delay LIMIT 10'
sqlstr2 <- 'select * from kor_pop'
#4. ResultSet
air <- dbGetQuery(conn, sqlstr);
#5. Close
dbDisconnect(conn)
air
