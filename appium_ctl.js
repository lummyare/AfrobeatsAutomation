
/**
 * Unified Cross-Platform Appium Server Control Script
 * Manages Appium server lifecycle: start, stop, restart, status
 * Works seamlessly on both Mac and Windows
 */

const { execSync, spawn } = require('child_process');
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

class CrossPlatformAppiumController {
    constructor() {
        this.platform = this.detectPlatform();
        this.config = {
            defaultHost: '127.0.0.1',
            defaultPort: '4723',
            logDir: path.join(os.homedir(), 'appium-logs'),
            pidFile: path.join(os.homedir(), '.appium.pid')
        };
        
        // Parse command line arguments
        this.parseArgs();
        
        console.log(`${colors.blue}=== Unified Cross-Platform Appium Controller ===${colors.reset}`);
        console.log(`Platform: ${this.platform}`);
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
        this.command = args[0];
        this.options = {
            host: this.config.defaultHost,
            port: this.config.defaultPort,
            background: true,
            logLevel: 'info'
        };

        for (let i = 1; i < args.length; i++) {
            switch (args[i]) {
                case '--host':
                    this.options.host = args[++i];
                    break;
                case '--port':
                    this.options.port = args[++i];
                    break;
                case '--foreground':
                    this.options.background = false;
                    break;
                case '--log-level':
                    this.options.logLevel = args[++i];
                    break;
            }
        }
    }

