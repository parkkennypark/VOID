/**
 * Defines a post.
 *
 * @author Kenny Park
 * @version April 20, 2021
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class Post {
    private int postID;
    private int profileID;
    private String subject;
    private String body;
    private String timeStamp;


    public Post(int postID, int profileID, String subject, String body, String timeStamp) {
        this.postID = postID;
        this.profileID = profileID;
        this.subject = subject;
        this.body = body;
        this.timeStamp = timeStamp;
    }

    public Post() {
        this.subject = null;
        this.body = null;
    }

    public Post(String readFromFile) {
        Scanner scan = new Scanner(readFromFile).useDelimiter(",");
        int i = 0;
        while(scan.hasNext()) {
            if(i == 0) {
                this.postID = Integer.parseInt(scan.next());
            } else if (i == 1) {
                this.profileID = Integer.parseInt(scan.next());
            } else if (i == 2) {
                this.subject = scan.next();
            } else if (i == 3) {
                this.body = scan.next();
            }
            i++;
        }
    }

    public Post(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public String toString() {
        /*return String.format("postID:%s,identifier:%s,muffin:%s,subject:%s,body:%s", this.postID, this.identifier,
                this.muffin, this.subject, ts.body);*/

        return String.format("%s,%s,%s,%s", String.valueOf(this.postID), String.valueOf(this.profileID),
                this.subject, this.body);
    }

    public int getProfileID() {
        return this.profileID;
    }

    public int getPostID() {
        return postID;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
