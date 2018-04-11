#page 216 
#step1 변수 검토하자
welfare
class(welfare$income)


#table(welfare$income) => 이건 항목이 너무 많음
#중복되는 것을 모아 찍어보이게 하기 위해 summary사용
summary(welfare$income)

qplot(welfare$income)+xlim(0, 1000)
#x축 값을 제한함


#step2 전처리
summary(welfare$income)
#결측치가 12030

welfare$income <- ifelse(welfare$income %in% c(0,9999), NA, welfare$income)
#0이나 9999 밖이면 NA 처리 그렇지 않으면 원래 값

table(is.na(welfare$income))

library(dplyr)
#step3 - 성별 월급 평균표 만들기
sex_income <- welfare %>%
  filter(!is.na(income)) %>%  #NA제거
  group_by(sex) %>%
  summarise(mean_income = mean(income))


ggplot(data=sex_income, aes(x=sex, y=mean_income))+geom_col()
