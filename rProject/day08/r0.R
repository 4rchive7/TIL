#------------------------------------------------------------#
#install.packages('plotly')                                  #
#install.packages('htmlwidgets')           
#------------------------------------------------------------#

library(plotly)
library(ggplot2)
library(htmlwidgets)
p <- ggplot(data=mpg, aes(x=displ, y=hwy, col=drv))+geom_point()
#col을 분류로 점의 색이 구분되었다.
saveWidget(widget = p,file='asdf.html')
as.htmlWidget(p)
tmp <- as_widget(p)


tmp <- ggplotly(p)
saveWidget(widget = tmp, file="sdfsdf.html") 
#ggplotly를 넣어줘야 함 (이게 html형태이기 때문!)
