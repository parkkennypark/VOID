import java.io.Serializable;

/**
 *
 *
 * @author Danilo Dragovic
 * @version 
 */
public class Profile implements Serializable {
	private String identifier;
	private String password;
	private int muffinIndex;
	private int profileID;

	public Profile(int profileID, String identifier, String password, int muffinIndex) {
		this.profileID = profileID;
		this.identifier = identifier;
		this.password = password;
		this.muffinIndex = muffinIndex;
	}
	
	public Profile(String input) {
		this.identifier = input.substring(input.indexOf('=') + 1, input.indexOf(", muffinIndex="));
		this.muffinIndex = Integer.parseInt(input.substring(input.indexOf(", muffinIndex=") + 14, input.indexOf(", profileID=")));
		this.profileID = Integer.parseInt(input.substring(input.indexOf(", profileID=") + 12, input.length() - 1));
	}

	public Profile() {
		identifier = "invalid";
		muffinIndex = 0;
		profileID = -1;
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public void setMuffinIndex(int muffinIndex) {
		this.muffinIndex = muffinIndex;
	}
	
 	public void setProfileID(int profileID) {
 		this.profileID = profileID;
 	}
	
 	public String getIdentifier() {
 		return identifier;
 	}
 	
 	public int getMuffinIndex() {
 		return muffinIndex;
 	}
 	
 	public int getProfileID() {
 		return profileID;
 	}

	public String getPassword() {
		return password;
	}

	public String toString() {
		return "Profile<identifier=" + identifier + ", muffinIndex=" + muffinIndex + ", profileID=" + profileID + ">";
	}
}

