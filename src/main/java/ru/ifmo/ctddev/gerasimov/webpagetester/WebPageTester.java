package ru.ifmo.ctddev.gerasimov.webpagetester; /**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/22/12
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.InputElement;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class WebPageTester {
    private WebDriver driver;
    private Random random;

    public WebPageTester() {
        System.setProperty("webdriver.chrome.driver", "/L/tmp/chromedriver");
//        driver = new HtmlUnitDriver();
        driver = new ChromeDriver();
        random = new Random(424);
    }

    public void run() throws IOException {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new File("result.out"));
        System.out.println("Please enter url:");
        String url = in.nextLine();
        driver.get(url);

        //Selenium selenium = (Selenium)driver;
        //System.out.println(selenium.getAllFields());

        WebElement body = driver.findElement(By.tagName("body"));
        WebNode root = WebNode.buildTree(body);
        List<WebNode> forms = root.getForms();
        System.err.println(forms);
        for (WebNode formNode: forms) {
            Form form = Form.makeForm(formNode);
            System.err.println("Processing " + form);
            List<InputElement> inputs = form.getInputs();
            out.println(inputs);
            out.println(form.submit());
        }
        out.close();
        driver.quit();
    }

    public static void main(String[] args) throws IOException {
        //System.err.println(System.getProperty("webdriver.chrome.driver"));

        new WebPageTester().run();
    }
}
