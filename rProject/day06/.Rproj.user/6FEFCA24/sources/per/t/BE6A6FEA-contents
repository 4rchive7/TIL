#page220
#변수 검토하기
class(welfare$birth)
summary(welfare$birth)
qplot(welfare$birth)

#전처리
summary(welfare$birth)
table(is.na(welfare$birth)) #false만 나왔다면 결측치X

#파생변수 만들기 -> 연도를 나이로 환산하는 변수 만들겠음
welfare$age <- 2018 - welfare$birth + 1
summary(welfare$age)
qplot(welfare$age)


#------------------------------------------------------------
#나이와 월급의 관계 분석하기
age_income <- welfare %>%
  filter(!is.na(income)) %>%
  group_by(age) %>%
  summarise(mean_income = mean(income))

head(age_income)
ggplot(data = age_income, aes(x= age,y=mean_income)) + geom_line()


