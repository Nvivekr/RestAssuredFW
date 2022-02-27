package testutilities;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import io.restassured.path.json.JsonPath;
import reporting.ReportManager;
import reporting.Reporter;


public class SoftAssertion {
	
	private static Reporter reporter;
	private static SoftAssert sAssert;
	public SoftAssertion() {
		// TODO Auto-generated constructor stub
		reporter = ReportManager.getInstance().getReporter();
		sAssert = new SoftAssert();
	}
	
	
	public  void sAssert(String actual, String expected, String message) {
		if (expected.equals(actual))
			reporter.log(Status.PASS, message + " -Success.");
		else
			reporter.log(Status.FAIL, message + "-Failed.<br>Expected : " + expected + " <br>Actual : " + actual);
		sAssert.assertEquals(actual, expected, message + " -Failed");
	}

	public  void sAssert(int actual, int expected, String message) {
		if (expected == actual)
			reporter.log(Status.PASS, message + "-Success.");
		else
			reporter.log(Status.FAIL, message + "-Failed.<br>Expected : " + expected + " <br>Actual : " + actual);
		sAssert.assertEquals(actual, expected, message + "-Failed");
	}

	public  void sAssert(boolean actual, boolean expected, String message) {
		if (expected==actual)
			reporter.log(Status.PASS, message + "-Success.");
		else
			reporter.log(Status.FAIL, message + "-Failed.<br>Expected : " + expected + " <br>Actual : " + actual);
		sAssert.assertEquals(actual, expected, message + "-Failed");
	}

	public  void sAssert(double actual, double expected, String message) {
		if (expected == actual)
			reporter.log(Status.PASS, message + "-Success.");
		else
			reporter.log(Status.FAIL, message + "-Failed.<br>Expected : " + expected + " <br>Actual : " + actual);
		sAssert.assertEquals(actual, expected, message + "-Failed");
	}

	public  void sAssert(float actual, float expected, String message) {
		if (expected == actual)
			reporter.log(Status.PASS, message + "-Success.");
		else
			reporter.log(Status.FAIL, message + "-Failed.<br>Expected : " + expected + " <br>Actual : " + actual);
		sAssert.assertEquals(actual, expected, message + "-Failed");
	}

	public  void sAssertTrue(Boolean condition, String message) {
		if (condition)
			reporter.log(Status.PASS, message + "- Success");
		else
			reporter.log(Status.FAIL, message + "- Failed");
		sAssert.assertTrue(condition, message + "-Failed");
	}

	public  void sAssertFalse(Boolean condition, String message) {
		if (condition)
			reporter.log(Status.FAIL, message + "- Failed .");
		else
			reporter.log(Status.PASS, message + "- Success.");
		sAssert.assertFalse(condition, message + "-Success");
	}

	public  void sAssert(List<String> actual, List<String> expected, String message) {
		if (expected.equals(actual))
			reporter.log(Status.PASS, message + " -Success.");
		else
			reporter.log(Status.FAIL, message + "-Failed.<br>Expected : " + expected + " <br>Actual : " + actual);
		sAssert.assertEquals(actual, expected, message + " -Failed");
	}
			
	public  void sAssert(Map<String, String> actual, Map<String, String> expected, String message) {
		if(expected.equals(actual)) {
			reporter.log(Status.PASS, message + " -Success.");
		}else {
			reporter.log(Status.FAIL, message + "-Failed.<br>Expected : " + expected + " <br>Actual : " + actual);
			sAssert.assertEquals(actual, expected);
		}
	}
	
	public  void sAssert(JSONObject actual, JSONObject expected, JSONCompareMode mode) throws JSONException {
		JSONAssert.assertEquals(expected, actual, mode);
	}
	
	public  void sAssert(JSONArray actual, JSONArray expected, JSONCompareMode mode) throws JSONException {
		JSONAssert.assertEquals(expected, actual, mode);
	}
	
	public  void sAssert(String expectedstr, JSONObject jsonObject) {
		JSONAssert.assertEquals(expectedstr, jsonObject, false);
	}
	
	public  void assertAll() {
	sAssert.assertAll();
	}

}
