package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.BaseClass;

public class LoginPage {

	@FindBy(xpath = "//button[@type='button' and text()='Login']")
	public WebElement loginBtn;

	@FindBy(xpath = "//button[@type='button' and text()='Logout']")
	public WebElement logoutBtn;

	@FindBy(xpath = "//div[text()='Both fields are required.']")
	public WebElement errMsg;

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

	public LoginPage() {
		PageFactory.initElements(BaseClass.driver, this);
	}
}
