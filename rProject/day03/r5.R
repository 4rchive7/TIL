library(dplyr)
library(sqldf)
exam<-read.csv('csv_exam.csv')


#c1 c2는 같은 결과!
c1 <- exam[exam$class==1,]
c1 <- exam[exam$class %in% c(1,3),] # class가 1~3인 값을 갖고 오라는 뜻
c2<-exam %>% filter(class==1)


c3 <- exam[exam$math>=50 & exam$english >= 70,]
c4 <- sqldf('SELECT * FROM exam 
            WHERE math>=50
            AND english>=70
            ')
mpg




#page 133 displ이 4이상인차량 5이상인 차량의 고속도로 평균 연비를 비교하시오
qq <- data.frame(mpg)
is.data.frame(qq)
avg4 <- colMeans(sqldf('select hwy from mpg where displ< 5'))
avg5 <- colMeans(sqldf('select hwy from mpg where displ>=5.0'))


avg <- aggregate(hwy~displ, mpg, mean);
ex1_1 <- mean(avg[avg$displ<5,'hwy'])  

avg <- aggregate(hwy~displ <= 4, mpg, mean);


# manufacturer의 cty가 평균적으로 audi와 toyota중에 어느 쪽이 높은지 알아보자
avgAudi <- mean(qq[qq$manufacturer=='audi', c(8)])
avgToyota <- mean(qq[qq$manufacturer=='toyota', c(8)])

tmp <- aggregate(qq$cty~manufacturer, mpg, mean)
ex2 <- tmp[tmp$manufacturer=='audi' | tmp$manufacturer=='toyota',]

#chevrolet ford honda 자동차의 고속도려 연비 뽑아내고 전체 평균 구해보자(hwy)
tmp2 <- mpg[mpg$manufacturer=='chevrolet' 
            | mpg$manufacturer=='ford' 
            | mpg$manufacturer=='honda', c(1,8)]
tmp3 <- mpg[mpg$manufacturer %in% c('chevrolet', 'ford', 'honda'),c(1,8)]
ex3 <- colMeans(tmp3[,2])
ex4 <- colMeans(tmp2[,2])




temp <- aggregate(hwy~displ <=4, mpg, mean)
qplot(temp$hwy, x=c('over 4', 'under 5'))
