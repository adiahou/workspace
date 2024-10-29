package dxy.vs.pages;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.SetUp;

public class CMS_ManageCategoryPage extends SetUp{
	
	@FindBy(xpath="//span[text()='内容系统']/../..")
	public WebElement menu_CMS;
	
	@FindBy(xpath="//a[contains(text(),'栏目管理')]")
	public WebElement submenu_category;
	
	@FindBy(xpath="//a[contains(text(),'菜单管理')]")
	public WebElement submenu_menu;
	
	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	@FindBy(xpath="//a[text()='新增']")
	public WebElement btn_new;

	@FindBy(xpath=".//*[@id='role-list']/tbody/tr/td[1]")
	public List<WebElement> values_code;

	@FindBy(xpath=".//*[@id='role-list']/tbody/tr/td[2]")
	public List<WebElement> values_name;

	@FindBy(xpath=".//*[@id='role-list']/tbody/tr/td[3]")
	public List<WebElement> values_desc;

	@FindBy(xpath=".//*[@id='role-list']/tbody/tr/td[5]")
	public List<WebElement> values_promote;

	@FindBy(xpath="//a[text()='删除']")
	public List<WebElement> btn_delete;

	@FindBy(xpath="//a[text()='编辑']")
	public List<WebElement> btn_edit;

	@FindBy(xpath="//a[contains(text(),'编辑')]/..")
	public List<WebElement> text_manage;

	@FindBy(xpath="//a[contains(text(),'发布')]")
	public List<WebElement> text_publish;
	
	
	/*********************************************************栏目的新增、编辑页面***************************************************************/
	@FindBy(xpath=".//*[@id='name']")
	public WebElement input_name;
	
	@FindBy(xpath=".//*[@id='code']")
	public WebElement input_code;

	@FindBy(xpath=".//*[@id='description']")
	public WebElement input_description;

	@FindBy(xpath=".//*[@id='is_menu-element']/label")
	public List<WebElement> option_toMenu;

	@FindBy(xpath=".//*[@id='promote-element']/label")
	public List<WebElement> option_promote;

	@FindBy(xpath=".//*[@id='submitbutton']")
	public WebElement btn_save;
	
	
	ReusableFunctions reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
	CMS_ManageMenuPage cMS_ManageMenuPage = PageFactory.initElements(threadDriver.get(), CMS_ManageMenuPage.class);
	Front_Demo1Page front_Demo1Page = PageFactory.initElements(threadDriver.get(), Front_Demo1Page.class);
	
	 public  void openCategoryManagePage() throws Exception{
		 if(!menu_CMS.getAttribute("class").contains("active")){
				menu_CMS.click();
				Thread.sleep(1000);
		 }
		submenu_category.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_new);
		
		assertTrue("页面标题错误",pageTitle.getText().contains("栏目管理"));
	}
	
	 public  void clearOldData() throws Exception{
		 boolean isOldExist = false;
		 for(WebElement eachValue: values_code){
			 if(eachValue.getText().trim().equals(p("category_code"))){
				 isOldExist=true;
			 }
		 }
		 
		 if(isOldExist){
			 deleteCategory(false, p("category_code"),p("category_name_update"));
		 }
	 }
	 
	 public  void newCategory(String toMenu) throws Exception{

		//新增一个不推荐菜单的栏目
		btn_new.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_save);
		
		input_name.sendKeys(p("category_name"));
		input_code.sendKeys(p("category_code"));
		input_description.sendKeys(p("category_description"));
		if(toMenu.equals("no")){
			option_toMenu.get(0).click();
		}
		btn_save.click();
		waitForPageLoadComplete();
		
		//到菜单页面验证
		submenu_menu.click();
		waitForPageLoadComplete();
		
		boolean isMenuExist = cMS_ManageMenuPage.isMenuExist(p("category_code"));
		if(toMenu.equals("yes")){
			assertTrue("菜单"+p("category_code")+"未被同时创建",isMenuExist);
		}else if(toMenu.equals("no")){
			assertFalse("菜单："+p("category_code")+" 被创建了。", isMenuExist);
		}
		
		//返回栏目管理页面
		submenu_category.click();
		waitForPageLoadComplete();
	}
	
	
	 public  void publishCategory(String categoryCode, String menuName) throws Exception{
		
		String desiredRow = getNodeNo(values_code,categoryCode);
		WebElement btn_publish = driver.findElement(By.xpath(".//*[@id='role-list']/tbody/tr["+desiredRow+"]/td/a[text()='发布']"));
		
		//发布栏目
		btn_publish.click();
		waitForPageLoadComplete();
		assertFalse("点击发布后，发布按钮仍然存在。",isElementExist(btn_publish));
		
		//到菜单页发布相应菜单
		waitForElementExist(submenu_menu);
		submenu_menu.click();
		waitForPageLoadComplete();
		
		cMS_ManageMenuPage.publishMenu(categoryCode, menuName,menuName,true,true);
		
//		//返回栏目管理页面
//		waitForElementExist(submenu_category);
//		submenu_category.click();
//		waitForPageLoadComplete();
	}
	

	 public  void editCategory(String categoryCode) throws Exception{
		
		//打开指定栏目编辑页面
		String desiredRow = getNodeNo(values_code,categoryCode);
		WebElement btn_edit = driver.findElement(By.xpath(".//*[@id='role-list']/tbody/tr["+desiredRow+"]/td/a[text()='编辑']"));
		
		btn_edit.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_save);
		
		//验证代码不能编辑
		System.out.println(input_code.getAttribute("readonly"));
		assertTrue("代码字段非只读。",isElementReadOnly(input_code));
		
		//选择推荐到首页为是，更新名称并保存
		option_promote.get(1).click();
		input_name.clear();
		input_name.sendKeys(p("category_name_update"));
		input_description.clear();
		input_description.sendKeys(p("category_desc_update"));
		btn_save.click();
		waitForPageLoadComplete();
		
		//列表页验证更新
		int index = Integer.parseInt(desiredRow)-1;
		assertTrue(values_name.get(index).getText().trim().equals(p("category_name_update")));
		assertTrue(values_desc.get(index).getText().trim().equals(p("category_desc_update")));
		assertTrue(values_promote.get(index).getText().trim().equals("是"));
		
		//到菜单页验证名称
		submenu_menu.click();
		waitForPageLoadComplete();
		String desiredRow2 = getNodeNo(cMS_ManageMenuPage.values_code,categoryCode);
		int index2 = Integer.parseInt(desiredRow2)-1;
		assertTrue(cMS_ManageMenuPage.values_name.get(index2).getText().trim().equals(p("category_name_update")));
		
