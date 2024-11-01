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

public class CMS_ManageContentPage extends SetUp {

	
	@FindBy(xpath="//span[text()='内容系统']/../..")
	public WebElement menu_CMS;
	
	@FindBy(xpath="//a[text()='新增']")
	public WebElement btn_new;
	
	@FindBy(xpath="//a[contains(text(),'内容管理')]")
	public WebElement submenu_content;

	@FindBy(xpath="//a[contains(text(),'推荐管理')]")
	public WebElement submenu_promote;
	
	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	@FindBy(xpath="//a[text()='编辑']")
	public List<WebElement> link_edit;

	@FindBy(xpath="//a[text()='删除']")
	public List<WebElement> link_delete;
	
	@FindBy(xpath="//table[@id='content-list']/tbody/tr/td[2][contains(text(),'Auto_')]")
	public List<WebElement> values_title;
	
	@FindBy(xpath="//table[@id='content-list']/tbody/tr/td[5]")
	public List<WebElement> values_status;

//	@FindBy(xpath="//table[@id='content-list']/tbody/tr/td[5]")
//	public List<WebElement> values_comment;

	@FindBy(xpath="//table[@id='content-list']/tbody/tr/td[6]")
	public List<WebElement> values_stick;

	@FindBy(xpath="//div[contains(@class,'icheckbox')]")
	public List<WebElement> chechBox;

	@FindBy(xpath="//input[@value='搜索']")
	public WebElement btn_search;
	
	/***********************************************************新增、编辑内容页*************************************************************/

	@FindBy(xpath="//*[@id='share-element']//input[@value='/admin/content/create/type/standard']")
	public WebElement contentType_standard;

	@FindBy(xpath="//*[@id='share-element']//input[@value='/admin/smalltalk/save']")
	public WebElement contentType_talk;
	
	@FindBy(xpath="//button[text()='确定']")
	public WebElement btn_save1;
	
	@FindBy(xpath=".//*[@id='submitbutton']")
	public WebElement btn_save2;
	
	@FindBy(xpath="//a[text()='属性']")
	public WebElement tab_property;
	
	@FindBy(xpath=".//*[@id='title']") //列表页：标题搜索框，内容编辑页：标题输入框
	public WebElement input_title;

	@FindBy(xpath=".//*[@id='author']")
	public WebElement input_author;

	@FindBy(xpath=".//*[@id='source']")
	public WebElement input_source;

	@FindBy(xpath=".//*[@id='summary']")
	public WebElement input_summary;

	@FindBy(xpath="//span[text()='插入问卷']/../span[1]")
	public WebElement icn_insetSurvey;

	@FindBy(xpath="//table[@class='ck-survey-table']")
	public WebElement surveyListTable;

	@FindBy(xpath="//span[text()='确定']")
	public WebElement btn_yes;
	
	@FindBy(xpath="//iframe[@class='cke_wysiwyg_frame cke_reset']")
	public WebElement iframe_editor;

	@FindBy(xpath="html/body")
	public WebElement input_editorBox;

	@FindBy(xpath=".//*[@id='wechat_title']")
	public WebElement input_wechatTitle;
	
	@FindBy(xpath=".//*[@id='wechat_summary']")
	public WebElement input_wechatSummary;

	@FindBy(xpath=".//*[@id='status-element']/label")
	public List<WebElement> option_status;
	
	@FindBy(xpath=".//*[@id='comment-element']/label")
	public List<WebElement> option_conment;
	
	@FindBy(xpath=".//*[@id='sticky-element']/label")
	public List<WebElement> option_stick;
	
	@FindBy(xpath=".//*[@id='promote-element']/label/div")
	public List<WebElement> option_promote;
	
	@FindBy(xpath=".//*[@id='like-element']/label")
	public List<WebElement> option_like;
	
	@FindBy(xpath=".//*[@id='share-element']/label")
	public List<WebElement> option_share;

	@FindBy(xpath=".//*[@id='rate-element']/label")
	public List<WebElement> option_rate;

	@FindBy(xpath=".//*[@id='nosidebar-element']/label")
	public List<WebElement> option_sidebar;

	@FindBy(xpath="//label[text()='URL']/..")
	public WebElement value_url;

