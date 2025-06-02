
/**
 * Unified Cross-Platform Appium Test Runner Script
 * Automatically detects devices and runs appropriate test suites
 * Works seamlessly on both Mac and Windows
 */

const { execSync } = require('child_process');
const os = require('os');
const fs = require('fs');
const path = require('path');

// Colors for output
const colors = {
    red: '\x1b[31m',
    green: '\x1b[32m',
    yellow: '\x1b[33m',
    blue: '\x1b[34m',
    reset: '\x1b[0m'
};

class CrossPlatformTestRunner {
    constructor() {
        this.platform = this.detectPlatform();
        this.config = {
            testDir: 'src/test',
            featureDir: path.join('src', 'test', 'resources', 'features'),
            reportsDir: path.join('target', 'cucumber-reports'),
            defaultProfile: 'default',
            appiumHost: '127.0.0.1',
            appiumPort: '4723'
        };
        
        // Test execution options
        this.options = {
            smokeTest: false,
            regressionTest: false,
            parallelExecution: false,
            deviceFilter: '',
            tagFilter: '',
            dryRun: false
        };
        
        this.devices = {
            android: [],
            ios: []
        };
        
        this.parseArgs();
        
        console.log(`${colors.blue}=== Unified Cross-Platform Appium Test Runner ===${colors.reset}`);
        console.log(`Platform: ${this.platform}`);
        if (this.options.dryRun) {
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

    parseArgs() {
        const args = process.argv.slice(2);
        
        for (let i = 0; i < args.length; i++) {
            switch (args[i]) {
                case '--smoke':
                    this.options.smokeTest = true;
                    break;
                case '--regression':
                    this.options.regressionTest = true;
                    break;
                case '--parallel':
                    this.options.parallelExecution = true;
                    break;
                case '--device':
                    this.options.deviceFilter = args[++i];
                    break;
                case '--tag':
                    this.options.tagFilter = args[++i];
                    break;
                case '--host':
                    this.config.appiumHost = args[++i];
                    break;
                case '--port':
                    this.config.appiumPort = args[++i];
                    break;
                case '--dry-run':
                    this.options.dryRun = true;
                    break;
            }
        }
        
        // Default to smoke tests if no specific test type is selected
        if (!this.options.smokeTest && !this.options.regressionTest) {
            this.options.smokeTest = true;
            console.log(`${colors.yellow}No test type specified, defaulting to smoke tests${colors.reset}`);
        }
    }

    execCommand(command, description, options = {}) {
        console.log(`${colors.blue}${description}...${colors.reset}`);
        console.log(`Command: ${command}`);
        
        if (this.options.dryRun) {
            console.log(`${colors.yellow}DRY RUN: Would execute: ${command}${colors.reset}`);
            return '';
        }

        try {
            const result = execSync(command, { 
                encoding: 'utf8',
                stdio: options.silent ? 'pipe' : 'inherit',
                shell: true,
                ...options
            });
            console.log(`${colors.green}✓ ${description} completed${colors.reset}`);
            return result;
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

    checkPrerequisites() {
        console.log(`${colors.blue}Checking prerequisites...${colors.reset}`);
        
        // Check if Maven is installed
        const mavenCmd = this.platform === 'windows' ? 'mvn.cmd' : 'mvn';
        if (!this.commandExists(mavenCmd)) {
            console.error(`${colors.red}Error: Maven is not installed${colors.reset}`);
            console.log('Please run: node install_all.js');
            process.exit(1);
        }
        
        // Check if ADB is available for Android testing
        if (!this.commandExists('adb')) {
            console.log(`${colors.yellow}Warning: ADB is not available. Android testing may not work.${colors.reset}`);
        }
        
        // Check if project structure exists
        if (!fs.existsSync('pom.xml')) {
            console.log(`${colors.yellow}Warning: No pom.xml found. Creating basic Maven project structure...${colors.reset}`);
            this.createMavenProject();
        }
        
        console.log(`${colors.green}✓ Prerequisites checked${colors.reset}`);
    }

    createMavenProject() {
        if (this.options.dryRun) {
            console.log(`${colors.yellow}DRY RUN: Would create Maven project structure${colors.reset}`);
            return;
        }
        
        console.log(`${colors.blue}Creating Maven project structure...${colors.reset}`);
        
        // Create directories
        const dirs = [
            'src/main/java',
            'src/test/java',
            'src/test/resources/features',
            'target'
        ];
        
        dirs.forEach(dir => {
            if (!fs.existsSync(dir)) {
                fs.mkdirSync(dir, { recursive: true });
            }
        });
        
        // Create basic pom.xml if it doesn't exist
        if (!fs.existsSync('pom.xml')) {
            const pomContent = this.generatePomXml();
            fs.writeFileSync('pom.xml', pomContent);
        }
        
        // Create sample feature file
        const sampleFeature = path.join('src', 'test', 'resources', 'features', 'sample.feature');
        if (!fs.existsSync(sampleFeature)) {
            const featureContent = this.generateSampleFeature();
            fs.writeFileSync(sampleFeature, featureContent);
        }
        
        console.log(`${colors.green}✓ Maven project structure created${colors.reset}`);
    }

    generatePomXml() {
        return `<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.appium</groupId>
    <artifactId>appium-automation</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <appium.version>8.6.0</appium.version>
        <cucumber.version>7.14.0</cucumber.version>
        <testng.version>7.8.0</testng.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>io.appium</groupId>
            <artifactId>java-client</artifactId>
            <version>\${appium.version}</version>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>\${cucumber.version}</version>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-testng</artifactId>
            <version>\${cucumber.version}</version>
        </dependency>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>\${testng.version}</version>
        </dependency>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.15.0</version>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.2</version>
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>src/test/resources/testng.xml</suiteXmlFile>
                    </suiteXmlFiles>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>`;
    }

    generateSampleFeature() {
        return `@smoke @android
Feature: Sample Mobile App Test
  
  Scenario: Launch app and verify basic functionality
    Given I launch the mobile application
    When I interact with the main screen
    Then I should see the expected elements

@regression @ios
Feature: iOS Specific Tests
  
  Scenario: iOS specific functionality
    Given I launch the iOS application
    When I perform iOS specific actions
    Then I should see iOS specific results
`;
    }

    detectDevices() {
        console.log(`${colors.blue}Detecting connected devices...${colors.reset}`);
        
        // Detect Android devices
        if (this.commandExists('adb')) {
            try {
                const adbOutput = execSync('adb devices', { encoding: 'utf8', stdio: 'pipe' });
                const lines = adbOutput.split('\n');
                
                for (const line of lines) {
                    if (line.includes('\tdevice')) {
                        const deviceId = line.split('\t')[0];
                        this.devices.android.push(deviceId);
                        console.log(`${colors.green}✓ Android device found: ${deviceId}${colors.reset}`);
                    }
                }
            } catch (error) {
                console.log(`${colors.yellow}Warning: Could not detect Android devices${colors.reset}`);
            }
        }
        
        // Detect iOS devices (requires Xcode tools on macOS)
        if (this.platform === 'mac' && this.commandExists('xcrun')) {
            try {
                const simOutput = execSync('xcrun simctl list devices | grep "Booted"', { 
                    encoding: 'utf8', 
                    stdio: 'pipe' 
                });
                const lines = simOutput.split('\n');
                
                for (const line of lines) {
                    if (line.includes('(Simulator)')) {
                        const deviceName = line.replace('(Simulator)', '').trim();
                        this.devices.ios.push(deviceName);
                        console.log(`${colors.green}✓ iOS Simulator found: ${deviceName}${colors.reset}`);
                    }
                }
            } catch (error) {
                // No booted simulators
            }
        }
        
        // Summary
        console.log('');
        console.log('Device Summary:');
        console.log(`  Android devices: ${this.devices.android.length}`);
        console.log(`  iOS devices: ${this.devices.ios.length}`);
        
        if (this.devices.android.length === 0 && this.devices.ios.length === 0) {
            console.log(`${colors.yellow}Warning: No devices detected${colors.reset}`);
            if (!this.options.dryRun) {
                console.log('Please connect a device or start an emulator/simulator');
            }
        }
    }

    checkAppiumServer() {
        console.log(`${colors.blue}Checking Appium server...${colors.reset}`);
        
        try {
            let curlCmd;
            switch (this.platform) {
                case 'windows':
                    curlCmd = `powershell -Command "Invoke-WebRequest -Uri http://${this.config.appiumHost}:${this.config.appiumPort}/status -UseBasicParsing"`;
                    break;
                case 'mac':
                case 'linux':
                    curlCmd = `curl -s http://${this.config.appiumHost}:${this.config.appiumPort}/status`;
                    break;
            }
            
            execSync(curlCmd, { stdio: 'ignore' });
            console.log(`${colors.green}✓ Appium server is running${colors.reset}`);
        } catch (error) {
            console.log(`${colors.yellow}Appium server is not running. Starting it...${colors.reset}`);
            if (!this.options.dryRun) {
                this.execCommand('node appium_ctl.js start', 'Starting Appium server');
                // Wait for server to start
                setTimeout(() => {}, 5000);
            }
        }
    }

    buildMavenCommand() {
        const mavenCmd = this.platform === 'windows' ? 'mvn.cmd' : 'mvn';
        let command = `${mavenCmd} clean test`;
        
        // Add system properties
        command += ` -Dappium.host=${this.config.appiumHost}`;
        command += ` -Dappium.port=${this.config.appiumPort}`;
        
        // Add device information
        if (this.devices.android.length > 0) {
            command += ` -Dandroid.device=${this.devices.android[0]}`;
        }
        
        // Add test filters
        if (this.options.smokeTest) {
            command += ` -Dcucumber.filter.tags="@smoke"`;
        } else if (this.options.regressionTest) {
            command += ` -Dcucumber.filter.tags="@regression or @smoke"`;
        }
        
        // Add custom tag filter
        if (this.options.tagFilter) {
            command += ` -Dcucumber.filter.tags="${this.options.tagFilter}"`;
        }
        
        // Add device filter
        if (this.options.deviceFilter) {
            switch (this.options.deviceFilter) {
                case 'android':
                    command += ` -Dcucumber.filter.tags="@android"`;
                    break;
                case 'ios':
                    command += ` -Dcucumber.filter.tags="@ios"`;
                    break;
            }
        }
        
        // Add parallel execution
        if (this.options.parallelExecution) {
            command += ` -Dparallel=methods -DthreadCount=2`;
        }
        
        return command;
    }

    executeTests() {
        console.log(`${colors.blue}Executing tests...${colors.reset}`);
        
        const mavenCommand = this.buildMavenCommand();
        
        if (!this.options.dryRun) {
            // Create reports directory
            if (!fs.existsSync(this.config.reportsDir)) {
                fs.mkdirSync(this.config.reportsDir, { recursive: true });
            }
        }
        
        try {
            this.execCommand(mavenCommand, 'Running Maven tests');
            
            if (!this.options.dryRun && fs.existsSync(this.config.reportsDir)) {
                console.log(`Test reports available in: ${this.config.reportsDir}`);
            }
            
            return true;
        } catch (error) {
            return false;
        }
    }

    generateSummary() {
        console.log(`${colors.blue}Test Execution Summary${colors.reset}`);
        console.log('========================');
        console.log(`Timestamp: ${new Date().toISOString()}`);
        console.log(`Platform: ${this.platform}`);
        console.log(`Appium Server: http://${this.config.appiumHost}:${this.config.appiumPort}`);
        console.log(`Android Devices: ${this.devices.android.length}`);
        console.log(`iOS Devices: ${this.devices.ios.length}`);
        
        if (this.options.smokeTest) {
            console.log('Test Type: Smoke Tests');
        } else if (this.options.regressionTest) {
            console.log('Test Type: Regression Tests');
        } else {
            console.log('Test Type: Default');
        }
        
        if (this.options.tagFilter) {
            console.log(`Tag Filter: ${this.options.tagFilter}`);
        }
        
        if (this.options.deviceFilter) {
            console.log(`Device Filter: ${this.options.deviceFilter}`);
        }
        
        console.log(`Parallel Execution: ${this.options.parallelExecution}`);
        console.log('');
    }

    run() {
        try {
            this.checkPrerequisites();
            this.detectDevices();
            this.checkAppiumServer();
            this.generateSummary();
            
            const success = this.executeTests();
            
            console.log('');
            if (success) {
                console.log(`${colors.green}=== Test Execution Complete ===${colors.reset}`);
            } else {
                console.log(`${colors.red}=== Test Execution Failed ===${colors.reset}`);
                process.exit(1);
            }
        } catch (error) {
            console.error(`${colors.red}Test execution failed: ${error.message}${colors.reset}`);
            process.exit(1);
        }
    }

    showUsage() {
        console.log('Usage: node run_tests.js [options]');
        console.log('');
        console.log('Test Execution Options:');
        console.log('  --smoke           Run smoke tests only');
        console.log('  --regression      Run full regression suite');
        console.log('  --parallel        Enable parallel execution');
        console.log('  --device DEVICE   Filter by device (android|ios)');
        console.log('  --tag TAG         Filter by cucumber tag');
        console.log('  --dry-run         Show what would be executed');
        console.log('');
        console.log('Server Options:');
        console.log(`  --host HOST       Appium server host (default: ${this.config.appiumHost})`);
        console.log(`  --port PORT       Appium server port (default: ${this.config.appiumPort})`);
        console.log('');
        console.log('Examples:');
        console.log('  node run_tests.js --smoke');
        console.log('  node run_tests.js --regression --parallel');
        console.log('  node run_tests.js --device android --tag @login');
        console.log('  node run_tests.js --dry-run');
    }
}

// Main execution
if (require.main === module) {
    if (process.argv.includes('--help')) {
        const runner = new CrossPlatformTestRunner();
        runner.showUsage();
        process.exit(0);
    }
    
    const runner = new CrossPlatformTestRunner();
    runner.run();
}

module.exports = CrossPlatformTestRunner;
