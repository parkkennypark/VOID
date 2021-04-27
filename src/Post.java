/**
 * Defines a post.
 *
 * @author Kenny Park
 * @version April 20, 2021
 */
import java.text.SimpleDateFormat;
import java.util.Date;
public class Post {
    private int postID;
    private String identifier;
    private String muffin;
    private String subject;
    private String body;
    private String timeStamp;
    private String authorName;


    /*public Post(int postID, String identifier, String muffin, String subject, String body, String authorName) {
        this.postID = postID;
        this.identifier = identifier;
        this.subject = subject;
        this.authorName = authorName;
        this.muffin = muffin;
        this.body = body;
        this.timeStamp = this.getTimeStamp();
    }*/

    public Post() {
        this.subject = null;
        this.body = null;
    }

    public Post(String readFromFile) {
        for(int i = 0; i < 5; i++) {

        }
    }

    public Post(String subject, String body) {
        this.subject = subject;
        this.body = body;
        this.timeStamp = this.getTimeStamp();
    }

    public String toString() {
        return String.format("postID:%s,identifier:%s,muffin:%s,subject:%s,body:%s,authorName:%s", this.postID, this.identifier,
                this.muffin, this.subject, this.body, this.authorName);
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

    public String getTimeStamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(date);
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getMuffin() {
        return muffin;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
