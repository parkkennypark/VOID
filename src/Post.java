
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Post
 *
 * This program enable the Post functionality for the application
 *
 * @author Hao Zhou
 * @version May 1, 2021
 */
public class Post {
    private int postID; //the postID of the post
    private int profileID;  //the profileID of the user
    private String muffin;  //the favorite muffin of the user
    private String subject; //the subject of the post
    private String body;    //the body of the post
    private String timeStamp;   //time


    public Post(int postID, int profileID, String muffin, String subject, String body) {
        this.postID = postID;
        this.profileID = profileID;
        this.subject = subject;
        this.muffin = muffin;
        this.body = body;
    }

    public Post() {
        this.subject = null;
        this.body = null;
    }

    /**
     * reads in the information that was received and assgin values
     *
     * @param readFromFile the string sent to this method
     */
    public Post(String readFromFile) {
        Scanner scan = new Scanner(readFromFile).useDelimiter(",");
        int i = 0;
        while(scan.hasNext()) {
            if(i == 0) {
                this.postID = Integer.parseInt(scan.next());
            } else if (i == 1) {
                this.profileID = Integer.parseInt(scan.next());
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


    /**
     * print the information in a specific format
     *
     * @return a string that contains varibales in a specific format
     */
    public String toString() {
        /*return String.format("postID:%s,identifier:%s,muffin:%s,subject:%s,body:%s", this.postID, this.identifier,
                this.muffin, this.subject, ts.body);*/

        return String.format("%s,%s,%s,%s,%s", String.valueOf(this.postID), String.valueOf(this.profileID),
                this.muffin, this.subject, this.body);
    }

    /**
     * return the postID
     *
     * @return int the postID
     */
    public int getPostID() {
        return this.postID;
    }

    /**
     * return the profileID
     *
     * @return int the profileID
     */
    public int getProfileID() {
        return this.profileID;
    }

    /**
     * return the body
     *
     * @return String the body
     */
    public String getBody() {
        return body;
    }

    /**
     * return the subject
     *
     * @return String the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * return the time
     *
     * @return String the time
     */
    public String getTimeStamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * return the favorite muffin
     *
     * @return string the muffin
     */
    public String getMuffin() {
        return muffin;
    }

    /**
     * setes the subject to the input
     *
     * @param String subject, the subject of the post
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * setes the body to the input
     *
     * @param String body, the body of the post
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * setes the postID to the input
     *
     * @param int postID, the postID of the post
     */
    public void setPostID(int postID) {
        this.postID = postID;
    }


}
