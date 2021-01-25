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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UpdateFixtureSteps {
    private String baseURI;
    private Response response;

    @Given("the update fixture endpoint exists")
    public void theUpdateFixtureEndpointExists() {
        baseURI = "http://localhost:3000/fixture";
    }

    @When("I make put API call to fixtures service with below details")
    public void iMakeGetAPICallToFixturesService(DataTable datatable) {
        List<List<String>> rows = datatable.asLists(String.class);
        HashMap<String, String> hashMap = new HashMap<>();
        for (List<String> columns : rows) {
            hashMap.put(columns.get(0), columns.get(1));
        }
        response = given()
                .contentType(ContentType.JSON)
                .body(hashMap)
                .put(baseURI)
                .then().extract().response();
    }

    @And("the update response should have updated fixture {int}")
    public void theUpdateResponseShouldHaveUpdatedFixture(int noOfFixtures) {
        ArrayList<String> list = response.jsonPath().get("fixtureId");
        assertThat(list.size(), is(noOfFixtures));
    }

    @Then("update response code should be {int}")
    public void updateResponseCodeShouldBe(int code) {
        assertThat(code, is(200));
    }
}


