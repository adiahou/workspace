package dxy.vs.pages;


import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.SetUp;

public class CMS_ManageVideoPage extends SetUp{

	@FindBy(xpath="//span[text()='内容系统']")
	public WebElement menu_CMS;
	
	@FindBy(xpath="//a[@href='/admin/content/cc']")
	public WebElement submenu_video;
	
	@FindBy(xpath="//button[text()='上传视频']")
	public WebElement btn_upload;
	
	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	@FindBy(xpath="//input[@name='title']")
	public WebElement field_videoTitle;
	
	@FindBy(xpath="//input[@name='description']")
	public WebElement field_videoDesc;

	@FindBy(xpath="//input[@id='cc-file-field']")
	public WebElement field_videoFileUpload;

	@FindBy(xpath="//button[text()='开始上传']")
	public WebElement btn_startUpload;

	@FindBy(xpath="//div[contains(@class,'cc-upload-progress')]/div[contains(text(),'上传成功')]")
	public WebElement msg_uploadSuccess;

	@FindBy(xpath="//button[contains(@class,'cc-btn-done')]")
	public WebElement btn_finish;

	@FindBy(xpath="//table[contains(@class,'cc-video-table')]/tbody/tr/td[2]/div[@class='title']")
	public List<WebElement> titles;

	@FindBy(xpath="//table[contains(@class,'cc-video-table')]/tbody/tr/td[4]/span[contains(@class,'label')]")
	public List<WebElement> status;

	
	
	
	
	ReusableFunctions reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);	
	
	public  void openVideoManagePage() throws Exception{
		
		menu_CMS.click();
		Thread.sleep(1000);
		submenu_video.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_upload);
		
		Assert.assertTrue(pageTitle.getText().contains("视频管理"),"页面标题错误");
	}

	public void clearOldData() throws Exception{
		 for(WebElement eachValue: titles){
			 try{
				 if(eachValue.getText().trim().equals("Auto_videoTitle")){
					 deleteVideo("Auto_videoTitle");
				 }
			 }catch(StaleElementReferenceException e){}
		 }
	}
	
    public void uploadVideo() throws Exception{
    	
    	File downloadFileFolder = new File("testdata/test.flv");
    	String filePath = downloadFileFolder.getAbsolutePath();
    	
    	btn_upload.click();
    	Thread.sleep(1000);
    	waitForElementExist(field_videoFileUpload);
    	
    	try {
    	    Thread.sleep(1000);
        	field_videoFileUpload.sendKeys(filePath);
    	} catch (StaleElementReferenceException e) {
    	    e.printStackTrace();
    	}

    	field_videoTitle.sendKeys("Auto_videoTitle");
    	field_videoDesc.sendKeys("Auto_videoDescription");
    	
    	btn_startUpload.click();
    	waitForElementExist(msg_uploadSuccess,30);
    	waitForElementExist(btn_finish);
    	btn_finish.click();
		waitForPageLoadComplete();
    }
    
    public void waitForVideoList() throws InterruptedException {
    	
    	//wait for video add to list
    	boolean isadded = false;
    	int loop = 0;
    	while(!isadded){
    		driver.navigate().refresh();
    		waitForPageLoadComplete();
    		for(WebElement eachTitle: titles){
    			String title = eachTitle.getText().trim();
    			if(title.equals("Auto_videoTitle")){
    				isadded = true;
    				break;
    			}
    		}
    		Thread.sleep(3000);
    		loop++;
    		if(loop>10){
    			System.out.println("video Auto_videoTitle still not added to the list.");
    			break;
    		}
    	}
    	
    	//wait for process finish until 20 min
		boolean isUploadSuccess = false;
		
		for(int i=0;i<60;i++){
			
			String desiredRow = getNodeNo(titles,"Auto_videoTitle");
			int index = Integer.parseInt(desiredRow)-1;
			WebElement videoStatu = status.get(index);
			String status = videoStatu.getText().trim();
			
			if(status.equalsIgnoreCase("处理中")){
	    		driver.navigate().refresh();
	    		waitForPageLoadComplete();
				Thread.sleep(20000);
			}else if(status.equalsIgnoreCase("OK")){
				isUploadSuccess=true;
				break;
			}else if(status.equalsIgnoreCase("FAIL")){
				System.out.println("视频上传状态为失败。");
				break;
			}
		}

		Assert.assertTrue(isUploadSuccess,"视频未上传成功");
    }
    
    
    public void deleteVideo(String videoTitle) throws Exception {
    	String desiredRow = getNodeNo(titles,videoTitle);
    	int index = Integer.parseInt(desiredRow)+1;
    	WebElement deleteLink = driver.findElement(By.xpath("//table[contains(@class,'cc-video-table')]/tbody/tr["+index+"]/td[5]/a[text()='删除']"));
    	
    	//click delete
    	deleteLink.click();
    	Thread.sleep(1000);
		clickFromAlert("yes");
		Thread.sleep(1000);
		waitForPageLoadComplete();
		
		//verify delete successful
		for(WebElement eachTitle: titles){
			Assert.assertFalse(eachTitle.getText().trim().equals(videoTitle),"视频："+eachTitle.getText()+" 未被成功删除。");
		}
    }


}
