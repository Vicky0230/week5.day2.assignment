package week5.day2.assignments;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

public class DeleteLead extends BaseClass
{
	

	@DataProvider(name="fetchData")
	public Object[][] senddata() throws InvalidFormatException, IOException {
		
		String[][] data=ReadExcelData.readExcelData("Delete Lead");
		return data;
	}

	
	@Test(groups="Delete",dependsOnGroups="Reg", dataProvider="fetchData")
	public void deleteLead(String phoneNumber) throws InterruptedException 
	{

		driver.findElement(By.linkText("Find Leads")).click();
		//Thread.sleep(3000);

		driver.findElement(By.linkText("Phone")).click();

		driver.findElement(By.name("phoneNumber")).sendKeys("234");

		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		Thread.sleep(3000);

		String LeadId = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]")).getText();

		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a")).click();
		Thread.sleep(3000);

		driver.findElement(By.linkText("Delete")).click();
		//Thread.sleep(3000);

		driver.findElement(By.linkText("Find Leads")).click();
		//Thread.sleep(3000);

		driver.findElement(By.name("id")).sendKeys(LeadId);

		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();

		String Text = driver.findElement(By.xpath("//div[text()='No records to display']")).getText();
		System.out.println(Text);

		if(Text.contains("No records to display")) 
		{
			System.out.println("Lead is deleted");
		}
		else
		{
			System.out.println("Lead is not deleted");
		}


	} 

}