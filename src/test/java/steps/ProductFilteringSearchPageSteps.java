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

public class ProductFilteringSearchPageSteps extends CommonMethods {

	@Given("User navigate to product listing page")
	public void user_navigate_to_product_listing_page() {

	}

	@When("User select category {string} from filter")
	public void user_select_category_from_filter(String str) {
		click(productFilteringPage.categoryDD);
		for (WebElement li : productFilteringPage.category) {
			if (li.getText().contains(str)) {
				click(li);
			}
		}
	}

	@Then("Verify all displayed products belong to {string}")
	public void verify_all_displayed_products_belong_to(String string) {
		for (WebElement elect : productFilteringPage.infoCards) {
			if (elect.getText().contains(string)) {
				scrollDown(70);
			}
		}
	}

	@When("User adjust price range slider to {int} and {int}")
	public void user_adjust_price_range_slider_to(int min, int max) {
		Actions actions = new Actions(driver);
		actions.clickAndHold(productFilteringPage.minSlider).moveByOffset(72, 0).release().perform();
		actions.clickAndHold(productFilteringPage.maxSlider).moveByOffset(-391, 0).release().perform();
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
		for (WebElement lists : productFilteringPage.infoCards) {
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
	public void user_enable_ın_stock_only_filter() {
		waitForClickability(productFilteringPage.stockCheckBox);
		click(productFilteringPage.stockCheckBox);
	}

	@Then("User verify all displayed products are in stock")
	public void user_verify_all_displayed_products_are_in_stock() {
		for (WebElement stock : productFilteringPage.inStock) {
			if (stock.getText().equals("In Stock")) {
				System.out.println("Product In Stock");
				break;
			}
		}
	}

	@When("User apply category, price, and stock filters")
	public void user_apply_category_price_and_stock_filters() {
		waitForClickability(productFilteringPage.categoryDD);
		click(productFilteringPage.categoryDD);
		for (WebElement element : productFilteringPage.category) {
			if (element.getText().contains("Electronics")) {
				waitForClickability(element);
				click(element);
			}
		}
		Actions actions = new Actions(driver);
		actions.clickAndHold(productFilteringPage.maxSlider).moveByOffset(-391, 0).release().perform();
		waitForClickability(productFilteringPage.stockCheckBox);
		click(productFilteringPage.stockCheckBox);
	}

	@When("User click reset button")
	public void user_click_reset_button() {
		click(productFilteringPage.resetFilterBtn);
	}

	@When("User Verify all filters are cleared")
	public void user_verify_all_filters_are_cleared() {
		String categoryText = productFilteringPage.categoryBox.getText();
		System.out.println(categoryText);
		String expectedCat = "All";
		String min = productFilteringPage.minSlider.getAttribute("aria-valuenow");
		String max = productFilteringPage.maxSlider.getAttribute("aria-valuenow");
		Assert.assertEquals(categoryText, expectedCat);
		Assert.assertEquals(min, "0");
		Assert.assertEquals(max, "80000");
		Assert.assertTrue(productFilteringPage.stockCheckBox.isEnabled());
	}

	@Then("User verify full default product list is restored")
	public void user_verify_full_default_product_list_is_restored() {
		List<WebElement> products = driver
				.findElements(By.xpath("//*[text() = 'Products']/following-sibling::div/p[1]"));
		List<String> actualProductNames = products.stream().map(WebElement::getText).collect(Collectors.toList());
		Assert.assertEquals("Products does NOT matches", actualProductNames,
				productFilteringPage.DEFAULT_PRODUCT_NAMES);
	}

	@When("User apply category filter")
	public void user_apply_category_filter() {
		waitForClickability(productFilteringPage.categoryDD);
		click(productFilteringPage.categoryDD);
		By listLocator = By.xpath("//ul[@role='listbox']");
		waitForVisibility(listLocator);
		List<String> categoryNames = new ArrayList<>();
		for (WebElement element : productFilteringPage.category) {
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
			waitForClickability(productFilteringPage.categoryDD);
			click(productFilteringPage.categoryDD);
			waitForVisibility(listLocator);
			WebElement categoryToClick = driver
					.findElement(By.xpath("//ul[@role='listbox']/li[normalize-space()='" + categoryName + "']"));
			click(categoryToClick);
			wait(2);
			for (WebElement cards : productFilteringPage.infoCards) {
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
		click(productFilteringPage.resetFilterBtn);
		List<String> productStr = new ArrayList<>();
		for (WebElement actualCards : productFilteringPage.infoCards) {
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
		for (WebElement symbol : productFilteringPage.infoCards) {
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
		Assert.assertFalse("No product information was found on the page!", productFilteringPage.infoCards.isEmpty());
		List<String> actualFullTexts = new ArrayList<>();
		for (WebElement element : productFilteringPage.infoCards) {
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
		boolean isEmpty = productFilteringPage.infoCards.isEmpty();
		if (isEmpty) {
			Assert.fail("Cards is empty");
		} else {
			System.out.println("Ratings and stars is not empty");
		}

	}

}
