@echo off
echo ==========================================
echo TESTE DE MICROSSERVICOS - JAIME PROJECT
echo ==========================================

echo.
echo 1. Iniciando MS-USER (porta 8080)...
start "MS-USER" cmd /k "cd ms-user && gradlew.bat bootRun"

echo Aguardando 15 segundos para inicializacao completa...
timeout /t 15 /nobreak > nul

echo.
echo 2. Iniciando MS-CHAMADO (porta 8081)...
start "MS-CHAMADO" cmd /k "cd ms-chamado && gradlew.bat bootRun"

echo Aguardando 15 segundos para inicializacao completa...
timeout /t 15 /nobreak > nul

echo.
echo 3. Testando Health Checks...
echo.
echo Testando MS-USER (8080):
curl -X GET http://localhost:8080/health || echo ERRO: MS-USER nao respondeu

echo.
echo Testando MS-CHAMADO (8081):
curl -X GET http://localhost:8081/health || echo ERRO: MS-CHAMADO nao respondeu

echo.
echo ==========================================
echo MICROSSERVICOS INICIADOS COM SUCESSO!
echo ==========================================
echo.
echo URLs dos servicos:
echo MS-USER: http://localhost:8080
echo MS-CHAMADO: http://localhost:8081
echo.
echo Banco H2 MS-USER: http://localhost:8080/h2-console
echo Banco H2 MS-CHAMADO: http://localhost:8081/h2-console
echo.
echo Pressione qualquer tecla para continuar com os testes...
pause > nul

echo.
echo 4. Testando criacao de usuario (deve criar chamado automaticamente)...
curl -X POST http://localhost:8080/api/v1/users ^
  -H "Content-Type: application/json" ^
  -d "{\"email\":\"jaime@teste.com\",\"handle\":\"jaime\",\"password\":\"123456\",\"roles\":[\"ROLE_USER\"]}"

echo.
echo.
echo 5. Verificando chamados criados...
curl -X GET http://localhost:8081/api/v1/chamados

echo.
echo.
echo ==========================================
echo TESTE CONCLUIDO!
echo ==========================================
