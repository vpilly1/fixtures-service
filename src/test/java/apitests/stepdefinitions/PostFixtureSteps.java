package apitests.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PostFixtureSteps {
    private String baseURI;
    private Response response;

    @Given("the add fixtures endpoint exists")
    public void theAddFixturesEndpointExists() {
        baseURI = "http://localhost:3000/fixture";
    }

    @When("I make post API call to fixtures service with below details")
    public void iMakePostAPICallToFixturesService(DataTable datatable) {
        List<List<String>> rows = datatable.asLists(String.class);
        HashMap<String, String> hashMap = new HashMap<>();
        for (List<String> columns : rows) {
            hashMap.put(columns.get(0), columns.get(1));
        }
        response = given()
                .contentType(ContentType.JSON)
                .body(hashMap)
                .post(baseURI)
                .then().extract().response();
    }

    @And("the add response should have all fixtures {int}")
    public void theAddResponseShouldHaveAllFixtures(int noOfFixtures) {
        response = given()
                .contentType(ContentType.JSON)
                .get("http://localhost:3000/fixtures")
                .then().extract().response();
        ArrayList<String> list = response.jsonPath().get("fixtureId");
        assertThat(list.size(), is(noOfFixtures));
    }

    @Then("add response code should be {int}")
    public void addResponseCodeShouldBe(int code) throws InterruptedException {
        sleep(5000);
        assertThat(response.getStatusCode(), is(code));
    }
}

