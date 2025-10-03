package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.BaseClass;

public class LoginPageElements {

	@FindBy(xpath = "//button[@type='button' and text()='Login']")
	public WebElement loginBtn;

	@FindBy(xpath = "//div[text()='Both fields are required.']")
	public WebElement errMsg;

	@FindBy(xpath = "//input[@type='text']")
	public WebElement username;

	@FindBy(xpath = "//input[@type='password']")
	public WebElement password;

	@FindBy(xpath = "//div[text()='Invalid username or password.']")
	public WebElement invalidMsg;

	public LoginPageElements() {
		PageFactory.initElements(BaseClass.driver, this);
	}
}
