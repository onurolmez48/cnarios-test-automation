package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testbase.PageInitializer;

public class CommonMethods extends PageInitializer {

	/**
	 * This method clears the text of a web element and sends the text parameter to
	 * it
	 * 
	 * @param element
	 * @param text
	 */
	public static void sendText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * This method pauses the execution for a certain amount of time
	 * 
	 * @param seconds
	 */
	public static void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method selects a value from a radio button list or checkbox list
	 * 
	 * @param elementList
	 * @param selectValue
	 */
	public static void clickRadioOrCheckbox(List<WebElement> elementList, String selectValue) {
		for (WebElement el : elementList) {
			String elementValue = el.getDomProperty("value").trim();

			if (elementValue.equals(selectValue) && el.isEnabled()) {
				el.click();
				break;
			}
		}
	}

	/**
	 * This method selects an item from a dropdown using the visible text
	 * 
	 * @param element
	 * @param visibleText
	 */
	public static void selectDropdown(WebElement element, String visibleText) {
		try {
			Select sl = new Select(element);
			sl.selectByVisibleText(visibleText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method selects an item from a dropdown using the index
	 * 
	 * @param element
	 * @param visibleText
	 */
	public static void selectDropdown(WebElement element, int index) {
		try {
			Select sl = new Select(element);
			sl.selectByIndex(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method accepts an alert if it is present in page. Otherwise the
	 * exception is handled: NoAlertPresentException
	 */
	public static void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method dismisses an alert if it is present in page. Otherwise the
	 * exception is handled: NoAlertPresentException
	 */
	public static void dismissAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method returns the text of an alert. If there is no alert in page, it
	 * returns null.
	 * 
	 * @return
	 */
	public static String getAlertText() {
		try {
			Alert alert = driver.switchTo().alert();
			return alert.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method sends the provided text to the alert. If not alert is present in
	 * page, the exception is handled.
	 * 
	 * @param text
	 */
	public static void sendAlertText(String text) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method switches to the frame whose id or name is provided. If the frame
	 * does not exist, the exception is handled.
	 * 
	 * @param nameOrId
	 */
	public static void switchToFrame(String nameOrId) {
		try {
			driver.switchTo().frame(nameOrId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method switches to the frame whose index is provided. If the frame does
	 * not exist, the exception is handled.
	 * 
	 * @param nameOrId
	 */
	public static void switchToFrame(int index) {
		try {
			driver.switchTo().frame(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method switches to the frame whose WebElement is provided. If the frame
	 * does not exist, the exception is handled.
	 * 
	 * @param nameOrId
	 */
	public static void switchToFrame(WebElement element) {
		try {
			driver.switchTo().frame(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method switches to the child window if exists
	 */
	public static void switchToChildWindow() {
		String mainWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(mainWindow)) {
				driver.switchTo().window(handle);
			}
		}
	}

	/**
	 * This method creates and returns a WebDriverWait object using the default wait
	 * time under Constants
	 * 
	 * @return
	 */
	public static WebDriverWait getWaitObject() {
		return new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT_TIME));
	}

	/**
	 * This method creates and returns a WebDriverWait object using the provided
	 * wait time
	 * 
	 * @return
	 */
	public static WebDriverWait getWaitObject(int secondsToWait) {
		return new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
	}

	/**
	 * This method waits until the element whose locater is provided is visible in
	 * page.
	 * 
	 * @param locator
	 * @return
	 */
	public static WebElement waitForVisibility(By locator) {
		return getWaitObject().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * This method will wait for an element to be visible.
	 * 
	 * @param element
	 * @return
	 */
	public static WebElement waitForVisibility(WebElement element) {
		return getWaitObject().until(ExpectedConditions.visibilityOf(element));
	}

	public static boolean waitForInvisibility(By locator) {

		return getWaitObject().until(ExpectedConditions.invisibilityOfElementLocated(locator));

	}

	/**
	 * This method waits until the provided element is clickable in page.
	 * 
	 * @param locator
	 * @return
	 */
	public static WebElement waitForClickability(WebElement element) {
		return getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method clicks on the provided web element after making sure it is
	 * clickable.
	 * 
	 * @param element
	 */
	public static void click(WebElement element) {
		waitForClickability(element);
		element.click();
	}

	/**
	 * This method will cast the driver to a JavascriptExecutor object
	 * 
	 * @return
	 */
	public static JavascriptExecutor getJSObject() {
		return (JavascriptExecutor) driver;
	}

	/**
	 * This method will click on an element using the JavascriptExecuter
	 * 
	 * @param element
	 */
	public static void jsClick(WebElement element) {
		getJSObject().executeScript("arguments[0].click()", element);
	}

	/**
	 * This method scrolls the page down using the pixels parameter
	 * 
	 * @param pixels
	 */
	public static void scrollDown(int pixels) {
		getJSObject().executeScript("window.scrollBy(0," + pixels + ")");
	}

	/**
	 * This method scrolls the page up using the pixels parameter
	 * 
	 * @param pixels
	 */
	public static void scrollUp(int pixels) {
		getJSObject().executeScript("window.scrollBy(0,-" + pixels + ")");
	}

	/**
	 * This method scrolls the page until the web element is visible in page
	 * 
	 * @param element
	 */
	public static void scrollToElement(WebElement element) {
		getJSObject().executeScript("arguments[0].scrollIntoView(true)", element);
	}

	/**
	 * This method selects the day from a list of days
	 * 
	 * @param days
	 * @param selectedDay
	 */
	public static void selectCalendarDate(List<WebElement> days, String selectedDay) {
		for (WebElement day : days) {
			if (day.getText().equals(selectedDay)) {
				if (day.isEnabled()) {
					click(day);
					break;
				} else {
					System.out.println("This day is not enabled!!!");
					break;
				}
			}
		}
	}

	/**
	 * This method will take a screenshot saves it in the screenshots folder with
	 * the given fileName.
	 * 
	 * @param fileName
	 */
	public static byte[] takeScreenshot(String fileName) {
		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = Constants.SCREENSHOT_FILEPATH + fileName + getTimeStamp() + ".png";

		try {
			Files.copy(source.toPath(), Paths.get(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] image = ts.getScreenshotAs(OutputType.BYTES);

		return image;
	}

	/**
	 * This method returns the current timestamp in the format of
	 * yyyy-MM-dd_HH-mm-ss
	 * 
	 * @return
	 */
	public static String getTimeStamp() {
		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

		return sdf.format(date);
	}

	/**
	 * This method clicks on the element in the list that matches the value
	 * 
	 * @param list
	 * @param value
	 */
	public static void clickOnElement(List<WebElement> list, String value) {
		for (WebElement option : list) {
			if (option.getText().equalsIgnoreCase(value)) {
				option.click();
				break;
			}
		}
	}

	/**
	 * This method perform move to element action(hovers over a element).
	 * 
	 * @param element
	 */
	public void hover(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * This method will check if the WebElement(field) from the List of WebElements
	 * is displayed and it will print the field name if it is displayed or not
	 * 
	 * @param field
	 * @param fieldName
	 */
	public void verifyFieldVisibility(List<WebElement> fields, String fieldName) {
		for (WebElement field : fields) {
			if (field.isDisplayed()) {
				System.out.println(fieldName + " is visible!");
				Assert.assertTrue(fieldName + " is visible!", true);
			} else {
				System.out.println(fieldName + " is NOT visible!");
				Assert.fail(fieldName + " is NOT visible!");
			}
		}
	}

	/**
	 * This method will clear all the text boxes and print the field name if it does
	 * cleared or not
	 * 
	 * @param element
	 * @param fieldName
	 */
	public void clearField(WebElement element, String fieldName) {
		try {
			element.clear();
			System.out.println(fieldName + " field has been cleared.");
		} catch (Exception e) {
			System.out.println("Unable to clear field: " + fieldName);
		}

	}

	public static ChromeOptions getChromeOptionsWithDownloadPath(String downloadPath) {
		Map<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		return options;
	}

	public static String getDownloadsFolderPath() {
		return System.getProperty("user.home") + "\\Downloads";
	}

	public static String getExpectedFileName() {
		String firstName = ConfigsReader.getProperty("firstname");
		String lastName = ConfigsReader.getProperty("lastname");
		return firstName + "." + lastName + ".json";
	}

	public static String getExpectedDownloadFilePath() {
		return getDownloadsFolderPath() + "\\" + getExpectedFileName();
	}

	public static boolean deleteIfExists(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			return file.delete();
		}
		return true;
	}

	public static void verifyFileIsDownloaded(String folderPath, String fileName, int timeoutSeconds) {
		File file = new File(folderPath + "\\" + fileName);
		int waited = 0;
		while (!file.exists() && waited < timeoutSeconds) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			waited++;
		}
		if (!file.exists()) {
			throw new AssertionError("File not downloaded: " + file.getAbsolutePath());
		}
	}

}