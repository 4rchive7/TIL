#연령대 및 성별 월급 차이
# 변수 검토 및 전처리 -> 앞에서 함
# 연령대 및 성별 월급 평균표 만들기
sex_income <- welfare %>%
  filter(!is.na(income)) %>%
  group_by(ageg, sex) %>% #이 부분 때문에 앞에서 만든 것들로 바로 실행이 가능
  summarise(mean_income = mean(income))

sex_income
ggplot(data = sex_income, aes(x = ageg, y=mean_income, fill = sex)) + geom_col() + scale_x_discrete(limits = c("young", "middle", "old"))

#앞에 것과 다른 것은 aes에 fill = sex로 막대 내에서 구분지어준 것
#scale_x_discrete로 x축 값의 순서를 바꿔줌

ggplot(data=sex_income, aes(x=ageg, y=mean_income, fill = sex))+geom_col(position = "dodge") + scale_x_discrete(limits = c("young", "middle", "old"))
#위에랑 다른 것 geom_col속에 position="dodge"추가


#------------------------------------------------
#연령대를 구분하지 않고 나이 및 성별 월급 차이 분석

sex_age <- welfare %>%
  filter(!is.na(income)) %>%
  group_by(age, sex) %>%
  summarise(mean_income = mean(income))

ggplot(data = sex_age, aes(x=age,y=mean_income, col =sex)) + geom_line()

       