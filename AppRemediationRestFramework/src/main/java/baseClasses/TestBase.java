package baseClasses;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.restassured.response.Response;
import newframeworkres.*;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import reporting.ReportManager;
import reporting.Reporter;
import testResources.*;
import testResources.POJOs.ResponseClass;
import testResources.POJOs.ReqresUtils.HeaderPoJoforReqres;
import testResources.POJOs.ReqresUtils.ParamPoJoforReqres;
import testutilities.PayloadUtilities;
import testutilities.RESTUtilities;
import testutilities.ResponseExtractor;
import testutilities.SOAPUtilities;
import testutilities.SoftAssertion;
import testutilities.Utility;


public class TestBase {
	public Reporter reporter;
	//public ExcelUtil reader;
//	protected ResponseExtractor responseExtractor;  // will be modified
	protected PayloadUtilities putils;
	protected RESTUtilities rutils;
	protected SOAPUtilities sutils;
	protected Properties readpropfile;
	protected Utility utility;
	protected SoftAssertion softAssert;
	public ReportManager rm;
	protected Response response;
	protected MapingUtil mapingUtil;
	protected HeaderPoJoforReqres headersReqres;
	protected ParamPoJoforReqres paramsReqres;
	protected ResponseExtractor responseExtractor;
	protected ResponseClass responseextractor;
	
	public ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	public ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	private String reportFile = "";
	
	//Constructor
	public TestBase() {
		File folder = new File(getOutputFolder());
		folder.mkdirs();
		rutils = new RESTUtilities();
		sutils = new SOAPUtilities();
	    utility = new Utility();    
	    readpropfile = utility.readTestData();
	    responseExtractor = new ResponseExtractor();
	    putils = new PayloadUtilities();
	    mapingUtil= new MapingUtil();
	    paramsReqres = new ParamPoJoforReqres();
		headersReqres= new HeaderPoJoforReqres(); //accepttype, accounttype, userId, Content-type
		responseextractor=new ResponseClass();
	}
	
	public static TestBase getInstance() {
		return new TestBase();
	}

	@BeforeSuite(alwaysRun=true)
	public void beforeSuite(ITestContext itc) throws IOException {
		File file = new File(getOutputFolder());
		file.mkdirs();
		
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss"); 
		String time = formatter.format(Calendar.getInstance().getTime());
		formatter = new SimpleDateFormat("dd-MM-yy");
		String date = formatter.format(Calendar.getInstance().getTime());
		
		reportFile = getOutputFolder() +"/"+ date + "/"+ itc.getSuite().getName() +"_" + time + ".html";
		ExtentManager.createInstance(reportFile);
		ExtentTestManager.setReporter(ExtentManager.getInstance());
		
	}

	@BeforeTest(alwaysRun=true)
	public void beforeTest(ITestContext iTC) {
		ExtentTest parent = ExtentManager.getInstance()
				.createTest(iTC.getCurrentXmlTest().getName());
		parentTest.set(parent);
	}
	
		
	@BeforeClass(alwaysRun=true)
	public void beforeClass(ITestContext iTC) throws InterruptedException, IOException {
		reporter = ReportManager.getInstance().getReporter();		
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public void beforeMethod(Method method, ITestContext iTC) {
		
		Test test1 = method.getAnnotation(Test.class);
		System.out.println("Test description is " + test1.description());
		String strTestCaseName = method.getDeclaringClass().getPackage().getName();
		strTestCaseName = strTestCaseName.toLowerCase();
		softAssert = new SoftAssertion();
		
		System.out.println("==========================================");
		System.out.println("=====================================");
		System.out.println("Method Name executing: "+ method.getName());
		System.out.println("=====================================");
		String strMethodName = test1.description();
		System.out.println("Test Case Name: "+strMethodName);
		ExtentTest child = parentTest.get().createNode(strMethodName);
		test.set(child);
		reporter.initlogger(child);
		System.out.println("=====================================");
	}


	@AfterMethod(alwaysRun=true)
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			reporter.log(Status.FAIL, result.getThrowable().getMessage());
			test.get().fail(MarkupHelper.createLabel("TEST CASE FAILED", ExtentColor.PINK));
			}
		else if (result.getStatus() == ITestResult.SKIP)
				test.get().skip(MarkupHelper.createLabel(result.getThrowable().toString()/* getLocalizedMessage() */, ExtentColor.GREY));
		else if (result.getStatus() == ITestResult.SUCCESS)
			test.get().pass(MarkupHelper.createLabel("TEST CASE PASSED SUCCESSFULLY", ExtentColor.INDIGO));
		else if (result.getStatus() == ITestResult.CREATED)
			test.get().skip(MarkupHelper.createLabel("TEST CASE SKIPPED", ExtentColor.GREY));
		System.out.println("=====================================");
		System.out.println("Completed executing test:" + result.getMethod().getMethodName());
		System.out.println("==========================================");
		}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
	}
	
	@AfterTest(alwaysRun=true)
	public void aftertest() {
	}
	
	@AfterSuite(alwaysRun=true)
	public void afterSuite() {
//		sAssert.assertAll();
		ExtentManager.getInstance().flush();
		System.gc();
	}
	
	protected String getOutputFolder() {
		return System.getProperty("user.dir") + "/reports/";
	}
	
	
}
