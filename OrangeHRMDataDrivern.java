package datadrivenFramework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrangeHRMDataDrivern {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		//
		
		
		System.setProperty("Webdriver.Chrome.driver", "C:\\Users\\manka\\eclipse-workspace\\ProjectForDemo\\Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(2000);
		FileInputStream file=new FileInputStream("C:\\Users\\manka\\eclipse-workspace\\SimpleSelenium\\ImpData\\OrangeHRMLoginData.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheet("LoginDataSheet");
		int rows=sheet.getLastRowNum();
		
		for(int r=1;r<=rows;r++) {
			
			XSSFRow row=sheet.getRow(r);
			XSSFCell username=row.getCell(0);
			XSSFCell password=row.getCell(1);
			XSSFCell result=row.createCell(2);
			System.out.println("username is"+username+" password is "+password);
			
			driver.findElement(By.name("username")).sendKeys(username.toString());
			driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password.toString());
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(2000);
			try {
			driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//a[@class='oxd-userdropdown-link'])[4]")).click();
			System.out.println("valid data");
			result.setCellValue("Valid");
			}
			catch(Exception e) {
				System.out.println("invalid data");
				result.setCellValue("invalid");
				
				
			}
			
			}
		file.close();
	FileOutputStream fos=new FileOutputStream ("C:\\Users\\manka\\eclipse-workspace\\SimpleSelenium\\ImpData\\OrangeHRMLoginData.xlsx");
	workbook.write(fos);
	driver.close();


	}

}
