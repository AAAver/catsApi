package json.entity;

import java.util.Map;
import java.util.Objects;

public class User {

    private Map<String,String> name;
    private String _id;
    private String firstName;
    private String lastName;

    public User(Map<String,String> name, String _id) {
        this.name = name;
        this._id = _id;
    }
    public User(){}

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Map<String,String> getName() {
        return name;
    }

    public void setName(Map<String,String> name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName() {
        this.firstName = name.get("first");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName() {
        this.lastName = name.get("last");
    }

    @Override
    public String toString() {
        this.firstName = name.get("first");
        this.lastName = name.get("last");
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(_id, user._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }
}
