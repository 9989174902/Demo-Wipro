package stepdefinitions;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.containsString;

import static org.hamcrest.Matchers.lessThan;



import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.ExtentTest;



import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;

import io.cucumber.java.en.When;

import io.restassured.response.Response;



public class ReqresApi{

ExtentReports extentReport=Hooks.extentReport;

ExtentTest extentTest=Hooks.extentTest;

Response res;

@Given("I have a base URI of {string}")

public void i_have_a_base_uri_of(String URL) {

baseURI=URL;

}



@When("I send a GET request to {string}")

public void i_send_a_get_request_to(String endPoint) {

extentTest=extentReport.createTest("GET Request");

extentTest.pass("GET request sent to " + endPoint);

res=given()

.log().all()

.when()

.get(endPoint);

}



@Then("the response status code should be {int}")

public void the_status_code_should_be(Integer stsCode) {

res.then()

.log().all()

.statusCode(stsCode);

extentTest.info("Expected status code " +stsCode+", Actual status code " + res.getStatusCode());

extentTest.pass("Status code validation passed");

}

@Then("the response body should contain {string}")

public void the_response_body_should_contain(String expectedResult) {

res.then()

.log().all()

.body(containsString(expectedResult));

extentTest.info("Response body contains: " +expectedResult);

extentTest.pass("Response Body validation passed");

}

@When("I send a POST request to {string} with body as {string}")

public void i_send_a_post_request_to_with_body_as(String endPoint, String reqBody) {

extentTest=extentReport.createTest("POST Request");

extentTest.pass("POST request to " + endPoint + " with body as " + reqBody);

res=given()

.log().all()

.body(reqBody)

.when()

.post(endPoint);

extentTest.pass("POST request sent to : " + endPoint + "with body: " + reqBody);

}



@Then("the response time less then {long}")

public void the_response_time_less_then(Long milliSec) {

extentTest.info("Excepted response time " + milliSec + ",Actual response time" + res.getTime());

res.then()

.time(lessThan(milliSec));

extentTest.pass("The response time validation passed");

}

@When("I send a PUT request to {string} with body as {string}")

public void i_send_a_put_request_to_with_body_as(String endPoint, String reqBody) {

extentTest=extentReport.createTest("PUT Request");

extentTest.pass("PUT request to " + endPoint + " with body as " + reqBody);

res=given()

.log().all()

.body(reqBody)

.when()

.put(endPoint);

extentTest.pass("PUT request send to: " + endPoint + " with body: " + reqBody);

}

@When("I send a PATCH request to {string} with body as {string}")

public void i_send_a_patch_request_to_with_body_as(String endPoint, String reqBody) {

extentTest=extentReport.createTest("PATCH Request");

extentTest.pass("PATCH request to " + endPoint + "with body as" + reqBody);

res=given()

.log().all()

.body(reqBody)

.when()

.patch(endPoint);

extentTest.pass("PATCH request sent to: " + endPoint + " with body: " + reqBody);

}

@When("I send a DELETE request to {string}")

public void i_send_a_delete_request_to(String endPoint) {

extentTest=extentReport.createTest("DELETE Request");

extentTest.pass("DELETE request sent to " + endPoint);

res=given()

.log().all()

.when()

.delete(endPoint);

extentTest.pass("DELETE request send to: " + endPoint);

}

}