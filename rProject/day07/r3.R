library(KoNLP)
library(dplyr)
library(stringr)
library(wordcloud)
library(RColorBrewer)
useNIADic() 

txt1 <- readLines('txt1.txt')
txt2 <- readLines('txt2.txt')
txt3 <- readLines('txt3.txt')
txt <- c(txt1, txt2, txt3)
txt <- gsub('\\W', ' ', txt)#stringr에 속한 함수로  str_replace_all이 너무 안좋아서 이걸 사용함
txt <- gsub('[0-9]', '', txt)
txt <- gsub('[a-z]', '', txt)
txt <- gsub('[A-Z]', '', txt)
txt <- gsub('_', '', txt)
txt <- gsub('__', '', txt)
txt <- gsub('  ', '', txt)


txt <- extractNoun(txt)
vc <- unlist(txt)
wc <- table(vc)
wf <- as.data.frame(wc, stringsAsFactors = F)
str(wf)
wf <- filter(wf,nchar(vc)>=2)
str(wf)

wf<-(wf[order(wf$Freq, decreasing = T),]) 
wf2<-head((wf[order(wf$Freq, decreasing = T),]), 20)
#install.packages("wordcloud")
library(wordcloud)
library(RColorBrewer)

pal <- brewer.pal(6, "Dark2")
set.seed(134)


g1 <- function(a){
  
  library(ggplot2)
  
  if(a==1){
    jpeg(filename = "c:\\rProject\\day07\\wc.jpg", width = 680, height = 680, quality = 100)
    p=wordcloud(words = wf$vc,
                freq = wf$Freq,
                min.freq = 2,
                max.words =100,
                random.order =  F,
                rot.per = .1,
                scale = c(4, 0.3),
                colors = pal)
  }else{
    jpeg(filename = "c:\\rProject\\day07\\wcf.jpg", width = 680, height = 680, quality = 100)
    order <- arrange(wf2, Freq)$vc
    ggplot(data = wf2, aes(x=vc, y=Freq)) + ylim(0, 40)+geom_col()+coord_flip()+scale_x_discrete(limit=order)+geom_text(aes(label=Freq), hjust = -0.3)
  }
  
  print(p);
  dev.off();
}