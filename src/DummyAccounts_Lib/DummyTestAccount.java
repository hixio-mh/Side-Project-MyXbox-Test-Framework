package DummyAccounts_Lib;

public class DummyTestAccount {
	private String email;
	private String pW;
	private String friendToAdd;
	
	//Edit this class to your dummy email and pw for sign process.
	public DummyTestAccount () {
		//Edit Here
		this.email = "yourdummyemail";
		this.pW = "anypw";
		this.friendToAdd = "IncensedPark285";
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
	
	
	
}
