package dxy.vs.base;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import dxy.vs.pages.DashboardPage;
import dxy.vs.pages.SimPage;

public class ReusableFunctions extends SetUp {
	
	//Initialize Pages
	SimPage simPage;
	DashboardPage dashboard;

	public void loginSIM(String user, String pw) throws Exception{
		//Instance pages
		simPage = PageFactory.initElements(threadDriver.get(), SimPage.class);
		dashboard = PageFactory.initElements(threadDriver.get(), DashboardPage.class);
		String pageURL = "";
		
		//load login page
        driver.get(p("sim_url"));
        Thread.sleep(3000);
        waitForElementExist(simPage.btn_submit);
        
        //fill username/password
        input(simPage.input_user, user);
        input(simPage.input_pw, pw);
        
        //click submit
        simPage.btn_submit.click();
        waitForPageLoadComplete();
        if(isElementExist(simPage.link_ignorQR)){
        	waitForElementDispaly(simPage.link_ignorQR);
        	simPage.link_ignorQR.click();
        	waitForPageLoadComplete();
        }
        pageURL = driver.getCurrentUrl();
		if(pageURL.contains("https://sim.dxy.cn/japi/login")){
			driver.get(p("sim_url_vs"));
			 Thread.sleep(1000);
			waitForPageLoadComplete();		
		}
        waitForElementExist(simPage.tab_VS);
        
        //click VS tab
        simPage.tab_VS.click();
        waitForPageLoadComplete();		
//        waitForElementExist(simPage.group_VSDXY);
        
	}
	
    public void enterVS(int Group) throws Exception{
    	
    	loginSIM(p("user"),p("pw"));
    	
        //click  group
        if(Group == 1){
        	simPage.group_VSLilly.click();
        	Thread.sleep(3000);
        	waitForElementExist(simPage.btn_enterVS);
            
            //click to enter VS
            simPage.btn_enterVS.click();
            Thread.sleep(1000);
            switchToLastWindow();
            waitForPageLoadComplete();
            waitForElementExist(dashboard.text_dashboard);
            
            dashboard.selectLillySites();
            
        }else{
        	simPage.group_VSDXY.click();
        	Thread.sleep(3000);
        	 waitForElementExist(simPage.btn_enterVS);
             
             //click to enter VS
             simPage.btn_enterVS.click();
             Thread.sleep(2000);
             switchToLastWindow();
             waitForPageLoadComplete();
             waitForElementExist(dashboard.text_dashboard);
             
             dashboard.selectOneSite("标准模板");
        }
        Thread.sleep(3000);
    }

    public void enterVS(String user, String pw) throws Exception{
    	
    	loginSIM(user,pw);
    	
    	//click  group
    	if(isElementExist(simPage.group_VSDXY)){
    		simPage.group_VSDXY.click();
        	Thread.sleep(3000);
    	}
    	 waitForElementExist(simPage.btn_enterVS);
         
         //click to enter VS
         simPage.btn_enterVS.click();
         Thread.sleep(2000);
         switchToLastWindow();
         waitForPageLoadComplete();
         waitForElementExist(dashboard.text_dashboard);
    }

    public  void logout(){
//        click(".dropdown-toggle");
//        click("a", withText("退出"));
//        await().atMost(5, TimeUnit.SECONDS).until("button").withText().contains("登录").isPresent();
    }
    
    /**
     * 验证页码按钮
     * @param content:列表中内容
     * @param btn_next
     * @param btn_previous
     * @param btn_last
     * @param btn_first
     */
	public  void verifyPaginations(WebElement content, WebElement btn_next,WebElement btn_previous,WebElement btn_last,WebElement btn_first) throws Exception{
		
		if(isElementExist(btn_next)){
			btn_next.click();
			Thread.sleep(1000);
	        waitForPageLoadComplete();
			assertTrue(isElementExist(content));

			btn_previous.click();
			Thread.sleep(1000);
	        waitForPageLoadComplete();
			assertTrue(isElementExist(content));

			btn_last.click();
			Thread.sleep(1000);
	        waitForPageLoadComplete();
			assertTrue(isElementExist(content));

			btn_first.click();
			Thread.sleep(1000);
	        waitForPageLoadComplete();
			assertTrue(isElementExist(content));
		}else{
			System.out.println("页码按钮不存在。");
		}
	}
    
	
//	public  void switchToBackstage(String lastURL) throws Exception{
//		
////		int windowSize = threadDriver.get().getWindowHandles().size();
////		
////		while(windowSize>1){
////			driver.close();
////			switchToLastWindow();
////			Thread.sleep(1000);
////			windowSize = threadDriver.get().getWindowHandles().size();
////			System.out.println("window Size = "+windowSize);
////		}
//		
//		switchToWindow(0);
//		simPage.btn_enterVS.click();
//        Thread.sleep(2000);
//        switchToLastWindow();
//        waitForPageLoadComplete();
//		
//		driver.get(lastURL);
//        waitForPageLoadComplete();
//	}
}
