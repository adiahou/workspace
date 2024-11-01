package dxy.vs.testcase.system;

import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.SetUp;
import dxy.vs.pages.System_HCPLoginLogPage;

public class H03_HCPLoginLog extends SetUp{
	
	//Initialize Pages
	ReusableFunctions reusableFunctions;
	System_HCPLoginLogPage HCPLoginLogPage;
	
	@Test
	public void HCPLoginLog() throws Exception{
		
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		HCPLoginLogPage = PageFactory.initElements(threadDriver.get(), System_HCPLoginLogPage.class);
			
		reusableFunctions.enterVS(0);
		
		HCPLoginLogPage.openHCPLoginLogPage();
		
		reusableFunctions.verifyPaginations(HCPLoginLogPage.content, HCPLoginLogPage.btn_next, HCPLoginLogPage.btn_previous, HCPLoginLogPage.btn_last, HCPLoginLogPage.btn_first);
		
		HCPLoginLogPage.verifyFilters();
		
	}

}
