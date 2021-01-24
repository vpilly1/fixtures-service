package apitests.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GetFixturesSteps {
    private String baseURI;
    private Response response;

    @Given("the fixtures endpoint exists")
    public void theFixturesEndpointExists() {
        baseURI = "http://localhost:3000/fixtures";
    }

    @When("I make get API call to fixtures service")
    public void iMakeGetAPICallToFixturesService() {
        response = given()
                .contentType(ContentType.JSON)
                .get(baseURI)

                .then().extract().response();
    }

    @And("the response should have all fixtures {int}")
    public void theResponseShouldHaveAllFixtures(int noOfFixtures) {
        ArrayList<String> list = response.jsonPath().get("fixtureId");
        assertThat(list.size(), is(noOfFixtures));
    }

    @Then("response code should be {int}")
    public void responseCodeShouldBe(int code) {
        assertThat(code, is(200));
    }
}
