package pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.BaseClass;

public class ProductFilteringSearchPage {

	@FindBy(xpath = "//ul[@role='listbox']/li")
	public List<WebElement> category;
	
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

	public ProductFilteringSearchPage() {
		PageFactory.initElements(BaseClass.driver, this);
	}

}
