#!/bin/bash

echo "Iniciando Microsservicos..."
echo

echo "Iniciando ms-user na porta 8080..."
cd ms-user && ./gradlew bootRun &
USER_PID=$!

echo "Aguardando 10 segundos..."
sleep 10

echo "Iniciando ms-chamado na porta 8081..."
cd ../ms-chamado && ./gradlew bootRun &
CHAMADO_PID=$!

echo
echo "Microsservicos iniciados!"
echo "ms-user: http://localhost:8080"
echo "ms-chamado: http://localhost:8081"
echo
echo "Pressione Ctrl+C para parar os servicos..."

# Aguardar interrupção
trap "echo 'Parando servicos...'; kill $USER_PID $CHAMADO_PID; exit" INT
wait 