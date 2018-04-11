#---------------------------------------------------------------#
#<Console>                                                      |
#install.packages('devtools') => github에서 끌어오려도 사용함   |
#install.packages('stringi')                                    |
#devtools::install_github('cardiomoon/kormaps2014')             |
#devtools::install_github('cardiomoon/moonBook2')               |
#---------------------------------------------------------------#

library(kormaps2014)
library(ggplot2)
library(dplyr)
library(ggiraphExtra)




str(changeCode(korpop1))
korpop1 <-rename(korpop1,
                 pop = 총인구_명,
                 name = 행정구역별_읍면동)
str(changeCode(kormap1))
options(encoding="UTF-8")
options(encoding="CP949")
ggChoropleth(data = korpop1,
             aes(fill = pop,
                 map_id=code,
                 tooltip=name),
             map=kormap1, 
             interactive = T) 

