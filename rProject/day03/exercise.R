#ex1
#Fruits 데이터를 기반으로
#연도별 월별 slase와 profit의 합을 구하시오
pp <- aggregate(Sales~Year+substr(Date,6,7), Fruits, sum)
names(pp)[2] <- paste("Month")

#ex2
#1번의 결과를 기반으로 순이익(Psales)칼럼을 추가 한다.
#공식 (Sales - Profit) * 1.83
pp2 <- aggregate(Profit~Year+substr(Date,6,7), Fruits, sum)
pp$Psales <- (pp$Sales - pp2$Profit)*1.83 #이런식으로 추가 가
pp


#ex3
# 년별 월별 세금을 계산한다.
# tax = (Sales - Profit)*0.1
pp$Fee <- (pp$Sales - pp2$Profit)*0.1
pp

pp3 <- aggregate((Sales-Profit)*0.1~Year+substr(Date,6,7), Fruits, sum)
colnames(pp3) <- c('Year', 'Month', 'Tax') #전체로 지정하는 바업ㅂ

#colnames(pp3)[3] <- paste('tax')
#colnames(pp3)[3] <- paste('tax')
