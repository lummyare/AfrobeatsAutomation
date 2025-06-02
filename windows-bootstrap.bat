
@echo off
setlocal enabledelayedexpansion

echo ========================================
echo Appium Latest Upgrade - Windows Bootstrap
echo ========================================
echo.
echo This script will:
echo 1. Install Chocolatey (if not present)
echo 2. Install Node.js LTS (if not present)
echo 3. Run the unified upgrade scripts
echo.

REM Check if running as administrator
net session >nul 2>&1
if %errorLevel% neq 0 (
    echo ERROR: This script must be run as Administrator!
    echo Right-click on this file and select "Run as administrator"
    echo.
    pause
    exit /b 1
)

echo Step 1: Checking for Chocolatey...
where choco >nul 2>&1
if %errorLevel% neq 0 (
    echo Installing Chocolatey...
    powershell -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command ^
        "Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))"
    
    REM Refresh environment variables
    call refreshenv.cmd
    
    REM Add chocolatey to current session PATH
    set "PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin"
) else (
    echo Chocolatey is already installed.
)

echo.
echo Step 2: Checking for Node.js...
where node >nul 2>&1
if %errorLevel% neq 0 (
    echo Installing Node.js LTS via Chocolatey...
    choco install nodejs-lts -y
    
    if !errorLevel! neq 0 (
        echo Chocolatey installation failed. Trying direct download...
        echo Downloading Node.js LTS installer...
        
        REM Create temp directory
        if not exist "%TEMP%\nodejs-installer" mkdir "%TEMP%\nodejs-installer"
        
        REM Download Node.js LTS MSI (using PowerShell)
        powershell -Command ^
            "Invoke-WebRequest -Uri 'https://nodejs.org/dist/v20.11.0/node-v20.11.0-x64.msi' -OutFile '%TEMP%\nodejs-installer\nodejs.msi'"
        
        if exist "%TEMP%\nodejs-installer\nodejs.msi" (
            echo Installing Node.js from downloaded MSI...
            msiexec /i "%TEMP%\nodejs-installer\nodejs.msi" /quiet /norestart
            
            REM Clean up
            rmdir /s /q "%TEMP%\nodejs-installer"
        ) else (
            echo ERROR: Failed to download Node.js installer.
            echo Please manually install Node.js from https://nodejs.org/
            pause
            exit /b 1
        )
    )
    
    REM Refresh environment variables and PATH
    call refreshenv.cmd 2>nul || (
        echo Refreshing PATH manually...
        set "PATH=%PATH%;%ProgramFiles%\nodejs"
    )
) else (
    echo Node.js is already installed.
    node --version
)

echo.
echo Step 3: Verifying npm installation...
where npm >nul 2>&1
if %errorLevel% neq 0 (
    echo ERROR: npm not found after Node.js installation.
    echo Please restart your command prompt or computer and try again.
    pause
    exit /b 1
)

npm --version
echo.

echo Step 4: Installing dependencies and running upgrade scripts...
if exist package.json (
    echo Installing npm dependencies...
    npm install
    
    if !errorLevel! neq 0 (
        echo ERROR: Failed to install npm dependencies.
        pause
        exit /b 1
    )
    
    echo.
    echo Running Appium upgrade scripts...
    
    REM Try different possible script commands
    npm run upgrade 2>nul || (
        npm run start 2>nul || (
            node scripts/index.js 2>nul || (
                node index.js 2>nul || (
                    echo ERROR: Could not find the main upgrade script.
                    echo Available npm scripts:
                    npm run
                    echo.
                    echo Please run the appropriate script manually.
                    pause
                    exit /b 1
                )
            )
        )
    )
) else (
    echo ERROR: package.json not found in current directory.
    echo Please make sure you're running this script from the project root.
    pause
    exit /b 1
)

echo.
echo ========================================
echo Bootstrap completed successfully!
echo ========================================
echo.
echo Node.js and npm are now installed and configured.
echo The Appium upgrade scripts have been executed.
echo.
pause
