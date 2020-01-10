package model;

public class TweetResponse {

    private String tweet;
    private String tweetImage;
    private String username;
    private String fullname;
    private  int like;
    private int dislike;
    private int comment;

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getTweetImage() {
        return tweetImage;
    }

    public void setTweetImage(String tweetImage) {
        this.tweetImage = tweetImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public TweetResponse(String tweet, String tweetImage, String username, String fullname, int like, int dislike, int comment) {
        this.tweet = tweet;
        this.tweetImage = tweetImage;
        this.username = username;
        this.fullname = fullname;
        this.like = like;
        this.dislike = dislike;
        this.comment = comment;
    }
}
