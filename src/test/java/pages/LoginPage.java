package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.BaseClass;
import utils.CommonMethods;
import utils.ConfigsReader;

public class LoginPage extends CommonMethods {

	// Elements
	@FindBy(xpath = "//button[@type='button' and text()='Login']")
	public WebElement loginBtn;

	@FindBy(xpath = "//button[@type='button' and text()='Logout']")
	public WebElement logoutBtn;

	@FindBy(xpath = "//div[text()='Both fields are required.']")
	public WebElement emptyValidMsg;

	@FindBy(xpath = "//input[@type='text']")
	public WebElement username;

	@FindBy(xpath = "//input[@type='password']")
	public WebElement password;

	@FindBy(xpath = "//div[text()='Invalid username or password.']")
	public WebElement invalidMsg;

	@FindBy(xpath = "//h5[@class='MuiTypography-root MuiTypography-h5 text-center font-bold css-jcahak']")
	public WebElement welcomeMsg;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 font-medium css-1o5u7u9']")
	public WebElement dashboard;

	// Constructor
	public LoginPage() {
		PageFactory.initElements(BaseClass.driver, this);
	}

	// Methods

	public void navigateUrl() {
		String url = ConfigsReader.getProperty("baseUrl") + ConfigsReader.getProperty("loginUrl");
		driver.get(url);
	}

	public void loginWithCredentials(String user, String pass) {
		enterUsername(user);
		enterPassword(pass);
		clickLoginButton();
	}

	public void enterUsername(String user) {
		username.clear();
		sendText(username, user);
	}

	public void enterPassword(String pass) {
		password.clear();
		sendText(password, pass);
	}

	public void clickLoginButton() {
		waitForClickability(loginBtn);
		loginBtn.click();
	}

	public String getInvalidLoginMessage() {
		return invalidMsg.getText();
	}

	public String getRequiredFieldsErrorMessage() {
		return emptyValidMsg.getText();
	}

	public String getWelcomeMessageText() {
		return welcomeMsg.getText();
	}

	public boolean isLogoutButtonDisplayed() {
		try {
			return logoutBtn.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
