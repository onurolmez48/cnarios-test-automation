package testbase;

import pages.LoginPage;

public class PageInitializer extends BaseClass {

	public static LoginPage loginPage;

	public static void initialize() {
		loginPage = new LoginPage();
	}

}
