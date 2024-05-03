package Framework_Tutorial.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Framework_Tutorial.pageObjects.landingPage;

public class baseTest {
	public WebDriver driver;
	public	landingPage lp;
	public WebDriver initilizeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Framework_Tutorial//resources//globalData.properties");
		
		prop.load(fis);
		String browsername=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browsername=prop.getProperty("browser");
		System.out.println(browsername);
		if(browsername.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			if(browsername.contains("headless"))
			options.addArguments("headless");
			driver= new ChromeDriver(options);
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			driver= new FirefoxDriver(); 
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonToHashMap(String filepath) throws IOException
	{
		//read json to string
		String jsonContent =FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
	//convert string to list of hashmaps
		ObjectMapper mapper =new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
	return data;
	}
	public String getScreenShot(String testcaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ss=(TakesScreenshot)driver;
		File src=	ss.getScreenshotAs(OutputType.FILE);
		File dest= new File(System.getProperty("user.dir")+"//Reports//"+testcaseName+".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir")+"//Reports//"+testcaseName+".png";
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public landingPage launchDriver() throws IOException
	{
		driver = initilizeDriver();
		lp =new landingPage(driver);
		return lp;
	}
}
