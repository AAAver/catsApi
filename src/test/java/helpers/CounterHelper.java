package helpers;

import json.entity.Post;
import json.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CounterHelper {
    public static HashMap<User, Integer> sumPostsOfUser(List<Post> posts) {
        HashMap<User, Integer> occurrence = new HashMap<>();
        for (Post post : posts) {
            if (post.getUser() == null) {
                continue;
            }
            if (!occurrence.containsKey(post.getUser())) {
                occurrence.put(post.getUser(), 1);
            } else {
                occurrence.put(post.getUser(), occurrence.get(post.getUser()) + 1);
            }
        }
        return occurrence;
    }

    public static Object getMaxOccurrence(HashMap<?, Integer> occurrence) {
        Map.Entry<?, Integer> maxEntry = null;

        for (Map.Entry<?, Integer> entry : occurrence.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        return Objects.requireNonNull(maxEntry).getKey();
    }
}
