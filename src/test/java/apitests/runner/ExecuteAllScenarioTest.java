package apitests.stepdefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/cucumber-report.json"}, features = {
        "src/test/resources/cucumber.features/fixtures/GetFixtures.feature",
        "src/test/resources/cucumber.features/fixtures/PostFixture.feature",
        "src/test/resources/cucumber.features/fixtures/UpdateFixture.feature",
        "src/test/resources/cucumber.features/fixtures/RemoveFixture.feature"
},glue = "apitests.stepdefinitions",
        tags = "not @wip"
)
public class ExecuteAllScenarioTest {
}

