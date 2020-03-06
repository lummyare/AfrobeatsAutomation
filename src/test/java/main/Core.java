package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Core
{
	public static AppiumDriver<MobileElement> driver;

	private static Logger Log = Logger.getLogger(Core.class);
	static Date date = new Date();
	static DateFormat dateFormat = new SimpleDateFormat("EEEE_dd_MMMM_HH");
	public static String newdate = dateFormat.format(date);
	List<String> fileList = new ArrayList<String>();
	public static final String OUTPUT_ZIP_FILE = System.getProperty("user.dir")
	+ File.separator + "results" + File.separator + "MyFile.zip";
	public static final String SOURCE_FOLDER = System.getProperty("user.dir")
	+ File.separator + "test-output" + File.separator + newdate;
	public static final String timeStamp = new SimpleDateFormat(
	"yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
	public static final String dataFilePath = System.getProperty("user.dir")
	+ File.separator + "data" + File.separator + "TestData.xls";
	public static final String REPORT_FOLDER = System.getProperty("user.dir")
	+ File.separator + "target" + File.separator + "surefire-reports"
	+ File.separator + "html" + File.separator + "index.html";

	// ************************************BASIC
	// FUNCTIONS*********************************

	/*
	 * Description: FInd & Click element by Xpath Created date: 1stDec2014
	 * Updated date: 1stDec2014
	 */
	public WebElement findWebElementByXpathAndClick(String xPathValue)
	{
		Log.info("Finding the Element with Xpath - " + xPathValue);
		Log.info(driver.findElement(By.xpath(xPathValue)).isDisplayed() + ""
		+ driver.findElement(By.xpath(xPathValue)).isEnabled());
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(
		30, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS);
		WebElement element = (WebElement) wait.until(ExpectedConditions
		.elementToBeClickable(By.xpath(xPathValue)));
		element.click();
		return element;
	}

	public void findWebElementByIDAndClick(WebElement element)
	{
		
		element.click();

	}

	public String getText(WebElement element)
	{
		
		String text = element.getText();
		return text;
	}

	/*
	 * Description: Find element by Xpath Created date: 1stDec2014 Updated date:
	 * 1stDec2014
	 */
	public WebElement findWebElementByXpath(String xPathValue)
	{
		Log.info("Finding the Element with Xpath - " + xPathValue);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(
		30, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS);
		WebElement element = (WebElement) wait.until(ExpectedConditions
		.visibilityOfElementLocated(By.xpath(xPathValue)));
		return element;
	}

	public WebElement findWebElementByID(String ID)
	{
		Log.info("Finding the Element with Xpath - " + ID);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(
		30, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS);
		WebElement element = (WebElement) wait.until(ExpectedConditions
		.visibilityOfElementLocated(By.id(ID)));
	
		return element;
	}

	/*
	 * Description: wait for element to be clickable Created date: 02th Oct 2017
	 */
	public void waitClickAble(String xPathValue)
	{
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathValue)));
	}

	/*
	 * Description: Waits for mentioned seconds Created date: 09th Oct 2017
	 */
	public static void wait(int i)
	{
		long wait = System.currentTimeMillis() + (i * 1000);
		while (System.currentTimeMillis() < wait);
	}

	public boolean checkElementDisplayed(WebElement element)
	{

		boolean visibility;
		try
		{
			visibility = element.isDisplayed();
			return visibility;
		}
		catch (Exception ex)
		{
			return false;
		}

	}

	// Read excel and store it in data[][] array

	// Description: To verify text of any WebElement
	public boolean Verify_Message(String MsgElem, String Msg)
	{
		try
		{
			Log.info("Finding the Element with xpath - " + MsgElem);
			WebElement LoginString = driver.findElement(By.xpath(MsgElem));
			boolean strtxt = LoginString.getText() == Msg;
			System.out.println(strtxt);
			Log.info(Msg);
			return strtxt;
		}
		catch (Exception e)
		{

			System.out.println(e.getMessage());
			return false;
		}
	}

	// Description: To take screenshot
	public void takeScreenShot(String fileName)
	{
		File scrFile = ((TakesScreenshot) driver)
		.getScreenshotAs(OutputType.FILE);
		try
		{
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")
			+ File.separator + "test-output" + File.separator + newdate
			+ File.separator + "Web" + File.separator + fileName));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static boolean zip(List<File> files, String filename)
	{
		File zipfile = new File(filename);
		// Create a buffer for reading the files
		byte[] buf = new byte[1024];
		try
		{
			// create the ZIP file
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
			zipfile));
			// compress the files
			for (int i = 0; i < files.size(); i++)
			{
				FileInputStream in = new FileInputStream(files.get(i)
				.getCanonicalPath());
				// add ZIP entry to output stream
				out.putNextEntry(new ZipEntry(files.get(i).getName()));
				// transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0)
				{
					out.write(buf, 0, len);
				}
				// complete the entry
				out.closeEntry();
				in.close();
			}
			// complete the ZIP file
			out.close();
			return true;
		}
		catch (IOException ex)
		{
			System.err.println(ex.getMessage());
			return false;
		}
		// return null;
	}

	public String getAttribute(WebElement by, String attribute)
	{
		try
		{

			String value = by.getAttribute(attribute);
			if (value != null)
				return value.trim();
			return value;
		}
		catch (Exception ex)
		{
			System.err.println(ex.getMessage());
			return null;
		}

	}

	public void isAlertPresent()
	{
		try
		{
			Alert alert = driver.switchTo().alert();
			// alert is present
			Log.info(alert.getText());
			alert.accept();
		}
		catch (NoAlertPresentException n)
		{
			// Alert isn't present
			return;
		}
	}

	public static int randInt()
	{

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		int min = 0, max = 1000;
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public void zipIt(String zipFile)
	{
		byte[] buffer = new byte[1024];
		try
		{

			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);

			System.out.println("Output to Zip : " + zipFile);

			for (String file : this.fileList)
			{

				System.out.println("File Added : " + file);
				ZipEntry ze = new ZipEntry(file);
				zos.putNextEntry(ze);

				FileInputStream in = new FileInputStream(SOURCE_FOLDER
				+ File.separator + file);

				int len;
				while ((len = in.read(buffer)) > 0)
				{
					zos.write(buffer, 0, len);
				}
				in.close();
			}

			zos.closeEntry();
			// remember close it
			zos.close();

			System.out.println("Done");
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * Traverse a directory and get all files, and add the file into fileList
	 * 
	 * @param node
	 *            file or directory
	 */
	public void generateFileList(File node)
	{
		// add file only
		if (node.isFile())
		{
			fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
		}

		if (node.isDirectory())
		{
			String[] subNote = node.list();
			for (String filename : subNote)
			{
				generateFileList(new File(node, filename));
			}
		}
	}

	/**
	 * Format the file path for zip
	 * 
	 * @param file
	 *            file path
	 * @return Formatted file path
	 */
	private String generateZipEntry(String file)
	{
		return file.substring(SOURCE_FOLDER.length() + 1, file.length());
	}

	public boolean checkAlertPresent() {
		try 
	    { 
	        driver.switchTo().alert(); 
	        return true;
	    }   
	    catch (NoAlertPresentException Ex) 
	    { 
	       return false;
	    }   
	}
	

	public boolean isAttribtuePresent(WebElement element, String attribute) {
	    Boolean result = false;
	    try {
	        String value = element.getAttribute(attribute);
	        if (value != null){
	            result = true;
	        }
	    } catch (Exception e) {}

	    return result;
	}
	public void scroll(String id)
	{
		try
		{
			WebElement elementToClick = (WebElement) ((AndroidDriver) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).resourceId(\"" + id + "\")).flingForward()");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void scrollNew(String id)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);
	}
}
