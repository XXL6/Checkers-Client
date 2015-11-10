package loginHandler;

public class LoginInfoParser {
	
	private String username;
	private String address;
	
	public LoginInfoParser() {
		
	}
	
	public LoginInfoParser(String username, String address) {
		this.username = username;
		this.address = address;
	}
	
	public void setData(String username, String address) {
		this.username = username;
		this.address = address;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getAddress() {
		return address;
	}
	//Checks to make sure the username contains no spaces and is not greater than 20
	//characters (to prevent massive names being entered)
	public boolean usernameValid() {
		if (username.contains(" ") || username.length() > 20) {
			return false;
		} else {
			return true;
		}		
	}//end usernameValid
	
	//checks to make sure the address falls within IP address format
	//ie. each number not greater than 255 or less than 0 and has exactly
	//4 numbers separated by periods
	public boolean addressValid() {
		String[] addressContents = address.split("\\.");
		if (addressContents.length != 4) {
			return false;
		} else {
			for(String content : addressContents) {
				if (Integer.parseInt(content) > 255 || Integer.parseInt(content) < 0) {
					return false;
				}
			}
			return true;
		}
	}
}//end class
