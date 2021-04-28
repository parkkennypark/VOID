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
    private String identifier;
    private String muffin;
    private String subject;
    private String body;
    private String timeStamp;


    public Post(int postID, String identifier, String muffin, String subject, String body) {
        this.postID = postID;
        this.identifier = identifier;
        this.subject = subject;
        this.muffin = muffin;
        this.body = body;
        this.timeStamp = this.getTimeStamp();
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
                this.postID = scan.next();
            } else if (i == 1) {
                this.identifier = scan.next();
            } else if (i == 2) {
                this.muffin = scan.next();
            } else if (i == 3) {
                this.subject = scan.next();
            } else if (i == 4) {
                this.body = scan.next();
            }
            i++;
        }
    }

    public Post(String subject, String body) {
        this.subject = subject;
        this.body = body;
        this.timeStamp = this.getTimeStamp();
    }

    public String toString() {
        /*return String.format("postID:%s,identifier:%s,muffin:%s,subject:%s,body:%s", this.postID, this.identifier,
                this.muffin, this.subject, ts.body);*/hi

        return String.format("%s,%s,%s,%s,%s", this.postID, this.identifier,
                this.muffin, this.subject, ts.body);
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
