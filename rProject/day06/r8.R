#page 244 종교 유무와 이혼율

# 변수 분석
class(welfare$religion)
table(welfare$religion)

class(welfare$marriage)
table(welfare$marriage)

#전처리
welfare$religion <- ifelse(welfare$religion == 1, "yes", "no")
table(welfare$religion)

qplot(welfare$religion)


welfare$group_marriage <- ifelse(welfare$marriage == 1, "marriage",
                                 ifelse(welfare$marriage == 3, "divorce", NA))

table(welfare$group_marriage)
table(is.na(welfare$group_marriage))
qplot(welfare$group_marriage)


# 종교에 따른 이혼율 구하기
religion_marriage <- welfare %>%
  filter(!is.na(group_marriage)) %>%
  group_by(religion, group_marriage) %>%
  summarise(n=n()) %>%
  mutate(tot_group = sum(n)) %>%
  mutate(pct = round(n/tot_group*100, 1))
#위에 방법 또는 아래 방법도 가능
religion_marriage <- welfare%>%
  filter(!is.na(group_marriage))%>%
  count(religion, group_marriage)%>%
  group_by(religion) %>%
  mutate(pct = round(n/sum(n)*100, 1))

divorce <- religion_marriage %>%
  filter(group_marriage == "divorce") %>%
  select(religion, pct)
divorce
ggplot(data = divorce, aes(x =religion, y = pct)) + geom_col()

#연령대 및 종교 유무에 따른 이혼율 분석하기
ageg_marriage <- welfare %>%
  filter(!is.na(group_marriage))%>%
  group_by(ageg, group_marriage) %>%
  summarise(n=n())%>%
  mutate(tot_group = sum(n))%>%
  mutate(pct = round(n/tot_group*100, 1))
# 아래의 방법으로도 가능하다
ageg_marriage <- welfare %>%
  filter(!is.na(group_marriage))%>%
  count(ageg, group_marriage) %>%
  group_by(ageg)%>%
  mutate(pct = round(n/sum(n)*100, 1))

#연령대별 이혼율 그래프 만들기
ageg_divorce <- ageg_marriage %>%
  filter(ageg != "young" & group_marriage == "divorce") %>%
  select(ageg, pct)

ggplot(data = ageg_divorce, aes(x = ageg, y=pct)) + geom_col()

ageg_religion_marriage <- welfare %>%
  filter(!is.na(group_marriage) & ageg != "young")%>%
  group_by(ageg, religion, group_marriage)%>%
  summarise(n=n()) %>%
  mutate(tot_group = sum(n)) %>%
  mutate(pct = round(n/tot_group*100, 1))

df_divorce <- ageg_religion_marriage %>%
  filter(group_marriage == "divorce") %>%
  select(ageg, religion, pct)

ggplot(data=df_divorce, aes(x=ageg, y=pct, fill=religion)) + geom_col(position = "dodge")

class <- c(1, 1, 2, 3)
class <- c(2, 4 ,5, 4 )
is.vector(class)
