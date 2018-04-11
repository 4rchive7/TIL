name <- c('lee', 'kim', 'park', 'jang', 'hong');
age <- c(10,20,30,40,50);
weight <- c(40,50,60,70,80);
df<- data.frame(name,age,weight);
df
nrow(df);
NROW(df)

df2 <- df[c(1:3),]
df2[,c('age','weight')] #i에서 뽑아내면 문자열과 숫자가 혼합되어 백터라고 하기 힘듬
                        #백터는 같은 데이터 타입을 가진 배열이라고 생각하지
                        #그래서 칼럼을 이용해 평균을 
colMeans(df2[,c('age','weight')])
print(df2)

#17 18 모두 같은 결과
df2 <- df[, 'age'];
df$age
mean(df$age[1:3])
which(df$age == 50)
indexMax <- which(df$age == max(df$age))
indexMin <- which(df$age == min(df$age))
print(indexMin)
mean(df$age[c(-indexMax,-indexMin)])

df2 <- df[df$weight >=20 & df$weight <= 40,] # &는 한번만 써주자! 자바 C랑은 다르다!
print(df2)
df2[which(df2$age == max(df2$age)),]
