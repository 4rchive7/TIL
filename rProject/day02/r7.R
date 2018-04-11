getcsv <- function(){
  #data <- read.csv('csv_exam2.csv', header = F)
  data <- read.csv('csv_exam3.csv', header = F, stringsAsFactors=F, encoding ='UTF-8') #문자열 섞이면 이걸 사용하자
  class(data$V2)
  return (data)
}

writedata <- function(df){
  write.csv(df, file='result0328_3.csv');
}
data <- getcsv()#파일 불러오기
data$avg <- round(rowMeans(data[,c(4:6)]), 2) # avg라는 열을 만들고 반올림해준것이다!
writedata(data) #파일 저장 파일명으로 이미 저장되어있으면 다르게 해 줘야함!


newdata <- read.csv('csv_exam3.csv', header = F, stringsAsFactors=F, encoding ='UTF-8')
load('result0328')