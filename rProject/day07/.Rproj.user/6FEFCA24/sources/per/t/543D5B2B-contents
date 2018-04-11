#---------------------------------------------------------#
#DataLoad : data Frame형태로                              #
#wifi함수 -> parameter 1 <-> kt, 2<-> lg, 3<->skt         #
#마지막으로 그림 저장                                     #
#---------------------------------------------------------#


library('readxl')
library(dplyr)
library(RgoogleMaps)
data<-read.csv(file="seoul_wifiloc.csv", header =F)
data <- rename(data,
               gu=V1,
               cate=V2,
               name=V3,
               lat=V5,
               lon=V4,
               comp=V6)

mycenter = map.center.loc=c(37.501390, 127.039606)
mymap <- GetMap(
  center = mycenter,
  zoom = 11,
  maptype = 'road', #또는 imgmap
  #format = 'roadmap',
  #destfile = 'mymap2.jpg'
)



write_map <- function(a){
  
  if(a == 2){
    jpeg(filename = "c:\\rProject\\day07\\LGU+.jpg", width = 680, height = 680, quality = 100)
    p=PlotOnStaticMap(mymap,
                      lat=data[data$comp=='LGU+',c('lat')],
                      lon=data[data$comp=='LGU+',c('lon')],
                      cex=1, pch=10, col='red')
    
  }else if(a == 1){
    jpeg(filename = "c:\\rProject\\day07\\KT.jpg", width = 680, height = 680, quality = 100)
    p=PlotOnStaticMap(mymap,
                      lat=data[data$comp=='KT',c('lat')],
                      lon=data[data$comp=='KT',c('lon')],
                      cex=1, pch=10, col='red')
  }else if(a == 3){
    jpeg(filename = "c:\\rProject\\day07\\SKT.jpg", width = 680, height = 680, quality = 100)
    p=PlotOnStaticMap(mymap,
                      lat=data[data$comp=='SKT',c('lat')],
                      lon=data[data$comp=='SKT',c('lon')],
                      cex=1, pch=10, col='red')
  }
  
  print(p)
  dev.off()
  
}



