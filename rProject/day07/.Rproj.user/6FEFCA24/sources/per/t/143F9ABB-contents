library(KoNLP)
library(dplyr)
library(stringr)
useNIADic() #KoNLP에서 지원하는 것 하나의 사전이라고 생각하자
#txt <- readLines("hiphop.txt")
txt <- readLines("http://www.sportalkorea.com")
head(txt) #일단 한번 보면 특수문자들 들어있는게 보임
#txt <- str_replace_all(txt, '\\W',' ') 
#특수문자 제거하는 중
# '\\W' => 특수문자를 뜻함 위의 명령은 특수문자를 공백으로 바꾸는 명령어

txt <- gsub('\\W', ' ', txt)#stringr에 속한 함수로  str_replace_all이 너무 안좋아서 이걸 사용함
txt <- gsub('[0-9]', ' ', txt)
txt <- gsub('[a-z]', ' ', txt)
txt <- gsub('[A-Z]', ' ', txt)
#영어와 숫자를 제거함 

nouns <- extractNoun(txt)
vc <- unlist(nouns) #추출한 명사 list를 문자열 벡터로 변환해 줌
wc <- table(vc) #table에 넣으면 자동으로 빈도수 계산해서 정리
df_word <- as.data.frame(wc, stringsAsFactors = F)
#stringsAsFactors => Factor형태로 넣을 것인가 String으로 넣을 것인가에 대한 속성 속성
# 테이블을 다시 data.frame으로 만들어 줌 => 쉽게 다루기위해 
head(df_word)

df_word <- rename(df_word, word = vc, freq = Freq)
head(df_word)
df_word <- filter(df_word,nchar(word)>=2) # 글자의 길이가 2 이상인 것들만 뽑아 냄

wf<-head(df_word[order(df_word$freq, decreasing = T),], 20) #정렬 후 20개 출력
wf<-df_word[order(df_word$freq, decreasing = T),]


#install.packages("wordcloud")
library(wordcloud)
library(RColorBrewer)

pal <- brewer.pal(6, "Dark2")
set.seed(134)

wordcloud(words = wf$word,
          freq = wf$freq,
          min.freq = 2,
          max.words =100,
          random.order =  F,
          rot.per = .1,
          scale = c(4, 0.3),
          colors = pal)

g1 <- function(){
  
  library(ggplot2)
  
  jpeg(filename = "c:\\rProject\\mychart7.jpg", width = 680, height = 680, quality = 100)
  
  p=  wordcloud(words = wf$word,
            freq = wf$freq,
            min.freq = 2,
            max.words =200,
            random.order =  F,
            rot.per = .1,
            scale = c(10, 1),
            colors = pal)
  print(p);
  
  dev.off();
}


