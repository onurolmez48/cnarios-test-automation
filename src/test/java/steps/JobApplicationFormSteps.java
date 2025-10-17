package steps;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import messages.utils.JobApplicationMessages;
import utils.CommonMethods;

public class JobApplicationFormSteps extends CommonMethods {

	String downloadsFolderPath;
	String expectedFileName;
	String expectedDownloadFilePath;

	@Given("Navigate to url")
	public void navigate_to_url() {
		jobApplicationPage.navigateUrl();
	}

	@When("User enter valid input forms")
	public void user_enter_valid_input_forms(DataTable dataTable) {
		Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
		jobApplicationPage.validPersonalDetails(dataMap.get("Salutation"), dataMap.get("First Name"),
				dataMap.get("Last Name"), dataMap.get("Email"), dataMap.get("Mobile"));
	}

	@When("User select gender and languagess")
	public void user_select_gender_and_languagess(DataTable dataTable) {
		Map<String, String> dataMap = dataTable.asMap(String.class, String.class);

		String genderToSelect = dataMap.get("Gender");
		String languageToSelect = dataMap.get("Language");

		if (genderToSelect != null && !genderToSelect.isEmpty()) {
			jobApplicationPage.selectGender(genderToSelect);
		} else {
			Assert.fail("Gender fail!");
		}

		if (languageToSelect != null && !languageToSelect.isEmpty()) {
			jobApplicationPage.selectLanguage(languageToSelect);
		} else {
			Assert.fail("Language fail!");
		}
	}

	@When("User upload vaild .pdf {string} resume")
	public void user_upload_vaild_pdf_resume(String pdfNames) {
		jobApplicationPage.uploadResume(pdfNames);
		Assert.assertEquals("Resume messages does NOT equals", pdfNames, jobApplicationPage.getResumeText());
	}

	@When("User add skills using Enter key")
	public void user_add_skills_using_enter_key(DataTable dataTable) {
		List<String> skillsList = dataTable.asList(String.class);
		for (String skill : skillsList) {
			jobApplicationPage.addSkill(skill);
		}
	}

	@When("User Select multiple job roles")
	public void user_select_multiple_job_roles(DataTable dataTable) {
		List<String> rolesToSelect = dataTable.asList(String.class);
		jobApplicationPage.selectJobRole(rolesToSelect);
	}

	@When("User set rating slider to {int}")
	public void user_set_rating_slider_to(Integer rating) {
		jobApplicationPage.setRatingSlider(rating);
		Assert.assertTrue("Slider is NOT displayed!!", jobApplicationPage.isRatingSliderDisplayed());
	}

	@When("User pick valid date {string} and time {string}")
	public void user_pick_valid_date_and_time(String date, String hour) {
		jobApplicationPage.selectDate(date);
		jobApplicationPage.selectTime(hour);
	}

	@When("User Check I accept terms checkbox")
	public void user_check_i_accept_terms_checkbox() {
		jobApplicationPage.clickTerms();
		Assert.assertTrue("Terms checkbos is NOT displayed!!", jobApplicationPage.isTermCheckboxDisplayed());
	}

	@When("User click Submit")
	public void user_click_submit() {
		jobApplicationPage.clickSubmit();
		Assert.assertTrue("Submit button is NOT displayed!!", jobApplicationPage.isSubmitBtnDisplayed());
	}

	@Then("User verify success snackbar message")
	public void user_verify_success_snackbar_message() {
		Assert.assertEquals("Success message is NOT displayed !!", jobApplicationPage.getSuccessText(),
				JobApplicationMessages.SUBMITTED_MESSAGE);
	}

	@When("User enter invalid email {string}")
	public void user_enter_invalid_email(String string) {
		jobApplicationPage.enterEmail(string);
	}

	@When("User fill remaining required fields")
	public void user_fill_remaining_required_fields() {
		jobApplicationPage.enterFirstName("Onur");
		jobApplicationPage.enterLastName("Olmez");
		jobApplicationPage.enterMobile("1231231231");
		jobApplicationPage.enterSalutation("Mr.");
		jobApplicationPage.selectGender("Male");
		jobApplicationPage.selectLanguage("Hindi");
		jobApplicationPage.addSkill("java");
		jobApplicationPage.selectJobRole("QA");
		jobApplicationPage.uploadResume("Onur OLMEZ - CV.pdf");
		jobApplicationPage.setRatingSlider(4);
		jobApplicationPage.selectDate("12-12-2024");
		jobApplicationPage.selectTime("14:30");
	}

	@When("User try submit the form")
	public void user_try_submit_the_form() {
		jobApplicationPage.clickSubmit();
	}

