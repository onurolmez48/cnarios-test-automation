package steps;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;

public class LoginPageSteps extends CommonMethods {

	@Given("User navigate to login page")
	public void user_navigate_to_login_page() {

	}

	@When("User click on login button")
	public void user_click_on_login_button() {
		click(LoginPage.loginBtn);
	}

	@Then("The error message {string} should be displayed")
	public void the_error_message_should_be_displayed(String string) {

		String errMessage = LoginPage.errMsg.getText();
		Assert.assertEquals("The error message does NOT matches", string, errMessage);
	}

	@When("User enter username {string} and password {string}")
	public void user_enter_username_and_password(String username, String password) {
		sendText(LoginPage.username, username);
		sendText(LoginPage.password, password);
	}

	@Then("User verify the error message {string} should be displayed")
	public void user_verify_the_error_message_should_be_displayed(String string) {
		String invalidMessage = LoginPage.invalidMsg.getText();
		Assert.assertEquals("The invalid error message does NOT matches", string, invalidMessage);
	}
}
