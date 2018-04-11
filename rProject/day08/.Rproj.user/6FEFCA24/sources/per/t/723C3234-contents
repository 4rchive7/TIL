#---------------------------------------------------------#
#install.packages('sqldf')

library(sqldf)

df <- iris[iris$Species =='setosa',]
mydata <- sqldf('SELECT * FROM iris
                where Species == "setosa"
                ')
iris



mydata2 <- sqldf('SELECT avg("Sepal.Length"), Species FROM iris GROUP BY Species')


#=====================================================
tmp0 <- aggregate(Sepal.Length~Species, iris, mean)
tmp1 <- aggregate(Sepal.Width~Species, iris, mean)
tmp2 <- aggregate(Petal.Length~Species, iris, mean)
tmp3 <- aggregate(Petal.Width~Species, iris, mean)

tmp5 <- tmp0
tmp5$Sepal.Width <- tmp1$Sepal.Width
tmp5$Petal.Length <- tmp2$Petal.Length
tmp5$Petal.Width <- tmp3$Petal.Width


#tmp6으로 하려면 먼저 종별로 잘라낸 뒤에 하는게 좋다.
tmp6<-sapply(iris[,c(1:4)], mean)


library(dplyr)
species.sum2 <- iris %>% 
  group_by(Species) %>% 
  summarise_all(mean)
library(ggplot2)

ggplot(data=tmp5, aes(x=Sepal.Length, y=Species, fill=Petal.Width))+ geom_col(position = "dodge")
