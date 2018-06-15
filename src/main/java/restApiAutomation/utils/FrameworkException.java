package restApiAutomation.utils;

import org.testng.Reporter;

@SuppressWarnings("serial")
public class FrameworkException extends Exception {

	FrameworkException(String message) {
		super(message);
		Reporter.log(message);
	}

}
