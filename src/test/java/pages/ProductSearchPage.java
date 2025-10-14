package pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.BaseClass;
import utils.CommonMethods;
import utils.ConfigsReader;

public class ProductSearchPage extends CommonMethods {

	@FindBy(xpath = "//ul[@role='listbox']/li")
	public List<WebElement> category;

	@FindBy(xpath = "//*[text() = 'Products']/following-sibling::div/p[1]")
	public List<WebElement> products;

	@FindBy(xpath = "//div[@role='combobox']")
	public WebElement categoryBox;

	@FindBy(xpath = "//div[@aria-labelledby='category']")
	public WebElement categoryDD;

	@FindBy(xpath = "//input[@type='range' and @data-index='0']")
	public WebElement minSlider;

	@FindBy(xpath = "//input[@type='range' and @data-index='1']")
	public WebElement maxSlider;

	@FindBy(css = "span.MuiButtonBase-root.MuiCheckbox-root")
	public WebElement stockCheckBox;

	@FindBy(css = "span.MuiTypography-root.MuiTypography-caption")
	public List<WebElement> inStock;

	@FindBy(xpath = "//*[text() = 'Products']/following-sibling::div/p[2]")
	public List<WebElement> infoCards;

	@FindBy(xpath = "//button[@type='button' and text()='Reset Filters']")
	public WebElement resetFilterBtn;

	public List<String> DEFAULT_PRODUCT_NAMES = Arrays.asList("Wireless Mouse", "Bluetooth Keyboard", "USB-C Charger",
			"Running Shoes", "Tennis Racket", "Water Bottle", "Cotton T-Shirt", "Jeans", "Jacket", "Smartphone",
			"Tablet", "Gaming Console", "Yoga Mat", "Soccer Ball", "Formal Shirt");

	public ProductSearchPage() {
		PageFactory.initElements(BaseClass.driver, this);
	}

	// Methods

	public void navigateUrl() {
		String url = ConfigsReader.getProperty("baseUrl") + ConfigsReader.getProperty("productFilteringUrl");
		driver.get(url);
	}

	public void clickResetFilters() {
		waitForClickability(resetFilterBtn);
		click(resetFilterBtn);
	}

	public void selectCategory(String categoryName) {
		click(categoryDD);
		for (WebElement li : productSearchPage.category) {
			if (li.getText().contains(categoryName)) {
				click(li);
			}
		}
	}

	public void setPriceRangeByOffset(int minOffset, int maxOffset) {
		Actions actions = new Actions(driver);
		actions.clickAndHold(minSlider).moveByOffset(minOffset, 0).release().perform();
		actions.clickAndHold(maxSlider).moveByOffset(maxOffset, 0).release().perform();
	}

}
