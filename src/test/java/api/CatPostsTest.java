package api;

import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import json.entity.Post;
import json.entity.User;
import json.parser.JacksonDataParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatPostsTest {

    @Test
    public void getAuthorWithMostOccurrences() {
        JsonPath json = getJson("https://cat-fact.herokuapp.com", "/facts");
        List<String> first = json.get("all.user.name.first");
        List<String> last = json.get("all.user.name.last");
        List<String> names = new ArrayList<>();
        for (int i = 0; i < first.size(); i++) {
            names.add(first.get(i) + " " + last.get(i));
        }

        String name = getMostOccurrences(names);
        Assert.assertEquals(name, "Kasimir Schulz");
    }

    @Test
    public void countUpvotes() {
        JsonPath json = getJson("https://cat-fact.herokuapp.com", "/facts");
        List<String> firstNames = json.get("all.user.name.first");
        List<String> lastNames = json.get("all.user.name.last");
        List<Integer> eachVote = json.get("all.upvotes");

        List<String> authors = new ArrayList<>();
        for (int i = 0; i < firstNames.size(); i++) {
            authors.add(firstNames.get(i) + " " + lastNames.get(i));
        }

        Map<String, Integer> authorVotes = new HashMap<>();
        for (int i = 0; i < authors.size(); i++) {
            String author = authors.get(i);
            int vote = eachVote.get(i);
            if (authorVotes.get(author) != null) {
                int sum = authorVotes.get(author) + vote;
                authorVotes.put(author, sum);
            }else {
                authorVotes.put(author, vote);
            }
        }

        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : authorVotes.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        Assert.assertEquals(maxEntry.getKey(), "Alex Wohlbruck");
    }

    @Test
    public void getAuthorWithMostOccurrencesDeserialize() {
        List<Object> post = JacksonDataParser.parseJSON("https://cat-fact.herokuapp.com/facts", "all", new TypeReference<List<Post>>(){});
        int maximum = 1;
        int current = 0;
        User withMaxOccurrence = null;
        for (int i = 0; i < post.size(); i++) {
            for (int j = i; j < post.size(); j++) {
                User firstUser = ((Post) post.get(i)).getUser();
                User secondUser = ((Post) post.get(j)).getUser();
                if (firstUser != null && firstUser.equals(secondUser))
                    current++;
                if (maximum < current) {
                    maximum = current;
                    withMaxOccurrence = firstUser;
                }
            }
            current = 0;
        }
        Assert.assertEquals(withMaxOccurrence != null ? withMaxOccurrence.toString() : null, "Alex Wohlbruck");
    }


    private String getMostOccurrences(List<String> list) {
        int maximum = 1;
        int current = 0;
        String maximumOccurrences = null;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j)))
                    current++;
                if (maximum < current) {
                    maximum = current;
                    maximumOccurrences = list.get(i);
                }
            }
            current = 0;
        }
        return maximumOccurrences;
    }


    private JsonPath getJson(String url, String endpoint) {
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        Response response = request.request(Method.GET, endpoint);
        return response.jsonPath();
    }
}
