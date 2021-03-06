package problemSolving;

public class pswdValidator {

	public static boolean passwordCheck(String pswd) {
		String str = pswd;
		if(str.length() > 7)
			if(str.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%])[a-zA-Z0-9!@#$%]+{8,}$"))
			   return true;
	
	    return false;
	}
	
	public static boolean mailCheck(String mail) {
		String str = mail;
		if(str.matches("^[a-zA-Z0-9!#$%._-]+(\\.[a-zA-Z0-9!#$%])*@([a-zA-Z0-9]+([a-zA-Z0-9]*)\\.)+[a-zA-Z]+$"))
			return true;
	
	    return false;
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(passwordCheck("a1bA@cd2"));
		System.out.println(mailCheck("par.am_234.test@test.com"));
		System.out.println(mailCheck("Pj-ya123d!#$av.qa@te12st.ser12ver.com"));
	}

}
