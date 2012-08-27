package ru.ifmo.ctddev.gerasimov.webpagetester; /**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/22/12
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

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

    public List<WebElement> getBlocks(WebElement body) {
        List<WebElement> blocks = body.findElements(By.tagName("form"));
        return blocks;
    }

    public List<WebElement> getInputs(WebElement form) {
        List<WebElement> inputs = form.findElements(By.tagName("input"));
        return inputs;
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
        List<WebNode> blocks = root.getBlocks();
        System.err.println(blocks);
        for (WebNode block: blocks) {
            System.err.println("Processing " + block);
            List<InputElement> inputs = InputElement.getInputs(block);
            out.println(inputs);
        }
        out.close();
        driver.quit();
    }

    public static void main(String[] args) throws IOException {
        //System.err.println(System.getProperty("webdriver.chrome.driver"));

        new WebPageTester().run();
    }
}
