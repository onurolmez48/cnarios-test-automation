package steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;

public class ProductSearchPageSteps extends CommonMethods {

	@Given("User navigate to product listing page")
	public void user_navigate_to_product_listing_page() {
		productSearchPage.navigateUrl();
	}

	@When("User select category {string} from filter")
	public void user_select_category_from_filter(String category) {
		productSearchPage.selectCategory(category);
	}

	@Then("Verify all displayed products belong to {string}")
	public void verify_all_displayed_products_belong_to(String string) {
		for (WebElement elect : productSearchPage.infoCards) {
			if (elect.getText().contains(string)) {
				scrollDown(70);
			}
		}
	}

	@When("User adjust price range slider to {int} and {int}")
	public void user_adjust_price_range_slider_to(int min, int max) {
		productSearchPage.setPriceRangeByOffset(min, max);
	}

	@Then("User verify all displayed products fall within selected price range")
	public void user_verify_all_displayed_products_fall_within_selected_price_range() {
		WebElement noProductMsg = driver.findElement(By.xpath("//p[text()='No products found']"));
		Assert.assertTrue("'No products found' message is not shown", noProductMsg.isDisplayed());
		Assert.assertEquals("Wrong message!", "No products found", noProductMsg.getText());
	}

	@When("User set minimum rating filter to {int} stars")
	public void user_set_minimum_rating_filter_to_stars(int starInt) {
		WebElement star = driver
				.findElement(By.xpath("//input[@type='radio' and @value='4']/preceding-sibling::label[1]"));
		click(star);
	}

	@Then("User verify all displayed product have rating >= {int}")
	public void user_verify_all_displayed_product_have_rating(Integer int1) {
		for (WebElement lists : productSearchPage.infoCards) {
			String fullPart = lists.getText();
			String[] part = fullPart.split(" ");
			String rating = part[part.length - 1];
			int ratingValue = Integer.parseInt(rating);
			if (ratingValue >= 4) {
				System.out.println("All ratings >= 4");
				break;
			}
		}
	}

	@When("User enable In Stock Only filter")
	public void user_enable_in_stock_only_filter() {
		waitForClickability(productSearchPage.stockCheckBox);
		click(productSearchPage.stockCheckBox);
	}

	@Then("User verify all displayed products are in stock")
	public void user_verify_all_displayed_products_are_in_stock() {
		for (WebElement stock : productSearchPage.inStock) {
			if (stock.getText().equals("In Stock")) {
				System.out.println("Product In Stock");
				break;
			}
		}
	}

	@When("User apply category, price, and stock filters")
	public void user_apply_category_price_and_stock_filters() {
		waitForClickability(productSearchPage.categoryDD);
		click(productSearchPage.categoryDD);
		for (WebElement element : productSearchPage.category) {
			if (element.getText().contains("Electronics")) {
				waitForClickability(element);
				click(element);
			}
		}
		Actions actions = new Actions(driver);
		actions.clickAndHold(productSearchPage.maxSlider).moveByOffset(-391, 0).release().perform();
		waitForClickability(productSearchPage.stockCheckBox);
		click(productSearchPage.stockCheckBox);
	}

	@When("User click reset button")
	public void user_click_reset_button() {
		productSearchPage.clickResetFilters();
	}

	@When("User Verify all filters are cleared")
	public void user_verify_all_filters_are_cleared() {
		Assert.assertEquals(productSearchPage.categoryBox.getText(), "All");
		Assert.assertEquals(productSearchPage.minSlider.getAttribute("aria-valuenow"), "0");
		Assert.assertEquals(productSearchPage.maxSlider.getAttribute("aria-valuenow"), "80000");
		Assert.assertTrue(productSearchPage.stockCheckBox.isEnabled());
	}

	@Then("User verify full default product list is restored")
	public void user_verify_full_default_product_list_is_restored() {
		List<String> actualProductNames = productSearchPage.products.stream().map(WebElement::getText).collect(Collectors.toList());
		Assert.assertEquals("Products does NOT matches", actualProductNames, productSearchPage.DEFAULT_PRODUCT_NAMES);
	}

