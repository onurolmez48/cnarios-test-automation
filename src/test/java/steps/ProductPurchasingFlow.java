package steps;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;
import utils.ConfigsReader;

public class ProductPurchasingFlow extends CommonMethods {

	@When("User click Add to Card on {string}")
	public void user_click_add_to_card_on_wireless_headphones(String productName) {
		List<WebElement> productCards = driver.findElements(By.cssSelector("div.MuiCardContent-root"));
		for (WebElement names : productCards) {
			try {
				WebElement productNameElement = names
						.findElement(By.cssSelector("div.MuiCardContent-root > h6.MuiTypography-h6"));
				String currentProductName = productNameElement.getText();
				if (currentProductName.equals(productName)) {
					WebElement addToCartButton = names.findElement(By.cssSelector("button.MuiButton-root"));
					addToCartButton.click();
					System.out.println("Clicked 'Add to Cart' for: " + productName);
					break;
				}
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}

	@When("User click cart icon in the navbar")
	public void user_click_cart_icon_in_the_navbar() {
		waitForClickability(productPurchasingPage.navbar);
		click(productPurchasingPage.navbar);
		wait(2);
	}

	@Then("User verify Wireless Headphones is diplayed with correct price and quantity")
	public void user_verify_wireless_headphones_is_diplayed_with_correct_price_and_quantity() {
		String expectedAmount = "120";
		String actualAmount = productPurchasingPage.totalAmount.getText();
		String expectedQuantity = "1";
		String actualQuantity = productPurchasingPage.quantity.getText();
		Assert.assertTrue("The total amount does NOT matches", actualAmount.contains(expectedAmount));
		Assert.assertEquals("The quantity does NOT matches", expectedQuantity, actualQuantity);
	}

	@When("User add {string} to card")
	public void user_add_to_card(String product) {
		for (WebElement names : productPurchasingPage.productsCard) {
			try {
				WebElement productNameElement = names
						.findElement(By.cssSelector("div.MuiCardContent-root > h6.MuiTypography-h6"));
				String currentProductName = productNameElement.getText();
				if (currentProductName.equals(product)) {
					WebElement addToCartButton = names.findElement(By.cssSelector("button.MuiButton-root"));
					addToCartButton.click();
					System.out.println("Clicked 'Add to Cart' for: " + product);
					break;
				}
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}

	@When("User open the cart")
	public void user_open_the_cart() {
		waitForClickability(productPurchasingPage.navbar);
		click(productPurchasingPage.navbar);
		wait(2);
	}

	@When("User click + button to increase quantity")
	public void user_click_button_to_increase_quantity() {
		waitForClickability(productPurchasingPage.increaseButton);
		click(productPurchasingPage.increaseButton);
		wait(2);
	}

	@Then("User verify quantity increases and total updates")
	public void user_verify_quantity_increases_and_total_updates() {
		String actualQuantity = productPurchasingPage.quantity.getText();
		String expectedQuantity = "2";
		Assert.assertEquals("The product quantity has not increased to 2!", expectedQuantity, actualQuantity);
		String singlePriceText = productPurchasingPage.singleProduct.getText();
		String cleanedSinglePrice = singlePriceText.replaceAll("[^\\d]", "");
		String totalAmountText = productPurchasingPage.totalAmount.getText();
		String cleanedTotalAmount = totalAmountText.replaceAll("[^\\d]", "");
		int expectedTotal = Integer.parseInt(cleanedSinglePrice) * Integer.parseInt(actualQuantity);
		Assert.assertEquals("The total amount has been calculated incorrectly!", expectedTotal,
				Integer.parseInt(cleanedTotalAmount));
		System.out.println("Single product price: $" + cleanedSinglePrice + "Product quantity: " + actualQuantity
				+ " Total Amount: " + totalAmountText);
	}

	@Then("User click - button to decrease quantity")
	public void user_click_button_to_decrease_quantity() {
		waitForClickability(productPurchasingPage.decreaseButton);
		click(productPurchasingPage.decreaseButton);
		wait(2);
	}

	@Then("User verify quantity decrease and total updates")
	public void user_verify_quantity_decrease_and_total_updates() {
		String actualQuantity = productPurchasingPage.quantity.getText();
		String expectedQuantity = "1";
		Assert.assertEquals("The product quantity has not increased to 2!", expectedQuantity, actualQuantity);
		String singlePriceText = productPurchasingPage.singleProduct.getText();
		String cleanedSinglePrice = singlePriceText.replaceAll("[^\\d]", "");
		String totalAmountText = productPurchasingPage.totalAmount.getText();
		String cleanedTotalAmount = totalAmountText.replaceAll("[^\\d]", "");
		int expectedTotal = Integer.parseInt(cleanedSinglePrice) * Integer.parseInt(actualQuantity);
		Assert.assertEquals("The total amount has been calculated incorrectly!", expectedTotal,
				Integer.parseInt(cleanedTotalAmount));
		System.out.println("Sinlge product price: $" + cleanedSinglePrice + "Product quantity: " + actualQuantity
				+ " Total Amount: " + totalAmountText);
	}

	@Then("User verify {string} is no longer in the cart")
	public void user_verify_is_no_longer_in_the_cart(String product) {
		List<WebElement> productsElement = driver.findElements(
				By.xpath("//div[contains(@class, 'MuiCard-root') and .//p[contains(text(), '\" + product + \"')]]"));
		if (productsElement.isEmpty()) {
			System.out.println("Başarılı: '" + product + "' ürünü sepetten kaldırıldı.");
		} else {
			System.out.println("Başarısız: '" + product + "' ürünü sepetten kaldırılmadı.");
		}

	}

	@Then("User verify cart count in navbar is updated")
	public void user_verify_cart_count_in_navbar_is_updated() {
		String classes = productPurchasingPage.navbarCount.getAttribute("class");
		Assert.assertTrue(classes.contains("MuiBadge-invisible"));
	}

	@When("User click Proceed to Address button")
	public void user_click_proceed_to_address_button() {
		click(productPurchasingPage.proceedAddressButton);

	}

	@When("User leave all address fields empty")
	public void user_leave_all_address_fields_empty() {

	}

	@Then("User verify Proceed to Payment button is disabled")
	public void user_verify_proceed_to_address_button_is_disabled() {
		if (productPurchasingPage.proceedPaymentButton.isEnabled()) {
			System.out.println(productPurchasingPage.proceedPaymentButton.getText() + " button is enabled!");
			Assert.fail("Proceed to Payment button is enabled!");
		} else {
			System.out.println(productPurchasingPage.proceedPaymentButton.getText() + " button is disabled!");
			Assert.assertTrue(true);
		}
	}

	@Then("User enter valid first name {string}, last name {string}, address {string}")
	public void user_enter_valid_first_name_last_name_address(String firstName, String lastName, String address) {
		waitForClickability(productPurchasingPage.firstNameInput);
		waitForClickability(productPurchasingPage.lastNameInput);
		waitForClickability(productPurchasingPage.addressInput);
		sendText(productPurchasingPage.firstNameInput, firstName);
		sendText(productPurchasingPage.lastNameInput, lastName);
		sendText(productPurchasingPage.addressInput, address);
		wait(2);
	}

	@Then("User click Proceed to Payment button")
	public void user_click_proceed_to_payment_button() {
		waitForClickability(productPurchasingPage.proceedPaymentButton);
		click(productPurchasingPage.proceedPaymentButton);
	}

	@Then("User click Pay Now button")
	public void user_click_pay_now_button() {
		click(productPurchasingPage.payNowButton);
	}

	@Then("User Verify success message with billing details and total is displayed")
	public void user_verify_success_message_with_billing_details_and_total_is_displayed() {
		String expectedSuccessMessage = "Order Placed Successfully!";
		String actualSuccessMessage = driver.findElement(By.cssSelector(".MuiTypography-h5")).getText();
		String expectedFirstLastName = ConfigsReader.getProperty("firstname") + " "
				+ ConfigsReader.getProperty("lastname");
		String actualFirstLastName = productPurchasingPage.firstLastNameBill.getText();
		String expectedAddress = ConfigsReader.getProperty("address");
		String actualAddress = productPurchasingPage.addressBill.getText();
		String total = productPurchasingPage.totalAmountBill.getText();
		String expected = "60";
		System.out.println(total.contains(expected));
		Assert.assertTrue(total.contains(expected));
		Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage));
		Assert.assertTrue(actualFirstLastName.contains(expectedFirstLastName));
		Assert.assertEquals(expectedAddress, actualAddress);
	}

	@Then("User click Cancel button")
	public void user_click_cancel_button() {
		click(productPurchasingPage.cancelButton);
	}

	@Then("User verify failure message and Go Home button are displayed")
	public void user_verify_failure_message_and_go_home_button_are_displayed() {
		String expectedFailure = "PaymentFailed";
		String actualFailure = productPurchasingPage.failureElement.getText();
		String clean = actualFailure.replaceAll("[^a-zA-Z]", "");
		System.out.println(clean);

		Assert.assertTrue(clean.contains(expectedFailure));
		Assert.assertTrue(productPurchasingPage.backToHomeButton.isDisplayed());
	}

	@Then("User click Go Home button")
	public void user_click_go_home_button() {
		click(productPurchasingPage.goHomeButton);
	}

	@Then("User verify product listing page is shown and navbar is empty")
	public void user_verify_product_listing_page_is_shown_and_navbar_is_empty() {
		waitForVisibility(productPurchasingPage.firstAddCartButton);
		Assert.assertTrue("The addToCardButton not found!", productPurchasingPage.firstAddCartButton.isDisplayed());
		String classes = productPurchasingPage.navbarCount.getAttribute("class");
		Assert.assertTrue(classes.contains("MuiBadge-invisible"));
	}

}
