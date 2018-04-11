library(KoNLP)
library(dplyr)
library(stringr)

txt <- readLines("kyuwon.txt", encoding = 'UTF-8')


txt <- gsub('\\W', ' ', txt)
txt <- gsub('[0-9][0-9][0-9][0-9]년', '', txt)
txt <- gsub('[0-9][0-9]월', '', txt)
txt <- gsub('월', '', txt)
txt <- gsub('[0-9]일', '', txt)
txt <- gsub('[0-9][0-9]일', '', txt)
txt <- gsub('오후', '', txt)
txt <- gsub('오전', '', txt)
txt <- gsub('[0-9]', '', txt)
txt <- gsub('[a-z]', '', txt)
txt <- gsub('[A-Z]', '', txt)
txt <- gsub('ㅋ', '', txt)
txt <- gsub('ㅂ', '', txt)
txt <- gsub('ㄷ', '', txt)
txt <- gsub('ㅅ', '', txt)
txt <- gsub('김도형', '', txt)
txt <- gsub('박진우', '', txt)
txt <- gsub('이규원', '', txt)
txt <- gsub('최지형', '', txt)
txt <- gsub('장태익', '', txt)
txt <- gsub('공준기', '', txt)
txt <- gsub('이모티콘', '', txt)
txt <- gsub('이모티', '', txt)
txt <- gsub('이모', '', txt)
txt <- gsub('티콘', '', txt)
txt <- gsub('ㄱ', '', txt)
txt <- gsub('ㄴ', '', txt)
txt <- gsub('ㅈ', '', txt)
txt <- gsub('ㅎ', '', txt)
txt <- gsub('ㅇ', '', txt)
txt <- gsub('ㄲ', '', txt)
txt <- gsub('ㄹ', '', txt)
txt <- gsub('ㅁ', '', txt)
txt <- gsub('ㅆ', '', txt)
txt <- gsub('회원', '', txt)
txt <- gsub('알수없', '', txt)
txt <- gsub('ㅊ', '', txt)
txt <- gsub('오늘', '', txt)
txt <- gsub('저녁', '', txt)
txt <- gsub('진짜', '', txt)
txt <- gsub('근데', '', txt)
txt <- gsub('다들', '', txt)
txt <- gsub('사람', '', txt)
txt <- gsub('우리', '', txt)
txt <- gsub('시간', '', txt)
txt <- gsub('님사', '', txt)
txt <- gsub('이거', '', txt)
txt <- gsub('  ', '', txt)
#영어와 숫자를 제거함 

nouns <- extractNoun(txt)
vc <- unlist(nouns) #추출한 명사 list를 문자열 벡터로 변환해 줌
wc <- table(vc) #table에 넣으면 자동으로 빈도수 계산해서 정리
df_word <- as.data.frame(wc, stringsAsFactors = F)

#stringsAsFactors => Factor형태로 넣을 것인가 String으로 넣을 것인가에 대한 속성 속성
# 테이블을 다시 data.frame으로 만들어 줌 => 쉽게 다루기위해 


df_word <- rename(df_word, word = vc, freq = Freq)
df_word <- filter(df_word,nchar(word)>=2) # 글자의 길이가 2 이상인 것들만 뽑아 냄
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
          scale = c(4, 0.1),
          colors = pal)

