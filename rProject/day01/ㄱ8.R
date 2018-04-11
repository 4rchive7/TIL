name <- c('kim', 'lee', 'hong', 'jang');
ko <- c(80,80,90,99);
en <- c(90,99,78,89);
si <- c(70, 78, 82, 78);
ma <- c(100, 72, 78, 90);
df<- data.frame(name,ko,en,si,ma);
print(df)

ko <- mean(df$ko);
en <- mean(df$en);
si <- mean(df$si);
ma <- mean(df$ma);
name <- 'avg';
addRow <- data.frame(name, ko, en, si, ma);
print(addRow);

final <- rbind(df, addRow)


final$avg <- rowMeans(final[,c('ko', 'en', 'si', 'ma')]);
print(final)

final$Sum <- final$ko + final$en + final$si + final$ma;
print(final)