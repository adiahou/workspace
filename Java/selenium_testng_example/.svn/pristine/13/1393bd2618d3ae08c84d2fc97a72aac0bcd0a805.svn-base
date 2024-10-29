package dxy.vs.testcase.cms;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.SetUp;
import dxy.vs.pages.CMS_ManageVideoPage;


public class E29_manageVideo extends SetUp{

	
	ReusableFunctions reusableFunctions;
	CMS_ManageVideoPage manageVideoPage;
	
	@Test
	public void manageVideoTest() throws Exception{
		
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		manageVideoPage = PageFactory.initElements(threadDriver.get(), CMS_ManageVideoPage.class);
		
		reusableFunctions.enterVS(0);
		
		manageVideoPage.openVideoManagePage();
		
		manageVideoPage.clearOldData();
		
		manageVideoPage.uploadVideo();
		
		manageVideoPage.waitForVideoList();
		
		manageVideoPage.deleteVideo("Auto_videoTitle");
		
	}
}
