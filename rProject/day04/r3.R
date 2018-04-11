st <- read.csv('csv_exam.csv', header=T)
st
View(st)
colnames(st) <- c('id','class','ko','en','ma')
table(is.na(st$ko))
mean(st$ko, na.rm = T)

# 학생 정보를 조회 하시오
# 단 id,class,ko 결측치가 있는 학생은 빼시오

#st[which(st$ko != NA),c(1:3)]
st[!is.na(st$ko) & !is.na(st$class), c(1:3)]
st2<-na.omit(st[,c(1:3)])#이게 더 쉬운 방법


# 국어, 영어, 수학 성적의 평균을 구하여
# vector를 만든다.
ex1 <- rowMeans(na.omit(st[,c(3:5)]))
ex1_1 <- colMeans(st[,c(3:5)], na.rm = T)

# NA 값을 모두 0으로 바꾸시오
notNA 
notNA$ko <- ifelse(is.na(notNA$ko), 0, notNA$ko)
notNA$en <- ifelse(is.na(notNA$en), 0, notNA$en)
notNA$ma <- ifelse(is.na(notNA$ma), 0, notNA$ma)
notNA
## 이 방법도 생각해보자
st[is.na(st)]=0 # 문제는 class의 결측치도 0이 되니 주의해서 사용하자
boxplot(st$en)
hist(st$en)
