package com.gorest.testsuite;

/**
 * Write the following test inside ProductsExtractionTest class.
 * Extraction Example
 * 1. Extract the title
 * 2. Extract the total number of record
 * 3. Extract the body of 15th record
 * 4. Extract the user_id of all the records
 * 5. Extract the title of all the records
 * 6. Extract the title of all records whose user_id = 5914200
 * 7. Extract the body of all records whose id = 93957
 */

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        response = given()
                .param("page", 1)
                .param("per_page", 20)
                .when()
                .get("/public/v2/posts")

                .then().statusCode(200);
    }
    @Test
    public void extractPostData(){
        //Extract the title
        List<String> titles = response.extract().path("title");
        System.out.println("The Title Of all : " + titles);
        //Extract the total number of record
       int totalRecords = response.extract().path("size()");
        System.out.println("The Total Number: " + totalRecords);
       //Extract the body of 15th record
       String bodyOf15th = response.extract().path("body[14]");
        System.out.println("The Body Of 15th Record is: " + bodyOf15th);
       //Extract the user_id of all the records
       List<Integer> userIds = response.extract().path("user_id");
        System.out.println("User Ids of All Record: " + userIds);
       //Extract the title of all the records
       List<String> allTitles = response.extract().path("title");
        System.out.println("The title of All Records: " + allTitles);
        //Extract the title of all records whose user_id = 7015123
       List<String> titlesOfUser = response.extract().path("findAll {it.user_id == 7015123}.title");
        System.out.println("The Record of userId 7015123 : " + titlesOfUser);
        //Extract the body of all records whose id =139914
       List<String> bodiesOfId = response.extract().path("findAll {it.id == 139914}.body");
        System.out.println("The Body of Id 139914: " + bodiesOfId);

    }


}
