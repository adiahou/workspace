package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import dxy.vs.base.SetUp;

public class System_ManageSite extends SetUp{

	@FindBy(xpath="//span[text()='系统管理']/../..")
	public WebElement menu_system;

	@FindBy(xpath="//a[contains(text(),'站点管理')]")
	public WebElement submenu_manageSite;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	@FindBy(xpath=".//*[@id='project-list']/tbody/tr/td[1]")
	public List<WebElement> values_name;
	
	@FindBy(xpath="//a[text()='配置']/../../td[1]")
	public List<WebElement> values_name2;

	@FindBy(xpath="//*[contains(text(),'新增')]")
	public WebElement btn_new;

	@FindBy(xpath="//*[contains(text(),'编辑')]")
	public List<WebElement> btn_edit;

	@FindBy(xpath="//a[contains(text(),'删除')]")
	public List<WebElement> btn_delete;

	@FindBy(xpath="//a[text()='配置']")
	public List<WebElement> btn_cfg;

	@FindBy(xpath="//a[text()='配置']/..")
	public List<WebElement> text_manage;

	@FindBy(xpath="//*[contains(text(),'发布')]")
	public List<WebElement> text_publish;
	
	/**************************************************************************站点配置页面******************************************************************************/
	
	@FindBy(xpath="//a[text()='常规']/..")
	public WebElement tag_regular;
	
	@FindBy(xpath="//a[text()='首页和内页']/..")
	public WebElement tag_page;
	
	@FindBy(xpath="//a[text()='支持请求']/..")
	public WebElement tag_support;

	@FindBy(xpath="//a[text()='病历上传']/..")
	public WebElement tag_addcase;

	@FindBy(xpath="//section[@class='content']//input[@id='submit']")
	public WebElement btn_submit;

	/*************常规标签页************/
	@FindBy(xpath=".//*[@id='name']")
	public WebElement input_name;

	@FindBy(xpath=".//*[@id='keyword']")
	public WebElement input_keyword;

	@FindBy(xpath=".//*[@id='site_description']")
	public WebElement input_desc;
	
	@FindBy(xpath=".//*[@id='site_search_keyword']")
	public WebElement input_searchKeyword;

	@FindBy(xpath=".//*[@id='online_date']")
	public WebElement input_onlineDate;

	@FindBy(xpath=".//*[@id='landing_statement']")
	public WebElement input_landStatement;

	@FindBy(xpath=".//*[@id='background_image_landing_page']")
	public WebElement input_landImage;

	@FindBy(xpath=".//*[@id='terms_statement']")
	public WebElement input_terms;

	@FindBy(xpath=".//*[@id='privacy_statement']")
	public WebElement input_privacy;

	@FindBy(xpath=".//*[@id='right_to_interpret']")
	public WebElement input_explain;

	@FindBy(xpath=".//*[@id='leave_statement']")
	public WebElement input_leaveStatement;

	@FindBy(xpath=".//*[@id='redirect_url']")
	public WebElement input_redirectUrl;

	@FindBy(xpath=".//*[@id='background_image_leave_page']")
	public WebElement input_leaveImage;

	@FindBy(xpath=".//*[@id='site_favicon']")
	public WebElement input_favIcon;

	@FindBy(xpath=".//*[@id='header_logo']")
	public WebElement input_headerLogo;

	@FindBy(xpath=".//*[@id='footer_logo']")
	public WebElement input_footerLogo;

	@FindBy(xpath=".//*[@id='footer_statement']")
	public WebElement input_footerStatement;

	@FindBy(xpath=".//*[@id='target_hcp_number']")
	public WebElement input_hcpNo;

	@FindBy(xpath=".//*[@id='target_hcp_visit']")
	public WebElement input_hcpVisit;

	@FindBy(xpath=".//*[@id='hcp_license']")
	public WebElement input_hcpLicense;

	@FindBy(xpath=".//*[@id='email']")
	public WebElement input_email;

