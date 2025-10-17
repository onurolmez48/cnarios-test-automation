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
	private WebElement salutationInput;

	@FindBy(css = "input[name='firstName']")
	private WebElement firstNameInput;

	@FindBy(css = "input[name='lastName']")
	private WebElement lastNameInput;

	@FindBy(css = "input[name='mobile']")
	private WebElement mobileInput;

	@FindBy(css = "input[name='email']")
	private WebElement emailInput;

	@FindBy(xpath = "//label[contains(., 'Known Languages')]/following-sibling::label")
	private List<WebElement> languages;

	@FindBy(xpath = "//div[@role='radiogroup']//label")
	private List<WebElement> genders;

	@FindBy(xpath = "//div[@role='radiogroup']//label[contains(., 'Female')]")
	private WebElement femaleGender;

	@FindBy(xpath = "//div[@role='radiogroup']//label[contains(., 'Male')]")
	private WebElement maleGender;

	@FindBy(xpath = "//div[@role='radiogroup']//label[contains(., 'Do Not Disclose')]")
	private WebElement doNotDiscloseGender;

	@FindBy(xpath = "//div[@role='radiogroup']//label[contains(., 'Other')]")
	private WebElement otherGender;

	@FindBy(xpath = "//input[@type='file']")
	private WebElement resumeUploadInput;

	@FindBy(xpath = "//span[contains(., 'Add a Skill')]/ancestor::div[contains(@class, 'MuiInputBase-root')]//input")
	private WebElement addToSkill;

	@FindBy(xpath = "//div[@id='mui-component-select-jobRoles']")
	private WebElement jobRolesInput;

	@FindBy(xpath = "//ul[@role='listbox']//li")
	private List<WebElement> jobRoles;

	@FindBy(xpath = "//span[contains(@class, 'MuiSlider-thumb')]")
	private WebElement ratingSliderThumb;

	@FindBy(css = ".MuiSlider-rail")
	private WebElement ratingSliderRail;

	@FindBy(xpath = "//input[@name='availableDate']")
	private WebElement availableDate;

	@FindBy(xpath = "//input[@name='availableTime']")
	private WebElement availableTime;

	@FindBy(xpath = "//label[.//input[@name='termsAccepted']]")
	private WebElement termsCheckBox;

	@FindBy(xpath = "//button[text()='Submit']")
	private WebElement submitButton;

	@FindBy(xpath = "//button[text()='Download']")
	private WebElement downloadButton;

	@FindBy(xpath = "//button[text()='Clear']")
	private WebElement clearButton;

	@FindBy(css = "div.MuiAlert-colorSuccess .MuiAlert-message")
	private WebElement successMessage;

	@FindBy(xpath = "//input[@name='rating' and @type='range']")
	private WebElement ratingInput;

	@FindBy(xpath = "//p[text()='Resume:']/following-sibling::a")
	private WebElement resumeText;

	@FindBy(css = "p.Mui-error")
	private WebElement invalidEmailText;

	@FindBy(css = "div.MuiAlert-colorError .MuiAlert-message")
	private WebElement pdfErrorText;

	@FindBy(css = ".MuiChip-label")
	private WebElement skillChip;

	@FindBy(css = ".MuiChip-deleteIcon")
	private WebElement deleteChip;

	@FindBy(css = "div[role='alert'].MuiAlert-colorSuccess .MuiAlert-message")
	private WebElement submitSuccessMessage;

	@FindBy(xpath = "//button[text()='Preview']")
	private WebElement previewButton;

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

	public String getFirstNameValue() {
		return firstNameInput.getAttribute("value");
	}

	public String getLastNameValue() {
		return lastNameInput.getAttribute("value");
	}

	public String getEmailValue() {
		return emailInput.getAttribute("value");
	}

	public String getMobileValue() {
		return mobileInput.getAttribute("value");
	}

	public String getSalutationValue() {
		return salutationInput.getAttribute("value");
	}

	public List<WebElement> getLanguageCheckboxes() {
		return languages;
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

	public boolean isRatingSliderDisplayed() {
		try {
			waitForVisibility(ratingSliderThumb);
			return ratingSliderThumb.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isTermCheckboxDisplayed() {
		try {
			waitForVisibility(termsCheckBox);
			return termsCheckBox.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isTermCheckboxSelected() {
		try {
			waitForVisibility(termsCheckBox);
			return termsCheckBox.isSelected();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isSubmitBtnDisplayed() {
		try {
			waitForVisibility(submitButton);
			return submitButton.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
