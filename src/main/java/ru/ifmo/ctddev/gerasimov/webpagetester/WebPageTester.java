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
import ru.ifmo.ctddev.gerasimov.webpagetester.forms.Form;
import ru.ifmo.ctddev.gerasimov.webpagetester.forms.FormFactory;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.InputElement;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.InputElementFactory;
import ru.ifmo.ctddev.gerasimov.webpagetester.links.Link;
import ru.ifmo.ctddev.gerasimov.webpagetester.links.LinkFactory;
import ru.ifmo.ctddev.gerasimov.webpagetester.utils.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class WebPageTester {
    private final WebDriver driver;
    private String testName;

    public WebPageTester(WebDriver driver) {
        this.driver = driver;
    }

    private void linkTests(WebNode root) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new File(testName + ".links"));

        List<Link> links = Link.getLinks(root, LinkFactory.getInstance());
        for (Link link: links) {
            out.println(link.getDescription() + " link");
        }

        out.close();
    }

    private void formTests(WebNode root) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new File(testName + ".forms"));

        List<Form> forms = Form.getForms(root, FormFactory.getInstance(), InputElementFactory.getInstance());
        System.err.println(forms);
        for (Form form: forms) {
            System.err.println("Processing " + form);
            out.println("Processing " + form);
            for (int i = 0; i < 5; i++) {
                out.println("Test case #" + i);
                List<Pair<InputElement, String>> test = form.generate();
                for (Pair<InputElement, String> p: test) {
                    out.println(p.first.toString() + ": " + p.second);
                }
                out.println();
            }
            out.println();
        }
        out.close();
    }

    public void run(String url) throws IOException {
        testName = url.replaceAll("[\\?\\*\\:\\\\/\\>\\<]", "_");
        driver.get(url);

        WebNode root = WebNode.buildTree(driver.findElement(By.tagName("body")));

        formTests(root);
        linkTests(root);

        driver.quit();
    }

    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "/L/tmp/chromedriver");
        new WebPageTester(new FirefoxDriver()).run(args[0]);
    }
}
