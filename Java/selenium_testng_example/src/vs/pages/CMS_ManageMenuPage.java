package dxy.vs.pages;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.SetUp;

public class CMS_ManageMenuPage  extends SetUp{
	
	@FindBy(xpath="//span[text()='内容系统']/../..")
	public WebElement menu_CMS;
	
	@FindBy(xpath="//a[contains(text(),'菜单管理')]")
	public WebElement submenu_menu;

	@FindBy(xpath="//a[text()='新增']")
	public WebElement btn_new;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;

	@FindBy(xpath=".//*[@id='role-list']/tbody/tr/td[2]")
	public List<WebElement> values_code;

	@FindBy(xpath=".//*[@id='role-list']/tbody/tr/td[1]")
	public List<WebElement> values_name;
	
	@FindBy(xpath=".//*[@id='role-list']/tbody/tr/td[3]")
	public List<WebElement> values_place;

	@FindBy(xpath=".//*[@id='role-list']/tbody/tr/td[4]")
	public List<WebElement> values_url;

	@FindBy(xpath=".//*[@id='role-list']/tbody/tr/td[5]")
	public List<WebElement> values_newWindow;
	
	@FindBy(xpath="//a[text()='删除']")
	public List<WebElement> btn_delete;

	@FindBy(xpath="//a[text()='编辑']")
	public List<WebElement> btn_edit;

	@FindBy(xpath="//a[contains(text(),'编辑')]/..")
	public List<WebElement> text_manage;

	@FindBy(xpath="//a[contains(text(),'发布')]")
	public List<WebElement> text_publish;
	
	/*********************************************************菜单的新增、编辑页面***************************************************************/
	@FindBy(xpath=".//*[@id='name']")
	public WebElement input_name;
	
	@FindBy(xpath=".//*[@id='code']")
	public WebElement input_code;
	
	@FindBy(xpath=".//*[@id='path']")
	public WebElement input_url;

	@FindBy(xpath=".//*[@id='weight']")
	public WebElement input_place;

	@FindBy(xpath=".//*[@id='target_blank-element']/label")
	public List<WebElement> option_newWindow;

	@FindBy(xpath=".//*[@id='status-element']/label")
	public List<WebElement> option_enable;

	@FindBy(xpath=".//*[@id='submitbutton']")
	public WebElement btn_save;
	
	
	
	Front_Demo1Page front_Demo1Page = PageFactory.initElements(threadDriver.get(), Front_Demo1Page.class);
	ReusableFunctions reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);

	 public  void openMenuManagePage() throws Exception{
		
		 if(!menu_CMS.getAttribute("class").contains("active")){
			 menu_CMS.click();
			 Thread.sleep(1000);
		 }
		submenu_menu.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_new);
		
		assertTrue("页面标题错误",pageTitle.getText().contains("菜单管理"));
	}
	
	 public  void clearOldData() throws Exception{
		 boolean isOldExist = false;
		 for(WebElement eachValue: values_code){
			 if(eachValue.getText().trim().equals(p("menu_code"))){
				 isOldExist=true;
			 }
		 }
		 
		 if(isOldExist){
			 deleteMenu(p("menu_code"),null,null);
		 }
	 }

	 public  void newMenu() throws Exception{
		//新增一条菜单
		btn_new.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_save);
		
		input_name.sendKeys(p("menu_name"));
		input_code.sendKeys(p("menu_code"));
		input_url.sendKeys(p("menu_url_outside"));
		input_place.sendKeys(p("menu_place"));
		
		btn_save.click();
		waitForPageLoadComplete();
		
		//验证菜单已成功添加到列表中
		String desiredRow = getNodeNo(values_code,p("menu_code"));
		int index = Integer.parseInt(desiredRow)-1;
		assertTrue(values_name.get(index).getText().trim().equals(p("menu_name")));
		assertTrue(values_place.get(index).getText().trim().equals(p("menu_place")));
		assertTrue(values_url.get(index).getText().trim().equals(p("menu_url_outside")));
	}
	

	 public  void newSubMenu(String parentMenuCode) throws Exception{
		
		String desiredRow = getNodeNo(values_code,parentMenuCode);
		WebElement btn_newSubMenu = driver.findElement(By.xpath(".//*[@id='role-list']/tbody/tr["+desiredRow+"]/td/a[text()='二级菜单']"));
		
		//新增一条二级菜单
		btn_newSubMenu.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_save);
		
		input_name.sendKeys(p("submenu_name"));
		input_code.sendKeys(p("submenu_code"));
		input_url.sendKeys(p("submenu_url"));
		input_place.sendKeys(p("submenu_place"));
		
		btn_save.click();
		waitForPageLoadComplete();
		
		//验证菜单已成功添加到列表中
		String desiredRow2 = getNodeNo(values_code,p("submenu_code"));
		int index = Integer.parseInt(desiredRow2)-1;
		assertTrue(values_name.get(index).getText().trim().equals(p("submenu_name")));
		assertTrue(values_place.get(index).getText().trim().equals(p("submenu_place")));
		assertTrue(values_url.get(index).getText().trim().equals(p("submenu_url")));
	}
	
	
	 public  void editMenu_newWindow(String menuCode) throws Exception{
		//打开指定菜单编辑页面
		String desiredRow = getNodeNo(values_code,menuCode);
		WebElement btn_edit = driver.findElement(By.xpath(".//*[@id='role-list']/tbody/tr["+desiredRow+"]/td/a[text()='编辑']"));
		
		btn_edit.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_save);
		
		//验证代码不可编辑
		assertTrue("代码字段非只读。",isElementReadOnly(input_code));
		
		//更新菜单名称、url、新窗口打开属性
		input_name.clear();
		input_name.sendKeys(p("menu_name_update1"));
		input_url.clear();
		input_url.sendKeys(p("menu_url"));
		option_newWindow.get(1).click();
		option_enable.get(1).click();
		btn_save.click();
		waitForPageLoadComplete();
		
		//菜单列表页验证更新
		int index = Integer.parseInt(desiredRow)-1;
		assertTrue(values_name.get(index).getText().trim().equals(p("menu_name_update1")));
		assertTrue(values_url.get(index).getText().trim().equals(p("menu_url")));
		assertTrue(values_newWindow.get(index).getText().trim().equals("是"));

