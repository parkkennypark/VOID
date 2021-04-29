import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 *
 * @author Kenny Park
 * @version
 */
public class Comment {
    private int postIDReplyingTo;
    private int commentID;
    private String text;
    private String timestamp;

    public Comment(int postIDReplyingTo, int commentID, String text) {
        this.postIDReplyingTo = postIDReplyingTo;
        this.commentID = commentID;
        this.text = text;
    }

    public Comment() {
        text = null;
    }

    public Comment(String readFromFile) {
        Scanner scan = new Scanner(readFromFile).useDelimiter(",");
        int i = 0;
        while(scan.hasNext()) {
            if(i == 0) {
                this.postIDReplyingTo = Integer.parseInt(scan.next());
            } else if (i == 1) {
                this.commentID = Integer.parseInt(scan.next());
            } else if (i == 2) {
                this.text = scan.next();
            }
            i++;
        }
    }

    public String toString() {
        return String.format("%s,%s,%s", String.valueOf(this.postIDReplyingTo), String.valueOf(this.commentID),
                this.text);
    }

    public int getPostIDReplyingTo() {
        return postIDReplyingTo;
    }

    public int getCommentID() {
        return commentID;
    }

    public String getText() {
        return text;
    }

    public String getTimestamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(date);
    }

    public void setPostIDReplyingTo(int postIDReplyingTo) {
        this.postIDReplyingTo = postIDReplyingTo;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public void setText(String text) {
        this.text = text;
    }
}