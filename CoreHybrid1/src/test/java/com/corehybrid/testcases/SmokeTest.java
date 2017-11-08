package com.corehybrid.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.corehybrid.Keywords;
import com.corehybrid.utils.Constants;
import com.corehybrid.utils.ExtentManager;
import com.corehybrid.utils.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

	public class SmokeTest {

		String testUnderExecution = "GmailTest";
		String testUnderExecution2 = "WorkFlow2";
		String testUnderExecution1 = "WorkFlow1";
		
		ExtentReports rep = ExtentManager.getInstance();
		ExtentTest test;
		
		
	/*@Test
		public void newBuyerViewItemDetails() throws Exception{
			test = rep.startTest(testUnderExecution1);
			test.log(LogStatus.INFO, "Starting New Buyer (workflow1) TEST");
			Xls_Reader xls = new Xls_Reader(Constants.SUITA_XLS);
			Keywords app = new Keywords(test);
			test.log(LogStatus.INFO, "Execting Keywords");
			app.executeKeyword(testUnderExecution1, xls);
			app.getGenericKeywords().takescreenshot();
			test.log(LogStatus.PASS, "New Buyer (Workflow1) PASSED");
		}*/

	@Test
		public void coreBuyerSearchAndBuyItem() throws Exception{
			test = rep.startTest(testUnderExecution2);
			test.log(LogStatus.INFO, "Starting core Buyer (workflow2) TEST");
			Xls_Reader xls = new Xls_Reader(Constants.SUITA_XLS);
			Keywords app = new Keywords(test);
			test.log(LogStatus.INFO, "Execting Keywords");
			app.executeKeyword(testUnderExecution2, xls);
			app.getGenericKeywords().takescreenshot();
			test.log(LogStatus.PASS, "core Buyer (Workflow2) PASSED");
		}
		
		
		@AfterTest
		public void quit(){
			rep.endTest(test);
			rep.flush();
		}
	}