//		//到前台页面验证菜单未更新
//		String currentURL = driver.getCurrentUrl();
//		openNewWindow();
//		front_Demo1Page.openDemo1Page();
//		assertFalse(front_Demo1Page.isMenuAndCategoryPublished(p("menu_name_update1")));	
//		reusableFunctions.switchToBackstage(currentURL);
		
		//发布菜单并验证
		publishMenu(menuCode,p("menu_name_update1"),null,true,false);
		
		front_Demo1Page.openOutMenu(p("menu_name_update1"), p("menu_url"));
		
//		//验证新窗口打开
//		String currentURL1 = driver.getCurrentUrl();
//		openNewWindow();
//		front_Demo1Page.openDemo1Page();
//		front_Demo1Page.openOutMenu(p("menu_name_update1"), p("menu_url"));
//		reusableFunctions.switchToBackstage(currentURL1);
	}
	

	 public  void editMenu_notNewWindow(String menuCode) throws Exception{
		//打开指定菜单编辑页面
		String desiredRow = getNodeNo(values_code,menuCode);
		WebElement btn_edit = driver.findElement(By.xpath(".//*[@id='role-list']/tbody/tr["+desiredRow+"]/td/a[text()='编辑']"));
		
		btn_edit.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_save);
		
		//验证代码不可编辑
		assertTrue("代码字段非只读。",isElementReadOnly(input_code));
		
		//更新菜单名称、url、新窗口打开属性
		input_name.clear();
		input_name.sendKeys(p("menu_name_update2"));
		input_url.clear();
		input_url.sendKeys(p("menu_url_cateogry"));
		option_newWindow.get(0).click();
		option_enable.get(1).click();
		btn_save.click();
		waitForPageLoadComplete();
		
		//菜单列表页验证更新
		int index = Integer.parseInt(desiredRow)-1;
		assertTrue(values_name.get(index).getText().trim().equals(p("menu_name_update2")));
		assertTrue(values_url.get(index).getText().trim().equals(p("menu_url_cateogry")));
		assertTrue(values_newWindow.get(index).getText().trim().equals("否"));
	    
		//发布菜单并验证
		publishMenu(menuCode,p("menu_name_update2"),"行医经历",true,true);
	}
	

	 public  void editMenu_enable(String menuCode, boolean isEnable) throws Exception{
		//打开指定菜单编辑页面
		String desiredRow = getNodeNo(values_code,menuCode);
		WebElement btn_edit = driver.findElement(By.xpath(".//*[@id='role-list']/tbody/tr["+desiredRow+"]/td/a[text()='编辑']"));
		
		btn_edit.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_save);
		
		//更新菜单名称、url、新窗口打开属性
		input_name.clear();
		input_name.sendKeys(p("menu_name_update3"));
		if(isEnable){
			option_enable.get(1).click();
		}else{
			option_enable.get(0).click();
		}
		btn_save.click();
		waitForPageLoadComplete();
		
		//菜单列表页验证更新
		int index = Integer.parseInt(desiredRow)-1;
		assertTrue(values_name.get(index).getText().trim().equals(p("menu_name_update3")));
	    
		//发布菜单并验证
		publishMenu(menuCode,p("menu_name_update3"),null,isEnable,false);
	}
	

	 public  void publishMenu(String menuCode, String menuName, String categoryName, boolean isEnable, boolean isCategory) throws Exception{

		String desiredRow = getNodeNo(values_code,menuCode);
		WebElement btn_publish = driver.findElement(By.xpath(".//*[@id='role-list']/tbody/tr["+desiredRow+"]/td/a[text()='发布']"));
		
		btn_publish.click();
		waitForPageLoadComplete();
		assertFalse("点击发布后，发布按钮仍然存在。",isElementExist(btn_publish));
		
		//到前台验证栏目和菜单是否发布成功
//		String currentURL = driver.getCurrentUrl();

		front_Demo1Page.openDemo1Page();
		if(isEnable){
			assertTrue("菜单："+menuName+" 没有成功发布",front_Demo1Page.isMenuAndCategoryPublished(menuName));	
		}else{
			assertFalse("菜单："+menuName+" 存在",front_Demo1Page.isMenuAndCategoryPublished(menuName));	
		}
		
		if(isCategory){
			front_Demo1Page.openCategoryMenuPage(menuName,categoryName);
		}
		
//		reusableFunctions.switchToBackstage(currentURL);
	}
	 

	 public  void publishSubMenu() throws Exception{
		String desiredRow = getNodeNo(values_code,p("submenu_code"));
		WebElement btn_publish = driver.findElement(By.xpath(".//*[@id='role-list']/tbody/tr["+desiredRow+"]/td/a[text()='发布']"));
		
		//发布菜单
		btn_publish.click();
		waitForPageLoadComplete();
		assertFalse("点击发布后，发布按钮仍然存在。",isElementExist(btn_publish));
		
		//到前台验证菜单
//		String currentURL = driver.getCurrentUrl();
		openNewWindow();
		front_Demo1Page.openDemo1Page();
		front_Demo1Page.openSubMenu(p("submenu_name"),p("menu_name_update3"), p("submenu_url"));
		
//		reusableFunctions.switchToBackstage(currentURL);
	}
	

	 public  void deleteMenu(String menuCode,String menuName,String subMenuName) throws Exception{
		
		String desiredRow = getNodeNo(values_code,menuCode);
		WebElement btn_delete = driver.findElement(By.xpath(".//*[@id='role-list']/tbody/tr["+desiredRow+"]/td/a[text()='删除']"));
		//点击删除链接
		btn_delete.click();
		Thread.sleep(1000);
		clickFromAlert("yes");
		Thread.sleep(1000);
		waitForPageLoadComplete();
		
		//验证列表中已删除
		for(WebElement eachCode: values_code){
			assertFalse("栏目："+eachCode.getText()+" 未被成功删除。", eachCode.getText().trim().equals(menuCode));
		}
		
		if(menuName!=null){
			//到前台验证已删除
//			String currentURL = driver.getCurrentUrl();
			openNewWindow();
			front_Demo1Page.openDemo1Page();
			if(subMenuName == null){
				assertFalse(front_Demo1Page.isMenuAndCategoryPublished(menuName));
			}else{
				assertFalse(front_Demo1Page.isSubMenuPublished(menuName, subMenuName));
			}
//			reusableFunctions.switchToBackstage(currentURL);
		}
	}
	
