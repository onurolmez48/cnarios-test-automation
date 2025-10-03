package steps;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;

public class LoginPageSteps extends CommonMethods {

	@Given("User navigate to login page")
	public void user_navigate_to_login_page() {
		setUp();
	}

	@When("User click on login button")
	public void user_click_on_login_button() {
		click(loginPage.loginBtn);
	}

	@Then("The error message {string} should be displayed")
	public void the_error_message_should_be_displayed(String string) {

		String errMessage = loginPage.errMsg.getText();
		Assert.assertEquals("The error message does NOT matches", string, errMessage);

		tearDown();
	}
}