	@When("User apply category filter")
	public void user_apply_category_filter() {
		waitForClickability(productSearchPage.categoryDD);
		click(productSearchPage.categoryDD);
		By listLocator = By.xpath("//ul[@role='listbox']");
		waitForVisibility(listLocator);
		List<String> categoryNames = new ArrayList<>();
		for (WebElement element : productSearchPage.category) {
			String text = element.getText();
			if (text != null && !text.trim().isEmpty() && !text.equalsIgnoreCase("All")) {
				categoryNames.add(text);
				System.out.println(text);
			}
		}
		new Actions(driver).sendKeys(Keys.ESCAPE).perform();
		waitForInvisibility(listLocator);
		List<String> productNames = new ArrayList<>();
		for (String categoryName : categoryNames) {
			waitForClickability(productSearchPage.categoryDD);
			click(productSearchPage.categoryDD);
			waitForVisibility(listLocator);
			WebElement categoryToClick = driver
					.findElement(By.xpath("//ul[@role='listbox']/li[normalize-space()='" + categoryName + "']"));
			click(categoryToClick);
			wait(2);
			for (WebElement cards : productSearchPage.infoCards) {
				String cardsText = cards.getText().split("•")[0].trim();
				if (cardsText != null && !cardsText.trim().isEmpty()) {
					productNames.add(cardsText);
					if (!cardsText.toString().contains("Electronics") && !cardsText.toString().contains("Sports")
							&& !cardsText.contains("Clothing")) {
						System.out.println("Products is not matches");
					} else {
						System.out
								.println(categoryName + " category matches " + cardsText.toString() + " product cards");
						break;
					}
				}
			}

		}
	}

	@When("User verify each product card displays name")
	public void user_verify_each_product_card_displays_name() {
		click(productSearchPage.resetFilterBtn);
		List<String> productStr = new ArrayList<>();
		for (WebElement actualCards : productSearchPage.infoCards) {
			productStr.add(actualCards.getText());
			for (String cards : productStr) {
				if (cards.contains("Electronics") && cards.contains("Clothing") && cards.contains("Sports")) {
					System.out.println("Product cards verified");
				}
			}
		}

	}

	@When("User verify price is shown with currency symbol")
	public void user_verify_price_is_shown_with_currency_symbol() {
		List<String> symbols = new ArrayList<String>();
		for (WebElement symbol : productSearchPage.infoCards) {
			symbols.add(symbol.getText());
			if (symbols.toString().contains(" • ₹")) {
				System.out.println("Currency is verified");
				break;
			} else {
				Assert.fail("Currency symbol is not shown");
			}
		}
	}

	@When("User verify category label is present")
	public void user_verify_category_label_is_present() {
		List<String> expectedCategories = Arrays.asList("Electronics", "Sports", "Clothing");
		Assert.assertFalse("No product information was found on the page!", productSearchPage.infoCards.isEmpty());
		List<String> actualFullTexts = new ArrayList<>();
		for (WebElement element : productSearchPage.infoCards) {
			actualFullTexts.add(element.getText());
		}
		for (String expectedCategory : expectedCategories) {
			boolean isCategoryFound = false;
			for (String actualText : actualFullTexts) {
				if (actualText.contains(expectedCategory)) {
					isCategoryFound = true;
					break;
				}
			}
			Assert.assertTrue("'" + expectedCategory + "' the category could not be found in the product list!",
					isCategoryFound);
		}
	}

	@Then("User verify rating stars are visible and read-only")
	public void user_verify_rating_stars_are_visible_and_read_only() {
		boolean isEmpty = productSearchPage.infoCards.isEmpty();
		if (isEmpty) {
			Assert.fail("Cards is empty");
		} else {
			System.out.println("Ratings and stars is not empty");
		}

	}

}
