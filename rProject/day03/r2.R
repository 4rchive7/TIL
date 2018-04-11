library('ggplot2')
mpg
#View(mpg)


tf <- data.frame(x=c(1:5),y=(6:10))
tfc <- tf
colnames(tfc) <- c('Col1', 'Col2')


#install.packages('dplyr')
library(dplyr)
tfc <- rename(tf, 'XX1'=x)

mpg2 <-rename(mpg, 'city'='cty', 'highway' = 'hwy')

mpg2$drvavg <- (mpg2$highway+mpg2$city)/2
mpg2$total <-(mpg2$city+mpg2$highway)/2
hist(mpg2$total)
View(mpg2)

mpg2$test <- ifelse(mpg2$drvavg >= 20, 'PASS', 'FAIL')
class(table(mpg2$test))
as.vector(table(mpg2$test))
hist(table(mpg2$test))
qplot(mpg2$test)

#total연비를 3등급으로 누나기
#30 이상 A
#20 이상 B
#20 미만 C
#grad라는 칼럼믈 만들어 추가 후 grade를 기준으로 그래프 ㄱㄱ
mpg2$grad <- ifelse(mpg2$drvavg >= 30, 'A', 
                    ifelse(mpg2$drvavg >=20 , 'B', 'C'))
#else if 문 사용
mpg2
qplot(mpg2$grad)
View(mpg2)     
