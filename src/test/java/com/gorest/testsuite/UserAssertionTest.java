package com.gorest.testsuite;
/**
 * Assert the following:
 * 1. Verify the if the total record is 20
 * 2. Verify the if the name of id = 5914197 is equal to ”Bhilangana Dhawan”
 * 3. Check the single ‘Name’ in the Array list (Dev Bhattacharya)
 * 4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
 * 5. Verify the emai of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
 * 6. Verify the status is “Active” of user name is “Amaresh Rana”
 * 7. Verify the Gender = male of user name is “Dhanalakshmi Pothuvaal”
 */

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
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

    // Verify  if the total record is 20
    @Test
    public void test001() {

        response.body("size()", equalTo(20));
    }
    //Verify the if the name of id =  7015043 is equal to "Leela Kocchar"
    @Test
    public void test002(){
        response.body("find{it.id == 7015043}.name", equalTo("Leela Kocchar"));
    }
    //3. Check the single ‘Name’ in the Array list ( Daksha Patel)
    @Test
    public void test003(){
      response.body("name", hasItem("Daksha Patel"));

    }
    //Check the multiple ‘Names’ in the ArrayList (Deeptimoyee Nair, Daksha Patel, Menka Marar, Nawal Johar )
    @Test
    public void test004(){
        response.body("name",hasItems("Deeptimoyee Nair",
                                        "Daksha Patel",
                                        "Menka Marar",
                                        "Nawal Johar"));
    }
    //Verify the email of userid = 7015051 is equal “patel_daksha@pacocha-oreilly.example”
@Test
    public void test005(){
        response.body("find { it.id == 7015051 }.email", equalTo("patel_daksha@pacocha-oreilly.example"));
}
//Verify the status is “Active” of user name is “Deeptimoyee Nair”
    @Test
    public void test006(){
    response.body("find{it.name == 'Deeptimoyee Nair'}.status",equalTo("active"));
    }

    //Verify the Gender = male of user name is “Nawal Johar"
    @Test
    public void test007(){
        response.body("find { it.name == 'Nawal Johar' }.gender", equalTo("male"));
    }
}



