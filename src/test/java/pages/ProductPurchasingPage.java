package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.BaseClass;
import utils.CommonMethods;
import utils.ConfigsReader;

public class ProductPurchasingPage extends CommonMethods {

	// Elements
	@FindBy(css = "div.MuiCardContent-root")
	private List<WebElement> productsCard;

	@FindBy(css = "button:has(span.MuiBadge-root)")
	private WebElement navbar;

	@FindBy(xpath = "//p[preceding-sibling::button and following-sibling::button]")
	private WebElement quantity;

	@FindBy(css = "div.MuiCard-root h6.MuiTypography-h6")
	private WebElement totalAmount;

	@FindBy(css = "div.MuiCardContent-root > h6.MuiTypography-h6")
	private List<WebElement> productNames;

	@FindBy(xpath = "//p[contains(@class, 'MuiTypography-body1')]/following-sibling::button[1]")
	private WebElement increaseButton;

	@FindBy(xpath = "(//div[contains(@class, 'MuiCardContent-root')]//button[contains(@class, 'MuiIconButton-root')])[1]")
	private WebElement decreaseButton;

	@FindBy(xpath = "//div[contains(@class, 'MuiCardContent-root') and contains(@class, 'justify-between')]/p[1]")
	private WebElement singleProduct;

	@FindBy(xpath = "//button[text()='Proceed to Payment']")
	private WebElement proceedPaymentButton;

	@FindBy(xpath = "//button[text()='Proceed to Address']")
	private WebElement proceedAddressButton;

	@FindBy(css = "span.MuiBadge-badge")
	private WebElement navbarCount;

	@FindBy(xpath = "//label[text()='First Name']/following-sibling::div/input")
	private WebElement firstNameInput;

	@FindBy(xpath = "//label[text()='Last Name']/following-sibling::div/input")
	private WebElement lastNameInput;

	@FindBy(xpath = "//label[text()='Address']/following-sibling::div/textarea[1]")
	private WebElement addressInput;

	@FindBy(xpath = "//button[text()='Pay Now']")
	private WebElement payNowButton;

	@FindBy(xpath = "//h6[text()='Billing Details:']/following-sibling::p[1]")
	private WebElement firstLastNameBill;

	@FindBy(xpath = "//h6[text()='Billing Details:']/following-sibling::p[2]")
	private WebElement addressBill;

	@FindBy(xpath = "//h6[text()='Order Summary:']/following-sibling::h6")
	private WebElement totalAmountBill;

	@FindBy(css = ".MuiTypography-h5")
	private WebElement successMessage;

	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement cancelButton;

	@FindBy(xpath = "//h6[contains(text(), 'Payment Failed!')]")
	private WebElement failureElement;

	@FindBy(xpath = "//button[text()='Back to Home']")
	private WebElement backToHomeButton;

	@FindBy(xpath = "//button[text()='Back to Home']")
	private WebElement goHomeButton;

	@FindBy(xpath = "//button[contains(@class, 'MuiButton-containedPrimary')][1]")
	public WebElement firstAddCartButton;

	// Constructor
	public ProductPurchasingPage() {
		PageFactory.initElements(BaseClass.driver, this);
	}

	// Methods

	public void navigateUrl() {
		String url = ConfigsReader.getProperty("baseUrl") + ConfigsReader.getProperty("productPurchasingUrl");
		driver.get(url);
	}

	public void goToCart() {
		waitForClickability(navbar);
		click(navbar);
	}

	public void proceedToPayment() {
		waitForClickability(proceedPaymentButton);
		click(proceedPaymentButton);
	}

	public void proceedToAddress() {
		waitForClickability(proceedAddressButton);
		click(proceedAddressButton);
	}

	public void clickPayNow() {
		waitForClickability(payNowButton);
		click(payNowButton);
	}

	public void clickCancelBtn() {
		waitForClickability(cancelButton);
		click(cancelButton);
	}

	public void clickBackToHomeBtn() {
		waitForClickability(backToHomeButton);
		click(backToHomeButton);
	}

	public void clickGoHomeBtn() {
		waitForClickability(goHomeButton);
		click(goHomeButton);
	}

	public void clickDecreaseButton() {
		waitForClickability(decreaseButton);
		click(decreaseButton);
	}

	public void increaseFirstProductQuantity(int count) {
		for (int i = 0; i < count; i++) {
			click(increaseButton);
		}
	}

	public String getAddressFromBill() {
		return addressBill.getText();
	}

	public String getFullNameFromBill() {
		return firstLastNameBill.getText();
	}

	public String getTotalAmountFromBill() {
		return totalAmountBill.getText();
	}

	public String getSuccessMessageFromBill() {
		return successMessage.getText();
	}

	public String getFailureMessage() {
		return failureElement.getText();
	}

	public String getQuantityText() {
		return quantity.getText();
	}

	public String getTotalAmountText() {
		return totalAmount.getText();
	}

	public String getSingleProductText() {
		return singleProduct.getText();
	}

	public String getNavbarClass() {
		return navbarCount.getAttribute("class");
	}

	public String getProceedButtonText() {
		return proceedPaymentButton.getText();
	}

	public boolean isProceedButtoEnabled() {
		try {
			waitForVisibility(proceedPaymentButton);
			return proceedPaymentButton.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isfirstAddCardButtonDisplayed() {
		try {
			waitForVisibility(firstAddCartButton);
			return firstAddCartButton.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isBackToHomeButtonDisplayed() {
		try {
			waitForVisibility(backToHomeButton);
			return backToHomeButton.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void fillShippingDetailsAndProceed(String firstName, String lastName, String address) {
		waitForClickability(firstNameInput);
		sendText(firstNameInput, firstName);
		waitForClickability(lastNameInput);
		sendText(lastNameInput, lastName);
		waitForClickability(addressInput);
		sendText(addressInput, address);
	}

	public void addProductToCartByName(String productName) {
		List<WebElement> productCards = productsCard;
		for (WebElement names : productCards) {
			try {
				WebElement productNameElement = names
						.findElement(By.cssSelector("div.MuiCardContent-root > h6.MuiTypography-h6"));
				String currentProductName = productNameElement.getText();
				if (currentProductName.equals(productName)) {
					WebElement addToCartButton = names.findElement(By.cssSelector("button.MuiButton-root"));
					addToCartButton.click();
					break;
				}
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}

}
