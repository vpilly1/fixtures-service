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

public class DeleteFixturesSteps {
    private String baseURI;
    private Response response;

    @Given("the fixture delete endpoint exists")
    public void theFixtureDeleteEndpointExists() {
        baseURI = "http://localhost:3000/fixture";
    }

    @When("I make Delete API call to fixtures service {int}")
    public void iMakeDeleteAPICallToFixturesService(int fixtureId) {
        response = given()
                .contentType(ContentType.JSON)
                .body(fixtureId)
                .delete(baseURI)
                .then().extract().response();
    }

    @And("the delete response should have one less fixture {int}")
    public void theDeleteResponseShouldHaveOneLessFixture(int noOfFixtures) {
        ArrayList<String> list = response.jsonPath().get("fixtureId");
        assertThat(list.size(), is(noOfFixtures));
    }

    @Then("delete response code should be {int}")
    public void deleteresponseCodeShouldBe(int code) {
        assertThat(code, is(200));
    }
}
