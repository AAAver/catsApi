package api;

import io.restassured.http.Method;
import json.entity.Post;
import json.entity.User;
import helpers.CounterHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;

public class CatPostsTest {

    @Test
    public void getAuthorWithMostOccurrences() {
        baseURI = "https://cat-fact.herokuapp.com";
        List<Post> posts = given()
                .request(Method.GET, "/facts")
                .jsonPath()
                .getList("all", Post.class);

        HashMap<User, Integer> userToPostsNumber = CounterHelper.sumPostsOfUser(posts);
        User withMaxOccurrence = (User) CounterHelper.getMaxOccurrence(userToPostsNumber);

        Assert.assertEquals(withMaxOccurrence.toString(), "Alex Wohlbruck");
    }
}