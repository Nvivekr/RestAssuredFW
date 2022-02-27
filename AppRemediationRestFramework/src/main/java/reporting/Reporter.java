package reporting;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Reporter {
	private ExtentReports extent;
	private ExtentTest logger;
	private int screenshotnum = 1;

	public Reporter() {
	}
	

	public void createReport() {
		extent = ExtentManager.getInstance();
	}

	public void initlogger(ExtentTest logger) {
		this.logger = logger;
	}

	public void log(Status status, String msg) {
		if (status == Status.PASS) {
			logger.log(Status.PASS, MarkupHelper.createLabel(msg, ExtentColor.GREEN));
			System.out.println(msg);
		} else if (status == Status.SKIP) {
			logger.log(Status.INFO, MarkupHelper.createLabel(msg, ExtentColor.ORANGE));
			System.out.println("SKIPPED - " + msg);
		} else if (status == Status.ERROR) {
			logger.log(status, MarkupHelper.createLabel(msg, ExtentColor.RED));
			System.err.println(msg);
		} else if (status == Status.INFO) {
			logger.log(Status.INFO, MarkupHelper.createLabel(msg, ExtentColor.WHITE));
			System.out.println(msg);
		} else if (status == Status.WARNING) {
			logger.log(Status.WARNING, MarkupHelper.createLabel(msg, ExtentColor.INDIGO));
			System.out.println(msg);
		} else if (status == Status.FAIL) {
			logger.log(status, MarkupHelper.createLabel(msg, ExtentColor.RED));
			System.out.println(msg);
//			org.testng.Assert.fail();
		}
	}
	

	public void log(String msg, WebDriver driver) {
		try {
			logger.log(Status.FAIL, msg, MediaEntityBuilder.createScreenCaptureFromPath(this.captureScreen(driver)).build());
//			System.err.println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logWithScreenshot(String msg, WebDriver driver) {
		try {
			logger.log(Status.INFO, msg,
					MediaEntityBuilder.createScreenCaptureFromPath(this.captureScreen(driver)).build());
			System.out.println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String captureScreen(WebDriver driver) {
		String filePath = "screenshots/" +"SS"+ screenshotnum + ".png";
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE, 500, true)
				.withName("SS"+screenshotnum)
				.save(System.getProperty("user.dir") + "/Reports/" + "screenshots/");
		screenshotnum += 1;
		return filePath;
	}

	public void close() {
		extent.flush();
	}

}
