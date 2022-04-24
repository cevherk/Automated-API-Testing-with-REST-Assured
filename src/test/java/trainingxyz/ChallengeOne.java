package trainingxyz;

import models.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ChallengeOne {

    @Test
    public void createSweatband(){
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        String body = """
                {
                "name": "Sweatband",
                "description": "Sweat bir band.",
                "price": 5,
                "category_id": 3
                }
                """;
        var response = given().body(body).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateSweatband(){
        String endpoint = "http://localhost:8888/api_testing/product/update.php";
        String body = """
                {
                "id": 1008,
                "price": 6
                }
                """;
        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void getSweatband(){
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php";
        var response =
                given().
                        queryParam("id", 1008).
                        when().
                        get(endpoint).
                        then();
        response.log().body();
    }

    @Test
    public void deleteSweatband(){
        String endpoint = "http://localhost:8888/api_testing/product/delete.php";
        String body = """
                {
                "id":1008
                }
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }
}
