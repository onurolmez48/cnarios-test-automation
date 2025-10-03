package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = "src/test/resources/features/",

		glue = "steps",

		tags = "@TC_002",

		dryRun = false,

		monochrome = true,

		plugin = { "html:target/html-report/regression.html", "json:target/cucumber.json", }

)

public class TestRunner {

}
