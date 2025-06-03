
/**
 * Unified Cross-Platform Appium Installation Script
 * Automatically detects OS (Mac vs Windows) and executes appropriate commands
 * Works seamlessly on both Mac and Windows
 */

const { execSync } = require('child_process');
const os = require('os');
const path = require('path');
const fs = require('fs');

// Colors for output
const colors = {
    red: '\x1b[31m',
    green: '\x1b[32m',
    yellow: '\x1b[33m',
    blue: '\x1b[34m',
    reset: '\x1b[0m'
};

// Configuration
const config = {
    javaVersion: '11',
    mavenVersion: '3.9.5',
    nodeVersion: '18',
    appiumVersion: 'latest'
};

class CrossPlatformInstaller {
    constructor() {
        this.platform = this.detectPlatform();
        this.dryRun = process.argv.includes('--dry-run');
        this.forceOs = this.getForceOs();
        
        if (this.forceOs) {
            this.platform = this.forceOs;
            console.log(`${colors.yellow}Forcing OS detection to: ${this.platform}${colors.reset}`);
        }
        
        console.log(`${colors.blue}=== Unified Cross-Platform Appium Installer ===${colors.reset}`);
        console.log(`Detected Platform: ${this.platform}`);
        if (this.dryRun) {
            console.log(`${colors.yellow}Running in DRY RUN mode${colors.reset}`);
        }
        console.log('');
    }

    detectPlatform() {
        const platform = os.platform();
        switch (platform) {
            case 'win32':
                return 'windows';
            case 'darwin':
                return 'mac';
            case 'linux':
                return 'linux';
            default:
                throw new Error(`Unsupported platform: ${platform}`);
        }
    }

    getForceOs() {
        const osIndex = process.argv.indexOf('--os');
        if (osIndex !== -1 && process.argv[osIndex + 1]) {
            const forcedOs = process.argv[osIndex + 1].toLowerCase();
            if (['windows', 'mac', 'linux'].includes(forcedOs)) {
                return forcedOs;
            }
        }
        return null;
    }

    execCommand(command, description) {
        console.log(`${colors.blue}${description}...${colors.reset}`);
        console.log(`Command: ${command}`);
        
        if (this.dryRun) {
            console.log(`${colors.yellow}DRY RUN: Would execute: ${command}${colors.reset}`);
            return;
        }

        try {
            const output = execSync(command, { 
                stdio: 'inherit',
                shell: true,
                encoding: 'utf8'
            });
            console.log(`${colors.green}✓ ${description} completed${colors.reset}`);
            return output;
        } catch (error) {
            console.error(`${colors.red}✗ ${description} failed: ${error.message}${colors.reset}`);
            throw error;
        }
    }

    commandExists(command) {
        try {
            const checkCmd = this.platform === 'windows' ? `where ${command}` : `which ${command}`;
            execSync(checkCmd, { stdio: 'ignore' });
            return true;
        } catch {
            return false;
        }
    }

    installJava() {
        console.log(`${colors.blue}Installing Java ${config.javaVersion}...${colors.reset}`);
        
        if (this.commandExists('java')) {
            console.log(`${colors.yellow}Java already installed${colors.reset}`);
            return;
        }

        switch (this.platform) {
            case 'windows':
                console.log(`${colors.yellow}Please install Java ${config.javaVersion} manually from:${colors.reset}`);
                console.log('https://adoptium.net/');
                console.log('Or use Chocolatey: choco install openjdk11');
                break;
            
            case 'mac':
                if (this.commandExists('brew')) {
                    this.execCommand(`brew install openjdk@${config.javaVersion}`, 'Installing Java via Homebrew');
                } else {
                    console.log(`${colors.yellow}Please install Homebrew first, then run: brew install openjdk@${config.javaVersion}${colors.reset}`);
                }
                break;
            
            case 'linux':
                this.execCommand(`sudo apt-get update && sudo apt-get install -y openjdk-${config.javaVersion}-jdk`, 'Installing Java via apt');
                break;
        }
    }

    installMaven() {
        console.log(`${colors.blue}Installing Maven ${config.mavenVersion}...${colors.reset}`);
        
        if (this.commandExists('mvn')) {
            console.log(`${colors.yellow}Maven already installed${colors.reset}`);
            return;
        }

        switch (this.platform) {
            case 'windows':
                console.log(`${colors.yellow}Please install Maven manually or use Chocolatey:${colors.reset}`);
                console.log('choco install maven');
                break;
            
            case 'mac':
                if (this.commandExists('brew')) {
                    this.execCommand('brew install maven', 'Installing Maven via Homebrew');
                } else {
                    console.log(`${colors.yellow}Please install Homebrew first, then run: brew install maven${colors.reset}`);
                }
                break;
            
            case 'linux':
                this.execCommand('sudo apt-get install -y maven', 'Installing Maven via apt');
                break;
        }
    }

