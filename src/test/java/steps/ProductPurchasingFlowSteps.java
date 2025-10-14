package steps;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import messages.utils.ProductPurchasingMessages;
import utils.CommonMethods;

public class ProductPurchasingFlowSteps extends CommonMethods {

	@When("User click Add to Card on {string}")
	public void user_click_add_to_card_on_wireless_headphones(String productName) {
		productPurchasingPage.addProductToCartByName(productName);
	}

	@When("User click cart icon in the navbar")
	public void user_click_cart_icon_in_the_navbar() {
		productPurchasingPage.goToCart();
	}

	@Then("User verify Wireless Headphones is diplayed with correct price and quantity")
	public void user_verify_wireless_headphones_is_diplayed_with_correct_price_and_quantity() {
		String expectedAmount = "120";
		String expectedQuantity = "1";
		Assert.assertTrue("The total amount does NOT matches",
				productPurchasingPage.totalAmount.getText().contains(expectedAmount));
		Assert.assertEquals("The quantity does NOT matches", expectedQuantity, productPurchasingPage.getQuantityText());
	}

	@When("User add {string} to card")
	public void user_add_to_card(String product) {
		productPurchasingPage.addProductToCartByName(product);
	}

	@When("User open the cart")
	public void user_open_the_cart() {
		productPurchasingPage.goToCart();
	}

	@When("User click + button to increase quantity")
	public void user_click_button_to_increase_quantity() {
		productPurchasingPage.increaseFirstProductQuantity(2);
	}

	@Then("User verify quantity increases and total updates")
	public void user_verify_quantity_increases_and_total_updates() {
		String expectedQuantity = "2";
		Assert.assertEquals("The product quantity has not increased to 2!", expectedQuantity,productPurchasingPage.quantity.getText());
		String singlePriceText = productPurchasingPage.singleProduct.getText();
		String cleanedSinglePrice = singlePriceText.replaceAll("[^\\d]", "");
		String totalAmountText = productPurchasingPage.totalAmount.getText();
		String cleanedTotalAmount = totalAmountText.replaceAll("[^\\d]", "");
		
		int expectedTotal = Integer.parseInt(cleanedSinglePrice) * Integer.parseInt(productPurchasingPage.quantity.getText());
		Assert.assertEquals("The total amount has been calculated incorrectly!", expectedTotal, Integer.parseInt(cleanedTotalAmount));
		System.out.println("Single product price: $" + cleanedSinglePrice + "Product quantity: "
				+ productPurchasingPage.quantity.getText() + " Total Amount: " + totalAmountText);
	}

	@Then("User click - button to decrease quantity")
	public void user_click_button_to_decrease_quantity() {
		waitForClickability(productPurchasingPage.decreaseButton);
		click(productPurchasingPage.decreaseButton);
	}

	@Then("User verify quantity decrease and total updates")
	public void user_verify_quantity_decrease_and_total_updates() {
		String expectedQuantity = "1";
		Assert.assertEquals("The product quantity has not increased to 2!", expectedQuantity, productPurchasingPage.getQuantityText());
		String singlePriceText = productPurchasingPage.singleProduct.getText();
		String cleanedSinglePrice = singlePriceText.replaceAll("[^\\d]", "");
		String totalAmountText = productPurchasingPage.totalAmount.getText();
		String cleanedTotalAmount = totalAmountText.replaceAll("[^\\d]", "");
		int expectedTotal = Integer.parseInt(cleanedSinglePrice) * Integer.parseInt(productPurchasingPage.getQuantityText());
		Assert.assertEquals("The total amount has been calculated incorrectly!", expectedTotal,
				Integer.parseInt(cleanedTotalAmount));
	}

	@Then("User verify {string} is no longer in the cart")
	public void user_verify_is_no_longer_in_the_cart(String product) {
		List<WebElement> productsElement = driver.findElements(
				By.xpath("//div[contains(@class, 'MuiCard-root') and .//p[contains(text(), '\" + product + \"')]]"));
		Assert.assertTrue(productsElement.isEmpty());

	}

	@Then("User verify cart count in navbar is updated")
	public void user_verify_cart_count_in_navbar_is_updated() {
		String classes = productPurchasingPage.navbarCount.getAttribute("class");
		Assert.assertTrue(classes.contains("MuiBadge-invisible"));
	}

	@When("User click Proceed to Address button")
	public void user_click_proceed_to_address_button() {
		productPurchasingPage.proceedToAddress();
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
		productPurchasingPage.fillShippingDetailsAndProceed(firstName, lastName, address);
	}

	@Then("User click Proceed to Payment button")
	public void user_click_proceed_to_payment_button() {
		productPurchasingPage.proceedToPayment();
	}

	@Then("User click Pay Now button")
	public void user_click_pay_now_button() {
		productPurchasingPage.clickPayNow();
	}

	@Then("User Verify success message with billing details and total is displayed")
	public void user_verify_success_message_with_billing_details_and_total_is_displayed() {
		String expectedSuccessMessage = "Order Placed Successfully!";
		String expectedFirstLastName = "Onur Olmez";
		String expectedAddress = "Marmaris";
		String expected = "60";

		Assert.assertTrue(productPurchasingPage.getTotalAmountFromBill().contains(expected));
		Assert.assertTrue(productPurchasingPage.getSuccessMessageFromBill().contains(expectedSuccessMessage));
		Assert.assertTrue(productPurchasingPage.getFullNameFromBill().contains(expectedFirstLastName));
		Assert.assertEquals(expectedAddress, productPurchasingPage.getAddressFromBill());
	}

	@Then("User click Cancel button")
	public void user_click_cancel_button() {
		productPurchasingPage.clickCancelBtn();
	}

	@Then("User verify failure message and Go Home button are displayed")
	public void user_verify_failure_message_and_go_home_button_are_displayed() {
		String actualFailure = productPurchasingPage.getFailureMessage();
		Assert.assertTrue(actualFailure.replaceAll("[^a-zA-Z]", "").contains(ProductPurchasingMessages.PAYMENT_FAIL));
		Assert.assertTrue(productPurchasingPage.backToHomeButton.isDisplayed());
	}

	@Then("User click Go Home button")
	public void user_click_go_home_button() {
		productPurchasingPage.clickGoHomeBtn();
	}

	@Then("User verify product listing page is shown and navbar is empty")
	public void user_verify_product_listing_page_is_shown_and_navbar_is_empty() {
		waitForVisibility(productPurchasingPage.firstAddCartButton);
		Assert.assertTrue("The addToCardButton not found!", productPurchasingPage.firstAddCartButton.isDisplayed());
		String classes = productPurchasingPage.navbarCount.getAttribute("class");
		Assert.assertTrue(classes.contains("MuiBadge-invisible"));
	}

}
