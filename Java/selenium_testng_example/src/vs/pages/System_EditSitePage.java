package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class System_EditSitePage extends SetUp{

	@FindBy(xpath="//td[contains(text(),'标准模板 Demo')]/..//a[text()='编辑']")
	public WebElement btn_edit_demo1;

	@FindBy(xpath="//section[@class='content']//input[@id='submit']")
	public WebElement btn_submit;

	@FindBy(xpath="//select[@id='is_show_land_page']/option")
	public List<WebElement> options_landPage;
	
	public void updateEditPage(String projectName, String landMode) throws Exception {
		
		WebElement btn_edit = driver.findElement(By.xpath("//td[contains(text(),'"+projectName+"')]/..//a[text()='编辑']"));
		btn_edit.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_submit);
		
		for(WebElement eachOption:options_landPage){
			if(eachOption.getText().equals(landMode)){
				eachOption.click();
				break;
			}
		}
		btn_submit.click();
		waitForPageLoadComplete();
	}
	
}