    execCommand(command, description, options = {}) {
        console.log(`${colors.blue}${description}...${colors.reset}`);
        
        try {
            const result = execSync(command, { 
                encoding: 'utf8',
                stdio: options.silent ? 'pipe' : 'inherit',
                shell: true,
                ...options
            });
            return result;
        } catch (error) {
            if (!options.silent) {
                console.error(`${colors.red}✗ ${description} failed: ${error.message}${colors.reset}`);
            }
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

    checkAppium() {
        if (!this.commandExists('appium')) {
            console.error(`${colors.red}Error: Appium is not installed or not in PATH${colors.reset}`);
            console.log('Please run: node install_all.js');
            process.exit(1);
        }
    }

    isPortInUse(port) {
        try {
            let command;
            switch (this.platform) {
                case 'windows':
                    command = `netstat -an | findstr :${port}`;
                    break;
                case 'mac':
                case 'linux':
                    command = `lsof -i :${port}`;
                    break;
            }
            
            execSync(command, { stdio: 'ignore' });
            return true;
        } catch {
            return false;
        }
    }

    getPidByPort(port) {
        try {
            let command;
            switch (this.platform) {
                case 'windows':
                    command = `netstat -ano | findstr :${port}`;
                    const output = execSync(command, { encoding: 'utf8' });
                    const lines = output.split('\n');
                    for (const line of lines) {
                        if (line.includes('LISTENING')) {
                            const parts = line.trim().split(/\s+/);
                            return parts[parts.length - 1];
                        }
                    }
                    break;
                case 'mac':
                case 'linux':
                    command = `lsof -ti :${port}`;
                    return execSync(command, { encoding: 'utf8' }).trim();
            }
        } catch {
            return null;
        }
    }

    startServer() {
        console.log(`${colors.blue}Starting Appium server...${colors.reset}`);
        
        this.checkAppium();
        
        // Check if already running
        if (this.isPortInUse(this.options.port)) {
            const pid = this.getPidByPort(this.options.port);
            console.log(`${colors.yellow}Appium server already running on port ${this.options.port} (PID: ${pid})${colors.reset}`);
            return;
        }
        
        // Create log directory
        if (!fs.existsSync(this.config.logDir)) {
            fs.mkdirSync(this.config.logDir, { recursive: true });
        }
        
        // Prepare log file
        const timestamp = new Date().toISOString().replace(/[:.]/g, '-');
        const logFile = path.join(this.config.logDir, `appium-${timestamp}.log`);
        
        // Build Appium command
        const appiumCmd = [
            'appium',
            'server',
            '--address', this.options.host,
            '--port', this.options.port,
            '--session-override',
            '--log-level', this.options.logLevel,
            '--log', logFile
        ];
        
        console.log(`Command: ${appiumCmd.join(' ')}`);
        console.log(`Log file: ${logFile}`);
        
        if (this.options.background) {
            // Start in background
            const child = spawn(appiumCmd[0], appiumCmd.slice(1), {
                detached: true,
                stdio: 'ignore'
            });
            
            child.unref();
            
            // Save PID
            fs.writeFileSync(this.config.pidFile, child.pid.toString());
            
            // Wait a moment and check if it started successfully
            setTimeout(() => {
                if (this.isPortInUse(this.options.port)) {
                    console.log(`${colors.green}✓ Appium server started successfully${colors.reset}`);
                    console.log(`  Host: ${this.options.host}`);
                    console.log(`  Port: ${this.options.port}`);
                    console.log(`  PID: ${child.pid}`);
                    console.log(`  Log: ${logFile}`);
                    console.log(`  Server URL: http://${this.options.host}:${this.options.port}`);
                } else {
                    console.log(`${colors.red}✗ Failed to start Appium server${colors.reset}`);
                    if (fs.existsSync(this.config.pidFile)) {
                        fs.unlinkSync(this.config.pidFile);
                    }
                }
            }, 3000);
        } else {
            // Start in foreground
            console.log(`${colors.yellow}Starting Appium server in foreground (Ctrl+C to stop)...${colors.reset}`);
            try {
                execSync(appiumCmd.join(' '), { stdio: 'inherit', shell: true });
            } catch (error) {
                console.log(`${colors.red}Appium server stopped${colors.reset}`);
            }
        }
    }

    stopServer() {
        console.log(`${colors.blue}Stopping Appium server...${colors.reset}`);
        
        // Try to get PID from port
        let pid = this.getPidByPort(this.options.port);
        
        // Also check PID file
        if (!pid && fs.existsSync(this.config.pidFile)) {
            try {
                pid = fs.readFileSync(this.config.pidFile, 'utf8').trim();
            } catch {
                // Ignore error
            }
        }
        
        if (!pid) {
            console.log(`${colors.yellow}No Appium server found running on port ${this.options.port}${colors.reset}`);
            if (fs.existsSync(this.config.pidFile)) {
                fs.unlinkSync(this.config.pidFile);
            }
            return;
        }
        
        try {
            let killCommand;
            switch (this.platform) {
                case 'windows':
                    killCommand = `taskkill /PID ${pid} /F`;
                    break;
                case 'mac':
                case 'linux':
                    killCommand = `kill -TERM ${pid}`;
                    break;
            }
            
            execSync(killCommand, { stdio: 'ignore' });
            console.log(`${colors.green}✓ Appium server stopped (PID: ${pid})${colors.reset}`);
        } catch (error) {
            console.log(`${colors.red}✗ Failed to stop Appium server (PID: ${pid})${colors.reset}`);
        }
        
        // Clean up PID file
        if (fs.existsSync(this.config.pidFile)) {
            fs.unlinkSync(this.config.pidFile);
        }
    }

    checkStatus() {
        console.log(`${colors.blue}Checking Appium server status...${colors.reset}`);
        
        const pid = this.getPidByPort(this.options.port);
        
        if (pid) {
            console.log(`${colors.green}✓ Appium server is running${colors.reset}`);
            console.log(`  Host: ${this.options.host}`);
            console.log(`  Port: ${this.options.port}`);
            console.log(`  PID: ${pid}`);
            
            // Test server connectivity
            try {
                const curlCmd = this.platform === 'windows' ? 
                    `powershell -Command "Invoke-WebRequest -Uri http://${this.options.host}:${this.options.port}/status -UseBasicParsing"` :
                    `curl -s http://${this.options.host}:${this.options.port}/status`;
                
                execSync(curlCmd, { stdio: 'ignore' });
                console.log(`${colors.green}  Status: Responding to requests${colors.reset}`);
            } catch {
                console.log(`${colors.yellow}  Status: Not responding (starting up?)${colors.reset}`);
            }
        } else {
            console.log(`${colors.red}✗ Appium server is not running on port ${this.options.port}${colors.reset}`);
            
            // Clean up stale PID file
            if (fs.existsSync(this.config.pidFile)) {
                console.log('Removing stale PID file');
                fs.unlinkSync(this.config.pidFile);
            }
            
            return false;
        }
        
        return true;
    }

    showLogs() {
        console.log(`${colors.blue}Showing Appium server logs...${colors.reset}`);
        
        if (!fs.existsSync(this.config.logDir)) {
            console.log(`${colors.yellow}No log directory found: ${this.config.logDir}${colors.reset}`);
            return;
        }
        
        // Find the most recent log file
        const logFiles = fs.readdirSync(this.config.logDir)
            .filter(file => file.startsWith('appium-') && file.endsWith('.log'))
            .map(file => ({
                name: file,
                path: path.join(this.config.logDir, file),
                mtime: fs.statSync(path.join(this.config.logDir, file)).mtime
            }))
            .sort((a, b) => b.mtime - a.mtime);
        
        if (logFiles.length === 0) {
            console.log(`${colors.yellow}No log files found in ${this.config.logDir}${colors.reset}`);
            return;
        }
        
        const latestLog = logFiles[0].path;
        console.log(`Latest log file: ${latestLog}`);
        console.log('');
        
        try {
            const tailCmd = this.platform === 'windows' ? 
                `powershell -Command "Get-Content '${latestLog}' -Wait"` :
                `tail -f "${latestLog}"`;
            
            execSync(tailCmd, { stdio: 'inherit', shell: true });
        } catch (error) {
            // User probably pressed Ctrl+C
            console.log(`${colors.yellow}Log viewing stopped${colors.reset}`);
        }
    }

    restartServer() {
        console.log(`${colors.blue}Restarting Appium server...${colors.reset}`);
        this.stopServer();
        setTimeout(() => {
            this.startServer();
        }, 2000);
    }

    run() {
        switch (this.command) {
            case 'start':
                this.startServer();
                break;
            case 'stop':
                this.stopServer();
                break;
            case 'restart':
                this.restartServer();
                break;
            case 'status':
                this.checkStatus();
                break;
            case 'logs':
                this.showLogs();
                break;
            default:
                this.showUsage();
                process.exit(1);
        }
    }

    showUsage() {
        console.log('Usage: node appium_ctl.js {start|stop|restart|status|logs} [options]');
        console.log('');
        console.log('Commands:');
        console.log('  start     Start Appium server');
        console.log('  stop      Stop Appium server');
        console.log('  restart   Restart Appium server');
        console.log('  status    Check server status');
        console.log('  logs      Show server logs');
        console.log('');
        console.log('Options:');
        console.log(`  --host HOST       Server host (default: ${this.config.defaultHost})`);
        console.log(`  --port PORT       Server port (default: ${this.config.defaultPort})`);
        console.log('  --foreground      Run in foreground (default: background)');
        console.log('  --log-level LEVEL Log level: debug, info, warn, error (default: info)');
        console.log('');
        console.log('Examples:');
        console.log('  node appium_ctl.js start');
        console.log('  node appium_ctl.js start --port 4724 --foreground');
        console.log('  node appium_ctl.js stop');
        console.log('  node appium_ctl.js status');
    }
}

// Main execution
if (require.main === module) {
    if (process.argv.includes('--help') || process.argv.length < 3) {
        const controller = new CrossPlatformAppiumController();
        controller.showUsage();
        process.exit(process.argv.includes('--help') ? 0 : 1);
    }
    
    const controller = new CrossPlatformAppiumController();
    controller.run();
}

module.exports = CrossPlatformAppiumController;
