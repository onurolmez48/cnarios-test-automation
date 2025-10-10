package pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.BaseClass;

public class ProductPurchasingPage {

	@FindBy(css = "div.MuiCardContent-root")
	public List<WebElement> productsCard;

	@FindBy(css = "button:has(span.MuiBadge-root)")
	public WebElement navbar;

	@FindBy(xpath = "//p[preceding-sibling::button and following-sibling::button]")
	public WebElement quantity;

	@FindBy(css = "div.MuiCard-root h6.MuiTypography-h6")
	public WebElement totalAmount;

	@FindBy(css = "div.MuiCardContent-root > h6.MuiTypography-h6")
	public List<WebElement> productNames;

	@FindBy(xpath = "//p[contains(@class, 'MuiTypography-body1')]/following-sibling::button[1]")
	public WebElement increaseButton;

	@FindBy(xpath = "(//div[contains(@class, 'MuiCardContent-root')]//button[contains(@class, 'MuiIconButton-root')])[1]")
	public WebElement decreaseButton;

	@FindBy(xpath = "//div[contains(@class, 'MuiCardContent-root') and contains(@class, 'justify-between')]/p[1]")
	public WebElement singleProduct;

	@FindBy(xpath = "//button[text()='Proceed to Payment']")
	public WebElement proceedPaymentButton;

	@FindBy(xpath = "//button[text()='Proceed to Address']")
	public WebElement proceedAddressButton;

	@FindBy(css = "span.MuiBadge-badge")
	public WebElement navbarCount;

	@FindBy(xpath = "//label[text()='First Name']/following-sibling::div/input")
	public WebElement firstNameInput;

	@FindBy(xpath = "//label[text()='Last Name']/following-sibling::div/input")
	public WebElement lastNameInput;

	@FindBy(xpath = "//label[text()='Address']/following-sibling::div/textarea[1]")
	public WebElement addressInput;

	@FindBy(xpath = "//button[text()='Pay Now']")
	public WebElement payNowButton;

	@FindBy(xpath = "//h6[text()='Billing Details:']/following-sibling::p[1]")
	public WebElement firstLastNameBill;

	@FindBy(xpath = "//h6[text()='Billing Details:']/following-sibling::p[2]")
	public WebElement addressBill;

	@FindBy(xpath = "//h6[text()='Order Summary:']/following-sibling::h6")
	public WebElement totalAmountBill;

	@FindBy(xpath = "//button[text()='Cancel']")
	public WebElement cancelButton;

	@FindBy(xpath = "//h6[contains(text(), 'Payment Failed!')]")
	public WebElement failureElement;

	@FindBy(xpath = "//button[text()='Back to Home']")
	public WebElement backToHomeButton;

	@FindBy(xpath = "//button[text()='Back to Home']")
	public WebElement goHomeButton;
	
	@FindBy(xpath = "//button[contains(@class, 'MuiButton-containedPrimary')][1]")
	public WebElement firstAddCartButton;

	public List<String> productsNames = Arrays.asList("Wireless Headphones", "Smartphone Stand", "Bluetooth Speaker",
			"Laptop Backpack", "Fitness Band");

	public ProductPurchasingPage() {
		PageFactory.initElements(BaseClass.driver, this);
	}
}
