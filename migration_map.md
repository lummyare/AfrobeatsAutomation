# Appium 2.x Migration Map

## Key Changes Required

### 1. Dependencies Update
- Appium Java Client: 7.3.0 → 8.6.0+
- Selenium: 3.141.59 → 4.15.0+
- Cucumber: 1.2.5 → 7.14.0+
- TestNG: 6.9.9 → 7.8.0+

### 2. Class Replacements
| Old (Appium 1.x) | New (Appium 2.x) | Notes |
|------------------|------------------|-------|
| `MobileElement` | `WebElement` | Universal element interface |
| `AndroidDriver<MobileElement>` | `AndroidDriver` | No generic type needed |
| `IOSDriver<MobileElement>` | `IOSDriver` | No generic type needed |
| `AppiumDriver<MobileElement>` | `AppiumDriver` | No generic type needed |
| `DesiredCapabilities` | `UiAutomator2Options` / `XCUITestOptions` | Platform-specific options |
| `MobileBy` | `AppiumBy` | Updated locator strategies |
| `TouchAction` | W3C Actions API | Use PointerInput and Sequence |

### 3. Import Changes
```java
// Remove these imports
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.interactions.touch.TouchActions;

// Add these imports
import org.openqa.selenium.WebElement;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
```

### 4. Driver Initialization Changes
```java
// Old way
DesiredCapabilities capabilities = new DesiredCapabilities();
capabilities.setCapability("platformName", "Android");
driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

// New way
UiAutomator2Options options = new UiAutomator2Options();
options.setPlatformName("Android");
driver = new AndroidDriver(new URL("http://0.0.0.0:4723/"), options);
```

### 5. Element Finding Changes
```java
// Old way
MobileElement element = driver.findElement(MobileBy.id("elementId"));

// New way
WebElement element = driver.findElement(AppiumBy.id("elementId"));
```

### 6. Touch Actions Replacement
```java
// Old TouchAction
new TouchAction(driver).tap(PointOption.point(x, y)).perform();

// New W3C Actions
PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
Sequence tap = new Sequence(finger, 1);
tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
driver.perform(Arrays.asList(tap));
```
