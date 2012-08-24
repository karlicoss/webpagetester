package ru.ifmo.ctddev.gerasimov; /**
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

        //System.out.println(body);

        //List<WebElement> blocks = getBlocks(driver.findElement(By.tagName("body")));
//        for (WebElement form: forms) {
//            if (form.isDisplayed()) {
//                System.out.println("Form: " + form.getAttribute("class"));
//                System.out.println("Inputs:");
//                List<WebElement> inputs = getInputs(form);
//                for (WebElement input: inputs) {
//                    if (input.isDisplayed())
//                        System.out.println(input.getAttribute("type"));
//                }
//            }
//        }
        driver.quit();
    }

    private void go(String url) {
        System.out.println("Moving to " + url);
        driver.get(url);
        List<WebElement> links = driver.findElements(By.tagName("a"));
        if (links.size() == 0)
            return;
        String href = null;
        while (href == null) {
            WebElement link = links.get(random.nextInt(links.size()));
            href = link.getAttribute("href");
        }
        go(href);
    }

    public static void main(String[] args) {
        //System.err.println(System.getProperty("webdriver.chrome.driver"));

        new WebPageTester().run();
    }
}