    installNodejs() {
        console.log(`${colors.blue}Installing Node.js ${config.nodeVersion}...${colors.reset}`);
        
        if (this.commandExists('node')) {
            console.log(`${colors.yellow}Node.js already installed${colors.reset}`);
            return;
        }

        switch (this.platform) {
            case 'windows':
                console.log(`${colors.yellow}Please install Node.js manually from:${colors.reset}`);
                console.log('https://nodejs.org/');
                console.log('Or use Chocolatey: choco install nodejs');
                break;
            
            case 'mac':
                if (this.commandExists('brew')) {
                    this.execCommand(`brew install node@${config.nodeVersion}`, 'Installing Node.js via Homebrew');
                } else {
                    console.log(`${colors.yellow}Please install Homebrew first, then run: brew install node@${config.nodeVersion}${colors.reset}`);
                }
                break;
            
            case 'linux':
                this.execCommand(`curl -fsSL https://deb.nodesource.com/setup_${config.nodeVersion}.x | sudo -E bash - && sudo apt-get install -y nodejs`, 'Installing Node.js via NodeSource');
                break;
        }
    }

    installAndroidSDK() {
        console.log(`${colors.blue}Installing Android SDK...${colors.reset}`);
        
        const androidHome = this.platform === 'windows' ? 
            path.join(process.env.USERPROFILE || '', 'AppData', 'Local', 'Android', 'Sdk') :
            path.join(process.env.HOME || '', 'Library', 'Android', 'sdk');

        if (fs.existsSync(androidHome)) {
            console.log(`${colors.yellow}Android SDK already installed at: ${androidHome}${colors.reset}`);
            return;
        }

        switch (this.platform) {
            case 'windows':
                console.log(`${colors.yellow}Please install Android Studio manually from:${colors.reset}`);
                console.log('https://developer.android.com/studio');
                console.log('Or use Chocolatey: choco install androidstudio');
                break;
            
            case 'mac':
                if (this.commandExists('brew')) {
                    this.execCommand('brew install --cask android-studio', 'Installing Android Studio via Homebrew');
                } else {
                    console.log(`${colors.yellow}Please install Android Studio manually from:${colors.reset}`);
                    console.log('https://developer.android.com/studio');
                }
                break;
            
            case 'linux':
                console.log(`${colors.yellow}Please install Android Studio manually from:${colors.reset}`);
                console.log('https://developer.android.com/studio');
                break;
        }
    }

    installAppium() {
        console.log(`${colors.blue}Installing Appium...${colors.reset}`);
        
        if (this.commandExists('appium')) {
            console.log(`${colors.yellow}Appium already installed${colors.reset}`);
            return;
        }

        const npmCmd = this.platform === 'windows' ? 'npm.cmd' : 'npm';
        this.execCommand(`${npmCmd} install -g appium@${config.appiumVersion}`, 'Installing Appium globally');
        
        // Install common drivers
        try {
            this.execCommand(`${npmCmd} install -g appium-doctor`, 'Installing Appium Doctor');
            this.execCommand('appium driver install uiautomator2', 'Installing UiAutomator2 driver');
            if (this.platform === 'mac') {
                this.execCommand('appium driver install xcuitest', 'Installing XCUITest driver');
            }
        } catch (error) {
            console.log(`${colors.yellow}Warning: Some Appium drivers may not have installed correctly${colors.reset}`);
        }
    }

    setupEnvironment() {
        console.log(`${colors.blue}Setting up environment variables...${colors.reset}`);
        
        if (this.dryRun) {
            console.log(`${colors.yellow}DRY RUN: Would setup environment variables${colors.reset}`);
            return;
        }

        if (this.platform === 'windows') {
            this.setupWindowsEnvironment();
        } else {
            this.setupUnixEnvironment();
        }
    }

    setupWindowsEnvironment() {
        console.log(`${colors.yellow}For Windows, please run these PowerShell commands as Administrator:${colors.reset}`);
        console.log(`[Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\\Program Files\\Eclipse Adoptium\\jdk-11.0.x", "Machine")`);
        console.log(`[Environment]::SetEnvironmentVariable("ANDROID_HOME", "$env:USERPROFILE\\AppData\\Local\\Android\\Sdk", "Machine")`);
        console.log(`${colors.yellow}Or manually add these to your System Environment Variables through Control Panel${colors.reset}`);
    }

