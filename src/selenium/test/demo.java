package selenium.test;

 import java.io.IOException;
import java.nio.charset.Charset;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import com.csvreader.CsvReader;
import java.io.FileOutputStream;  
import java.io.PrintStream; 
public class demo {
    
public static void main(String[] args) throws IOException {
	    //输出到TXT文件中
	    PrintStream ps = new PrintStream("C:/Users/lenovo/Desktop/log.txt");
        System.setOut(ps);                    
        //生成CsvReader对象，以，为分隔符，GBK编码方式
        CsvReader r = new CsvReader("C://Users//lenovo//Desktop//软件测试名单.csv", ',',Charset.forName("GBK"));
        //读取表头
        r.readHeaders();
        //逐条读取记录，直至读完
        while (r.readRecord()) {                        
        //读取一条记录           
        //按列名读取这条记录的值
        String number_csv = r.get("学号"); 
        String name_csv = r.get("姓名");
        String address_csv = r.get("git地址");
        String pwd_csv = number_csv.substring(4);
                        
             //打开火狐浏览器
        FirefoxBinary firefoxBinary = new FirefoxBinary();
       //在不显示界面的情况下显示结果
        firefoxBinary.addCommandLineOptions("--headless");
               System.setProperty("webdriver.gecko.driver", "D:\\javatest\\geckodriver.exe"); 
               //WebDriver driver = new FirefoxDriver();
               FirefoxOptions firefoxOptions = new FirefoxOptions();
               firefoxOptions.setBinary(firefoxBinary);
               FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
              //访问给定网址
              driver.get("http://121.193.130.195:8800");               
              driver.manage().window().maximize();
             //输入用户名
              WebElement input_name = driver.findElement(By.name("id"));
              input_name.clear();
              input_name.sendKeys(number_csv);
             //输入密码
              WebElement input_pwd = driver.findElement(By.name("password"));
              input_pwd.clear();
              input_pwd.sendKeys(pwd_csv);
             //点击登录按钮
              WebElement btn = driver.findElement(By.id("btn_login"));
              btn.click();
            //登录成功之后，获得当前页面的用户信息
              //clickAndWait(By.xpath("//tbody[@id='table-main']"));
              //String info_web = driver.findElement(By.xpath("//tbody[@id='table-main']")).getText();
              String name_web = driver.findElement(By.id("student-name")).getText();
              String number_web = driver.findElement(By.id("student-id")).getText();
              String address_web = driver.findElement(By.id("student-git")).getText();
           //比较查询信息           
              if(name_csv.equals(name_web)&&number_csv.equals(number_web)&&address_csv.equals(address_web))
                         {
            	                   System.out.println(number_web+"用户信息一致.");
            	         }
             else
               {
                       System.out.println(number_web+"的信息不一致.");
               }
               driver.close();
                }
                r.close();   
       
   }


}


