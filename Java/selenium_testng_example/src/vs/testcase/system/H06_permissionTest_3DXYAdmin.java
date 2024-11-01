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
import dxy.vs.pages.Report_TPLogPage;
import dxy.vs.pages.Report_ViewOpsReportPage;
import dxy.vs.pages.SimPage;
import dxy.vs.pages.System_HCPLoginLogPage;
import dxy.vs.pages.System_ManageSite;
import dxy.vs.pages.System_ManageTagePage;
import dxy.vs.pages.Template_AuditTemplatePage;
import dxy.vs.pages.Template_SelectTemplatePage;
import dxy.vs.pages.UserManagePage;

public class H06_permissionTest_3DXYAdmin extends SetUp{

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
	Report_TPLogPage TPLogPage;
	OpsDataPage opsDataPage;
	OpsDetail_SupportPage supportPage;
	Template_SelectTemplatePage selectTemplatePage;
	Template_AuditTemplatePage auditTemplatePage;
	OpsViewHCPPage opsViewHCPPage;
	System_HCPLoginLogPage HCPLoginLogPage;
	System_ManageTagePage manageTagePage;
	Report_ViewOpsReportPage viewOpsReportPage;
	
	@Test
	public void setUserPermission_DXYAdmin() throws Exception{
		simPage = PageFactory.initElements(threadDriver.get(), SimPage.class);
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		userManagePage = PageFactory.initElements(threadDriver.get(), UserManagePage.class);
		
		String[] DXYPermissionGroup={
				"DXY站点管理","DXY 查看HCP登陆日志","DXY标签管理","DXY内容栏目管理","DXY内容菜单管理","DXY热点视频管理","DXY品牌信息管理","DXY内容管理",
				"DXY查看 TP 日志","DXY查看运营报表","DXY查看互动数据","DXY互动管理","选择模板","DXY模板审核管理","DXY HCP 管理","DXY 查看HCP详细",
				"Engaged Target HCP","TP on Map"};

		reusableFunctions.loginSIM(p("user3"), p("pw3"));
		
		simPage.accessToPermissionConfig();
		simPage.selectDesirePermission(DXYPermissionGroup);
		
		userManagePage.openAndRefreshUserPermission();
	}
	
	@Test
	(dependsOnMethods = {"setUserPermission_DXYAdmin"})
	public void verifyPermission_DXYAdmin() throws Exception{
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		dashboardPage = PageFactory.initElements(threadDriver.get(), DashboardPage.class);
		manageSite = PageFactory.initElements(threadDriver.get(), System_ManageSite.class);
		manageCategoryPage = PageFactory.initElements(threadDriver.get(), CMS_ManageCategoryPage.class);
		manageMenuPage = PageFactory.initElements(threadDriver.get(), CMS_ManageMenuPage.class);
		manageContentPage = PageFactory.initElements(threadDriver.get(), CMS_ManageContentPage.class);
		manageHotVideo_BrandPage = PageFactory.initElements(threadDriver.get(), CMS_ManageHotVideo_BrandPage.class);
		managePromotePage = PageFactory.initElements(threadDriver.get(), CMS_ManagePromotePage.class);
		topListPage = PageFactory.initElements(threadDriver.get(), CMS_TopListPage.class);
		TPLogPage = PageFactory.initElements(threadDriver.get(), Report_TPLogPage.class);
		opsDataPage = PageFactory.initElements(threadDriver.get(), OpsDataPage.class);
		supportPage = PageFactory.initElements(threadDriver.get(), OpsDetail_SupportPage.class);
		selectTemplatePage = PageFactory.initElements(threadDriver.get(), Template_SelectTemplatePage.class);
		auditTemplatePage = PageFactory.initElements(threadDriver.get(), Template_AuditTemplatePage.class);
		opsViewHCPPage = PageFactory.initElements(threadDriver.get(), OpsViewHCPPage.class);
		HCPLoginLogPage = PageFactory.initElements(threadDriver.get(), System_HCPLoginLogPage.class);
		manageTagePage = PageFactory.initElements(threadDriver.get(), System_ManageTagePage.class);
		viewOpsReportPage = PageFactory.initElements(threadDriver.get(), Report_ViewOpsReportPage.class);
		
		reusableFunctions.enterVS(p("user"), p("pw"));
		dashboardPage.selectOneSite("简洁模板");
		
		//verify permission 522,525: Engaged Target HCP, TP on Map
		dashboardPage.verifyPermission_DXY();
		
		//verify permission 470: DXY站点配置
		manageSite.openSiteManagePage();
		manageSite.verifySiteManagePermission_DXY();
		
		//verify permission 517,414: DXY查看HCP登陆日志, DXY标签管理
		HCPLoginLogPage.openHCPLoginLogPage();
		manageTagePage.openManageTagPage();
		
		//verify permission 465: DXY栏目管理
		manageCategoryPage.openCategoryManagePage();
		manageCategoryPage.verifyCategoryManagePermission_DXY();
		
		//verify permission 466: DXY菜单管理
		manageMenuPage.openMenuManagePage();
		manageMenuPage.verifyCategoryManagePermission_DXY();
		
		//verify permission 469: DXY内容管理
		manageContentPage.openContentManagePage();
		manageContentPage.verifyContentManagePermission_DXY();
		
//		//verify permission 467,468: DXY热点视频、品牌信息管理
//		manageHotVideo_BrandPage.openHotVideoPage();
//		manageHotVideo_BrandPage.verifyHotVideoAndBrandPermission_DXY();
//		manageHotVideo_BrandPage.openBrandPage();
//		manageHotVideo_BrandPage.verifyHotVideoAndBrandPermission_DXY();
		
		//verify permission 481,482: DXY查看TP Log, DXY查看运营报表
		TPLogPage.openDXYTPLogPage();
		viewOpsReportPage.openOpsReportPage();
		
		//verify permission 491,492: DXY查看互动数据, DXY互动管理
		opsDataPage.openOpsDataManagePage();
		opsDataPage.verifyManageOpsDataPermission_DXY();
		supportPage.verifyManageOpsDataPermission_DXY();
		
		//verify permission 500,501: 选择模板, DXY模板审核
		selectTemplatePage.openSelectTemplatePage();
		selectTemplatePage.submitATemplate();
		auditTemplatePage.openAuditTemplatePage();
		auditTemplatePage.verifyTemplatePermission_DXY();
		
		//verify permission 515,516: DXY查看HCP互动数据&详细
		opsViewHCPPage.openViewHCPPage();
		opsViewHCPPage.link_detail.get(0).click();
		waitForPageLoadComplete();
		opsViewHCPPage.verifyViewHCPPermission_DXY();
	}	
}
