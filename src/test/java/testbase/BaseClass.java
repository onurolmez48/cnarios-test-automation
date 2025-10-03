package testbase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.ConfigsReader;
import utils.Constants;

public class BaseClass {

	public static WebDriver driver;

	/*
	 * This method opens the browser and navigates to the url in configs file
	 */
	public static void setUp() {
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
		String browser = ConfigsReader.getProperty("browser");

		switch (browser.toLowerCase()) {
		case "chrome": {
			driver = new ChromeDriver();
			break;
		}
		case "firefox": {
			driver = new FirefoxDriver();
			break;
		}
		case "edge": {
			driver = new EdgeDriver();
			break;
		}
		default:
			break;
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME));
		String url = ConfigsReader.getProperty("url");
		driver.get(url);

		PageInitializer.initialize();
	}

	/*
	 * This methods closes the browser
	 */
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}