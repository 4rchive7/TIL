library(ggplot2)
#step1
#aes로 x, y축을 설정해준다.
ggplot(data=mpg, aes(x=displ, y=hwy))


#step2
#+geom_point() 해주기 대신 같은 줄에 있어야함 
ggplot(data=mpg, aes(x=displ, y=hwy))+geom_point()
#또는 마지막 줄에 +넣기
ggplot(data=mpg, aes(x=displ, y=hwy))+
  geom_point()

#step3
# xlim / ylim은 범위를 지정해준
ggplot(data=mpg, aes(x=displ, y=hwy)) + geom_point() + xlim(3,6) + ylim(10, 30)



#page188
ggplot(data=mpg, aes(x=cty, y=hwy))
ggplot(data=mpg, aes(x=cty, y=hwy)) + geom_point()

ggplot(data=midwest, aes(x=poptotal, y=popasian))+geom_point() + xlim(0, 500000) + ylim(0, 10000)

fname <- 'c:\rProject\mygraph.png'
saveplot(filename = fname,type='png')
