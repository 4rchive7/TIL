mpg2 <- mpg[,c('model', 'year')]

exam
test1 <- exam[exam$class==1,c('english', 'math')]


temp <- c(3,1,3,5,4,2,3)
sort(temp)
sort(temp, decreasing = T)
exam[order(exam$math),]
exam[order(exam$class, exam$math),]
exam[order((exam$math), decreasing = T),]
exam %>% arrange(math)
exam %>% arrange(desc(math))
exam %>% arrange(class, math)
sort(exam.x = exam$math) #요것은 안됨 sort는 백터만 가능!

exam2<-read.csv('csv_exam.csv')
exam2[order(exam2$class, exam2$math, decreasing = T),]
exam2 <- exam %>% arrange(class, desc(math))

exam3 <- exam %>% arrange(class, desc(math))
head(exam3, 5) # 상위 5개만
tail(exam3, 5) # 하위 5개


ex_exam <- exam
ex_exam$avg <- round(rowMeans(ex_exam[,c(3:5)]),2)
ex_exam[order(ex_exam$class, ex_exam$avg, decreasing=T),]
ex_exam %>% arrange(class, desc(avg))

mpg
ex <- mpg[,c('manufacturer', 'model', 'hwy')]
exModel <- aggregate(hwy~model, ex, mean)
head(exModel[order(exModel$hwy, decreasing = T),], 5)



#p144 #1
copy_mpg <- function(){
  return (mpg);  
}

copy <- copy_mpg()
copy$total <- copy$hwy+copy$cty


#2
copy$avg <- copy$total/2
View(copy)

#3
ex3 <- head(copy[order(copy$avg, decreasing = T),],3)

#4
library(dplyr)
mpg2 <- mpg
mpg2 %>% mutate(avg = (mpg2$hwy + mpg2$cty)/2) %>% arrange(desc(avg)) %>% head(3)
                
                