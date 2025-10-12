package testbase;

import pages.LoginPage;
import pages.ProductSearchPage;
import pages.ProductPurchasingPage;

public class PageInitializer extends BaseClass {

	public static LoginPage loginPage;
	public static ProductSearchPage productSearchPage;
	public static ProductPurchasingPage productPurchasingPage;

	public static void initialize() {
		loginPage = new LoginPage();
		productSearchPage = new ProductSearchPage();
		productPurchasingPage = new ProductPurchasingPage();
	}

}
