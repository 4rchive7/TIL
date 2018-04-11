tf <- data.frame(
  id=c(1:5),
  test=c(86:90)
)
tf2 <- data.frame(
  id=c(6:10),
  test=c(91:95)
)
total <- rbind(tf, tf2)


total$test2 <- c(89:98)

total$avg <- rowMeans(total[,c(2,3)], 1)

library(dplyr)
tmp <- total
tmp %>% arrange(desc(avg))
head(tmp$avg, 3)

pp <- mean(total[,c(4)])
tmp2 <- tmp[which(tmp$avg > pp),]
