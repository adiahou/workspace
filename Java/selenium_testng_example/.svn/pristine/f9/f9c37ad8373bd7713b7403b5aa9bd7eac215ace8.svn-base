package dxy.vs.pages;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class CMS_ManagePromotePage extends SetUp {
	
	@FindBy(xpath="//span[text()='内容系统']/../..")
	public WebElement menu_CMS;
	
	@FindBy(xpath="//a[contains(@href,'recommend-list')]")
	public WebElement submenu_promote;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;

	@FindBy(xpath="//table/tbody/tr/td[2]")
	List<WebElement> values_title;

	@FindBy(xpath="//a[text()='取消推荐']")
	List<WebElement> links_cancel;
	
	
	public  void openPromotePage() throws Exception{
		 if(!menu_CMS.getAttribute("class").contains("active")){
				menu_CMS.click();
				Thread.sleep(1000);
		 }
		 submenu_promote.click();
		waitForPageLoadComplete();
		waitForElementExist(pageTitle);
		
		assertTrue("页面标题错误",pageTitle.getText().contains("推荐管理"));
	}
	
	public boolean isContentPromoted(String contentTitle) throws Exception{
		boolean isExist = false;
		for(WebElement eachTitle: values_title){
			if(eachTitle.getText().trim().contains(contentTitle)){
				isExist = true;
				break;
			}
		}
		return isExist;
	}
	
	
	public void cancelPromote(String contentTitle) throws Exception{
		
		String desiredRow = getNodeNo(values_title,contentTitle);
		int index =  Integer.parseInt(desiredRow)-1;
		
		//点击取消推荐按钮
		links_cancel.get(index).click();
		waitForPageLoadComplete();
		assertFalse(isContentPromoted(contentTitle));
	}

}
