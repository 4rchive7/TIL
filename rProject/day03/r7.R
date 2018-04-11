aggregate(math~class,exam,mean) # 여기서 english를 math옆에 추가할 수 없다.

exam %>%
  group_by(class) %>%
  summarise(mean_math=mean(math),
            sum_eng=sum(english),
            mean_science=mean(science),
            n=n() #자동으로 count!!!
            )

mpg %>%
  group_by(manufacturer, drv) %>%
  summarise(mcty=mean(cty)) %>%
  arrange(desc(mcty)) %>%
  head(5)
mpg %>%
  group_by(manufacturer, drv) %>%
  summarise(mcty=mean(cty))
