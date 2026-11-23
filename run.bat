@echo off
cd /d "%~dp0"
if not exist bin mkdir bin
javac -d bin src\*.java
if errorlevel 1 (
  echo Falha na compilacao.
  pause
  exit /b 1
)
java -cp bin Main
