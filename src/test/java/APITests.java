import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;


public class APITests {

    @Test (priority = 1)
    void test1(){

        Response response = get("https://jsonplaceholder.typicode.com/posts");

        System.out.println("Response: " + response.asString());

        int userId = response.jsonPath().getInt("userId[0]");
        Assert.assertEquals(userId, 1);

        int id = response.jsonPath().getInt("id[0]");
        Assert.assertEquals(id, 1);

        String title = response.jsonPath().getString("title[0]");
        Assert.assertEquals(title, "sunt aut facere repellat provident " +
                "occaecati excepturi optio reprehenderit");

        String body = response.jsonPath().getString("body[0]");
        Assert.assertEquals(body, "quia et suscipit\nsuscipit recusandae " +
                "consequuntur expedita et cum\nreprehenderit molestiae ut " +
                "ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");

        get("https://jsonplaceholder.typicode.com/posts").then()
                .assertThat()
                .body("userId[0]", equalTo(1))
                .body("id[0]", equalTo(1))
                .body("title[0]", equalTo("sunt aut facere repellat provident " +
                        "occaecati excepturi optio reprehenderit"))
                .body("body[0]", equalTo("quia et suscipit\nsuscipit recusandae " +
                        "consequuntur expedita et cum\nreprehenderit molestiae ut " +
                        "ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"));

    }

    @Test (priority = 2)
    void test2(){

        given().get("https://jsonplaceholder.typicode.com/posts").then().body("userId[0]", equalTo(1));
        given().get("https://jsonplaceholder.typicode.com/posts").then().body("id[0]", equalTo(1));
        given().get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .body("title[0]", equalTo("sunt aut facere repellat " +
                        "provident occaecati excepturi optio reprehenderit"));
        given().get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .body("body[0]", equalTo("quia et suscipit\nsuscipit recusandae" +
                        " consequuntur expedita et cum\nreprehenderit molestiae ut " +
                        "ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"));


    }
}
