

library(sqldf)
mympg <- sqldf('SELECT *, (cty+hwy)/2 as total FROM mpg')
mympg



#total연비를 3등급으로 누나기
#30 이상 A
#20 이상 B
#20 미만 C
#grad라는 칼럼믈 만들어 추가 후 grade를 기준으로 그래프 ㄱㄱ

sqldf('SELECT
      CASE WHEN total > 20 
      THEN "pass" 
      ELSE "fail" 
      END AS test 
      FROM mympg')
mpg4$grad <- ifelse(mpg2$drvavg >= 30, 'A', 
                    ifelse(mpg2$drvavg >=20 , 'B', 'C'))


mpg2 <-rename(mpg, 'city'='cty', 'highway' = 'hwy')

mpg2$drvavg <- (mpg2$highway+mpg2$city)/2
sqldf('SELECT drvavg,
      CASE WHEN drvavg >= 30
      THEN "A" 
      ELSE CASE WHEN drvavg >= 20
      THEN "B"
      ELSE "C"  END
      END
      AS test2 
      FROM mpg2')
#else if 문 사용
mpg2
qplot(mpg2$grad)
View(mpg2)     

