package ru.ifmo.ctddev.gerasimov.webpagetester; /**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/22/12
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.*;

public class WebPageTester {
    private WebDriver driver;
    private Random random;

    public WebPageTester() {
        System.setProperty("webdriver.chrome.driver", "/L/tmp/chromedriver");
        driver = new HtmlUnitDriver();
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

    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter url:");
        String url = in.nextLine();
        driver.get(url);

        WebElement body = driver.findElement(By.tagName("body"));
        WebNode root = WebNode.buildTree(body);
        List<WebNode> blocks = root.getBlocks();
        for (WebNode block: blocks) {
            System.out.println(block);
        }
        driver.quit();
    }

    public static void main(String[] args) {
        //System.err.println(System.getProperty("webdriver.chrome.driver"));

        new WebPageTester().run();
    }
}
