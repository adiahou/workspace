package dxy.vs.testcase.system;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.SetUp;
import dxy.vs.pages.CMS_ManageCategoryPage;
import dxy.vs.pages.CMS_ManageContentPage;
import dxy.vs.pages.CMS_ManageHotVideo_BrandPage;
import dxy.vs.pages.CMS_ManageMenuPage;
import dxy.vs.pages.CMS_ManagePromotePage;
import dxy.vs.pages.CMS_TopListPage;
import dxy.vs.pages.DashboardPage;
import dxy.vs.pages.OpsDataPage;
import dxy.vs.pages.OpsDetail_SupportPage;
import dxy.vs.pages.OpsViewHCPPage;
import dxy.vs.pages.Report_CommonReportPage;
import dxy.vs.pages.Report_CustomReportPage;
import dxy.vs.pages.Report_TPLogPage;
import dxy.vs.pages.SimPage;
import dxy.vs.pages.System_ManageSite;
import dxy.vs.pages.Template_AuditTemplatePage;
import dxy.vs.pages.Template_SelectTemplatePage;
import dxy.vs.pages.UserManagePage;

public class H06_permissionTest_2ClientAdmin extends SetUp{

	SimPage simPage;
	ReusableFunctions reusableFunctions;
	UserManagePage userManagePage;
	DashboardPage dashboardPage;
	System_ManageSite manageSite;
	CMS_ManageCategoryPage manageCategoryPage;
	CMS_ManageMenuPage manageMenuPage;
	CMS_ManageContentPage manageContentPage;
	CMS_ManageHotVideo_BrandPage manageHotVideo_BrandPage;
	CMS_ManagePromotePage managePromotePage;
	CMS_TopListPage topListPage;
	Report_CommonReportPage commonReportPage;
	Report_CustomReportPage customReportPage;
	Report_TPLogPage TPLogPage;
	OpsDataPage opsDataPage;
	OpsDetail_SupportPage supportPage;
	Template_SelectTemplatePage selectTemplatePage;
	Template_AuditTemplatePage auditTemplatePage;
	OpsViewHCPPage opsViewHCPPage;
	
	@Test
	public void setUserPermission_clientAdmin() throws Exception{
		simPage = PageFactory.initElements(threadDriver.get(), SimPage.class);
		userManagePage = PageFactory.initElements(threadDriver.get(), UserManagePage.class);
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		
		String clientPermissionGroup[]={
				"站点管理","内容栏目管理","热点视频管理","内容菜单管理","品牌信息管理","内容管理","推荐内容管理","查看热点内容",
				"查看常用报表","查看自定义报表","查看 TP 日志","查看互动数据","互动管理","选择模板","查看模板审核记录","HCP 管理","查看HCP详细","Touch Point"};

		reusableFunctions.loginSIM(p("user3"), p("pw3"));
		
		simPage.accessToPermissionConfig();
		simPage.selectDesirePermission(clientPermissionGroup);
		
		userManagePage.openAndRefreshUserPermission();
	}
	
	@Test
	(dependsOnMethods = {"setUserPermission_clientAdmin"})
	public void verifyPermission_clientAdmin() throws Exception{
		
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		dashboardPage = PageFactory.initElements(threadDriver.get(), DashboardPage.class);
		manageSite = PageFactory.initElements(threadDriver.get(), System_ManageSite.class);
		manageCategoryPage = PageFactory.initElements(threadDriver.get(), CMS_ManageCategoryPage.class);
		manageMenuPage = PageFactory.initElements(threadDriver.get(), CMS_ManageMenuPage.class);
		manageContentPage = PageFactory.initElements(threadDriver.get(), CMS_ManageContentPage.class);
		manageHotVideo_BrandPage = PageFactory.initElements(threadDriver.get(), CMS_ManageHotVideo_BrandPage.class);
		managePromotePage = PageFactory.initElements(threadDriver.get(), CMS_ManagePromotePage.class);
		topListPage = PageFactory.initElements(threadDriver.get(), CMS_TopListPage.class);
		commonReportPage = PageFactory.initElements(threadDriver.get(), Report_CommonReportPage.class);
		customReportPage = PageFactory.initElements(threadDriver.get(), Report_CustomReportPage.class);
		TPLogPage = PageFactory.initElements(threadDriver.get(), Report_TPLogPage.class);
		opsDataPage = PageFactory.initElements(threadDriver.get(), OpsDataPage.class);
		supportPage = PageFactory.initElements(threadDriver.get(), OpsDetail_SupportPage.class);
		selectTemplatePage = PageFactory.initElements(threadDriver.get(), Template_SelectTemplatePage.class);
		auditTemplatePage = PageFactory.initElements(threadDriver.get(), Template_AuditTemplatePage.class);
		opsViewHCPPage = PageFactory.initElements(threadDriver.get(), OpsViewHCPPage.class);
		
		
		
		reusableFunctions.enterVS(p("user"), p("pw"));
		dashboardPage.selectOneSite("简洁模板");
		
		//verify permission 523: Touch Point
		dashboardPage.verifyTPPermission_client();
		
		//verify permission 464: 站点配置
		manageSite.openSiteManagePage();
		manageSite.verifySiteManagePermission_client();
		
		//verify permission 459: 栏目管理
		manageCategoryPage.openCategoryManagePage();
		manageCategoryPage.verifyCategoryManagePermission_client();
		
		//verify permission 460: 菜单管理
		manageMenuPage.openMenuManagePage();
		manageMenuPage.verifyCategoryManagePermission_client();
		
		//verify permission 461: 内容管理
		manageContentPage.openContentManagePage();
		manageContentPage.verifyContentManagePermission_client();
		
//		//verify permission 462,463: 热点视频、品牌信息管理
//		manageHotVideo_BrandPage.openHotVideoPage();
//		manageHotVideo_BrandPage.verifyHotVideoAndBrandPermission_client();
//		manageHotVideo_BrandPage.openBrandPage();
//		manageHotVideo_BrandPage.verifyHotVideoAndBrandPermission_client();
		
//		//verify permission 471,472: 推荐内容管理
//		managePromotePage.openPromotePage();
//		topListPage.openTopListPage();
		
		//verify permission 478,479,480: 查看常用报表, 查看自定义报表, 查看TP Log
		commonReportPage.openCommonReportPage();
		customReportPage.openCustomReportPage();
		TPLogPage.openTPLogPage();
		
		//verify permission 489,490: 查看互动数据, 互动管理
		opsDataPage.openOpsDataManagePage();
		opsDataPage.verifyManageOpsDataPermission_client();
		supportPage.verifyManageOpsDataPermission_client();
		
		//verify permission 499,500: 选择模板, 查看模板审核
		selectTemplatePage.openSelectTemplatePage();
		selectTemplatePage.submitATemplate();
		auditTemplatePage.openAuditTemplatePage();
		auditTemplatePage.verifyTemplatePermission_client();
		
		//verify permission 513,514: 查看HCP互动数据&详细
		opsViewHCPPage.openViewHCPPage();
		if(opsViewHCPPage.link_detail.size()>0){
			opsViewHCPPage.link_detail.get(0).click();
			waitForPageLoadComplete();
			opsViewHCPPage.verifyViewHCPPermission_client();
		}
	}	
}
