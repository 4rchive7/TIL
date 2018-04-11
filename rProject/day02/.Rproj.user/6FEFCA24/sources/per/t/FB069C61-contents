exdata <- function(){
  library(readxl) #dataframe형태로 만들어서 보내준다.
  #data <- read_excel('excel_exam.xlsx'); #같은 workspace가 아니면 c:부터 경로 다 써줘함야
  #data <- read_excel('excel_no_head.xlsx', col_names=F); #head명 안쓰겠다는 이야기
  data <- read_excel('excel_exam_3.xlsx', sheet=2); #여러개의 sheet중 하나를 갖고 옴
  #is.data.frame(data);
  return (data);
}

writedata <- function(df){
  write.csv(df, file='result0328.csv');
}

data <- exdata();
#mean(data$math)
#colnames(data) <- c('a','b','c','d', 'e')
data$avg <- round(rowMeans(data[,c(4:6)]), 2) # avg라는 열을 만들고 반올림해준것이다!
writedata(data);
