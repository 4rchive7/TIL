#connJDBC를 실행 후 볼것

#ex1 emp 회사의 근속 연수(오늘 날짜기준 일)와 월급의 상관 관계를 그래프로 ㄱㄱ

raw_emp <- emp
tmp <- emp[,c('HIREDATE', 'SAL')]
class(tmp$HIREDATE)


tmp$year <- as.numeric(substr(tmp$HIREDATE,1,4))
tmp$month <- as.numeric(substr(tmp$HIREDATE, 6, 7))
tmp$hiredmonth <- (2018-tmp$year)*12 + (12-tmp$month) + 4

library(ggplot2)
ggplot(data = tmp, aes(x=hiredmonth, y=SAL))+geom_line()


# SELECT MONTHS_BETWEEN(SYSDATE, HIREDATE) AS MON FROM EMP;

tmp2 <- emp
tmp2
tmp2$HIREDATE <- as.Date(tmp2$HIREDATE)
now <- Sys.Date()
tmp3 <- now-tmp2$HIREDATE




#ex2 sql developer로 데이터 삽입후 r로 분석하여

#조사 단위 별로 연도별로 1~7인가구의 변화
raw_emp<-emp

library('dplyr')
tmp <- aggregate(TOTAL~LOC+YEAR+FAMILY, emp, sum)
tmp <- tmp[tmp$LOC !='동부' & tmp$YEAR==2010,]
loc_name <- c('동부', '면부', '읍부', '경기도', '경상남도', '경상북도')
tmp <- tmp[tmp$LOC != loc_name & tmp$YEAR==2010,]
View(tmp)
tmp <- tmp[tmp$FAMILY !='총계' & tmp$YEAR==2010,]
pp <- tmp[tmp$YEAR==2010,]
View(pp)
pp <- pp[pp$LOC !='동부',]
View(pp)
tmp2 <- head(tmp[order(tmp$TOTAL, decreasing =T),], 50)

tmp2 <- head(pp[order(pp$TOTAL, decreasing =T),], 10)
View(tmp)
library('ggplot2')
#options(scipen = 10000000)
ggplot(data=tmp2, aes(x=FAMILY, y=TOTAL, col=LOC))+geom_point()

ggplot(data=pp, aes(x=FAMILY, y=TOTAL, col=LOC))+geom_point()








