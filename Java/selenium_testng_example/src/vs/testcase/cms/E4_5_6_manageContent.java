package dxy.vs.testcase.cms;

import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.SetUp;
import dxy.vs.pages.CMS_ManageContentPage;

public class E4_5_6_manageContent extends SetUp{

	//Initialize Pages
	ReusableFunctions reusableFunctions;
	CMS_ManageContentPage cMS_ManageContentPage;
		
	@Test
	public void newContent() throws Exception{
		
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_ManageContentPage = PageFactory.initElements(threadDriver.get(), CMS_ManageContentPage.class);
		
		reusableFunctions.enterVS(0);

		cMS_ManageContentPage.openContentManagePage();
		
		cMS_ManageContentPage.clearOldData();
		
		cMS_ManageContentPage.newContent();
	}
	
	@Test(dependsOnMethods = {"newContent"})
	public void editContent_allYes() throws Exception{
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_ManageContentPage = PageFactory.initElements(threadDriver.get(), CMS_ManageContentPage.class);
		
		reusableFunctions.enterVS(0);

		cMS_ManageContentPage.openContentManagePage();
		
		cMS_ManageContentPage.editContent_allYes();
	}
	
	@Test(dependsOnMethods = {"editContent_allYes"})
	public void managePromote() throws Exception{
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_ManageContentPage = PageFactory.initElements(threadDriver.get(), CMS_ManageContentPage.class);
		
		reusableFunctions.enterVS(0);

		cMS_ManageContentPage.openContentManagePage();
		
		cMS_ManageContentPage.managePromote();
	}
	
	@Test(dependsOnMethods = {"managePromote"})
	public void editContent_allNo() throws Exception{
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_ManageContentPage = PageFactory.initElements(threadDriver.get(), CMS_ManageContentPage.class);
		
		reusableFunctions.enterVS(0);

		cMS_ManageContentPage.openContentManagePage();
		
		cMS_ManageContentPage.editContent_allNo();
	}
	
	@Test(dependsOnMethods = {"editContent_allNo"})
	public void deleteContent() throws Exception{
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_ManageContentPage = PageFactory.initElements(threadDriver.get(), CMS_ManageContentPage.class);
		
		reusableFunctions.enterVS(0);

		cMS_ManageContentPage.openContentManagePage();
		
		cMS_ManageContentPage.deleteContent();
	}
	
}
