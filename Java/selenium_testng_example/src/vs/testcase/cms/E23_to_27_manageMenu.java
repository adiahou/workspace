package dxy.vs.testcase.cms;

import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.SetUp;
import dxy.vs.pages.CMS_ManageMenuPage;
import dxy.vs.pages.Front_Demo1Page;

public class E23_to_27_manageMenu extends SetUp{

	//Initialize Pages
	ReusableFunctions reusableFunctions;
	CMS_ManageMenuPage cMS_ManageMenuPage;
	Front_Demo1Page front_Demo1Page;
	
	@Test
	public void createMenu() throws Exception{

		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_ManageMenuPage = PageFactory.initElements(threadDriver.get(), CMS_ManageMenuPage.class);
		front_Demo1Page = PageFactory.initElements(threadDriver.get(), Front_Demo1Page.class);
		
		reusableFunctions.enterVS(0);
		
		cMS_ManageMenuPage.openMenuManagePage();
		
		cMS_ManageMenuPage.clearOldData();
		
		cMS_ManageMenuPage.newMenu();
		
		cMS_ManageMenuPage.publishMenu(p("menu_code"), p("menu_name"), null,true,false);
		
		front_Demo1Page.openOutMenu(p("menu_name"),p("menu_url_outside"));
	}
	
	@Test(dependsOnMethods = {"createMenu"})
	public void editMenu_newWindow() throws Exception{
		
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_ManageMenuPage = PageFactory.initElements(threadDriver.get(), CMS_ManageMenuPage.class);
		
		reusableFunctions.enterVS(0);
		
		cMS_ManageMenuPage.openMenuManagePage();
		
		cMS_ManageMenuPage.editMenu_newWindow(p("menu_code"));

	}
	
	@Test
	(dependsOnMethods = {"createMenu"})
	public void editMenu_notNewWindow() throws Exception{
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_ManageMenuPage = PageFactory.initElements(threadDriver.get(), CMS_ManageMenuPage.class);
		
		reusableFunctions.enterVS(0);
		
		cMS_ManageMenuPage.openMenuManagePage();
		
		cMS_ManageMenuPage.editMenu_notNewWindow(p("menu_code"));
	}
	
	@Test(dependsOnMethods = {"createMenu"})
	public void editMenu_disable() throws Exception{
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_ManageMenuPage = PageFactory.initElements(threadDriver.get(), CMS_ManageMenuPage.class);
		
		reusableFunctions.enterVS(0);
		
		cMS_ManageMenuPage.openMenuManagePage();
		
		cMS_ManageMenuPage.editMenu_enable(p("menu_code"),false);
	}
	
	@Test
	(dependsOnMethods = {"editMenu_disable"})
	public void editMenu_enable() throws Exception{
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_ManageMenuPage = PageFactory.initElements(threadDriver.get(), CMS_ManageMenuPage.class);
		
		reusableFunctions.enterVS(0);
		
		cMS_ManageMenuPage.openMenuManagePage();
		
		cMS_ManageMenuPage.editMenu_enable(p("menu_code"),true);
	}
	
	@Test(dependsOnMethods = {"editMenu_enable"})
	public void newSubMenu() throws Exception{
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_ManageMenuPage = PageFactory.initElements(threadDriver.get(), CMS_ManageMenuPage.class);
		
		reusableFunctions.enterVS(0);
		
		cMS_ManageMenuPage.openMenuManagePage();
		
		cMS_ManageMenuPage.newSubMenu(p("menu_code"));
		
		cMS_ManageMenuPage.publishSubMenu();
	}
	
	@Test(dependsOnMethods = {"newSubMenu"})
	public void deleteSubMenu() throws Exception{
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_ManageMenuPage = PageFactory.initElements(threadDriver.get(), CMS_ManageMenuPage.class);
		
		reusableFunctions.enterVS(0);
		
		cMS_ManageMenuPage.openMenuManagePage();
		
		cMS_ManageMenuPage.deleteMenu(p("submenu_code"), p("menu_name_update3"), p("submenu_name"));
	}
	
	@Test(dependsOnMethods = {"deleteSubMenu"})
	public void deleteMenu() throws Exception{
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_ManageMenuPage = PageFactory.initElements(threadDriver.get(), CMS_ManageMenuPage.class);
		
		reusableFunctions.enterVS(0);
		
		cMS_ManageMenuPage.openMenuManagePage();
		
		cMS_ManageMenuPage.deleteMenu(p("menu_code"), p("menu_name_update3"), null);
	}
}
