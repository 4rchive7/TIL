m1 <- matrix(c(80, 80, 90, 99,90,99,78,89,70,78,82,78,100,72,78,90), ncol=4);

colnames(m1) <- (c('ko', 'en', 'si', 'ma'));
rownames(m1) <- (c('kim', 'lee', 'hong', 'jang'));

print(m1)



meanStu <- c(mean(m1[c(1),c(1:3)]), mean(m1[c(2),c(1:3)]), mean(m1[c(3),c(1:3)]),  mean(m1[c(4),c(1:3)]) )
print(meanStu)

meanSubj <- c(mean(m1[c(1:3),c(1)]), mean(m1[c(1:3),c(2)]), mean(m1[c(1:3),c(3)]))

print(meanSubj)


rm <- rowMeans(m1)
print(rm)
cm <- colMeans(m1)
print(cm)
