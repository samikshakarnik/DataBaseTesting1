package TestCases;

import java.sql.*;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Case1 {
	@Test
	public static void Connection() throws Exception {

		WebDriver driver = new ChromeDriver();

//Hosting driver from local machine

		driver.get("http://localhost/OpenCartSite/upload/index.php?route=account/register");

//Storing the Test Data	
		
		String firstname = "Ashok";
		String lastname = "Purohit";
		String email = "ashokpurohit@gmail.com";
		String password = "Ashok123";
		
//Passing the data
		
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(firstname);
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(password);
		
		WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
		        .until(ExpectedConditions.elementToBeClickable(By.className("form-check-input")));
		firstResult.click();
		//driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	
//DataBase Connection
		
		Connection con = DriverManager.getConnection("jdbc:mariadb://localhost/opencartpract", "root", "");
		Statement stmt = con.createStatement();
		
//Passing the SQL Query
		String q = "SELECT firstname, lastname, email FROM oc_customer";
		ResultSet rs =stmt.executeQuery(q);
		
		boolean status =false;
		while (rs.next()) {
			String dbfirstname = rs.getString("firstname");
			String dblastname = rs.getString("firstname");
			String dbemail = rs.getString("firstname");
			
			if (firstname.equalsIgnoreCase(dbfirstname)&& lastname.equalsIgnoreCase(dblastname)
					&& email.equalsIgnoreCase(dbemail))
			{
				System.out.println("Record Found in DataBase || TEST PASS");
				status=true;
				break;
			}
		}
		
		if(status == false) {
			System.out.println("Record Not Found in DataBase || TEST FAILED");
		}
		
		
	}
}