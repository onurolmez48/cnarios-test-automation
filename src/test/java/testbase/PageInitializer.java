package testbase;

import pages.LoginPage;
import pages.ProductFilteringSearchPage;

public class PageInitializer extends BaseClass {

	public static LoginPage loginPage;
	public static ProductFilteringSearchPage productFilteringPage;

	public static void initialize() {
		loginPage = new LoginPage();
		productFilteringPage = new ProductFilteringSearchPage();
	}

}
