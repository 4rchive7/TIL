#page 225 - 연령대에 따른 월급차이
#변수 검토 및 전처리
# age/ income 앞에서 이리 함
# 변수간 관계 => 파생변수 만들기
# 결과가 책과 다를 수 있는데 이유는
# r2 2018년을 기준으로 나이를 책정했기 때문이다.
welfare <- welfare %>%
  mutate(ageg = ifelse(age < 30, "young",
                       ifelse(age <=59, "middle", "old")))

table(welfare$ageg)

# 연령대에 따른 월급 차이 분석하기
ageg_income <- welfare %>%
  filter(!is.na(income)) %>%
  group_by(ageg) %>%
  summarise(mean_income = mean(income)) 

ageg_income


ggplot(data = ageg_income, aes(x=ageg, y=mean_income)) + geom_col()


# 나이순으로 그래프 다시 만들기
ggplot(data = ageg_income, aes(x=ageg, y=mean_income)) + geom_col()+ scale_x_discrete(limits = c("young", "middle", "old"))
