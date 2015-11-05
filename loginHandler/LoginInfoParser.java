package loginHandler;

public class LoginInfoParser {
	
	private String username;
	private String address;
	
	public LoginInfoParser() {
		
	}
	
	public LoginInfoParser(String address, String username) {
		this.address = address;
		this.username = username;
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
	public boolean usernameValid() {
		if (username.contains(" ") || username.length() > 20) {
			return false;
		} else {
			return true;
		}		
	}//end usernameValid
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
