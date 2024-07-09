package com.gorest.testsuite;
/**
 * Verify the if the total record is 25
 * 2. Verify the if the title of id = 139905 is equal to "Eum consequatur acidus admoveo ascit absorbeo amor vado conspergo."
 * 3. Check the single user_id in the Array list (7015123)
 * 4. Check the multiple ids in the ArrayList (7015118, 7015117, 7015114)
 * 5. Verify the body of userid = 7015124 is equal “Desidero vorax adsum. Non confero clarus.
 * Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus
 * vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio
 * tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
 * acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”
 */

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
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
    public void testPostAssertion(){

        response.body("size()", equalTo(20))
                .body("find {it.id == 139905}.title", equalTo("Eum consequatur acidus admoveo ascit absorbeo amor vado conspergo."))
                .body("user_id", hasItem(7015123))
                .body("user_id", hasItems(7015118,7015117,7015114))
                .body("find { it.user_id == 7015124}.body",equalTo("Ultio cattus patrocinor. Sint cubitum vapulus. Valetudo tertius excepturi. Convoco delego sollers. Supellex antepono admoveo. Culpa appello deleniti. Aro dolores certo. Avaritia testimonium degero. Vir culpa temeritas. Vel modi theca. Voluptas vado error. Abduco sulum desipio. Suffoco quibusdam spiritus. Ea convoco velit."));

    }

}
