package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testbase.BaseClass;
import utils.CommonMethods;
import utils.ConfigsReader;

public class JobApplicationFormPage extends CommonMethods {

	@FindBy(css = "input[name='salutation']")
	public WebElement salutationInput;

	@FindBy(css = "input[name='firstName']")
	public WebElement firstNameInput;

	@FindBy(css = "input[name='lastName']")
	public WebElement lastNameInput;

	@FindBy(css = "input[name='mobile']")
	public WebElement mobileInput;

	@FindBy(css = "input[name='email']")
	public WebElement emailInput;

	@FindBy(xpath = "//label[contains(., 'Known Languages')]/following-sibling::label")
	public List<WebElement> languages;

	@FindBy(xpath = "//div[@role='radiogroup']//label")
	public List<WebElement> genders;

	@FindBy(xpath = "//div[@role='radiogroup']//label[contains(., 'Female')]")
	public WebElement femaleGender;

	@FindBy(xpath = "//div[@role='radiogroup']//label[contains(., 'Male')]")
	public WebElement maleGender;

	@FindBy(xpath = "//div[@role='radiogroup']//label[contains(., 'Do Not Disclose')]")
	public WebElement doNotDiscloseGender;

	@FindBy(xpath = "//div[@role='radiogroup']//label[contains(., 'Other')]")
	public WebElement otherGender;

	@FindBy(xpath = "//input[@type='file']")
	public WebElement resumeUploadInput;

	@FindBy(xpath = "//span[contains(., 'Add a Skill')]/ancestor::div[contains(@class, 'MuiInputBase-root')]//input")
	public WebElement addToSkill;

	@FindBy(xpath = "//div[@id='mui-component-select-jobRoles']")
	public WebElement jobRolesInput;

	@FindBy(xpath = "//ul[@role='listbox']//li")
	public List<WebElement> jobRoles;

	@FindBy(xpath = "//span[contains(@class, 'MuiSlider-thumb')]")
	public WebElement ratingSliderThumb;

	@FindBy(css = ".MuiSlider-rail")
	public WebElement ratingSliderRail;

	@FindBy(xpath = "//input[@name='availableDate']")
	public WebElement availableDate;

	@FindBy(xpath = "//input[@name='availableTime']")
	public WebElement availableTime;

	@FindBy(xpath = "//label[.//input[@name='termsAccepted']]")
	public WebElement termsCheckBox;

	@FindBy(xpath = "//button[text()='Submit']")
	public WebElement submitButton;

	@FindBy(xpath = "//button[text()='Download']")
	public WebElement downloadButton;

	@FindBy(xpath = "//button[text()='Clear']")
	public WebElement clearButton;

	@FindBy(css = "div.MuiAlert-colorSuccess .MuiAlert-message")
	public WebElement successMessage;

	@FindBy(xpath = "//input[@name='rating' and @type='range']")
	private WebElement ratingInput;

	@FindBy(xpath = "//p[text()='Resume:']/following-sibling::a")
	public WebElement resumeText;

	@FindBy(css = "p.Mui-error")
	public WebElement invalidEmailText;

	@FindBy(css = "div.MuiAlert-colorError .MuiAlert-message")
	public WebElement pdfErrorText;

	@FindBy(css = ".MuiChip-label")
	public WebElement skillChip;

	@FindBy(css = ".MuiChip-deleteIcon")
	public WebElement deleteChip;

	@FindBy(css = "div[role='alert'].MuiAlert-colorSuccess .MuiAlert-message")
	public WebElement submitSuccessMessage;

	@FindBy(xpath = "//button[text()='Preview']")
	public WebElement previewButton;

	// Constructor
	public JobApplicationFormPage() {
		PageFactory.initElements(BaseClass.driver, this);
	}

	// Methods

	public void navigateUrl() {
		String url = ConfigsReader.getProperty("baseUrl") + ConfigsReader.getProperty("jobAppUrl");
		driver.get(url);
	}

	public void validPersonalDetails(String salutation, String firstName, String lastName, String email,
			String mobile) {
		sendText(salutationInput, salutation);
		sendText(firstNameInput, firstName);
		sendText(lastNameInput, lastName);
		sendText(emailInput, email);
		sendText(mobileInput, mobile);
	}

