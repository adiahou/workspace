package dxy.vs.pages;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class Front_Demo1Page extends SetUp{
	

/********************************************登陆\声明\不能进入声明页面********************************************************/
	@FindBy(xpath="//a[contains(@class,'btn-agree')]")
	public WebElement btn_agree;
	
	@FindBy(xpath="//div[@class='box-content']")
	public WebElement msg_landstatement;

	@FindBy(xpath="//input[@id='username']")
	public WebElement input_username;

	@FindBy(xpath="//input[@name='password']")
	public WebElement input_password;

	@FindBy(xpath="//input[@name='validateCode']")
	public WebElement input_validate;

	@FindBy(xpath=".//*[contains(@class,'ico_pc')]")
	public WebElement link_turnToPC;

	@FindBy(xpath=".//*[@id='j_loginTab1']")
	public WebElement quLoginCode;
	
	@FindBy(xpath="//button[text()='登录']")
	public WebElement btn_login;

	@FindBy(xpath="//span[text()='跳过']")
	public WebElement btn_ignor;

	@FindBy(xpath="//div[@class='box-content']")
	public WebElement stat_noaccept;

	@FindBy(xpath="//span[text()='欢迎访问丁香园']/..")
	public WebElement btn_noaccept_redirect;

	@FindBy(xpath="//div[@class='box-footer']/p")
	public WebElement text_explain;
	
/**************************************************首页*********************************************************/

	@FindBy(xpath="//meta[@name='description']")
	public WebElement meta_desc;

	@FindBy(xpath="//meta[@name='keywords']")
	public WebElement meta_keyword;

	@FindBy(xpath="//input[@id='key']")
	public WebElement input_search;

	@FindBy(xpath="//div[@class='copyright']")
	public WebElement stat_footer;

	@FindBy(xpath="//div[@class='bx-viewport']")
	public WebElement banner;

	@FindBy(xpath="//div[@class='bx-viewport']//li[not(@class='bx-clone')][1]/a")
	public WebElement link_banner1;

	@FindBy(xpath="//*[@id='nav']/div/ul/li[contains(@class,'sub')]/a")
	public List<WebElement> btn_menu;
	
	@FindBy(xpath="//*[@id='nav']/div/ul/li[contains(@class,'sub')]/a[contains(text(),'首页')]")
	public WebElement menu_home;
	
	@FindBy(xpath="//a[contains(@class,'cub')]")
	public List<WebElement> btn_subMenu;
	
	@FindBy(xpath="//*[@id='container']//div[contains(@class,'module-header')]/h2/span")
	public WebElement header_listPage;

	@FindBy(xpath="//div[contains(@class,'module-header')]/h2/span")
	public List<WebElement> header_categories;
	
	@FindBy(xpath="//a[text()='继续访问']")
	public WebElement btn_continue;

	@FindBy(xpath=".//*[@id='nav']//a[@href='/demo/support/rewrite']")
	public WebElement menu_support;

	@FindBy(xpath=".//*[@id='nav']//a[@href='/demo/support/rewrite']/../../..")
	public WebElement parentMenu_support;

	//右边栏热门推荐中的链接，包括图片和各标题
	@FindBy(xpath="//span[text()='热门推荐']/../../..//a")
	public List<WebElement> links_promote;

	//右边栏热点排行中的链接，包括图片和各标题
	@FindBy(xpath="//span[text()='浏览排行']/../../..//a")
	public List<WebElement> links_hotList;

	/**************************************************列表页*********************************************************/

	@FindBy(xpath="//h2[@class='media-title']/a")
	public List<WebElement> contentTitles;
	
	@FindBy(xpath="//h2[@class='media-title'][1]/span[@class='icon icon-sticky']")
	public WebElement tag_promote;
	
	/**************************************************内容详情页*********************************************************/

	@FindBy(xpath="//h1[@class='title']")
	public WebElement text_title;

	@FindBy(xpath="//div[@class='post-header']//li[contains(text(),'来源')]")
	public WebElement text_source;

	@FindBy(xpath="//div[@class='post-header']//li[contains(text(),'作者')]")
	public WebElement text_author;

	@FindBy(xpath="//div[@class='post-content']/p[1]")
	public WebElement text_body;
	
	@FindBy(xpath=".//*[@id='J_share_0']")
	public WebElement btns_share;

	@FindBy(xpath="//div[contains(text(),'这篇内容对您是否有帮助')]")
	public WebElement btns_rate;

	@FindBy(xpath="//div[contains(@class,'action-item J_vote')]")
	public WebElement btns_like;
	
	@FindBy(xpath=".//*[text()='提交']")
	public WebElement btns_submitComm;

	@FindBy(xpath="//div[@class='sidebar']")
	public WebElement div_sidebar;
	
	/**************************************************支持请求页*********************************************************/

	@FindBy(xpath="//h3[contains(text(),'您希望得到哪些学术支持')]/..//label")
	public List<WebElement> supportOptions;

	@FindBy(xpath="//div[contains(text(),'特别声明')]")
	public WebElement statement;

	@FindBy(xpath="//label[text()='其它']/../div/input")
	public WebElement input_other;
	
	/**************************************************答题/调研/投票内容页*********************************************************/

	@FindBy(xpath="//div[@class='survey-form-questions']")
	public WebElement surveyForm;

	@FindBy(xpath="//div[@class='survey-form-questions']/h1[contains(text(),'Auto_ExamTitle')]")
	public WebElement examTitle;

	@FindBy(xpath="//div[@class='survey-question-title']/span")
	public List<WebElement> quesNo;

	@FindBy(xpath=".//*[@id='container']//input[@value='提交']")
	public WebElement btn_submit;

	@FindBy(xpath="//div[contains(@class,'active')]//a[contains(@class,'btn-next')]")
	public WebElement btn_next;

	@FindBy(xpath="//span[@class='survey-option-text']/input")
	public List<WebElement> input_otherOption;

	@FindBy(xpath="//div[contains(@class,'active')]//label[text()='此项必填']/../div[@class='survey-question-title']")
	public List<WebElement> requireQuestions;

	@FindBy(xpath="//input[@type='radio']/../../../../div[@class='survey-question-title']")
	public List<WebElement> radioQuestions;

	@FindBy(xpath="//input[@type='checkbox']/../../../../div[@class='survey-question-title']")
	public List<WebElement> checkboxQuestions;

	@FindBy(xpath="//div[@class='survey-question-item'][1]//div[@class='survey-option-item']")
	public List<WebElement> options_Q1;

	@FindBy(xpath="//div[@class='survey-question-item'][2]//div[@class='survey-option-item']")
	public List<WebElement> options_Q2;

	@FindBy(xpath="//div[@class='survey-question-item'][3]//div[@class='survey-option-item']")
	public List<WebElement> options_Q3;

	@FindBy(xpath="//div[@class='survey-question-item'][4]//div[@class='survey-option-item']")
	public List<WebElement> options_Q4;

	@FindBy(xpath="//div[contains(@class,'active')]//div[@class='survey-question-item'][1]//option")
	public List<WebElement> dropdown_Q1;

	@FindBy(xpath="//div[contains(@class,'active')]//div[@class='survey-question-item'][2]//option")
	public List<WebElement> dropdown_Q2;

	@FindBy(xpath="//input[@data-type='chinese']")
	public WebElement input_chinese;

	@FindBy(xpath="//input[@data-type='chinese']/../../label[text()='只能输入中文']")
	public WebElement msg_chinese;

	@FindBy(xpath="//input[@data-type='number']")
	public WebElement input_number;

	@FindBy(xpath="//input[@data-type='number']/../../label[text()='只能输入数字']")
	public WebElement msg_number;

	@FindBy(xpath="//input[@data-type='hospital']")
	public WebElement input_hospital;

	@FindBy(xpath="//div[@id='J_hospital_widget_0']//div[@class='hosp-name']//a")
	public List<WebElement> hospitalName;

	@FindBy(xpath="//input[@data-type='dept']")
	public WebElement input_dept;

	@FindBy(xpath="//div[@id='J_cascading_list_widget_0']//li")
	public List<WebElement> deptName;
	
	@FindBy(xpath="//div[@class='ui-dialog-buttonset']//span[text()='确认']")
	public WebElement btn_deptFlip_yes;

	@FindBy(xpath="//input[@data-type='email']")
	public WebElement input_email;

	@FindBy(xpath="//input[@data-type='email']/../../label[text()='邮件地址格式不对']")
	public WebElement msg_email;

	@FindBy(xpath="//input[@data-type='phone']")
	public WebElement input_phone;

	@FindBy(xpath="//input[@data-type='phone']/../../label[text()='请输入正确的手机号']")
	public WebElement msg_phone;

	@FindBy(xpath="//textarea[@data-type='text']")
	public WebElement input_text;

	@FindBy(xpath="//div[@class='survey-form-status']")
	public WebElement msg_feedback;

	@FindBy(xpath="//div[@class='survey-question-answer']")
	public List<WebElement> answer;

	@FindBy(xpath="//div[@class='survey-result']")
	public WebElement voteResult;

	
	
	 public  void openDemo1Page(String name,String pw, boolean isAccept, boolean isUseSession) throws Exception{	
		String pageURL = "";
		openNewWindow();
		driver.get(p("demo1_url"));
        Thread.sleep(1000);
		waitForPageLoadComplete();		
		
		//若不能进入，则重新打开页面
		pageURL = driver.getCurrentUrl();
		if(pageURL.contains("noaccept")){
			driver.get(p("demo1_url"));
			 Thread.sleep(1000);
			waitForPageLoadComplete();		
		}
		
		//点击同意，登录页面
		if(isElementExist(btn_agree)){
			btn_agree.click();
			waitForElementExist(input_username);
			waitForElementExist(input_password);
			waitForElementExist(btn_login);
		}
		
		if(isElementExist(input_username)){
			if(quLoginCode.getAttribute("style").contains("display: block")){
				link_turnToPC.click();
				Thread.sleep(2000);
			}
			if(isElementExist(input_validate)){
				if(isUseSession){
					//读取cookie数据
		    		Cookie cookie = new Cookie.Builder("CASTGC", p("loginCookie")).domain("auth.dxy.cn").isHttpOnly(true).path("/").build();
		    		driver.manage().addCookie(cookie);
		    		
		    		driver.navigate().refresh();
		    		Thread.sleep(2000);
				}else{
					throw new Exception("无法登录，需要输入验证码。");
				}
				
			}else {
				input_username.sendKeys(name);
				input_password.sendKeys(pw);
				btn_login.click();
				waitForPageLoadComplete();	
					
				if(isElementExist(btn_ignor)){
					btn_ignor.click();
					waitForPageLoadComplete();	
				}
			}
		}
		
		pageURL = driver.getCurrentUrl();
		
		if(isAccept){
			if(isElementExist(btn_agree)){
				btn_agree.click();
			}
			waitForElementExist(menu_home);
		}else{
			assertTrue(pageURL.contains("noaccept"));
		}
	}
	
	 public  void openDemo1Page() throws Exception{	
		openDemo1Page(p("user_demo1"),p("pw_demo1"),true,true);
	}	
	
	 public  boolean isMenuAndCategoryPublished(String menuName) throws Exception{
		boolean isPublished = false;
		for(WebElement eachMenu:btn_menu){
			isPublished = eachMenu.getText().trim().equals(menuName);
			if(isPublished){
				break;
			}
		}
		return isPublished;	
	}
	
	 public  boolean isSubMenuPublished(String parentMenuName, String subMenuName) throws Exception{
		boolean isPublished = false;
		
		WebElement parentMenu = driver.findElement(By.xpath("//*[@id='nav']/div/ul/li[contains(@class,'sub')]/a[contains(text(),'"+parentMenuName+"')]"));
		Actions action = new Actions(driver);
		action.moveToElement(parentMenu).build().perform();
		
		for(WebElement eachMenu:btn_subMenu){
			isPublished = eachMenu.getText().trim().equals(subMenuName);
			if(isPublished){
				break;
			}
		}
		return isPublished;	
	}
	
	/*
	 * 验证栏目是否推荐到首页
	 * @param CategoryName
	 * @return
	 * @throws Exception
	 */
	 public  boolean isCategoryPromoted(String CategoryName) throws Exception{
		boolean isPublished = false;
		for(WebElement eachHeader:header_categories){
			isPublished = eachHeader.getText().trim().equals(CategoryName);
			if(isPublished){
				break;
			}
		}
		return isPublished;	
	}
	
		/*
		 * 验证内容是否在热门推荐列表中
		 * @param contentTitle
		 * @return
		 * @throws Exception
		 */
	 public  boolean isContentPromoted(String contentTitle) throws Exception{
			boolean isPromoted = false;
			for(WebElement eachHeader:links_promote){
				try{
					isPromoted = eachHeader.getAttribute("title").trim().equalsIgnoreCase(contentTitle);
				}catch (Exception e){}
				
				if(isPromoted){
					break;
				}
			}
			return isPromoted;	
		}

	 public  void openCategoryMenuPage(String menuName, String categoryName) throws Exception{	
		boolean isMenuExist = false;
		for(WebElement eachMenu:btn_menu){
			if(eachMenu.getText().trim().equals(menuName)){
				isMenuExist = true;
				eachMenu.click();
				waitForPageLoadComplete();
				waitForElementExist(header_listPage);
				assertTrue("栏目页面没有正确打开",header_listPage.getText().trim().equals(categoryName));
				break;
			}
		}
		assertTrue("没有找到菜单",isMenuExist);
	}

	 public  void openSubMenu(String menuName,String parentMenuName, String subMenuUrl) throws Exception{	
		boolean isSubMenuExist = false;
		
		WebElement parentMenu = driver.findElement(By.xpath("//*[@id='nav']/div/ul/li[contains(@class,'sub')]/a[contains(text(),'"+parentMenuName+"')]"));
		Actions action = new Actions(driver);
		action.moveToElement(parentMenu).build().perform();
		
		for(WebElement eachMenu:btn_subMenu){
			if(eachMenu.getText().trim().equals(menuName)){
				isSubMenuExist = true;
				eachMenu.click();
				waitForPageLoadComplete();
				assertTrue(driver.getCurrentUrl().contains(subMenuUrl));
				break;
			}
		}
		assertTrue("没有找到二级菜单",isSubMenuExist);
	}
	
	 public  void openOutMenu(String menuName,String outURL) throws Exception{	
		
		 for(WebElement eachMenu:btn_menu){
			if(eachMenu.getText().trim().equals(menuName)){
				eachMenu.click();
				break;
			}
		}
        Thread.sleep(1000);
		if(isElementExist(btn_continue)){
			btn_continue.click();
	        Thread.sleep(1000);
		}
		switchToLastWindow();
		waitForPageLoadComplete();
		System.out.println(driver.getCurrentUrl());
		assertTrue(driver.getCurrentUrl().contains(outURL));
		
		driver.close();
		switchToLastWindow();
	}
	
	 public void verifyLandingPage(String mode) throws Exception{
		 String currentUrl="";
		 openNewWindow();
		 
		 //clear agree record
		driver.get("http://vs.dxy.cn/admin/test/update-agree/dxy_id/"+p("user_demo1")+"/project/12/m/demo");
		waitForPageLoadComplete();				 
		
		driver.get(p("support_url"));
        Thread.sleep(1000);
		waitForPageLoadComplete();		
		
		if(mode.equals("整页模式")){
			assertTrue(msg_landstatement.getText().contains(p("landStatment")+time));
			waitForElementExist(btn_agree);
			btn_agree.click();
			waitForPageLoadComplete();	
		}

		//sso页面登陆
		waitForElementExist(input_username);
		waitForElementExist(input_password);
		waitForElementExist(btn_login);
		
		if(quLoginCode.getAttribute("style").contains("display: block")){
			link_turnToPC.click();
			Thread.sleep(2000);
		}
		if(isElementExist(input_validate)){
				//读取cookie数据
	    		Cookie cookie = new Cookie.Builder("CASTGC", p("loginCookie")).domain("auth.dxy.cn").isHttpOnly(true).path("/").build();
	    		driver.manage().addCookie(cookie);
	    		
	    		driver.navigate().refresh();
	    		Thread.sleep(2000);
		}else {
			input_username.sendKeys(p("vs_account"));
			input_password.sendKeys(p("vs_password"));
			btn_login.click();
			waitForPageLoadComplete();	
		}
		
		if(mode.equals("悬浮模式")){
			assertTrue(msg_landstatement.getText().contains(p("landStatment")+time));
			waitForElementExist(btn_agree);
			btn_agree.click();
			waitForPageLoadComplete();	
		}
		//登录后，判断是否成功跳转
		currentUrl=driver.getCurrentUrl();
		assertTrue(currentUrl.contains(p("support_url")));
		
	 }

	 public  void verifyContentProperty_allYes(String listUrl, String contentUrl) throws Exception{	
		
		//栏目列表页验证
		driver.get(listUrl);
		waitForPageLoadComplete();
		//验证置顶
		String index = getNodeNo(contentTitles,p("content_title"));
		assertTrue(index.equals("1"));
		//验证评分、评分、赞、摘要、推荐
		WebElement rate = driver.findElement(By.xpath("//span[contains(@class,'icon-sticky')]/../..//div[contains(text(),'评分')]"));
		WebElement like = driver.findElement(By.xpath("//span[contains(@class,'icon-sticky')]/../..//div[@class='action-item J_vote']"));
		WebElement comment = driver.findElement(By.xpath("//span[contains(@class,'icon-sticky')]/../..//div[contains(text(),'评论')]"));
		WebElement summary = driver.findElement(By.xpath("//span[contains(@class,'icon-sticky')]/../..//div[@class='detail']"));
		assertTrue(isElementExist(rate));
		assertTrue(isElementExist(like));
		assertTrue(isElementExist(comment));
		assertTrue(summary.getText().trim().contains(p("content_summary")));
		assertTrue(isContentPromoted(p("content_title")));
		
		//内容页验证
		driver.get(contentUrl);
		waitForPageLoadComplete();
		//验证内容
		assertTrue(text_title.getText().contains(p("content_title")));
		assertTrue(text_source.getText().contains(p("content_source")));
		assertTrue(text_author.getText().contains(p("content_author")));
		assertTrue(text_body.getText().contains(p("content_body")));
		//验证属性
		assertTrue(isElementExist(btns_share));
		assertTrue(isElementExist(btns_rate));
		assertTrue(isElementExist(btns_like));
		assertTrue(isElementExist(btns_submitComm));
		assertTrue(isElementExist(div_sidebar));
		
	}
	

	 public  void verifyContentProperty_allNo(String listUrl, String contentUrl) throws Exception{	
		
		//栏目列表页验证
		driver.get(listUrl);
		waitForPageLoadComplete();
		//验证置顶
		String index = getNodeNo(contentTitles,p("content_title"));
		//验证评分、评分、赞、摘要、置顶
		List<WebElement> promote = driver.findElements(By.xpath("//div[@class='media-item']["+index+"]//span[@class='icon icon-sticky']"));
		List<WebElement>  rate = driver.findElements(By.xpath("//div[@class='media-item']["+index+"]//div[text()='评分']"));
		List<WebElement>  like = driver.findElements(By.xpath("//div[@class='media-item']["+index+"]//div[@class='action-item J_vote']"));
		List<WebElement>  comment = driver.findElements(By.xpath("//div[@class='media-item']["+index+"]//div[contains(text(),'评论')]"));
//		List<WebElement> sideBarContent = driver.findElements(By.xpath("//span[text()='热门推荐']/../../..//a[@title='"+p("content_title")+"']"));
		assertTrue(promote.size() == 0);
		assertTrue(rate.size() == 0);
		assertTrue(like.size() == 0);
		assertTrue(comment.size() == 0);
		assertFalse(isContentPromoted(p("content_title")));
		
		//内容页验证
		driver.get(contentUrl);
		waitForPageLoadComplete();
		//验证内容
		assertTrue(text_title.getText().contains(p("content_title")));
		assertTrue(text_source.getText().contains(p("content_source")));
		assertTrue(text_author.getText().contains(p("content_author")));
		assertTrue(text_body.getText().contains(p("content_body")));
		//验证属性
		assertFalse(isElementExist(btns_share));
		assertFalse(isElementExist(btns_rate));
		assertFalse(isElementExist(btns_like));
		assertFalse(isElementExist(btns_submitComm));
		assertFalse(isElementExist(div_sidebar));
	}
	
	 public void verifySiteConfig(boolean isShowBanner) throws Exception{
		 //验证首页
		 assertTrue(driver.getTitle().equals(p("siteName")+time));
		 assertTrue(meta_desc.getAttribute("content").equals(p("siteDesc")+time));
		 assertTrue(meta_keyword.getAttribute("content").equals(p("siteKeyword")+time));
		 assertTrue(input_search.getAttribute("placeholder").equals(p("searchKeyword")));
		 assertTrue(stat_footer.getText().equals(p("footerStatment")+time));
		 
		 if(isShowBanner){
			 assertTrue(isElementExist(banner));
			 assertTrue(link_banner1.getAttribute("href").equals(p("support_url")));
		 }else{
			 assertFalse(isElementExist(banner));
		 }
		 
		 //验证不能进入声明页
		 driver.get(p("noaccept_url"));
		 waitForPageLoadComplete();
		 assertTrue(stat_noaccept.getText().contains(time));
		 assertTrue(btn_noaccept_redirect.getAttribute("href").equals(p("noacceptUrl")));
		 assertTrue(text_explain.getText().equals(p("explainRight")+time));
	 }	

	 public  void verifySupport() throws Exception{	
		
		driver.get(p("support_url"));
		driver.navigate().refresh();
		driver.get(p("support_url"));
		waitForPageLoadComplete();
		
		String options[]={"A","B","C","D","E","其它"};
		for(int i=0;i<supportOptions.size();i++){
			assertTrue("第"+i+"个选项错误",supportOptions.get(i).getText().contains(options[i]));
		}
		assertTrue("其他输入框未显示",isElementExist(input_other));
		assertTrue("声明内容错误",statement.getText().contains(p("support_statement")));
	}
	
	 public  void verifyExam(String url,boolean isTitleDisplay,boolean isAnwserDisplay,boolean isOnce,boolean isEnable,boolean isVote,boolean isNoDisplay) throws Exception{	
		 openDemo1Page();
		 driver.get(url);
		 waitForPageLoadComplete();		
		 
		 if(isEnable){
			 //验证标题
			 if(isTitleDisplay){
				 Assert.assertTrue("问卷标题未显示", isElementExist(examTitle));
			 }else{
				 Assert.assertFalse("问卷标题显示了", isElementExist(examTitle));
			 }
			 //验证序号显示
			 if(isNoDisplay){
				 Assert.assertTrue("问题序号未显示", quesNo.size()>0);
			 }else{
				 Assert.assertFalse("问题序号显示了",quesNo.size()>0);
			 }
			 //验证单选/多选
			 for(WebElement eachQuestion: radioQuestions){
				 Assert.assertTrue("单选属性不正确", eachQuestion.getText().contains("单选"));
			 }
			 for(WebElement eachQuestion: checkboxQuestions){
				 Assert.assertTrue("多选属性不正确", eachQuestion.getText().contains("多选"));
			 }
			 //验证必填
			 btn_next.click();
			 Thread.sleep(500);
			 for(WebElement eachQuestion: requireQuestions){
				 Assert.assertTrue("非必填的题目也有必填效验。", eachQuestion.getText().contains("必填"));
			 }
			 //输入
			 options_Q1.get(0).click();
			 options_Q2.get(0).click();
			 options_Q3.get(0).click();
			 options_Q3.get(1).click();
			 options_Q4.get(2).click();
			 options_Q4.get(3).click();
			 //输入其他选项
			 if(input_otherOption.size()>0){
				 for(WebElement eachOther: input_otherOption){
					 eachOther.click();
					 eachOther.sendKeys("other value.");
				 }
			 }
			 
			 //下一页
			 btn_next.click();
			 waitForElementExist(dropdown_Q1.get(1));
			//验证必填
			 btn_next.click();
			 Thread.sleep(500);
			 for(WebElement eachQuestion: requireQuestions){
				 Assert.assertTrue("非必填的题目也有必填效验。", eachQuestion.getText().contains("必填"));
			 }
			 //输入
			 dropdown_Q1.get(1).click();
			 dropdown_Q2.get(1).click();
			 
			 //下一页
			 btn_next.click();
			 waitForElementExist(btn_submit);
			 //验证必填
			 btn_submit.click();
			 Thread.sleep(500);
			 for(WebElement eachQuestion: requireQuestions){
				 Assert.assertTrue("非必填的题目也有必填效验。", eachQuestion.getText().contains("必填"));
			 }
			 //验证中文输入框
			 input_chinese.clear();
			 input_chinese.sendKeys("abc");
			 input_number.click();
			 Assert.assertTrue("中文输入框未显示错误提示", isElementExist(msg_chinese));
			 input_chinese.clear();
			 input_chinese.sendKeys("中文字符");
			//验证数字输入框
			 input_number.clear();
			 input_number.sendKeys("abc");
//			 Thread.sleep(500);
//			 input_number.sendKeys(Keys.TAB);
			 input_chinese.click();
			 Assert.assertTrue("数字输入框未显示错误提示", isElementExist(msg_number));
			 input_number.clear();
			 input_number.sendKeys("123");			 
			 //验证邮箱输入框
			 input_email.clear();
			 input_email.sendKeys("abc");
			 input_phone.click();
//			 Thread.sleep(500);
//			 input_email.sendKeys(Keys.TAB);
			 Assert.assertTrue("邮箱输入框未显示错误提示", isElementExist(msg_email));
			 input_email.clear();
			 input_email.sendKeys("abc@test.com");
			 //验证手机输入框
			 input_phone.clear();
			 input_phone.sendKeys("123");
//			 Thread.sleep(500);
//			 input_phone.sendKeys(Keys.TAB);
			 input_email.click();
			 Assert.assertTrue("手机输入框未显示错误提示", isElementExist(msg_phone));
			 input_phone.clear();
			 input_phone.sendKeys("18011112222");
			 //输入医院
			 input_hospital.click();
			 waitForElementExist(hospitalName.get(0));
			 hospitalName.get(0).click();
			 Thread.sleep(2000);
			 //输入科室
			 input_dept.click();
			 waitForElementExist(deptName.get(0));
			 deptName.get(0).click();
			 btn_deptFlip_yes.click();
			 Thread.sleep(3000);
			 //输入自由文本
			 input_text.clear();
			 input_text.sendKeys("123abd^&*");
			 
			 //提交
			 btn_submit.click();
			 Thread.sleep(500);
			 
			 //验证答案显示
			 if(isAnwserDisplay){
				 Assert.assertTrue("正确答案未显示", answer.size()>0);
			 }else{
				 Assert.assertTrue("正确答案显示了", answer.size()==0);
			 }
			 //验证投票结果
			 if(isVote){
				Assert.assertTrue("提交后投票结果未显示",isElementExist(voteResult)); 
			 }
			 //验证次数
			 driver.navigate().refresh();
			 waitForPageLoadComplete();		
			 if(isOnce){
				 Assert.assertFalse("刷新后，仍然可重新答题",isElementExist(btn_submit));
			 }else{
				 Assert.assertTrue("刷新后，不可重新答题",isElementExist(btn_submit));
			 }
		 }else{
			 Assert.assertFalse("禁用的问卷仍然显示", isElementExist(surveyForm));
		 }

	 }
	
	/*
	 * 验证URL是否存在
	 * @param CategoryName
	 * @return
	 * @throws Exception
	 */
	public boolean isUrlExist(String url) throws Exception{
		driver.get(url);
        Thread.sleep(1000);
		waitForPageLoadComplete();		
		if(driver.getTitle().contains("404 Page not found")){
			return false;
		}else{
			return true;
		}
	}
	
}
