package trainingxyz;

import models.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ChallengeTwo {

    @Test
    public void getProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php";
        given().
                queryParam("id", 16).
        when().
                get(endpoint).
        then().
                assertThat().
                    statusCode(200).
                    header("Content-Type", equalTo("application/json")).
                    body("id", equalTo("16")).
                    body("name", equalTo("Iron 65 mg (150 caplets)")).
                    body("description", equalTo("Iron: Re+vive, re+fuel, re+generate, naturally, with H+SPORT Iron. Our supplements are sourced from the purest ingredients—and potency is proven in independent laboratory tests. Iron is essential for transporting oxygen in the body and for the formation of red blood cells.")).
                    body("price", equalTo("14.00")).
                    body("category_id", equalTo("4")).
                    body("category_name", equalTo("Supplements"));
    }
}