	public void enterSalutation(String salutation) {
		waitForClickability(salutationInput);
		sendText(salutationInput, salutation);
	}

	public void enterFirstName(String firstName) {
		waitForClickability(firstNameInput);
		sendText(firstNameInput, firstName);
	}

	public void enterLastName(String lastName) {
		waitForClickability(lastNameInput);
		sendText(lastNameInput, lastName);
	}

	public void enterEmail(String email) {
		waitForClickability(emailInput);
		sendText(emailInput, email);
	}

	public void enterMobile(String mobile) {
		waitForClickability(mobileInput);
		sendText(mobileInput, mobile);
	}

	public void clickResume() {
		waitForClickability(resumeUploadInput);
		click(resumeUploadInput);
	}

	public void clickJobRolesDD() {
		waitForClickability(jobRolesInput);
		click(jobRolesInput);
	}

	public void clickTerms() {
		waitForClickability(termsCheckBox);
		click(termsCheckBox);
	}

	public void clickSubmit() {
		waitForClickability(submitButton);
		click(submitButton);
	}

	public void clickDeleteChip() {
		waitForVisibility(deleteChip);
		waitForClickability(deleteChip);
		click(deleteChip);
	}

	public void clickDownloadButton() {
		waitForClickability(downloadButton);
		click(downloadButton);
	}

	public void clickClearButton() {
		waitForClickability(clearButton);
		click(clearButton);
	}

	public void clickPreviewButton() {
		waitForClickability(previewButton);
		click(previewButton);
	}

	public void selectGender(String genderText) {
		String genderLocatorXPath = "//div[.//label[contains(., 'Gender*')]]//label[contains(., '" + genderText + "')]";
		WebElement genderOption = driver.findElement(By.xpath(genderLocatorXPath));
		click(genderOption);
	}

	public void selectLanguage(String languageText) {
		String languageLocatorXPath = "//div[.//label[contains(., 'Known Languages')]]//label[contains(., '"
				+ languageText + "')]";
		WebElement languageOption = driver.findElement(By.xpath(languageLocatorXPath));
		click(languageOption);
	}

	public void uploadResume(String fileName) {
		String fullPath = System.getProperty("user.dir") + "/src/test/resources/files/" + fileName;
		sendText(resumeUploadInput, fullPath);
	}

	public void addSkill(String skill) {
		waitForClickability(addToSkill);
		sendText(addToSkill, skill);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	}

	public void selectJobRole(List<String> rolesToSelect) {
		click(jobRolesInput);
		for (String roleToSelect : rolesToSelect) {
			for (WebElement availableRole : jobRoles) {
				if (availableRole.getText().trim().equalsIgnoreCase(roleToSelect.trim())) {
					click(availableRole);
					break;
				}
			}
		}
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ESCAPE).perform();
		waitForClickability(jobRolesInput);
	}

	public void setRatingSlider(int targetRating) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='rating' and @type='range']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		String script = "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;"
				+ "nativeInputValueSetter.call(arguments[0], arguments[1]);"
				+ "var event = new Event('input', { bubbles: true });" + "arguments[0].dispatchEvent(event);";

		js.executeScript(script, ratingInput, String.valueOf(targetRating));

	}

	public void selectDate(String date) {
		waitForVisibility(availableDate);
		sendText(availableDate, date);
	}

	public void selectTime(String hour) {
		waitForVisibility(availableTime);
		sendText(availableTime, hour);
	}

	public String getSuccessText() {
		return successMessage.getText();
	}

	public String getResumeText() {
		return resumeText.getText();
	}

	public String getInvalidEmailText() {
		return invalidEmailText.getText();
	}

	public String getInvalidResumeText() {
		return pdfErrorText.getText();
	}

	public String getSkillChipText() {
		return skillChip.getText();
	}

	public String getSubmittedSuccessText() {
		return submitSuccessMessage.getText();
	}

	public void selectJobRole(String roles) {
		waitForClickability(jobRolesInput);
		click(jobRolesInput);
		WebElement role = driver.findElement(By.xpath("//li[contains(text(),'" + roles + "')]"));
		click(role);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ESCAPE).perform();
		waitForClickability(jobRolesInput);
	}

}
