package DummyAccounts_Lib;

public class DummyTestAccount {
	private String email;
	private String pW;
	private String friendToAdd;
	private String profileName;
	
	//Edit this class to your dummy email and pw for sign process.
	public DummyTestAccount () {
		//Edit Here
		this.email = "your dummy email";
		this.pW = "your dummy's pw";
		this.friendToAdd = "a second dummy account";
		this.profileName = "yourprofile";
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPW() {
		return pW;
	}
	
	public String getNameToAddFriend(){
		return friendToAdd;
	}
	
	public String getProfileName() {
		return profileName;
	}
	
	
}
