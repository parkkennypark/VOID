import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Comment
 * <p>
 * This program enable the comment functionality for the application
 *
 * @author Hao Zhou, Kenny Park
 * @version May 1, 2021
 */
public class Comment implements Serializable {
    private int postIDReplyingTo;   //the postID that's being replied
    private int profileID;
    private int commentID;  //the commentID
    private String text;    //the comment text
    private String timestamp;   //time

    public Comment(int postID) {
        this.postIDReplyingTo = postID;
        this.commentID = -1;
        this.timestamp = Application.getTimeStamp();
    }

    public Comment(int postIDReplyingTo, int commentID, int profileID, String text) {
        this.postIDReplyingTo = postIDReplyingTo;
        this.commentID = commentID;
        this.profileID = profileID;
        this.text = text;
    }

    public Comment() {
        text = null;
    }

    /**
     * reads in the information that was received and assgin values
     *
     * @param readFromFile the string sent to this method
     */
    public Comment(String readFromFile) {
        Scanner scan = new Scanner(readFromFile).useDelimiter(",");
        int i = 0;
        while (scan.hasNext()) {
            if (i == 0) {
                this.postIDReplyingTo = Integer.parseInt(scan.next());
            } else if (i == 1) {
                this.commentID = Integer.parseInt(scan.next());
            } else if (i == 2) {
                this.text = scan.next();
            }
            i++;
        }
    }

    /**
     * print the information in a specific format
     *
     * @return a string that contains varibales in a specific format
     */
    public String toString() {
        return String.format("%s,%s,%s", String.valueOf(this.postIDReplyingTo), String.valueOf(this.commentID),
                this.text);
    }

    /**
     * return the postIDReplyingTo
     *
     * @return int the postIDReplyingTo
     */
    public int getPostIDReplyingTo() {
        return postIDReplyingTo;
    }

    /**
     * return the commentID
     *
     * @return int the commentID
     */
    public int getCommentID() {
        return commentID;
    }

    public int getProfileID() {
        return profileID;
    }

    /**
     * return the text
     *
     * @return String the text
     */
    public String getText() {
        return text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    /**
     * setes the postIDReplyingTo to the input
     *
     * @param int postIDReplyingTo the postID that was being replied
     */
    public void setPostIDReplyingTo(int postIDReplyingTo) {
        this.postIDReplyingTo = postIDReplyingTo;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    /**
     * setes the commentID to the input
     *
     * @param int commentID the commentID of the comment
     */
    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    /**
     * setes the text to the input
     *
     * @param String text the text of the comment
     */
    public void setText(String text) {
        this.text = text;
    }
}
