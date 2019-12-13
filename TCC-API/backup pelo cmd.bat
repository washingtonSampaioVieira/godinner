@echo off
REM Fazendo bkp do banco bd_locadora_w e salvando na pasta na area de trabalho 

c:
cd %USERPROFILE%\Desktop
md backup
cd "C:\Program Files\MySQL\MySQL Server 8.0\bin" 
mysqldump -u root -psenha db_godinner > %USERPROFILE%\desktop\TCC-API\Banco_de_dados\backupLocadora.sql 
@echo on
pause
REM -@- By. Marlon and Washington -@-