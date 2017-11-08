package com.corehybrid.utils;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getInstance() {

		if (extent == null) {
			
			Date d = new Date();
			System.out.println(d);
			String filename = d.toString().replace(":", "_").replace(" ","_");
			
			extent = new ExtentReports(System.getProperty("user.dir")+"//extentReports/report_"+filename+".html", true, DisplayOrder.NEWEST_FIRST);
			extent.loadConfig(new File(System.getProperty("user.dir")+ "//ReportsConfig.xml"));
			extent.addSystemInfo("QA Environment", "Core Framework").addSystemInfo("TestNG", "MAVEN");
		}
		return extent;
	}

}