    setupUnixEnvironment() {
        const homeDir = process.env.HOME;
        if (!homeDir) {
            console.log(`${colors.red}Error: HOME environment variable not set${colors.reset}`);
            return;
        }

        // Detect current shell and determine profile file
        const shell = process.env.SHELL || '';
        let profileFile;
        
        if (shell.includes('zsh')) {
            profileFile = path.join(homeDir, '.zshrc');
        } else if (shell.includes('bash')) {
            profileFile = path.join(homeDir, '.bash_profile');
            // Fallback to .bashrc if .bash_profile doesn't exist
            if (!fs.existsSync(profileFile)) {
                profileFile = path.join(homeDir, '.bashrc');
            }
        } else {
            // Default fallback
            profileFile = path.join(homeDir, '.bashrc');
        }

        console.log(`${colors.blue}Detected shell profile: ${profileFile}${colors.reset}`);

        // Define environment variables based on platform
        let envVars;
        if (this.platform === 'mac') {
            envVars = [
                'export JAVA_HOME=$(/usr/libexec/java_home -v 11)',
                'export ANDROID_HOME=$HOME/Library/Android/sdk',
                'export PATH=$PATH:$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools'
            ];
        } else { // linux
            envVars = [
                'export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64',
                'export ANDROID_HOME=$HOME/Android/Sdk',
                'export PATH=$PATH:$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools'
            ];
        }

        // Check if variables are already set and add them if not
        this.addEnvironmentVariables(profileFile, envVars);
    }

    addEnvironmentVariables(profileFile, envVars) {
        try {
            // Read existing profile file content or create empty string if file doesn't exist
            let profileContent = '';
            if (fs.existsSync(profileFile)) {
                profileContent = fs.readFileSync(profileFile, 'utf8');
            }

            // Check which variables are already present
            const missingVars = [];
            const appiumSectionStart = '# Appium Environment Variables - Auto-generated';
            const appiumSectionEnd = '# End Appium Environment Variables';

            // Remove existing Appium section if present
            const sectionRegex = new RegExp(`${appiumSectionStart}[\\s\\S]*?${appiumSectionEnd}\\n?`, 'g');
            profileContent = profileContent.replace(sectionRegex, '');

            // Check which variables are missing (not already manually added)
            envVars.forEach(envVar => {
                const varName = envVar.split('=')[0].replace('export ', '');
                const varRegex = new RegExp(`^\\s*export\\s+${varName}=`, 'm');
                if (!varRegex.test(profileContent)) {
                    missingVars.push(envVar);
                }
            });

            if (missingVars.length === 0) {
                console.log(`${colors.green}✓ All environment variables are already configured${colors.reset}`);
                return;
            }

            // Add missing variables in a clearly marked section
            const newSection = [
                '',
                appiumSectionStart,
                ...missingVars,
                appiumSectionEnd,
                ''
            ].join('\n');

            // Append the new section
            const updatedContent = profileContent + newSection;

            // Write back to file
            fs.writeFileSync(profileFile, updatedContent, 'utf8');

            console.log(`${colors.green}✓ Added ${missingVars.length} environment variable(s) to ${profileFile}${colors.reset}`);
            missingVars.forEach(envVar => {
                console.log(`  ${colors.blue}+ ${envVar}${colors.reset}`);
            });
            console.log(`${colors.yellow}Please restart your terminal or run: source ${profileFile}${colors.reset}`);

        } catch (error) {
            console.error(`${colors.red}Error updating profile file ${profileFile}: ${error.message}${colors.reset}`);
            console.log(`${colors.yellow}Please manually add these environment variables:${colors.reset}`);
            envVars.forEach(envVar => {
                console.log(`  ${envVar}`);
            });
        }
    }

    verifyInstallation() {
        console.log(`${colors.blue}Verifying installation...${colors.reset}`);
        
        const checks = [
            { cmd: 'java', name: 'Java' },
            { cmd: 'mvn', name: 'Maven' },
            { cmd: 'node', name: 'Node.js' },
            { cmd: 'npm', name: 'npm' },
            { cmd: 'appium', name: 'Appium' }
        ];

        checks.forEach(check => {
            if (this.commandExists(check.cmd)) {
                console.log(`${colors.green}✓ ${check.name} is available${colors.reset}`);
            } else {
                console.log(`${colors.red}✗ ${check.name} is not available${colors.reset}`);
            }
        });
    }

    run() {
        try {
            this.installJava();
            this.installMaven();
            this.installNodejs();
            this.installAndroidSDK();
            this.installAppium();
            this.setupEnvironment();
            this.verifyInstallation();
            
            console.log('');
            console.log(`${colors.green}=== Installation Complete ===${colors.reset}`);
            console.log(`${colors.yellow}Please restart your terminal and ensure environment variables are set${colors.reset}`);
            
        } catch (error) {
            console.error(`${colors.red}Installation failed: ${error.message}${colors.reset}`);
            process.exit(1);
        }
    }

    static showUsage() {
        console.log('Usage: node install_all.js [options]');
        console.log('');
        console.log('Options:');
        console.log('  --dry-run     Show what would be executed without running commands');
        console.log('  --os OS       Force OS detection (windows|mac|linux) - for testing');
        console.log('  --help        Show this help message');
        console.log('');
        console.log('Examples:');
        console.log('  node install_all.js');
        console.log('  node install_all.js --dry-run');
        console.log('  node install_all.js --os mac');
    }
}

// Main execution
if (require.main === module) {
    if (process.argv.includes('--help')) {
        CrossPlatformInstaller.showUsage();
        process.exit(0);
    }
    
    const installer = new CrossPlatformInstaller();
    installer.run();
}

module.exports = CrossPlatformInstaller;
