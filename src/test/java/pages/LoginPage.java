package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.BaseClass;

public class LoginPage {

	@FindBy(xpath = "//button[@type='button' and text()='Login']")
	public WebElement loginBtn;

	@FindBy(xpath = "//div[text()='Both fields are required.']")
	public WebElement errMsg;

	public LoginPage() {
		PageFactory.initElements(BaseClass.driver, this);
	}
}
