library('ggplot2')
library(dplyr)

View(midwest)

#1
df<-as.data.frame(midwest)
View(df)

#2
df <-rename(df, 'total'='poptotal', 'asian' = 'popasian')
df <-rename(df, 'asian'='asign')

#3
df$perAsian <- round((df$asian/df$total), 2)
avgAsian <- mean(df$perAsian)
df$test <- ifelse(df$perAsian > avgAsian, 'large', 'small')
View(df)
hist(df$test)
