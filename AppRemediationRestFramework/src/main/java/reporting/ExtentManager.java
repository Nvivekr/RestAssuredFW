package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	static ExtentReports extent;

	public static synchronized ExtentReports getInstance() {
		return extent;
	}

	public static synchronized ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTheme(Theme.STANDARD);
		String s[] = fileName.split("/");
		htmlReporter.config().setDocumentTitle("LMR Automation Report - " + s[s.length - 1]);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(s[s.length - 1]);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		return extent;
	}

}