package baseClasses;

import org.testng.*;
import org.testng.annotations.*;

public class DataUtility {
	//BasicTestcaseforpost
//	BasicTestcase
	@DataProvider(name = "BasicTestcase")
	public static String[][] BasicTestcase(ITestContext itc) {
		String[][] returnarray = null;
				returnarray = new String[][] {
					{"200","Janet"},
				};

		return returnarray;
	}
	
	@DataProvider(name = "BasicTestcaseforpost")
	public static String[][] BasicTestcaseforpost(ITestContext itc) {
		String[][] returnarray = null;
				returnarray = new String[][] {
					{"201","leader"},
				};

		return returnarray;
	}

	
}
