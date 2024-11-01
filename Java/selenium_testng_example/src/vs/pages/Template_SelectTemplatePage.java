package dxy.vs.pages;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class Template_SelectTemplatePage extends SetUp{

	@FindBy(xpath="//span[text()='模板系统']/../..")
	public WebElement menu_template;

	@FindBy(xpath="//a[contains(text(),'选择模板')]")
	public WebElement submenu_selectTemplate;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	@FindBy(xpath="//button[text()='预览']")
	public WebElement btn_preview;
	
	@FindBy(xpath="//input[@value='提交审核']")
	public WebElement btn_submit;

	@FindBy(xpath="//strong[text()='标准模板']/../div")
	public WebElement template_standard;

	@FindBy(xpath="//label[@title='中国红']")
	public WebElement color_chinaRed;

	@FindBy(xpath="//img[@class='fancybox-image']")
	public WebElement img_preview;
	
	@FindBy(xpath="//a[@title='Previous']")
	public WebElement btn_pre;

	@FindBy(xpath="//a[@title='Next']")
	public WebElement btn_next;

	@FindBy(xpath="//a[@title='Close']")
	public WebElement btn_close;

	
	
	 public  void openSelectTemplatePage() throws Exception{
		 if(!menu_template.getAttribute("class").contains("active")){
			 menu_template.click();
				Thread.sleep(1000);
		 }
		submenu_selectTemplate.click();
		
		waitForPageLoadComplete();
		waitForElementExist(btn_preview);
		
		//验证页面显示
		assertTrue("页面标题错误",pageTitle.getText().contains("选择模板"));
		assertTrue(isElementExist(btn_submit));
	}
	

	 public  void previewAColor() throws Exception{
		template_standard.click();
		color_chinaRed.click();
		btn_preview.click();
		waitForElementExist(img_preview);
		String imgURL1=img_preview.getAttribute("src");
		//	切换下一张预览图
		btn_next.click();
		Thread.sleep(1000);
		String imgURL2=img_preview.getAttribute("src");
		//	切换上一张预览图
		btn_pre.click();
		Thread.sleep(1000);
		String imgURL3=img_preview.getAttribute("src");
		
		assertTrue(imgURL1.equals(imgURL3));
		assertFalse(imgURL1.equals(imgURL2));
		
		//关闭预览
		btn_close.click();
		Thread.sleep(1000);
		assertFalse(isElementExist(img_preview));
	}
	

	 public  void submitATemplate() throws Exception{
		//提交审核
		btn_submit.click();
		Thread.sleep(1000);	
		if(isAlertPresent()){
			Alert alert = driver.switchTo().alert();
			assertTrue("alert information is wrong.",alert.getText().contains("当前已有一条模板申请在审核中，请等待该条审核出结果后再重新提交"));
			alert.accept();
			Thread.sleep(1000);
		}else{
			waitForPageLoadComplete();
			//再次提交
			btn_submit.click();
			Thread.sleep(1000);	
			
			assertTrue("再次提交模板审核时 alert 没有出现",isAlertPresent());
			Alert alert = driver.switchTo().alert();
			assertTrue("alert information is wrong.",alert.getText().contains("当前已有一条模板申请在审核中，请等待该条审核出结果后再重新提交"));
			alert.accept();
			Thread.sleep(1000);
		}
		
	}
}
