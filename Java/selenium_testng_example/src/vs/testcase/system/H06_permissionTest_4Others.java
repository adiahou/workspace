package dxy.vs.testcase.system;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.SetUp;
import dxy.vs.pages.CMS_ManageContentTypePage;
import dxy.vs.pages.CommentsManagePage;
import dxy.vs.pages.DashboardPage;
import dxy.vs.pages.Exam_ManageModePage;
import dxy.vs.pages.Exam_ManageQuestionPage;
import dxy.vs.pages.Exam_ScorePage;
import dxy.vs.pages.GrowthPage;
import dxy.vs.pages.OpsDataPage;
import dxy.vs.pages.OpsLogPage;
import dxy.vs.pages.OpsViewHCPPage;
import dxy.vs.pages.SimPage;
import dxy.vs.pages.System_ManageTargetHCPPage;
import dxy.vs.pages.Template_ManageTemplatePage;
import dxy.vs.pages.UserManagePage;

public class H06_permissionTest_4Others extends SetUp{

	SimPage simPage;
	ReusableFunctions reusableFunctions;
	UserManagePage userManagePage;
	DashboardPage dashboardPage;
	System_ManageTargetHCPPage manageTargetHCPPage;
	CMS_ManageContentTypePage manageContentTypePage;
	OpsLogPage opsLogPage;
	Template_ManageTemplatePage manageTemplatePage;
	CommentsManagePage commentsManagePage;
	Exam_ManageModePage manageModePage;
	Exam_ManageQuestionPage manageQuestionPage;
	Exam_ScorePage scorePage;
	GrowthPage growthPage;
	OpsDataPage opsDataPage;
	OpsViewHCPPage opsViewHCPPage;
	
	@Test
	public void setUserPermission_other() throws Exception{
		simPage = PageFactory.initElements(threadDriver.get(), SimPage.class);
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		userManagePage = PageFactory.initElements(threadDriver.get(), UserManagePage.class);
		
		String otherPermissionGroup[]={
				"DXY目标HCP管理","DXY内容类型管理","查看互动日志","DXY模板管理","DXY评论管理",
				"模式管理","题库管理","答题成绩","查看互动数据","DXY查看互动数据","HCP 管理","DXY HCP 管理"};

		reusableFunctions.loginSIM(p("user3"), p("pw3"));
		
		simPage.accessToPermissionConfig();
		simPage.selectDesirePermission(otherPermissionGroup);

		userManagePage.openAndRefreshUserPermission();
	}
	
	@Test
	(dependsOnMethods = {"setUserPermission_other"})
	public void verifyPermission_other() throws Exception{
		reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		dashboardPage = PageFactory.initElements(threadDriver.get(), DashboardPage.class);
		manageTargetHCPPage = PageFactory.initElements(threadDriver.get(), System_ManageTargetHCPPage.class);
		manageContentTypePage = PageFactory.initElements(threadDriver.get(), CMS_ManageContentTypePage.class);
		opsLogPage = PageFactory.initElements(threadDriver.get(), OpsLogPage.class);
		manageTemplatePage = PageFactory.initElements(threadDriver.get(), Template_ManageTemplatePage.class);
		commentsManagePage = PageFactory.initElements(threadDriver.get(), CommentsManagePage.class);
		manageModePage = PageFactory.initElements(threadDriver.get(), Exam_ManageModePage.class);
		manageQuestionPage = PageFactory.initElements(threadDriver.get(), Exam_ManageQuestionPage.class);
		scorePage = PageFactory.initElements(threadDriver.get(), Exam_ScorePage.class);
		opsDataPage = PageFactory.initElements(threadDriver.get(), OpsDataPage.class);
		opsViewHCPPage = PageFactory.initElements(threadDriver.get(), OpsViewHCPPage.class);
		growthPage = PageFactory.initElements(threadDriver.get(), GrowthPage.class);
		
		reusableFunctions.enterVS(p("user"), p("pw"));
		dashboardPage.selectOneSite("简洁模板");
		
		//verify permission 473: DXY目标HCP管理
		manageTargetHCPPage.openTargetHCPManagePage("简洁模板 Demo");
		
		//verify permission 458: DXY内容类型管理
		manageContentTypePage.openContentManagePage();
		
		//verify permission 488: 查看互动日志
		opsLogPage.openOpsLogPage();
		
		//verify permission 498: DXY模板管理
		manageTemplatePage.openManageTemplagePage();
		
		//verify permission 524: 评论管理
		commentsManagePage.openCommentsManagePage();
		
		//verify permission 532,533,534: 模式管理,题库管理,答题成绩
		manageModePage.openManageExamPage();
		manageQuestionPage.openManageQuestionPage();
		scorePage.openScorePage();
//		growthPage.openGrowthPage();
		
		//verify permission 489,491: 查看互动数据&DXY查看互动数据, without  互动管理&DXY互动管理
		opsDataPage.openOpsDataManagePage();
		opsDataPage.verifyManageOpsDataPermission_other();
		
		//verify permission 513,515: 查看HCP互动数据&DXY查看HCP互动数据, without  查看HCP互动数据详细&DXY查看HCP互动数据详细
		opsViewHCPPage.openViewHCPPage();
		opsViewHCPPage.verifyViewHCPPermission_other();		
	}
}
