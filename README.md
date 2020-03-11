**Pre-requisites:**
1. Appium should be installed & appium server should be running with latest version
2. WebRunnerAgent should be installed on iOS device through Appium inspector in order to execute scripts
3. Maven, Android SDK, Java(8+) should be installed
4. Configure Android SDK & Java path in appium client
5. Devices should be connected

**Structure:**

•	Platform details would be fetched from testng.xml file. Default settings are to execute on iOS & android platforms one by one. In case, the script is to be executed only on one device, respective block should be commented out. (Starting from <test> to </test>)

•	Device information would be fetched from /src/test/resources/config.properties file.


•	Following data should be updated for device information:

o	ANDROID_DEVICE_NAME=Name of the android device

o	ANDROID_PLATFORMVERSION=OS version for android device

o	ANDROID_APP_NAME=Name of apk file available at src/test/resources/installable location

o	IOS_UDID=UDID of iOS device

o	IOS_DEVICE_NAME=Name of iOS device

o	IOS_PLATFORMVERSION=iOS version

o	IOS_APP_NAME=Name of ipa file available at src/test/resources/installable location

o	In case of new application(.apk or .ipa files), files need to be replaced at src/test/resources/installable location


**Steps to run from terminal:**
1. Clone repo to local
2. Update required details as mentioned above
3. Open terminal
4. Go to the automation project directory path where pom.xml is present
5. Execute mvn clean test command

•	Jenkins job is scheduled to run at 12:00 AM everyday & whenever there are changes in GitHub repository
