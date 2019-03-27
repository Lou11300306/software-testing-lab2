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
	    //�����TXT�ļ���
	    PrintStream ps = new PrintStream("C:/Users/lenovo/Desktop/log.txt");
        System.setOut(ps);                    
        //����CsvReader�����ԣ�Ϊ�ָ�����GBK���뷽ʽ
        CsvReader r = new CsvReader("C://Users//lenovo//Desktop//�����������.csv", ',',Charset.forName("GBK"));
        //��ȡ��ͷ
        r.readHeaders();
        //������ȡ��¼��ֱ������
        while (r.readRecord()) {                        
        //��ȡһ����¼           
        //��������ȡ������¼��ֵ
        String number_csv = r.get("ѧ��"); 
        String name_csv = r.get("����");
        String address_csv = r.get("git��ַ");
        String pwd_csv = number_csv.substring(4);
                        
             //�򿪻�������
        FirefoxBinary firefoxBinary = new FirefoxBinary();
       //�ڲ���ʾ������������ʾ���
        firefoxBinary.addCommandLineOptions("--headless");
               System.setProperty("webdriver.gecko.driver", "D:\\javatest\\geckodriver.exe"); 
               //WebDriver driver = new FirefoxDriver();
               FirefoxOptions firefoxOptions = new FirefoxOptions();
               firefoxOptions.setBinary(firefoxBinary);
               FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
              //���ʸ�����ַ
              driver.get("http://121.193.130.195:8800");               
              driver.manage().window().maximize();
             //�����û���
              WebElement input_name = driver.findElement(By.name("id"));
              input_name.clear();
              input_name.sendKeys(number_csv);
             //��������
              WebElement input_pwd = driver.findElement(By.name("password"));
              input_pwd.clear();
              input_pwd.sendKeys(pwd_csv);
             //�����¼��ť
              WebElement btn = driver.findElement(By.id("btn_login"));
              btn.click();
            //��¼�ɹ�֮�󣬻�õ�ǰҳ����û���Ϣ
              //clickAndWait(By.xpath("//tbody[@id='table-main']"));
              //String info_web = driver.findElement(By.xpath("//tbody[@id='table-main']")).getText();
              String name_web = driver.findElement(By.id("student-name")).getText();
              String number_web = driver.findElement(By.id("student-id")).getText();
              String address_web = driver.findElement(By.id("student-git")).getText();
           //�Ƚϲ�ѯ��Ϣ           
              if(name_csv.equals(name_web)&&number_csv.equals(number_web)&&address_csv.equals(address_web))
                         {
            	                   System.out.println(number_web+"�û���Ϣһ��.");
            	         }
             else
               {
                       System.out.println(number_web+"����Ϣ��һ��.");
               }
               driver.close();
                }
                r.close();   
       
   }


}