	@FindBy(xpath=".//*[@id='status-element']/label[2]/div")
	public WebElement icon_publish;

	@FindBy(xpath=".//*[@id='status-element']/label[1]/div")
	public WebElement icon_draft;

	
	
	ReusableFunctions reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);	
	Front_Demo1Page front_Demo1Page = PageFactory.initElements(threadDriver.get(), Front_Demo1Page.class);
	CMS_ManagePromotePage cMS_ManagePromotePage = PageFactory.initElements(threadDriver.get(), CMS_ManagePromotePage.class);
	
	String originUrl = null;
	String  url_content = null;
	String url_category = null;
	
	 public  void openContentManagePage() throws Exception{
		
		 if(!menu_CMS.getAttribute("class").contains("active")){
				menu_CMS.click();
				Thread.sleep(1000);
		 }
		submenu_content.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_new);
		
		assertTrue("页面标题错误",pageTitle.getText().contains("内容管理"));
	}

	 public  void clearOldData() throws Exception{
		 for(int i=values_title.size()-1;i>-1;i--){
				if(values_title.get(i).getText().contains("Auto_")){
					link_delete.get(i).click();
					Thread.sleep(500);
					clickFromAlert("yes");
					Thread.sleep(500);
					waitForPageLoadComplete();
				}
			}
	 }
	 
	 public  void newContent() throws Exception{
		//打开新建内容页
		btn_new.click();
		waitForPageLoadComplete();
		
		contentType_standard.click();
		btn_save1.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_save2);
		
		//输入各字段内容
		input_title.sendKeys(p("content_title"));
		input_author.sendKeys(p("content_author"));
		input_source.sendKeys(p("content_source"));
		input_summary.sendKeys(p("content_summary"));
		driver.switchTo().frame(iframe_editor);
		input_editorBox.sendKeys(p("content_body"));
		driver.switchTo().defaultContent();
		
		//状态为草稿并保存
		tab_property.click();
		Thread.sleep(1000);
		option_status.get(0).click();
		btn_save2.click();
		waitForPageLoadComplete();
		
		//内容列表页验证
		values_title.get(0).equals(p("content_title"));
		values_status.get(0).equals("草稿");
//		values_comment.get(0).equals("否");
		values_stick.get(0).equals("否");
		
		//得到该内容url
		link_edit.get(0).click();
		waitForPageLoadComplete();
		originUrl = value_url.getText().trim();
		driver.navigate().back();
		waitForPageLoadComplete();
		url_content = p("demo1_url") + originUrl.substring(4);
		
		//去前台验证内容不存在
//		String currentURL = driver.getCurrentUrl();
		openNewWindow();
		front_Demo1Page.openDemo1Page();
		assertFalse(front_Demo1Page.isUrlExist(url_content));
