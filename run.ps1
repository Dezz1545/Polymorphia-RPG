param(
    [switch]$buildOnly
)

# Ensure script runs from repo root
Set-Location -Path (Split-Path -Parent $MyInvocation.MyCommand.Path)

if (-not (Test-Path -Path out)) {
    New-Item -ItemType Directory -Path out | Out-Null
}

Write-Host "Collecting .java files..." -ForegroundColor Cyan
$files = Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName }

if (-not $files) {
    Write-Host "No .java files found under src" -ForegroundColor Yellow
    exit 1
}

Write-Host "Compiling..." -ForegroundColor Green
javac -d out $files
if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed." -ForegroundColor Red
    exit $LASTEXITCODE
}

Write-Host "Compilation succeeded." -ForegroundColor Green

if ($buildOnly) { exit 0 }

Write-Host "Running fr.Main..." -ForegroundColor Green
java -cp out fr.Main

exit 0
