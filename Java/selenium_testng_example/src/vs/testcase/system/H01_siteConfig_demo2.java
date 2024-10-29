package dxy.vs.testcase.system;

import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.SetUp;
import dxy.vs.pages.Front_Demo1Page;
import dxy.vs.pages.System_EditSitePage;
import dxy.vs.pages.System_ManageSite;

public class H01_siteConfig_demo2 extends SetUp{

	//Initialize Pages
	ReusableFunctions reusableFunctions;
	System_ManageSite manageSite;
	System_EditSitePage editSitePage;
	Front_Demo1Page front_Demo1Page;
	
	@Test
	public void siteConfig1() throws Exception{
		
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		manageSite = PageFactory.initElements(threadDriver.get(), System_ManageSite.class);
		
		reusableFunctions.enterVS(0);
		
		manageSite.openSiteManagePage();
		
		manageSite.verifySiteConfigPage("标准模板 Demo",false,false,false);
	}
	
	@Test
	public void siteConfig2() throws Exception{
		
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		manageSite = PageFactory.initElements(threadDriver.get(), System_ManageSite.class);
		
		reusableFunctions.enterVS(0);
		
		manageSite.openSiteManagePage();
		
		manageSite.verifySiteConfigPage("标准模板 Demo",true,false,false);
	}
	
	@Test
	(dependsOnMethods="siteConfig1")
	public void siteEdit1() throws Exception{
		
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		editSitePage = PageFactory.initElements(threadDriver.get(), System_EditSitePage.class);
		manageSite = PageFactory.initElements(threadDriver.get(), System_ManageSite.class);
		front_Demo1Page = PageFactory.initElements(threadDriver.get(), Front_Demo1Page.class);
		
		reusableFunctions.enterVS(p("user3"), p("pw3"));
		manageSite.openSiteManagePage();
		
		editSitePage.updateEditPage("标准模板 Demo","整页模式");

		front_Demo1Page.verifyLandingPage("整页模式");		
	}
	
	@Test
	public void siteEdsit2() throws Exception{
		
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		editSitePage = PageFactory.initElements(threadDriver.get(), System_EditSitePage.class);
		manageSite = PageFactory.initElements(threadDriver.get(), System_ManageSite.class);
		front_Demo1Page = PageFactory.initElements(threadDriver.get(), Front_Demo1Page.class);
		
		reusableFunctions.enterVS(p("user3"), p("pw3"));
		manageSite.openSiteManagePage();
		
		editSitePage.updateEditPage("标准模板 Demo","无声明页");

		front_Demo1Page.verifyLandingPage("无声明页");		
	}
	
	@Test
	(dependsOnMethods="siteConfig1")
	public void siteEdit3() throws Exception{
		
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		editSitePage = PageFactory.initElements(threadDriver.get(), System_EditSitePage.class);
		manageSite = PageFactory.initElements(threadDriver.get(), System_ManageSite.class);
		front_Demo1Page = PageFactory.initElements(threadDriver.get(), Front_Demo1Page.class);
		
		reusableFunctions.enterVS(p("user3"), p("pw3"));
		manageSite.openSiteManagePage();
		
		editSitePage.updateEditPage("标准模板 Demo","悬浮模式");

		front_Demo1Page.verifyLandingPage("悬浮模式");		
		
	}
}