	@Then("User verify email error helper text is shown")
	public void user_verify_email_error_helper_text_is_shown() {
		Assert.assertEquals("The invalid messages does not mathces", jobApplicationPage.getInvalidEmailText(),
				JobApplicationMessages.ENTER_VALID_EMAIL);
		scrollUp(400);
	}

	@When("User select a .jpg {string} file from system upload")
	public void user_select_a_jpg_img_jpg_file_from_system_upload(String jpg) {
		jobApplicationPage.uploadResume(jpg);
	}

	@Then("User verify snackbar shows error message")
	public void user_verify_snackbar_shows_error_message() {
		Assert.assertEquals("The error messages is NOT matches!!", jobApplicationPage.getInvalidResumeText(),
				JobApplicationMessages.PDF_ERR);
	}

	@When("User type {string} in skills input and press enter")
	public void user_type_in_skills_input_and_press_enter(String skill) {
		jobApplicationPage.addSkill(skill);
	}

	@Then("User verify {string} chip appears")
	public void user_verify_chip_appears(String string) {
		Assert.assertEquals("The chips does NOT matches", jobApplicationPage.getSkillChipText(), string);
	}

	@When("User delete chip")
	public void user_delete_chip() {
		jobApplicationPage.clickDeleteChip();
	}

	@Then("User verify chip is removed")
	public void user_verify_chip_is_removed() {

	}

	@When("User Fill all fields correctly except leave terms unchecked")
	public void user_fill_all_fields_correctly_except_leave_terms_unchecked() {
		jobApplicationPage.validPersonalDetails("Mr", "Onur", "Olmez", "onur@gmail.com", "1231231231");
		jobApplicationPage.selectGender("Male");
		jobApplicationPage.selectLanguage("Hindi");
		jobApplicationPage.uploadResume("Onur OLMEZ - CV.pdf");
		jobApplicationPage.addSkill("java");
		jobApplicationPage.selectJobRole("QA");
		jobApplicationPage.setRatingSlider(4);
		jobApplicationPage.selectDate("12-12-2024");
		jobApplicationPage.selectTime("14:30");
	}

	@Then("User Verify error snackbar shows {string}")
	public void user_verify_error_snackbar_shows(String error) {
		Assert.assertEquals("The error messages does not matches!!", jobApplicationPage.getSubmittedSuccessText(),
				error);
	}

	@When("User fill the firstname {string} and lastname {string} fields")
	public void user_fill_the_firstname_and_lastname_fields(String firstName, String lastName) {
		jobApplicationPage.enterFirstName(firstName);
		jobApplicationPage.enterLastName(lastName);
	}

	@Then("User click download button and verify file download as firstname.lastname.json")
	public void user_verify_file_download_as_firstname_lastname_json() {
		downloadsFolderPath = getDownloadsFolderPath();
		expectedFileName = getExpectedFileName();
		expectedDownloadFilePath = getExpectedDownloadFilePath();
		deleteIfExists(expectedDownloadFilePath);
		jobApplicationPage.clickDownloadButton();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.get("chrome://downloads");
		verifyFileIsDownloaded(downloadsFolderPath, expectedFileName, 30);
	}

	@When("User Fill some fields in the form")
	public void user_fill_some_fields_in_the_form() {
		jobApplicationPage.validPersonalDetails("Mr", "Onur", "Olmez", "onurolmez@gmail.com", "1231231231");
		jobApplicationPage.selectLanguage("English");
		jobApplicationPage.clickTerms();
		jobApplicationPage.selectGender("Female");
		jobApplicationPage.addSkill("Java");
	}

	@When("User click clear button")
	public void user_click_clear_button() {
		jobApplicationPage.clickClearButton();
	}

	@Then("User verify all fields reset to defaults")
	public void user_verify_all_fields_reset_to_defaults() {
		Assert.assertEquals(jobApplicationPage.getFirstNameValue(), "");
		Assert.assertEquals(jobApplicationPage.getLastNameValue(), "");
		Assert.assertEquals(jobApplicationPage.getEmailValue(), "");
		Assert.assertEquals(jobApplicationPage.getMobileValue(), "");
		Assert.assertEquals(jobApplicationPage.getSalutationValue(), "");
		for (WebElement cb : jobApplicationPage.getLanguageCheckboxes()) {
			Assert.assertFalse("Checkboxes still select!!!", cb.isSelected());
		}
		Assert.assertFalse("Terms checkboxes still select!!", jobApplicationPage.isTermCheckboxSelected());
	}

	@When("User click Preview button")
	public void user_click_preview_button() {
		jobApplicationPage.clickPreviewButton();
	}

	@Then("User verify JSON preview matches entered data")
	public void user_verify_json_preview_matches_entered_data() {
		System.out.println("....");
	}

}
