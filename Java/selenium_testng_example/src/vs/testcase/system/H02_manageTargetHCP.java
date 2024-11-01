package dxy.vs.testcase.system;

import org.testng.annotations.Listeners;

import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.ScreenshotListener;
import dxy.vs.base.SetUp;
import dxy.vs.pages.System_ManageTargetHCPPage;

@Listeners({ScreenshotListener.class})
public class H02_manageTargetHCP extends SetUp{

	//Initialize Pages
	ReusableFunctions reusableFunctions;
	System_ManageTargetHCPPage system_ManageTargetHCPPage;
	
	@Test
	public void newTargetHCP() throws Exception{

		system_ManageTargetHCPPage = PageFactory.initElements(threadDriver.get(), System_ManageTargetHCPPage.class);
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		
		reusableFunctions.enterVS(0);
		
		system_ManageTargetHCPPage.openTargetHCPManagePage("标准模板 Demo");
		
		system_ManageTargetHCPPage.clearOldData();
		
		system_ManageTargetHCPPage.newTargetHCP();
	}
	
	@Test(dependsOnMethods = {"newTargetHCP"})
	public void editTargetHCP() throws Exception{
		system_ManageTargetHCPPage = PageFactory.initElements(threadDriver.get(), System_ManageTargetHCPPage.class);
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		
		reusableFunctions.enterVS(0);
		
		system_ManageTargetHCPPage.openTargetHCPManagePage("标准模板 Demo");
		
		system_ManageTargetHCPPage.editTargetHCP();
	}
	
	@Test(dependsOnMethods = {"editTargetHCP"})
	public void filterAndDeleteTargetHCP() throws Exception{
		system_ManageTargetHCPPage = PageFactory.initElements(threadDriver.get(), System_ManageTargetHCPPage.class);
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		
		reusableFunctions.enterVS(0);
		
		system_ManageTargetHCPPage.openTargetHCPManagePage("标准模板 Demo");

		system_ManageTargetHCPPage.filterTargetHCP();
		
		system_ManageTargetHCPPage.deleteTargetHCP();
	}
	
}
