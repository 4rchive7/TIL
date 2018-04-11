library(KoNLP)
library(dplyr)
library(stringr)
useNIADic()
txt <- readLines("talk.txt", encoding = 'UTF-8')


txt <- gsub('\\W', ' ', txt)
txt <- gsub('[0-9][0-9][0-9][0-9]년', '', txt)
txt <- gsub('[0-9][0-9]월', '', txt)
txt <- gsub('월', '', txt)
txt <- gsub('[0-9]일', '', txt)
txt <- gsub('[0-9][0-9]일', '', txt)
txt <- gsub('오후', '', txt)
txt <- gsub('오전', '', txt)
txt <- gsub('[0-9]', '', txt)
txt <- gsub('기', '', txt)
txt <- gsub('ㅋ', ' ', txt)
txt <- gsub('김', ' ', txt)
txt <- gsub('김완', ' ', txt)
txt <- gsub('완훈', ' ', txt)
txt <- gsub('김완훈', ' ', txt)
txt <- gsub('김', ' ', txt)
txt <- gsub('김성', ' ', txt)
txt <- gsub('성렬', ' ', txt)
txt <- gsub('김성렬', ' ', txt)
txt <- gsub('박', ' ', txt)
txt <- gsub('박태', ' ', txt)
txt <- gsub('태환', ' ', txt)
txt <- gsub('박태환', ' ', txt)
txt <- gsub('조', ' ', txt)
txt <- gsub('조성', ' ', txt)
txt <- gsub('성준', ' ', txt)
txt <- gsub('조성준', ' ', txt)
txt <- gsub('오', ' ', txt)
txt <- gsub('오재', ' ', txt)
txt <- gsub('재걸', ' ', txt)
txt <- gsub('오재걸', ' ', txt)
txt <- gsub('ㄱ', '', txt)
txt <- gsub('ㄴ', '', txt)
txt <- gsub('ㅈ', '', txt)
txt <- gsub('ㅎ', '', txt)
txt <- gsub('ㅇ', '', txt)
txt <- gsub('ㄲ', '', txt)
txt <- gsub('ㄹ', '', txt)
txt <- gsub('ㅁ', '', txt)
txt <- gsub('ㅆ', '', txt)
txt <- gsub('생명', ' ', txt)
txt <- gsub('회원', ' ', txt)
txt <- gsub('모티', ' ', txt)
txt <- gsub('  ', '', txt)
txt <- gsub('근데', ' ', txt)
txt <- gsub('우리', ' ', txt)
txt <- gsub('진짜', ' ', txt)
txt <- gsub('다들', ' ', txt)
txt <- gsub('하시', ' ', txt)
txt <- gsub('어제', ' ', txt)
txt <- gsub('그것', ' ', txt)
txt <- gsub('누구', ' ', txt)

#영어와 숫자를 제거함 

nouns <- extractNoun(txt)
vc <- unlist(nouns) #추출한 명사 list를 문자열 벡터로 변환해 줌
wc <- table(vc) #table에 넣으면 자동으로 빈도수 계산해서 정리
df_word <- as.data.frame(wc, stringsAsFactors = F)
View(df_word)
#stringsAsFactors => Factor형태로 넣을 것인가 String으로 넣을 것인가에 대한 속성 속성
# 테이블을 다시 data.frame으로 만들어 줌 => 쉽게 다루기위해 
head(df_word)

df_word <- rename(df_word, word = vc, freq = Freq)
head(df_word)
df_word <- filter(df_word,nchar(word)>=2) # 글자의 길이가 2 이상인 것들만 뽑아 냄
str(df_word)
wf2<-head(df_word[order(df_word$freq, decreasing = T),], 20) #정렬 후 20개 출력
wf<-df_word[order(df_word$freq, decreasing = T),]


#install.packages("wordcloud")
library(wordcloud)
library(RColorBrewer)

pal <- brewer.pal(6, "Dark2")
set.seed(134)

wordcloud(words = wf$word,
          freq = wf$freq,
          min.freq = 2,
          max.words =300,
          random.order =  F,
          rot.per = .1,
          scale = c(4, 0.3),
          colors = pal)

g1 <- function(a){
  
  library(ggplot2)
  
  jpeg(filename = "c:\\rProject\\wc.jpg", width = 680, height = 680, quality = 100)
  
  if(a==1){
    p=wordcloud(words = wf$word,
              freq = wf$freq,
              min.freq = 2,
              max.words =400,
              random.order =  F,
              rot.per = .1,
              scale = c(4, 0.1),
              colors = pal)
  }else{
    order <- arrange(wf2, freq)$word
    ggplot(data = wf2, aes(x=word, y=freq)) + ylim(0, 2500)+geom_col()+coord_flip()+scale_x_discrete(limit=order)+geom_text(aes(label=freq), hjust = -0.3)
  }
  print(p);
  
  dev.off();
}


