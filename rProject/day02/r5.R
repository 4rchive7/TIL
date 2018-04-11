name <- c('김지훈', '이유진', '박동현', '김민지')
gender <- c('m', 'f', 'm','f')
ko <- c(90,80,60,70)
ma <- c(50,60,100,20)
si <- c(87,54,79,89)
en <- c(54,67,87,88)
add <- c('서울시', '광주시', '대전시', '부산시')
df <- data.frame(name, gender, ko, ma, si, en, add);



tmp <- df[,c(3:5)]
avPpeo <- round(rowMeans(tmp), 2) 
names(avPpeo) <- df[,1]

avPsub <-  round(colMeans(tmp), 2)
tmp <- df[,'add']
tmp2 <- as.character(tmp)
class(tmp2)
tmp2
substr(tmp2, 1, 2)
df
dfmale <- df[gender == 'm',]
dffemale <- df[gender == 'f',]
mavg<-colMeans(dfmale[, c(3:6)])
favg<-colMeans(dffemale[, c(3:6)])

qplot(y=mavg, x=names(mavg))

#전체 평균 중 여자인 학생만 나오게 하자
df[df$gender=='f' & mean(df[,c(3:6)]),]

df


#학생별로 국어 영어의 평균을 내시오
#칼럼명은 사람이름으로 !!
ex_avg <- rowMeans(df[,c(3, 6)])
names(ex_avg) <- df$name
ex_avg



# 남/여학생들의 과목 별 평균을 구하시오
# 단 칼럼 명은 과목 평이 표시된다.
# 마지막으로 dataFrame으로 만들어서 보내달라고 한다면??????
r1 <- colMeans(df[df$gender=='f',][,c(3:6)])

result2 <- df[df$gender=='m',]
r2 <- colMeans(result2[, c(3:6)])

result3 <- rbind(r1,r2)           #여기는 matrix!!
result4 <- as.data.frame(result3) #여기는 dataFrame!!
