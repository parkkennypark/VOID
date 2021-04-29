/**
 *
 *
 * @author Danilo Dragovic
 * @version 
 */
public class Profile {
	private String identifier;
	private int muffinIndex;
	private int profileID;
	
	public Profile(String identifier, int muffinIndex, int profileID) {
		this.identifier = identifier;
		this.muffinIndex = muffinIndex;
		this.profileID = profileID;
	}
	
	public Profile(String input) {
		this.identifier = input.substring(input.indexOf('=') + 1, input.indexOf(", muffinIndex="));
		this.muffinIndex = Integer.parseInt(input.substring(input.indexOf(", muffinIndex=") + 14, input.indexOf(", profileID=")));
		this.profileID = Integer.parseInt(input.substring(input.indexOf(", profileID=") + 12, input.length() - 1));
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
 	
	public String toString() {
		return "Profile<identifier=" + identifier + ", muffinIndex=" + muffinIndex + ", profileID=" + profileID + ">";
	}
}

