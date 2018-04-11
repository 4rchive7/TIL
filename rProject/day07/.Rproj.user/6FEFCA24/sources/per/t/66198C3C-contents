##-----------------------------------------------------------------#
#<Console>                                                         |
#install.packages('RgoogleMaps') => github에서 끌어오려도 사용함   |
#                                                                  |
#                                                                   
#
#------------------------------------------------------------------#
library(RgoogleMaps)
mycenter = map.center.loc=c(37.501390, 127.039606)

mylat <- c(37.501390, 37.506938, 37.759321)
mylon <- c(127.039606, 126.918539, 127.042027)
mymap <- GetMap(
  center = mycenter,
  zoom = 10,
  maptype = 'road', #또는 imgmap
  #format = 'roadmap',
  destfile = 'mymap.jpg'
)


jpeg(filename = "c:\\rProject\\day07\\mymap_point.jpg", width = 680, height = 680, quality = 100)
mydata <- data.frame(
  a=c('멀캠', '태익', '란영', '현우'),
  lat=c(37.501390, 37.506938, 37.759321, 37.504874),
  lon=c(127.039606, 126.918539, 127.042027, 127.039828)
  )
p=PlotOnStaticMap(mymap,
                lat=mydata$lat,#백터를 넣으면 모두 다 출력 됨
                lon=mydata$lon,
                destfile = 'mymap_point.jpg',
                cex=1, pch=10, col='red'
                )

print(p);
dev.off();
