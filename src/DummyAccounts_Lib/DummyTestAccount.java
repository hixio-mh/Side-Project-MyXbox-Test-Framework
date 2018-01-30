package DummyAccounts_Lib;

public class DummyTestAccount {
	private String email;
	private String pW;
	private String friendToAdd;
	private String profileName;
	private String realName;
	private String firstRealName;
	
	

	

	//Edit this class to your dummy email and pw for sign process.
	public DummyTestAccount () {
		//Edit Here
		this.email = "";
		this.pW = "";
		this.friendToAdd = "";
		this.profileName = "";
		this.realName = "";
		this.firstRealName = "";
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
	
	public String getRealName() {
		return realName;
	}
	public String getFirstRealName() {
		return firstRealName;
	}
}