//		reusableFunctions.switchToBackstage(currentURL);
	}	
	
	public String getUrl(){
		String url_origin = null;
		
		link_edit.get(0).click();
		waitForPageLoadComplete();
		url_origin = value_url.getText().trim();
		driver.navigate().back();
		waitForPageLoadComplete();
		return url_origin;
	}
	

	 public  void editContent_allYes() throws Exception{
		
		link_edit.get(0).click();
		waitForPageLoadComplete();
		originUrl = value_url.getText().trim();
		url_content = p("demo1_url") + originUrl.substring(4);
		url_category = p("demo1_url") + "/" + getTheValueBetweenAAndB(originUrl,"/","/") + "/list/rewrite";
		
		//改各属性为“是”
		tab_property.click();
		Thread.sleep(1000);
		
		option_status.get(1).click();
		option_conment.get(1).click();
		option_stick.get(1).click();
		option_promote.get(1).click();
		option_like.get(1).click();
		option_share.get(1).click();
		option_rate.get(1).click();
		option_sidebar.get(1).click();
		btn_save2.click();
		waitForPageLoadComplete();
		
		//列表页验证
		values_status.get(0).equals("发布");
//		values_comment.get(0).equals("是");
		values_stick.get(0).equals("是");
			
		//前台验证
//		String currentURL = driver.getCurrentUrl();
		openNewWindow();
		front_Demo1Page.openDemo1Page();
		front_Demo1Page.verifyContentProperty_allYes(url_category, url_content);
//		reusableFunctions.switchToBackstage(currentURL);
	}
	

	public void managePromote() throws Exception{
		
		//验证内容存在推荐列表中
		submenu_promote.click();
		waitForPageLoadComplete();
		assertTrue(cMS_ManagePromotePage.isContentPromoted(p("content_title")));
		
		//取消推荐
		cMS_ManagePromotePage.cancelPromote(p("content_title"));
		submenu_content.click();
		waitForPageLoadComplete();
		
		//内容编辑页验证
		link_edit.get(0).click();
		waitForPageLoadComplete();
		assertTrue(isRadioButtonChecked(option_promote.get(0)));
		driver.navigate().back();
		waitForPageLoadComplete();
		
		//前台页面验证
//		String currentURL = driver.getCurrentUrl();
		openNewWindow();
		front_Demo1Page.openDemo1Page();
		assertFalse(front_Demo1Page.isContentPromoted(p("content_title")));
//		reusableFunctions.switchToBackstage(currentURL);
	}


	 public  void editContent_allNo() throws Exception{
		
		link_edit.get(0).click();
		waitForPageLoadComplete();		
		originUrl = value_url.getText().trim();
		url_content = p("demo1_url") + originUrl.substring(4);
		url_category = p("demo1_url") + "/" + getTheValueBetweenAAndB(originUrl,"/","/") + "/list/rewrite";
		
		//改各属性为“是”
		tab_property.click();
		Thread.sleep(1000);
		
		option_status.get(1).click();
		option_conment.get(0).click();
		option_stick.get(0).click();
		option_promote.get(0).click();
		option_like.get(0).click();
		option_share.get(0).click();
		option_rate.get(0).click();
		option_sidebar.get(0).click();
		btn_save2.click();
		waitForPageLoadComplete();
		
		//列表页验证
		values_status.get(0).equals("发布");
//		values_comment.get(0).equals("否");
		values_stick.get(0).equals("否");
		
		//前台验证
//		String currentURL = driver.getCurrentUrl();
		openNewWindow();
		front_Demo1Page.openDemo1Page();
		front_Demo1Page.verifyContentProperty_allNo(url_category, url_content);
//		reusableFunctions.switchToBackstage(currentURL);
	}
		

	 public  void deleteContent() throws Exception{
		 
		link_edit.get(0).click();
		waitForPageLoadComplete();		
		originUrl = value_url.getText().trim();
		url_content = p("demo1_url") + originUrl.substring(4);
		driver.navigate().back();
		waitForPageLoadComplete();	
		
		//删除内容
		link_delete.get(0).click();
		Thread.sleep(1000);
		clickFromAlert("yes");
		Thread.sleep(1000);
		waitForPageLoadComplete();
		
		//验证已删除
		for(WebElement eachTitle: values_title){
			assertFalse("内容："+eachTitle.getText()+" 未被成功删除。", eachTitle.getText().trim().equals(p("content_title")));
		}
		
		//去前台验证内容不存在
//		String currentURL = driver.getCurrentUrl();
		openNewWindow();
		front_Demo1Page.openDemo1Page();
		assertFalse(front_Demo1Page.isUrlExist(url_content));
//		reusableFunctions.switchToBackstage(currentURL);
	}
		
	 public  void verifyContentManagePermission_client() throws Exception{
		 Assert.assertFalse(link_delete.size()>0, "内容管理权限：删除按钮存在。");
		 
		 //验证批量编辑权限
		 String desiredRow_draft = getNodeNo(values_status,"草稿");
		 if(desiredRow_draft!=null){
			 int draftRow = Integer.parseInt(desiredRow_draft);
			 Assert.assertTrue(chechBox.get(draftRow).getAttribute("aria-disabled").trim().equals("false"),"内容管理权限：草稿状态内容不可批量编辑");
			
			 //验证编辑页面
			 link_edit.get(draftRow-1).click();
			 waitForPageLoadComplete();
			 Assert.assertTrue(isElementExist(btn_save2),"内容管理权限：草稿状态内容不能保存");

			 driver.navigate().back();
			 waitForPageLoadComplete();
		 }
		 
		 String desiredRow_publish = getNodeNo(values_status,"发布");
		 if(desiredRow_publish!=null){
			 int publishRow = Integer.parseInt(desiredRow_publish);
			 Assert.assertTrue(chechBox.get(publishRow).getAttribute("aria-disabled").trim().equals("true"),"内容管理权限：发布状态内容可被批量编辑");
			 
			//验证编辑页面
			 link_edit.get(publishRow-1).click();
			 waitForPageLoadComplete();
			 Assert.assertFalse(isElementExist(btn_save2),"内容管理权限：发布状态内容能被保存");
			 
			 driver.navigate().back();
			 waitForPageLoadComplete();
		 }
		 
		 //验证新增页面
		 btn_new.click();		
		 contentType_standard.click();
		 btn_save1.click();
		 waitForPageLoadComplete();
		 waitForElementExist(tab_property);
		 tab_property.click();
		 Thread.sleep(1000);
		 Assert.assertTrue(icon_publish.getAttribute("aria-disabled").trim().equals("true"),"内容管理权限：发布状态可选。");
		 
	 }
	 
	 public  void verifyContentManagePermission_DXY() throws Exception{
		 Assert.assertTrue(link_delete.size()>0, "内容管理权限：删除按钮不存在。");
		 
		 //验证批量编辑权限
		 String desiredRow_draft = getNodeNo(values_status,"草稿");
		 if(desiredRow_draft!=null){
			 int draftRow = Integer.parseInt(desiredRow_draft);
			 String desiredRow_publish = getNodeNo(values_status,"发布");
			 int publishRow = Integer.parseInt(desiredRow_publish);
			 Assert.assertTrue(chechBox.get(draftRow).getAttribute("aria-disabled").trim().equals(chechBox.get(publishRow).getAttribute("aria-disabled").trim()),"DXY内容管理权限：草稿状态内容不可被批量编辑");
			
			 //验证编辑页面
			 link_edit.get(draftRow-1).click();
			 waitForPageLoadComplete();
			 Assert.assertTrue(isElementExist(btn_save2),"DXY内容管理权限：草稿状态内容不能保存");

			 driver.navigate().back();
			 waitForPageLoadComplete();
			 
			 link_edit.get(publishRow-1).click();
			 waitForPageLoadComplete();
			 Assert.assertTrue(isElementExist(btn_save2),"DXY内容管理权限：发布状态内容不能保存");
			 
			 driver.navigate().back();
			 waitForPageLoadComplete();
		 }
		 
		 //验证新增页面
		 btn_new.click();
		 contentType_standard.click();
		 btn_save1.click();
		 waitForPageLoadComplete();
		 waitForPageLoadComplete();
		 waitForElementExist(tab_property);
		 tab_property.click();
		 Thread.sleep(1000);
		 Assert.assertTrue(icon_publish.getAttribute("aria-disabled").trim().equals(icon_draft.getAttribute("aria-disabled").trim()),"DXY内容管理权限：发布状态不可选。");
	 }
	
	 public  String newExamContent(String surveyTitle) throws Exception{
		//打开新建内容页
		btn_new.click();
		contentType_standard.click();
		btn_save1.click();
		waitForPageLoadComplete();
		waitForElementExist(icn_insetSurvey);
		
		//输入各字段内容
		input_title.sendKeys(p("content_title")+"_for_exam");
		icn_insetSurvey.click();
		waitForElementExist(surveyListTable);
		WebElement desireSurvey=driver.findElement(By.xpath("//td[text()='"+surveyTitle+"']"));
		desireSurvey.click();
	    Thread.sleep(500);
	    btn_yes.click();
		
		//状态为 发布并保存
		tab_property.click();
		Thread.sleep(1000);
		option_status.get(1).click();
		btn_save2.click();
		waitForPageLoadComplete();
		
		//得到该内容url
		link_edit.get(0).click();
		waitForPageLoadComplete();
		originUrl = value_url.getText().trim();
		driver.navigate().back();
		waitForPageLoadComplete();
		url_content = p("demo1_url") + originUrl.substring(4);
		
		return url_content;
	}	
}
