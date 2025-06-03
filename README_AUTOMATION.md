# End-to-End Appium Automation Workflow

This project implements a complete end-to-end automation workflow for Appium testing.

## Features

✅ **Automatic Appium Server Management**
- Starts Appium server automatically
- Kills any existing Appium processes
- Handles server startup and shutdown

✅ **Device Detection**
- Automatically detects connected Android devices
- Falls back to mock mode if no devices available
- Supports both physical devices and emulators

✅ **App Management**
- Extracts package name from APK automatically
- Uninstalls existing app if present
- Installs fresh APK
- Launches app after installation

✅ **Test Execution**
- Runs all .feature files in the features directory
- Supports both Maven-based and Cucumber.js tests
- Provides detailed logging and progress tracking

✅ **Cleanup**
- Automatically stops Appium server when done
- Handles process termination gracefully
- Logs all activities for debugging

## Usage

### Quick Start
```bash
npm run e2e
```

### Manual Execution
```bash
node run_tests.js
```

## Configuration

The automation script automatically configures:
- **Appium Server**: `127.0.0.1:4723`
- **APK Path**: `src/test/resources/installable/app_0304_1.apk`
- **Features Path**: `src/test/resources/features/`
- **Android SDK**: `/home/ubuntu/android-sdk`

## Mock Mode

When no physical devices are available, the script automatically enters mock mode:
- Simulates device operations
- Demonstrates the complete workflow
- Useful for CI/CD environments without physical devices

## Workflow Steps

1. **Start Appium Server** - Launches Appium with proper configuration
2. **Detect Device** - Finds connected Android devices or uses mock mode
3. **Extract Package Name** - Gets app package from APK using aapt
4. **Uninstall App** - Removes existing app installation
5. **Install App** - Installs fresh APK to device
6. **Launch App** - Starts the application
7. **Run Tests** - Executes all .feature files
8. **Cleanup** - Stops Appium server and cleans up

## Logs

All activities are logged to:
- Console output with colored formatting
- `automation.log` file for detailed debugging

## Error Handling

The script includes comprehensive error handling:
- Graceful fallback to mock mode
- Proper cleanup on failures
- Detailed error messages and logging
- Process termination handling (SIGINT, SIGTERM)

## Dependencies

- Node.js 14+
- Appium 2.x
- Android SDK with ADB and AAPT
- Maven (for Java-based tests)

## Exit Codes

- `0` - Success
- `1` - Failure

The script ensures proper cleanup regardless of success or failure.