//		//未发布到前台页面验证
//		String currentURL = driver.getCurrentUrl();
//		openNewWindow();
//		front_Demo1Page.openDemo1Page();
//		assertFalse(front_Demo1Page.isMenuAndCategoryPublished(p("category_name_update")));		
//		reusableFunctions.switchToBackstage(currentURL);
		
		//发布栏目和菜单
		submenu_category.click();
		waitForPageLoadComplete();
		publishCategory(categoryCode,p("category_name_update"));		
		
		//验证已推荐到首页
		openNewWindow();
		front_Demo1Page.openDemo1Page();
		assertTrue(front_Demo1Page.isCategoryPromoted(p("category_name_update")));
//		reusableFunctions.switchToBackstage(currentURL1);
	}

	 public  void deleteCategory(boolean toMenu,String CategoryCode, String CategoryName) throws Exception{

		//点击删除链接
		for(int i=0;i<values_code.size();i++){
			if(values_code.get(i).getText().trim().equals(CategoryCode)){
				btn_delete.get(i).click();
				break;
			}
		}
		Thread.sleep(1000);
		clickFromAlert("yes");
		Thread.sleep(1000);
		waitForPageLoadComplete();
		
		//验证已删除
		for(WebElement eachCode: values_code){
			assertFalse("栏目："+eachCode.getText()+" 未被成功删除。", eachCode.getText().trim().equals(CategoryCode));
		}
		
		if(toMenu){		
			//到菜单页面验证
			submenu_menu.click();
			waitForPageLoadComplete();
			
			boolean isMenuExist = cMS_ManageMenuPage.isMenuExist(CategoryCode);
			assertFalse("菜单"+CategoryCode+"未被成功删除",isMenuExist);

			//到前台验证菜单已删除
			front_Demo1Page.openDemo1Page();
			assertFalse(front_Demo1Page.isMenuAndCategoryPublished(CategoryName));
		}

//		//返回栏目管理页面
//		submenu_category.click();
//		waitForPageLoadComplete();
		
	}
	
	 public  void verifyCategoryManagePermission_client() throws Exception{
		 Assert.assertFalse(btn_delete.size()>0, "栏目管理权限：删除按钮存在。");
		 
		 btn_edit.get(0).click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_save);
		 btn_save.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_new);

		 Assert.assertTrue(text_manage.get(0).getText().contains("审核中"), "站点管理权限：审核中文字不存在。");
		 Assert.assertFalse(text_publish.size()>0, "站点管理权限：发布按钮存在。");
	 }
		
	 public  void verifyCategoryManagePermission_DXY() throws Exception{
		 Assert.assertTrue(btn_delete.size()>0, "DXY栏目管理权限：删除按钮不存在。");
		 
		 btn_edit.get(0).click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_save);
		 btn_save.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_new);

		 Assert.assertTrue(text_publish.size()>0, "站点管理权限：发布按钮不存在。");
	 }
}
