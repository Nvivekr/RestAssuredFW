package reporting;

import reporting.Reporter;

public class ReportManager {

	private static Reporter reporter;
	
	public static ReportManager getInstance(){
		return new ReportManager();
	}
	
	public Reporter getReporter() {
		return (reporter == null) ? reporter = new Reporter() : reporter;
	}	
	
}
