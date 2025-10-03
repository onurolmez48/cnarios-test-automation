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
		click(loginPage.loginBtn);
	}

	@Then("The error message {string} should be displayed")
	public void the_error_message_should_be_displayed(String string) {

		String errMessage = loginPage.errMsg.getText();
		Assert.assertEquals("The error message does NOT matches", string, errMessage);
	}

	@When("User enter username {string} and password {string}")
	public void user_enter_username_and_password(String username, String password) {
		sendText(loginPage.username, username);
		sendText(loginPage.password, password);
	}

	@Then("User verify the error message {string} should be displayed")
	public void user_verify_the_error_message_should_be_displayed(String string) {
		String invalidMessage = loginPage.invalidMsg.getText();
		Assert.assertEquals("The invalid error message does NOT matches", string, invalidMessage);
	}

	@When("User enter valid username {string} and password {string}")
	public void user_enter_valid_username_and_password(String username, String password) {
		sendText(loginPage.username, username);
		sendText(loginPage.password, password);
	}

	@When("User verify welcome message for user")
	public void user_verify_welcome_message_for_user() {
		String actual = loginPage.welcomeMsg.getText();
		if (actual.contains("user")) {
			System.out.println("welcome USER");
		}
	}

	@Then("User verify the User Dashboard is shown")
	public void user_verify_the_user_dashboard_is_shown() {
		String actual = loginPage.dashboard.getText();
		String expected = "User Dashboard";
		Assert.assertEquals("The messages does NOT matches", actual, expected);
	}

	@When("User enter valid admins username {string} and password {string}")
	public void user_enter_valid_admins_username_and_password(String username, String password) {
		sendText(loginPage.username, username);
		sendText(loginPage.password, password);
	}

	@When("User verify welcome message for admin")
	public void user_verify_welcome_message_for_admin() {
		String actual = loginPage.welcomeMsg.getText();
		if (actual.contains("uadmin")) {
			System.out.println("welcome ADMIN");
		}
	}

	@Then("User verify the Admin Dashboard is shown")
	public void user_verify_the_admin_dashboard_is_shown() {
		String actual = loginPage.dashboard.getText();
		String expected = "Admin Dashboard";
		Assert.assertEquals("The messages does NOT matches", actual, expected);
	}

	@Given("User login with valid credentials")
	public void user_login_with_valid_credentials() {
		sendText(loginPage.username, "user");
		sendText(loginPage.password, "user123");
		click(loginPage.loginBtn);
	}

	@When("User click on logout button")
	public void user_click_on_logout_button() {
		click(loginPage.logoutBtn);
	}

	@When("User verify login form is displayed")
	public void user_verify_login_form_is_displayed() {
		Assert.assertTrue("Login form is NOT displayed!", loginPage.loginBtn.isDisplayed());
		if (loginPage.loginBtn.isDisplayed()) {
			System.out.println("Login Button is Displayed!");
		} else {
			driver.quit();
		}
	}

	@Then("User verify fields are reset to empty")
	public void user_verify_fields_are_reset_to_empty() {
		String inputValue = loginPage.username.getAttribute("value");
		System.out.println(inputValue);
		Assert.assertTrue("Inputs are not EMPTY", inputValue.isEmpty());
		if (inputValue.isEmpty()) {
			System.out.println("Input fields are empty");
		}
	}
}
