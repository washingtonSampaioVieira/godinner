ECHO Fazendo bkp do banco bd_locadora_w e salvando na pasta na area de trabalho 
c:
cd "C:\Users\washi\Desktop"
md backup
c:
cd "C:\Program Files\MySQL\MySQL Server 8.0\bin" 
mysqldump -u root -p bd_locadora_w > c:\Users\washi\Desktop\backup\backupLocadora.sql 

