library(dplyr)



#page 190
myp2 <- mpg[,c('drv', 'hwy')]
result <- aggregate(hwy~drv,mpg, mean)
ggplot(data=result, aes(x=drv, y=hwy))+geom_col()


#page 190
df_mpg <- mpg %>%
  group_by(drv)%>%
  summarise(mean_hwy = mean(hwy))

ggplot(data=df_mpg, aes(x= reorder(drv, -mean_hwy), y=mean_hwy))+geom_col()

#page 192 빈도 막대 그래
ggplot(data=mpg, aes(x=drv))+geom_bar()
ggplot(data=mpg, aes(x=hwy))+geom_bar()

View(mpg)
#page 130 ex1
tmp <- aggregate(cty~manufacturer+class, mpg, mean)
tmp <- tmp[which(tmp$class == 'suv'),]
tmp2 <- head(tmp %>% arrange(desc(cty)),5)
ggplot(data=tmp2, aes(x=reorder(manufacturer, -cty), y=cty))+geom_col()

ggplot(data=mpg, aes(x=class)+geom_bar()

       