package model;

public class UserInfoList {

    String _id;
    String username;
    String image;

    public UserInfoList(String _id, String username, String image) {

        this._id = _id;
        this.username = username;
        this.image = image;
    }


    public String get_id() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public String getImage() {
        return image;
    }
}
