package ru.ifmo.ctddev.gerasimov.webpagetester; /**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/22/12
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.InputElement;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class WebPageTester {
    private WebDriver driver;

    public WebPageTester(WebDriver driver) {
        this.driver = driver;
    }

    public void run() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter url:");
        String url = in.nextLine();
        driver.get(url);

        PrintWriter out = new PrintWriter(new File("reports", url.replaceAll("[\\?\\*\\:\\\\/\\>\\<]", "_") + ".report"));


        WebElement body = driver.findElement(By.tagName("body"));
        WebNode root = WebNode.buildTree(body);
        List<WebNode> forms = root.getForms();
        System.err.println(forms);
        for (WebNode formNode: forms) {
            Form form = Form.makeForm(formNode);
            System.err.println("Processing " + form);
            out.println("Processing " + form);
            for (int i = 0; i < 5; i++) {
                out.println("Test case #" + i);
                out.println(form.generate());
                out.println();
            }
        }
        out.close();
        driver.quit();
    }

    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "/L/tmp/chromedriver");
        new WebPageTester(new ChromeDriver()).run();
    }
}
