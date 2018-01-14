package xboxLiveTestFramework_Tests;

import org.testng.annotations.Test;

public class RegressionTest extends BaseTest{
  @Test(groups = {"regression", "smoke"})
  public void regression() throws InterruptedException {
	  driver.navigate().to("https://www.xbox.com/en-US/live");
	  Thread.sleep(5000);
  }
}
