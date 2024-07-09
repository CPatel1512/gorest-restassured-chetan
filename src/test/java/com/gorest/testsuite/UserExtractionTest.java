package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Write the following test inside UserExtractionTest class.
 * Extraction Example
 * 1. Extract the All Ids
 * 2. Extract the all Names
 * 3. Extract the name of 5th object
 * 4. Extract the names of all object whose status = inactive
 * 5. Extract the gender of all the object whose status = active
 * 6. Print the names of the object whose gender = female
 * 7. Get all the emails of the object where status = inactive
 * 8. Get the ids of the object where gender = male
 * 9. Get all the status
 * 10. Get email of the object where name = Nawal Johar
 * 11. Get gender of id = 7015048
 */

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        response = given()
                .param("page", 1)
                .param("per_page", 20)
                .when()
                .get("/public/v2/users")

                .then().statusCode(200);
    }
    @Test
    public void extractUserData(){
        //Extract the All Ids
        List<Integer> allIds = response.extract().path("id");
        System.out.println("The all Ids:" + allIds);
        //Extract the all Names
        List<String> allNames = response.extract().path("name");
        System.out.println("The all Names: " + allNames);
        //Extract the name of 5th object
        String fifthName = response.extract().path("name[4]");
        System.out.println("The Name of 5th Object: " + fifthName);
        //Extract the names of all object whose status = inactive
        List<String> inactiveNames = response.extract().path("findAll { it.status == 'inactive' }.name");
        System.out.println("The Name Of Inactive Objetcs : " + inactiveNames);
        //Extract the gender of all the object whose status = active
        List<String> activeGenders = response.extract().path("findAll { it.status == 'active' }.gender");
        System.out.println("The Gender Of all Active Objects :" + activeGenders);
        //Print the names of the object whose gender = female
        List<String> femaleNames = response.extract().path("findAll { it.gender == 'female' }.name");
        System.out.println("The Gender is Female: " + femaleNames);
        //Get all the emails of the object where status = inactive
        List<String> inactiveEmails = response.extract().path("findAll { it.status == 'inactive' }.email");
        System.out.println("The emails of Inactive objects:" + inactiveEmails);
        //Get the ids of the object where gender = male
        List<Integer> maleIds = response.extract().path("findAll { it.gender == 'male' }.id");
        System.out.println("The Ids whose gender is Male: " + maleIds);
        //Get all the status
        List<String> allStatuses = response.extract().path("status");
        System.out.println("Get all status: " + allStatuses);
        //Get email of the object where name = Nawal Johar
        String emailOfjohar = response.extract().path("find{it.name == 'Nawal Johar'}.email");
        System.out.println("Get Email of Nawal Johar: " + emailOfjohar);
       //Get gender of id = 7015048
        String genderOfId = response.extract().path("find{it.id == 7015048}.gender");
        System.out.println("Get the Gender of Id Is 7015048: " + genderOfId);
    }



}
