package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumberReport.html", "json:target/testReport.json"},
        features = "/Users/oles/Desktop/java/TariffAPI/src/test/resources",
        glue = "steps",
        tags = "@getAlltariffs",
        dryRun = false
)

public class runnerClass {


}