//
//	 public  void verifyOutMenu() throws Exception{
//		openNewWindow();
//		front_Demo1Page.openDemo1Page();
//		front_Demo1Page.openOutMenu(p("menu_name"),p("menu_url_outside"));
////		reusableFunctions.switchToBackstage(currentURL);
//	}


	 public  void verifySubMenu() throws Exception{
//		String currentURL = driver.getCurrentUrl();
		openNewWindow();
		front_Demo1Page.openDemo1Page();
		front_Demo1Page.openOutMenu(p("menu_name"),p("menu_url_outside"));
//		reusableFunctions.switchToBackstage(currentURL);
	}
	
	public boolean isMenuExist(String menuCode){
		boolean isMenuExist = false;
		for(WebElement eachCode: values_code){
			isMenuExist = eachCode.getText().trim().equals(menuCode);
			if(isMenuExist){
				break;
			}
		}
		return isMenuExist;
	}
	
	 public  void verifyCategoryManagePermission_client() throws Exception{
		 Assert.assertFalse(btn_delete.size()>0, "菜单管理权限：删除按钮存在。");
		 
		 btn_edit.get(0).click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_save);
		 btn_save.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_new);

		 Assert.assertTrue(text_manage.get(0).getText().contains("审核中"), "菜单管理权限：审核中文字不存在。");
		 Assert.assertFalse(text_publish.size()>0, "菜单管理权限：发布按钮存在。");
	 }
		
	 public  void verifyCategoryManagePermission_DXY() throws Exception{
		 Assert.assertTrue(btn_delete.size()>0, "DXY菜单管理权限：删除按钮存在。");
		 
		 btn_edit.get(0).click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_save);
		 btn_save.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_new);

		 Assert.assertTrue(text_publish.size()>0, "DXY菜单管理权限：发布按钮不存在。");
	 }
}
