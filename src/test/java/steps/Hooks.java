package steps;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks extends CommonMethods {

	@Before
	public void start() {
		setUp();
	}

	@After
	public void end(Scenario scenario) {
		byte[] screenshot;

		if (scenario.isFailed()) {
			screenshot = takeScreenshot("failed/" + scenario.getName());
		} else {
			screenshot = takeScreenshot("passed/" + scenario.getName());
		}

		scenario.attach(screenshot, "image/png", scenario.getName());
		tearDown();
	}
}
