package comm.seleniumUIAutomation.Script;

import org.testng.annotations.Test;

import com.SeleniumUIAutomation.TestBase.TestBase;

public class VerifyUserIsAbleToLogin extends TestBase {
	//Logger log = Logger.getLogger(VerifyUserIsAbleToLogin.class);
//Login Verification
	String username = prop.getProperty("email");
	String password = prop.getProperty("password");

	@Test
	public void loginToApplication() {
		login();

	}

}