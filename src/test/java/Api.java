


import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;


import io.restassured.response.Response;

import java.sql.Array;

    public class Api {
     /*
	 Positive Scenario
	 when I send a GET Request to https://restful-booker.herokuapp.com/booking
	 and I accept type "application/json"  ==> Means API is accepting data just in Json Format
	 then status code should be 200
	 and content type should be "application/json" ==> Response body must be in Json format
	 */

        @Test
        public void semaver() {
            Response response = given().when().get("https://restful-booker.herokuapp.com/booking");

            response.prettyPrint();

            //    response.then().assertThat().statusCode(200).contentType("application/json");
        }

    /*
	 Negative Scenario
	 when I send a GET Request to https://restful-booker.herokuapp.com/booking/1001
	 then status code should be 404
	 and Response Body contains "Not Found"
	 and Response Body does not contain "Suleyman"
	*/

        //url base
        //uri base + son kismi
        @Test
        public void semaver2(){

            Response response = given().when().get("https://restful-booker.herokuapp.com/booking/1001");

            response.then().assertThat().statusCode(404);

            Assert.assertTrue(response.asString().contains("Not Found"));
            Assert.assertFalse(response.asString().contains("Suleyman"));


        }
/*
		 	                    Warm Up (10 minutes)
		 1) Create a class and name it as GetRequest03
		 2) When I send a GET Request to https://restful-booker.herokuapp.com/booking/5
		    Then
		    HTTP Status code should be "200"
		    And  Content type should be in "JSON" format
		    And  Status Line should be "HTTP/1.1 200 OK"
		    And  response body does not contain "Not Found"
		    And  response body contains "bookingdates"
	*/

        @Test
        public void semaver3() {

            Response response = given().when().get("https://restful-booker.herokuapp.com/booking/5");

            response.prettyPrint();

            response.then().assertThat().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");

            Assert.assertFalse(response.asString().contains("Not Found"));
            Assert.assertTrue(response.asString().contains("bookingdates"));

/*
		When I send a GET request to REST API URL
		https://restful-booker.herokuapp.com/booking/1
	    And Accept type is “application/JSON”
	    Then
	    HTTP Status Code should be 200
	    And Response format should be "application/JSON"
	    And firstname should be "Susan"
	    And lastname should be "Brown"
	    And checkin date should be "2015-02-16"
	    And checkout date should be "2017-06-20"
   */

        }

        @Test
        public void semaver4(){

            Response response = given().
                    //accept("application/JSON").
                            when().
                            get("https://restful-booker.herokuapp.com/booking/10");

            response.prettyPrint();
            response.then().
                    assertThat().
                    contentType("application/JSON").
                    statusCode(200).
                    body("firstname", equalTo("Jim"),
                            "lastname", equalTo("Brown"),
                            "bookingdates.checkout", equalTo("2018-01-18"),
                            "bookingdates.checkin", equalTo("2016-01-05"));
        }
//    @Test
//    public void semaver5(){
//
//        Response response = given().accept("application/JSON").when().put("https://restful-booker.herokuapp.com/booking/1");
//
//        response.then().body("firstname", Matchers.equalTo("Emrah"));
//
//    }

/*
		When
		    I send a GET request to REST API URL
			http://dummy.restapiexample.com/api/v1/employees
	        And accept type is “application/JSON”
	    Then
		    HTTP Status Code should be 200
		    And Response format should be "application/JSON"
		    And there should be 24 employees
		    And "Ashton Cox" should be one of the employees
		    And 21, 61, and 23 should be among the employee ages
    */


        @Test
        public void semaver5() {

            Response response = given().when().get("http://dummy.restapiexample.com/api/v1/employees");

            response.prettyPrint();
            //  Array array = new Array[];
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.reader(response);

            response.then().
                    statusCode(200).
                    contentType(ContentType.JSON).
                    body("data.id", equalTo("61"));


            Assert.assertTrue(response.asString().contains("Ashton Cox"));
            Assert.assertTrue(response.asString().contains("61"));
            Assert.assertTrue(response.asString().contains("21"));
            Assert.assertTrue(response.asString().contains("23"));
            // Assert.assertEquals(hasSize(24),"data.id");

        }

    }




}
