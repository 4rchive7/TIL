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
library('rJava')
library('RJDBC')
library('DBI')
drvName <- 'oracle.jdbc.driver.OracleDriver';
#127.0.0.1:8080/apex에서 DB를 만들어줌
id <- 'ruser';
pwd <- 'ruser';
url <- 'jdbc:oracle:thin:@localhost:1521:XE';


#1. Driver Loading
drv <- JDBC(driverClass = drvName,
            classPath = 'c:\\java_hive_lib\\ojdbc6.jar');


#2. Connection
conn <- dbConnect(drv,url,id,pwd);
#3. Statement
sqlstr <- 'SELECT * FROM emp'
sqlstr2 <- 'select * from kor_pop'
#4. ResultSet
emp <- dbGetQuery(conn, sqlstr2);
#5. Close
dbDisconnect(conn)
View(emp)
