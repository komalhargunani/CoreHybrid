package com.corehybrid;


import com.corehybrid.utils.Constants;
import com.corehybrid.utils.Xls_Reader;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Keywords {
	ExtentTest test;
	GenericKeywords app;
	
	public Keywords(ExtentTest test) {
		this.test=test;
	}

	public void executeKeyword(String testUnderExecution, Xls_Reader xls) throws Exception{
		
		
		app = new GenericKeywords(test);
		
		int rows = xls.getRowCount(Constants.KEYWORDS_SHEET);
		
		System.out.println("rows" + rows);
		for(int rNum=2;rNum<=rows;rNum++){
			
			String tcid = xls.getCellData(Constants.KEYWORDS_SHEET, "TCID", rNum);
			if(tcid.equalsIgnoreCase(testUnderExecution)){
				
			String keyword = xls.getCellData(Constants.KEYWORDS_SHEET, "Keyword", rNum);
			String object = xls.getCellData(Constants.KEYWORDS_SHEET, "Object", rNum);
			String data = xls.getCellData(Constants.KEYWORDS_SHEET, "Data", rNum);
			test.log(LogStatus.INFO, tcid + " : " + keyword +" : " + object +" : " + data);
			app.takescreenshot();
			
			if(keyword.equals("openBrowser")){
				app.openBrowser(data);
			} else if (keyword.equals("navigate")){
				app.navigateTo(object);
			}else if (keyword.equals("click")){
				app.click(object);
			}else if (keyword.equals("inputText")){
				app.inputText(object, data);
			}else if (keyword.equals("closeBrowser")){
				app.closeBrowser();
			}else if (keyword.equals("verifytitle")){
				app.verifyTitle( data);	
			}else if (keyword.equals("assertdata")){
				app.assertValue(object,data);	
			}else if (keyword.equals("gettext")){
				app.getText(object);
			}else if (keyword.equals("closebrowser")){
				app.closeBrowser();
			}
			
		
			}
			}	
	}
	
	public GenericKeywords getGenericKeywords(){
		return app;
	}
	
}