	@FindBy(xpath=".//*[@id='wechat_title']")
	public WebElement input_wechat_title;

	@FindBy(xpath=".//*[@id='wechat_summary']")
	public WebElement input_wechat_summary;
	
	/*************首页和内页标签页************/

	@FindBy(xpath=".//*[@id='background_image_front_page']")
	public WebElement input_frontImage;

	@FindBy(xpath=".//*[@id='slide1']")
	public WebElement input_slide1;

	@FindBy(xpath=".//*[@id='slide_url1']")
	public WebElement input_slideUrl1;

	@FindBy(xpath=".//*[@id='slide2']")
	public WebElement input_slide2;

	@FindBy(xpath=".//*[@id='slide_url2']")
	public WebElement input_slideUrl2;

	@FindBy(xpath=".//*[@id='slide3']")
	public WebElement input_slide3;

	@FindBy(xpath=".//*[@id='slide_url3']")
	public WebElement input_slideUrl3;

	@FindBy(xpath=".//*[@id='slide4']")
	public WebElement input_slide4;

	@FindBy(xpath=".//*[@id='slide_url4']")
	public WebElement input_slideUrl4;

	@FindBy(xpath=".//*[@id='mobile_slide1']")
	public WebElement input_mobileSlide1;

	@FindBy(xpath=".//*[@id='mobile_slide_url1']")
	public WebElement input_mobileSlideUrl1;

	@FindBy(xpath=".//*[@id='mobile_slide2']")
	public WebElement input_mobileSlide2;

	@FindBy(xpath=".//*[@id='mobile_slide_url2']")
	public WebElement input_mobileSlideUrl2;

	@FindBy(xpath=".//*[@id='mobile_slide3']")
	public WebElement input_mobileSlide3;

	@FindBy(xpath=".//*[@id='mobile_slide_url3']")
	public WebElement input_mobileSlideUrl3;

	@FindBy(xpath=".//*[@id='mobile_slide4']")
	public WebElement input_mobileSlide4;

	@FindBy(xpath=".//*[@id='mobile_slide_url4']")
	public WebElement input_mobileSlideUrl4;

	@FindBy(xpath=".//*[@id='background_image_page']")
	public WebElement input_pageImage;

	@FindBy(xpath=".//*[text()='显示轮播图']/../label[@class='radio']")
	public List<WebElement> option_isShowBanner;

	/*************支持请求标签页************/
	@FindBy(xpath=".//*[@id='support_options']")
	public WebElement input_supportOptions;

	@FindBy(xpath="//label[text()='显示其它选项']")
	public WebElement input_isOther;

	@FindBy(xpath=".//*[@id='tab-site-config-tabpane-support']/div[2]/div/label/div")
	public List<WebElement> option_isOther;

	@FindBy(xpath=".//*[@id='support_statement']")
	public WebElement input_supportStatement;

	/*************病历上传标签页************/
	@FindBy(xpath=".//*[@id='case_introduce']")
	public WebElement input_intro;

	@FindBy(xpath=".//*[@id='case_rule']")
	public WebElement input_rule;

	@FindBy(xpath=".//*[@id='case_award']")
	public WebElement input_award;

	@FindBy(xpath=".//*[text()='首页banner入口']/../label[@class='radio']")
	public List<WebElement> option_bannerEntry;

	@FindBy(xpath=".//*[text()='显示右边栏']/../label[@class='radio']")
	public List<WebElement> option_isShowRight;

	
	
	Front_Demo1Page front_Demo1Page = PageFactory.initElements(threadDriver.get(), Front_Demo1Page.class);
	Front_Demo3Page front_Demo3Page = PageFactory.initElements(threadDriver.get(), Front_Demo3Page.class);
	

