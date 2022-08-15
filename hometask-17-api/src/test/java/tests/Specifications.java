package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;

public class Specifications {

    private static final String baseUri = "https://reqres.in/";

    private static RequestSpecification setRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .build();
    }

    private static ResponseSpecification setResponseSpec() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }

    static void installSpecifications() {
        requestSpecification = setRequestSpec();
        responseSpecification = setResponseSpec();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
