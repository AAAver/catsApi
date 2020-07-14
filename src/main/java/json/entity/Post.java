package json.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class Post {
    @JsonProperty("_id")
    private String id;
    private String text;
    private String type;
    private User user;
    private int upvotes;
    private boolean userUpvoted;

    public Post(String id, String text, String type, User user, int upvotes, boolean userUpvoted) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
        this.userUpvoted = userUpvoted;
    }
    public Post(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isUserUpvoted() {
        return userUpvoted;
    }

    public void setUserUpvoted(boolean userUpvoted) {
        this.userUpvoted = userUpvoted;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{" +
                "text='" + text + '\'' +
                ", user=" + user +
                '}';
    }
}
