library(ggplot2)
library(dplyr)

#page 160 ex1, 2
df<-midwest

tmp <- midwest[,c('popadults')]
tmp2 <- midwest[, c('poptotal')]
tmp3 <- (tmp2-tmp)/tmp2
names(tmp3) <- c("ex1")
df <- cbind(df, tmp3)
df <- df[c(order(df$ex1, decreasing = T)),]
head(df$ex1,5)

#ex3
mpg2$grad <- ifelse(mpg2$drvavg >= 30, 'A', 
                    ifelse(mpg2$drvavg >=20 , 'B', 'C'))
df$ex2 <- ifelse(df$ex1 >= 0.40, 'large',
                 ifelse(df$ex1 >=30, 'middle', 'small'))


#ex4
View(df)
df$rate <- df$popasian/df$poptotal*100
head(df[order(df$rate),c('state','county','rate')], 10)
