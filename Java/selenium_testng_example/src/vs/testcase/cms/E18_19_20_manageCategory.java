package dxy.vs.testcase.cms;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.SetUp;
import dxy.vs.pages.CMS_ManageCategoryPage;

public class E18_19_20_manageCategory extends SetUp{
	
	//Initialize Pages
	ReusableFunctions reusableFunctions;
	CMS_ManageCategoryPage cMS_manageCategoryPage;
	
	@Test
	public void newCagegory() throws Exception{
		
		reusableFunctions= PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_manageCategoryPage = PageFactory.initElements(threadDriver.get(), CMS_ManageCategoryPage.class);
		
		reusableFunctions.enterVS(0);
		
		//打开栏目管理页面
		cMS_manageCategoryPage.openCategoryManagePage();

		//清除旧数据
		cMS_manageCategoryPage.clearOldData();
		
		//新建一个不推荐菜单的栏目
		cMS_manageCategoryPage.newCategory("no");
		
		//删除该栏目
		cMS_manageCategoryPage.deleteCategory(false,p("category_code"), p("category_name"));
		
		//新建一个推荐菜单的栏目
		cMS_manageCategoryPage.newCategory("yes");
		
		//发布并验证该栏目
		cMS_manageCategoryPage.publishCategory(p("category_code"), p("category_name"));

	}
	
	@Test(dependsOnMethods = {"newCagegory"})
	public void editCagegory() throws Exception{

		reusableFunctions= PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_manageCategoryPage = PageFactory.initElements(threadDriver.get(), CMS_ManageCategoryPage.class);
		
		reusableFunctions.enterVS(0);
		
		//打开栏目管理页面
		cMS_manageCategoryPage.openCategoryManagePage(); 
		
		//编辑栏目
		cMS_manageCategoryPage.editCategory(p("category_code"));
	}
	
	
	@Test(dependsOnMethods = {"editCagegory"})
	public void deleteCagegory() throws Exception{

		reusableFunctions= PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
		cMS_manageCategoryPage = PageFactory.initElements(threadDriver.get(), CMS_ManageCategoryPage.class);
		
		reusableFunctions.enterVS(0);
		
		//打开栏目管理页面
		cMS_manageCategoryPage.openCategoryManagePage(); 
		
		//删除该栏目
		cMS_manageCategoryPage.deleteCategory(true ,p("category_code"),p("category_name_update"));
	}
	
}
