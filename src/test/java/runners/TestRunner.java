package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = "src/test/resources/features/",

		glue = "steps",

		dryRun = false,

		//tags = "@JAF_002",

		monochrome = true,

		plugin = { "html:target/html-report/regression.html", "json:target/cucumber.json", }

)

public class TestRunner {

}
