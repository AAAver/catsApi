package json.parser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class JacksonDataParser {
    public static List<Object> parseJSON(String url, String rootNode, TypeReference type) {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Object> objects = null;
        try {
            JsonNode root = objectMapper.readTree(new URL(url)).get(rootNode);
            objects = objectMapper.readValue(String.valueOf(root), type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }
}