	 public  void openSiteManagePage() throws Exception{
		
		//点击系统管理菜单
		 if(!menu_system.getAttribute("class").contains("active")){
				menu_system.click();
				Thread.sleep(1000);
		 }
		submenu_manageSite.click();
		
		waitForPageLoadComplete();
		waitForElementExist(pageTitle);
		
		//验证页面显示
		assertTrue("页面标题错误",pageTitle.getText().contains("站点管理"));
	}
	

	 public  void verifySiteConfigPage(String projectName, boolean isShowBanner,boolean case_showEntry, boolean case_showRight) throws Exception{
		
		 String desiredRow = getNodeNo(values_name,projectName);
		WebElement btn_config = driver.findElement(By.xpath(".//*[@id='project-list']/tbody/tr["+desiredRow+"]/td[4]/a[text()='配置']"));
				
		btn_config.click();
		waitForPageLoadComplete();

		//验证页面显示
		assertTrue("页面标题错误",pageTitle.getText().contains("配置站点"));
		//编辑标签页字段
		assertTrue(tag_regular.getAttribute("class").trim().equalsIgnoreCase("active"));
		input(input_name,p("siteName")+time);
		input(input_keyword,p("siteKeyword")+time);
		input(input_desc,p("siteDesc")+time);
		input(input_landStatement,p("landStatment")+time);
		input(input_terms,p("terms")+time);
		input(input_privacy,p("privacy")+time);
		input(input_explain,p("explainRight")+time);
		input(input_leaveStatement,p("noacceptStatement")+time);
		input(input_redirectUrl,p("noacceptUrl"));
		input(input_footerStatement,p("footerStatment")+time);
		
		if(projectName.contains("标准模板 Demo")){
			input(input_searchKeyword,p("searchKeyword"));
		}
		
		assertTrue(isElementExist(input_favIcon));
		assertTrue(isElementExist(input_headerLogo));
		assertTrue(isElementExist(input_footerLogo));
		assertTrue(isElementExist(input_wechat_title));
		assertTrue(isElementExist(input_wechat_summary));
		
		//验证首页和内页字段
		tag_page.click();
		Thread.sleep(3000);

		if(projectName.contains("标准模板 Demo")){
			input(input_slideUrl1,p("support_url"));
		}else if(projectName.contains("EMKT模板 Demo")){
			input(input_slideUrl1,p("support3_url"));
		}
		
		assertTrue(isElementExist(input_frontImage));
		assertTrue(isElementExist(input_slide1));
		assertTrue(isElementExist(input_slide2));
		assertTrue(isElementExist(input_slideUrl2));
		assertTrue(isElementExist(input_slide3));
		assertTrue(isElementExist(input_slideUrl3));
		assertTrue(isElementExist(input_slide4));
		assertTrue(isElementExist(input_slideUrl4));
		assertTrue(isElementExist(input_mobileSlide1));
		assertTrue(isElementExist(input_mobileSlideUrl1));
		assertTrue(isElementExist(input_mobileSlide2));
		assertTrue(isElementExist(input_mobileSlideUrl2));
		assertTrue(isElementExist(input_mobileSlide3));
		assertTrue(isElementExist(input_mobileSlideUrl3));
		assertTrue(isElementExist(input_mobileSlide4));
		assertTrue(isElementExist(input_mobileSlideUrl4));
		assertTrue(isElementExist(input_pageImage));
		
		if(isShowBanner){
			option_isShowBanner.get(0).click();
		}else{
			option_isShowBanner.get(1).click();
		}
		
		//支持请求配置
		tag_support.click();
		Thread.sleep(3000);
		assertTrue(isElementExist(input_supportOptions));
		assertTrue(isElementExist(input_isOther));
		assertTrue(isElementExist(input_supportStatement));
		
		input(input_supportOptions,"A"+"\n"+"B"+"\n"+"C"+"\n"+"D"+"\n"+"E");
		option_isOther.get(0).click();
		input(input_supportStatement,p("support_statement")+time);
		
		//病历上传配置
		tag_addcase.click();
		Thread.sleep(3000);
		
		input(input_intro,p("case_intro"));
		input(input_rule,p("case_rule"));
		input(input_award,p("case_award"));
		if(case_showEntry){
			option_bannerEntry.get(1).click();
		}else{
			option_bannerEntry.get(0).click();
		}
		if(case_showRight){
			option_isShowRight.get(1).click();
		}else{
			option_isShowRight.get(0).click();
		}
		
		btn_submit.click();
		waitForPageLoadComplete();
		
		//发布
		List<WebElement> btn_publish = driver.findElements(By.xpath(".//*[@id='project-list']/tbody/tr["+desiredRow+"]/td[4]/a[text()='发布']"));
		assertTrue(btn_publish.size()==1);
		btn_publish.get(0).click();
		waitForPageLoadComplete();
		
		//前台验证
		if(projectName.contains("标准模板 Demo")){
			front_Demo1Page.openDemo1Page();
			front_Demo1Page.verifySiteConfig(isShowBanner);
			front_Demo1Page.verifySupport();
		}else if(projectName.contains("EMKT模板 Demo")){
			front_Demo3Page.openDemo3Page();
			front_Demo3Page.verifySiteConfig();
			front_Demo3Page.verifySupport();
			front_Demo3Page.verifyAddCase(case_showEntry, case_showRight);
		}
		
	}

