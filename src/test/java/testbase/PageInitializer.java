package testbase;

import pages.LoginPageElements;

public class PageInitializer extends BaseClass {

	public static LoginPageElements LoginPage;

	public static void initialize() {
		LoginPage = new LoginPageElements();
	}

}
