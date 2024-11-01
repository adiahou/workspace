package dxy.vs.testcase.system;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.SetUp;
import dxy.vs.pages.DashboardPage;
import dxy.vs.pages.SimPage;
import dxy.vs.pages.UserManagePage;

public class H06_permissionTest_1Empty extends SetUp{

	SimPage simPage;
	ReusableFunctions reusableFunctions;
	UserManagePage userManagePage;
	DashboardPage dashboardPage;
	
	@Test
	public void emptyUserPermission() throws Exception{
		simPage = PageFactory.initElements(threadDriver.get(), SimPage.class);
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		userManagePage = PageFactory.initElements(threadDriver.get(), UserManagePage.class);
		
		reusableFunctions.loginSIM(p("user3"), p("pw3"));
		
		simPage.accessToPermissionConfig();
		simPage.emptyAll();
		
		userManagePage.openAndRefreshUserPermission();
	}
	
	@Test
	(dependsOnMethods = {"emptyUserPermission"})
	public void verifyPermission_empty() throws Exception{
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		dashboardPage = PageFactory.initElements(threadDriver.get(), DashboardPage.class);
		
		reusableFunctions.enterVS(p("user"), p("pw"));
		Assert.assertFalse(dashboardPage.isSiteExist("简洁模板"),"简洁模板站点对无任何权限用户仍可选。");
	}
}
