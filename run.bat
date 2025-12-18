@echo off
REM Simple wrapper that delegates to the PowerShell script
powershell -NoProfile -ExecutionPolicy Bypass -File "%~dp0run.ps1" %*
