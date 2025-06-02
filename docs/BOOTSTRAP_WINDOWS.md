
# Windows Bootstrap Guide

This guide helps Windows users who don't have Node.js installed to run the Appium Latest Upgrade scripts.

## Quick Start (Recommended)

1. **Download the repository** (if you haven't already):
   ```
   git clone <repository-url>
   cd appium-latest-upgrade
   ```

2. **Run the bootstrap script as Administrator**:
   - Right-click on `windows-bootstrap.bat`
   - Select "Run as administrator"
   - Follow the on-screen prompts

That's it! The script will automatically:
- Install Chocolatey (Windows package manager)
- Install Node.js LTS and npm
- Install project dependencies
- Run the Appium upgrade scripts

## What the Bootstrap Script Does

### Step 1: Install Chocolatey
- Checks if Chocolatey is already installed
- If not, downloads and installs it using PowerShell
- Chocolatey is a Windows package manager that makes installing software easier

### Step 2: Install Node.js
- Checks if Node.js is already installed
- If not, tries to install Node.js LTS via Chocolatey
- If Chocolatey fails, falls back to direct download from nodejs.org
- Installs the latest LTS version (v20.11.0) for maximum compatibility

### Step 3: Verify Installation
- Confirms that both `node` and `npm` commands are available
- Shows version numbers for verification

### Step 4: Run Upgrade Scripts
- Installs npm dependencies (`npm install`)
- Attempts to run the main upgrade script using various common patterns:
  - `npm run upgrade`
  - `npm run start`
  - `node scripts/index.js`
  - `node index.js`

## Troubleshooting

### "This script must be run as Administrator"
- Right-click on `windows-bootstrap.bat` and select "Run as administrator"
- Administrator privileges are required to install software

### "Chocolatey installation failed"
- The script will automatically try direct download from nodejs.org
- If both methods fail, manually install Node.js from https://nodejs.org/

### "npm not found after Node.js installation"
- Restart your command prompt or computer
- The PATH environment variable may need to be refreshed

### "Could not find the main upgrade script"
- The script will show available npm scripts
- Run the appropriate script manually using `npm run <script-name>`

### Antivirus Software Blocking Installation
- Some antivirus software may block the Chocolatey installation
- Temporarily disable real-time protection during installation
- Add the project folder to your antivirus whitelist

## Manual Installation (Alternative)

If the bootstrap script doesn't work for your system:

1. **Install Node.js manually**:
   - Go to https://nodejs.org/
   - Download the LTS version for Windows
   - Run the installer with default settings

2. **Verify installation**:
   ```cmd
   node --version
   npm --version
   ```

3. **Install dependencies and run scripts**:
   ```cmd
   npm install
   npm run upgrade
   ```

## System Requirements

- Windows 7 SP1 or later
- PowerShell 3.0 or later (usually pre-installed)
- Internet connection for downloading packages
- Administrator privileges for software installation

## Security Notes

- The script uses official installation methods from trusted sources
- Chocolatey: https://chocolatey.org/
- Node.js: https://nodejs.org/
- All downloads use HTTPS for security

## Support

If you encounter issues:

1. Check the troubleshooting section above
2. Ensure you're running as Administrator
3. Verify your internet connection
4. Try the manual installation method
5. Check the project's main README for additional help

---

**Note**: This bootstrap script is specifically designed for Windows users who don't have Node.js installed. If you already have Node.js, you can skip this and run the regular upgrade scripts directly.