	 public  void verifySiteManagePermission_client() throws Exception{
		 Assert.assertFalse(isElementExist(btn_new), "站点管理权限：新增按钮存在。");
		 Assert.assertFalse(btn_edit.size()>0, "站点管理权限：编辑按钮存在。");
		 Assert.assertFalse(btn_delete.size()>0, "站点管理权限：删除按钮存在。");
		 
		 String desiredRow = getNodeNo(values_name,"简洁模板 Demo");
		 WebElement btn_config = driver.findElement(By.xpath(".//*[@id='project-list']/tbody/tr["+desiredRow+"]/td[4]/a[text()='配置']"));
		 
		 btn_config.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_submit);
		 btn_submit.click();
		 waitForPageLoadComplete();
		 waitForElementExist(pageTitle);

		 String desiredRow2 = getNodeNo(values_name2,"简洁模板 Demo");
		 int rowNo=Integer.parseInt(desiredRow2)-1;
		 
		 Assert.assertTrue(text_manage.get(rowNo).getText().contains("审核中"), "站点管理权限：审核中文字不存在。");
		 Assert.assertFalse(text_publish.size()>0, "站点管理权限：发布按钮存在。");
	 }
	 
	 public  void verifySiteManagePermission_DXY() throws Exception{
		 Assert.assertFalse(isElementExist(btn_new), "DXY站点管理权限：新增按钮存在。");
		 Assert.assertFalse(btn_edit.size()>0, "DXY站点管理权限：编辑按钮存在。");
		 Assert.assertFalse(btn_delete.size()>0, "DXY站点管理权限：删除按钮存在。");
		 
		 String desiredRow = getNodeNo(values_name,"简洁模板 Demo");
		 System.out.println("******************************desireRow="+desiredRow);
		 List<WebElement> btn_edit = driver.findElements(By.xpath(".//*[@id='project-list']/tbody/tr["+desiredRow+"]/td[4]/a[text()='编辑']"));
		 List<WebElement> btn_publish = driver.findElements(By.xpath(".//*[@id='project-list']/tbody/tr["+desiredRow+"]/td[4]/a[text()='发布']"));
		 WebElement btn_config = driver.findElement(By.xpath(".//*[@id='project-list']/tbody/tr["+desiredRow+"]/td[4]/a[text()='配置']"));
		 
		 btn_config.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_submit);
		btn_submit.click();
		waitForPageLoadComplete();
		waitForElementExist(pageTitle);

		 Assert.assertTrue(btn_publish.size()>0, "DXY站点管理权限：发布按钮不存在。");
		 Assert.assertTrue(btn_edit.size()==0, "DXY站点管理权限：编辑按钮存在。");
	 }
			
}
