@echo off
echo Iniciando Microsservicos...
echo.

echo Iniciando ms-user na porta 8080...
start "ms-user" cmd /k "cd ms-user && gradlew bootRun"

echo Aguardando 10 segundos...
timeout /t 10 /nobreak > nul

echo Iniciando ms-chamado na porta 8081...
start "ms-chamado" cmd /k "cd ms-chamado && gradlew bootRun"

echo.
echo Microsservicos iniciados!
echo ms-user: http://localhost:8080
echo ms-chamado: http://localhost:8081
echo.
echo Pressione qualquer tecla para sair...
pause > nul 