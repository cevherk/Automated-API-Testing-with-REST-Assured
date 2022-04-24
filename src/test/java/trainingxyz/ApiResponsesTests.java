package trainingxyz;

import models.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiResponsesTests {

    @Test
    public void getProducts(){
        String endpoint = "http://localhost:8888/api_testing/product/read.php";
        given().
        when().
            get(endpoint).
        then().
             log().
                body().
                assertThat().
                statusCode(200).
                body("records.size()", greaterThan(0)).
                body("records.id", everyItem(notNullValue())).
                body("records.name", everyItem(notNullValue())).
                body("records.description", everyItem(notNullValue())).
                body("records.price", everyItem(notNullValue())).
                body("records.category_id", everyItem(notNullValue())).
                body("records.category_name", everyItem(notNullValue())).
                body("records.id[0]", equalTo("1008"));
    }

    @Test
    public void getProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php";
        var response =
                given().
                        queryParam("id", 2).
                when().
                        get(endpoint).
                then().
                assertThat().
                        statusCode(200).
                        body("id", equalTo("2")).
                        body("name", equalTo("Cross-Back Training Tank")).
                        body("description", equalTo("The most awesome phone of 2013!")).
                        body("price", equalTo("299.00")).
                        body("category_id", equalTo("2")).
                        body("category_name", equalTo("Active Wear - Women"));
    }

    @Test
    public void createProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        String body = """
                {
                "name": "Water Bottle",
                "description": "Red water bottle. Holds 61 ounces",
                "price": 18,
                "category_id": 3
                }
                """;
        var response = given().body(body).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/update.php";
        String body = """
                {
                "id": 1000,
                "name": "Water Bottle",
                "description": "Red water bottle. Holds 61 ounces.",
                "price": 18,
                "category_id": 3
                }
                """;
        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/delete.php";
        String body = """
                {
                "id":18
                }
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }

    @Test
    public void createSerializedProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        Product product = new Product(
                "Water Bottle 2",
                "Green water bottle. Hold 64 ounces",
                55,
                3
        );
        var response = given().body(product).when().post(endpoint).then();
        response.log().body();
    }

}