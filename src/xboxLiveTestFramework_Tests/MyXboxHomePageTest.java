package xboxLiveTestFramework_Tests;

import org.testng.annotations.Test;

import xboxLivePageObjects_Lib.*;


import static org.testng.Assert.*;

import org.testng.annotations.*;

public class MyXboxHomePageTest extends BaseTest {
	@AfterMethod(groups= {"homepage"})
	public void startPoint() {
	  driver.get("https://account.xbox.com/en-us/social?xr=socialtwistnav");
	}
	@BeforeClass(groups= {"homepage"})
	public void pause() {
		synchronized (driver) {
			  try {driver.wait(2000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		}
	}
	
	// Rework to edit this Test FrameWork to incorporate the Activity Feed Component Object.
	
	  @Test(groups= {"homepage", "post"})
	  public void postNewMessage() {
		  //ActivityFeed Component instead
		  ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		  homeActivityFeed.newPost();
		  synchronized (driver) {
			  try {driver.wait(4000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		  }
		  assertTrue(homeActivityFeed.findNewPost());
		  
	  }
	  
	  @Test(groups= {"homepage", "like"})
	  public void postALike() {
		  ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		  homeActivityFeed.likeAnyPost();
		  
		  homeActivityFeed = homeActivityFeed.refresh();
		  synchronized (driver) {
			  try {driver.wait(4000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		  }
		  assertTrue(homeActivityFeed.findRecentLikes());
	  }
	  
	  @Test(groups= {"homepage", "post"})
	  public void openBoxAndComment() {
		  ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		  homeActivityFeed.viewAnyPostAndComment();
		  synchronized (driver) {
			  try {driver.wait(4000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		  }
		  assertTrue(homeActivityFeed.veiwNewCommentDialogBox());
		  
	  }
	  
	  @Test(groups= {"homepage", "post"})
	  public void sharePosts() {
		  ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		  homeActivityFeed.shareAnyPost();
		  synchronized (driver) {
			  try {driver.wait(4000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		  }
		  homeActivityFeed = homeActivityFeed.refresh();
		  assertTrue(homeActivityFeed.findRecentShare());
		  
		  homeActivityFeed.expandPostedActivityFeed();
		  
		  assertTrue(homeActivityFeed.originalPostShared(dummyAccount.getProfileName()));
		  
	  }
	  
	  //Work deletePosts(have it general instead of specific), navigate to postedlinks, and get more function test..
	  @Test(groups= {"homepage"})
	  public void deletePost() {
		  ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		  homeActivityFeed = homeActivityFeed.deletePost();
		  
		  assertFalse(homeActivityFeed.findMatchInPost());
	  }
	  
	  @Test(groups= {"homepage"})
	  public void expandList() {
		  ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  System.out.println("Feed Size: " + homeActivityFeed.postedActivityFeed.size());
		  
		  homeActivityFeed.expandPostedActivityFeed();
		  
		  System.out.println("Feed Size: " + homeActivityFeed.postedActivityFeed.size());
		  
		  assertTrue(homeActivityFeed.postedActivityFeed.size() > 8);
	  }
  
	  


}
