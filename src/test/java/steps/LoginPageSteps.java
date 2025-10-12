package steps;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;
import utils.ConfigsReader;

public class LoginPageSteps extends CommonMethods {

	@Given("User navigate to login page")
	public void user_navigate_to_login_page() {

	}

	@When("User click on login button")
	public void user_click_on_login_button() {
		loginPage.clickLoginButton();
	}

	@Then("The error message {string} should be displayed")
	public void the_error_message_should_be_displayed(String string) {
		String actualErrorMessage = loginPage.getRequiredFieldsErrorMessage();
		Assert.assertEquals("The error message does NOT matches", string, actualErrorMessage);
	}

	@When("User enter username wrongUsername and password wrongPassword")
	public void user_enter_username_and_password() {
		loginPage.enterUsername(ConfigsReader.getProperty("wrongUsername"));
		loginPage.enterPassword(ConfigsReader.getProperty("wrongPassword"));
	}

	@Then("User verify the error message {string} should be displayed")
	public void user_verify_the_error_message_should_be_displayed(String string) {
		String invalidMessage = loginPage.getInvalidLoginMessage();
		Assert.assertEquals("The invalid error message does NOT matches", string, invalidMessage);
	}

	@When("User enter valid username {string} and password {string}")
	public void user_enter_valid_username_and_password(String username, String password) {
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}

	@When("User verify welcome message for user")
	public void user_verify_welcome_message_for_user() {
		String expectedName = "user";
		String actualName = loginPage.getWelcomeMessageText();
		Assert.assertTrue("Welcome message does NOT matches!!", actualName.contains(expectedName));
	}

	@Then("User verify the User Dashboard is shown")
	public void user_verify_the_user_dashboard_is_shown() {
		String actual = loginPage.dashboard.getText();
		String expected = "User Dashboard";
		Assert.assertEquals("The messages does NOT matches", actual, expected);
	}

	@When("User enter valid admins username {string} and password {string}")
	public void user_enter_valid_admins_username_and_password(String adminName, String adminPassword) {
		loginPage.enterUsername(adminName);
		loginPage.enterPassword(adminPassword);
	}

	@When("User verify welcome message for admin")
	public void user_verify_welcome_message_for_admin() {
		String expectedName = "admin";
		String actualName = loginPage.getWelcomeMessageText();
		Assert.assertTrue("Welcome message does NOT matches!!", actualName.contains(expectedName));
	}

	@Then("User verify the Admin Dashboard is shown")
	public void user_verify_the_admin_dashboard_is_shown() {
		String actual = loginPage.dashboard.getText();
		String expected = "Admin Dashboard";
		Assert.assertEquals("The messages does NOT matches", actual, expected);
	}

	@Given("User login with valid credentials")
	public void user_login_with_valid_credentials() {
		loginPage.loginWithCredentials("user", "user123");
	}

	@When("User click on logout button")
	public void user_click_on_logout_button() {
		loginPage.clickLoginButton();
	}

	@When("User verify login form is displayed")
	public void user_verify_login_form_is_displayed() {
		Assert.assertTrue("Login form is NOT displayed!", loginPage.isLogoutButtonDisplayed());
	}

	@Then("User verify fields are reset to empty")
	public void user_verify_fields_are_reset_to_empty() {
		String inputValue = loginPage.username.getAttribute("value");
		Assert.assertTrue("Inputs are not EMPTY", inputValue.isEmpty());
	}
}
