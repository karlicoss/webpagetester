package ru.ifmo.ctddev.gerasimov.webpagetester;
/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/22/12
 * Time: 6:22 PM
 */
import org.apache.commons.cli.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import ru.ifmo.ctddev.gerasimov.webpagetester.forms.Form;
import ru.ifmo.ctddev.gerasimov.webpagetester.forms.FormFactory;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.InputElement;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.InputElementFactory;
import ru.ifmo.ctddev.gerasimov.webpagetester.links.Link;
import ru.ifmo.ctddev.gerasimov.webpagetester.links.LinkFactory;
import ru.ifmo.ctddev.gerasimov.webpagetester.utils.Pair;

import java.io.*;
import java.util.*;

public class WebPageTester {
    private final WebDriver driver;
    private String testName;
    private String outputdir;

    public WebPageTester(WebDriver driver, String outputdir) {
        this.driver = driver;
        this.outputdir = outputdir;
    }

    private void linkTests(WebNode root) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter out = new PrintWriter(new File(outputdir, testName + ".links"), "UTF-8");

        List<Link> links = Link.getLinks(root, LinkFactory.getInstance());
        for (Link link: links) {
            out.println(link.getDescription() + " link");
        }

        out.close();
    }

    private void formTests(WebNode root) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter out = new PrintWriter(new File(outputdir, testName + ".forms"), "UTF-8");

        List<Form> forms = Form.getForms(root, FormFactory.getInstance(), InputElementFactory.getInstance());
        //System.err.println(forms);
        for (Form form: forms) {
            //System.err.println("Processing " + form);
            out.println("Processing " + form);
            for (int i = 0; i < Config.getInstance().getInt("forms.testcases"); i++) {
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

        //System.err.println(driver.findElement(By.id("quick_login_button")).getText());

        WebNode root = WebNode.buildTree(driver.findElement(By.tagName("body")));

        formTests(root);
        linkTests(root);
    }

    public static void main(String[] args) {
        //System.setProperty("webdriver.chrome.driver", "/L/tmp/chromedriver");
        Options options = new Options();
        options.addOption("c", "config", true, "Path ot config to use");
        options.addOption("d", "driver", true, "Driver to use");
        options.addOption("o", "output", true, "Directory to store output at");
        options.addOption("u", "url", true, "The url to test");

        CommandLineParser parser = new GnuParser();
        CommandLine commandLine;

        WebDriver driver = null;
        String configPath = null;
        String url = null;
        String outputdir = null;
        try {
            commandLine = parser.parse(options, args);
            if (commandLine.hasOption("config"))
                configPath = commandLine.getOptionValue("config");
            else
                configPath = "config.properties";

            if (commandLine.hasOption("output"))
                outputdir = commandLine.getOptionValue("output");
            else
                outputdir = ".";

            if (!commandLine.hasOption("driver"))
                throw new Exception("You have to specify driver");

            String drv = commandLine.getOptionValue("driver");
            if (drv.equals("firefox"))
                driver = new FirefoxDriver();
            else if (drv.equals("chrome"))
                driver = new ChromeDriver();
            else if (drv.equals("htmlunit"))
                driver = new HtmlUnitDriver();
            else
                throw new Exception("Driver " + drv + " is not supported yet");//TODO: more specific exception?

            if (!commandLine.hasOption("url"))
                throw new IOException("You have to specify url");

            url = commandLine.getOptionValue("url");


            Config.init(configPath);
            new WebPageTester(driver, outputdir).run(url);
        } catch (Exception e) {
            System.err.println("Something bad has happened:");
            e.printStackTrace();
        } finally {
            if (driver != null)
                driver.quit();
        }
    }
}
