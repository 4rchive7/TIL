library('googleVis')
head(Fruits)
class(Fruits)
is.data.frame(Fruits)
Fruits

banana <-Fruits[Fruits$Fruit=='Bananas',]
sum(banana$Sales)

#--------------------------------위 방법은 구닥다리 방법

aggregate(Sales~Fruit,Fruits, sum) # Fruits에서 Fruit을 기준으로 sales의 sum을 구해라
df1 <- aggregate(Profit~Year+Fruit,Fruits,sum)
df2 <- Fruits[, c(4:6)]

apply(df2,1,sum) #행을 다 더한 것
apply(df2,2,sum) #열을 다 더한 것
