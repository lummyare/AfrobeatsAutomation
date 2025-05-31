#!/bin/bash

# Appium 2.x Migration Script
echo "Starting Appium 2.x migration refactoring..."

# Get list of Java files to process
find src/ -name "*.java" > java_files.txt

echo "Found $(wc -l < java_files.txt) Java files to process"

# 1. Replace MobileElement with WebElement
echo "Replacing MobileElement with WebElement..."
for file in $(cat java_files.txt); do
    sed -i 's/import io.appium.java_client.MobileElement;/import org.openqa.selenium.WebElement;/g' "$file"
    sed -i 's/MobileElement/WebElement/g' "$file"
done

# 2. Replace AppiumDriver<MobileElement> with AppiumDriver
echo "Updating AppiumDriver declarations..."
for file in $(cat java_files.txt); do
    sed -i 's/AppiumDriver<MobileElement>/AppiumDriver/g' "$file"
    sed -i 's/AndroidDriver<MobileElement>/AndroidDriver/g' "$file"
    sed -i 's/IOSDriver<MobileElement>/IOSDriver/g' "$file"
done

# 3. Replace DesiredCapabilities imports and usage
echo "Replacing DesiredCapabilities..."
for file in $(cat java_files.txt); do
    sed -i 's/import org.openqa.selenium.remote.DesiredCapabilities;/import io.appium.java_client.android.options.UiAutomator2Options;\nimport io.appium.java_client.ios.options.XCUITestOptions;/g' "$file"
    sed -i 's/DesiredCapabilities capabilities = new DesiredCapabilities();/UiAutomator2Options options = new UiAutomator2Options();/g' "$file"
    sed -i 's/DesiredCapabilities/UiAutomator2Options/g' "$file"
done

# 4. Replace MobileBy with AppiumBy
echo "Replacing MobileBy with AppiumBy..."
for file in $(cat java_files.txt); do
    sed -i 's/import io.appium.java_client.MobileBy;/import io.appium.java_client.AppiumBy;/g' "$file"
    sed -i 's/MobileBy\./AppiumBy\./g' "$file"
done

# 5. Replace TouchAction imports
echo "Removing TouchAction imports..."
for file in $(cat java_files.txt); do
    sed -i '/import io.appium.java_client.TouchAction;/d' "$file"
    sed -i '/import org.openqa.selenium.interactions.touch.TouchActions;/d' "$file"
done

# 6. Update URL endpoints (remove /wd/hub)
echo "Updating Appium server URLs..."
for file in $(cat java_files.txt); do
    sed -i 's|http://0.0.0.0:4723/wd/hub|http://0.0.0.0:4723/|g' "$file"
    sed -i 's|http://127.0.0.1:4723/wd/hub|http://127.0.0.1:4723/|g' "$file"
    sed -i 's|http://localhost:4723/wd/hub|http://localhost:4723/|g' "$file"
done

# 7. Update capability setting methods
echo "Updating capability setting methods..."
for file in $(cat java_files.txt); do
    sed -i 's/capabilities\.setCapability("platformName"/options.setPlatformName(/g' "$file"
    sed -i 's/capabilities\.setCapability("deviceName"/options.setDeviceName(/g' "$file"
    sed -i 's/capabilities\.setCapability("app"/options.setApp(/g' "$file"
    sed -i 's/capabilities\.setCapability("automationName"/options.setAutomationName(/g' "$file"
    sed -i 's/capabilities\.setCapability("platformVersion"/options.setPlatformVersion(/g' "$file"
    sed -i 's/capabilities\.setCapability("udid"/options.setUdid(/g' "$file"
    sed -i 's/capabilities\.setCapability("bundleId"/options.setBundleId(/g' "$file"
    sed -i 's/capabilities\.setCapability("appPackage"/options.setAppPackage(/g' "$file"
    sed -i 's/capabilities\.setCapability("appActivity"/options.setAppActivity(/g' "$file"
done

# 8. Update driver instantiation
echo "Updating driver instantiation..."
for file in $(cat java_files.txt); do
    sed -i 's/new AndroidDriver<MobileElement>(new URL/new AndroidDriver(new URL/g' "$file"
    sed -i 's/new IOSDriver(new URL.*capabilities)/new IOSDriver(new URL("http:\/\/0.0.0.0:4723\/"), options)/g' "$file"
    sed -i 's/, capabilities)/, options)/g' "$file"
done

# 9. Add necessary imports for W3C Actions (will be added manually where TouchAction was used)
echo "Adding W3C Actions imports where needed..."
for file in $(grep -l "TouchAction" $(cat java_files.txt) 2>/dev/null); do
    if ! grep -q "import org.openqa.selenium.interactions.PointerInput;" "$file"; then
        sed -i '1i import org.openqa.selenium.interactions.PointerInput;\nimport org.openqa.selenium.interactions.Sequence;\nimport java.time.Duration;\nimport java.util.Arrays;' "$file"
    fi
done

# 10. Update Cucumber imports
echo "Updating Cucumber imports..."
for file in $(cat java_files.txt); do
    sed -i 's/import cucumber.api.java.en./import io.cucumber.java.en./g' "$file"
    sed -i 's/import cucumber.api.java./import io.cucumber.java./g' "$file"
    sed -i 's/import cucumber.api./import io.cucumber.java./g' "$file"
done

# Clean up
rm java_files.txt

echo "Bulk refactoring completed!"
echo "Manual review and fixes may be needed for:"
echo "1. TouchAction replacements with W3C Actions"
echo "2. Platform-specific capability adjustments"
echo "3. Any custom method calls that may have changed"
