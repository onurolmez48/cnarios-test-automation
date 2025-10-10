package testbase;

import pages.LoginPage;
import pages.ProductFilteringSearchPage;
import pages.ProductPurchasingPage;

public class PageInitializer extends BaseClass {

	public static LoginPage loginPage;
	public static ProductFilteringSearchPage productFilteringPage;
	public static ProductPurchasingPage productPurchasingPage;

	public static void initialize() {
		loginPage = new LoginPage();
		productFilteringPage = new ProductFilteringSearchPage();
		productPurchasingPage = new ProductPurchasingPage();
	}

}
