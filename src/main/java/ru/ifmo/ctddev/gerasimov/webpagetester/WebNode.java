package ru.ifmo.ctddev.gerasimov.webpagetester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.InputElement;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.InputFactory;
import ru.ifmo.ctddev.gerasimov.webpagetester.utils.Pair;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/24/12
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebNode {
    public final WebElement element;
    public final List<WebNode> children;
    public final WebNode parent;

    private WebNode(WebElement element, WebNode parent) {
        this.element = element;
        this.children = new ArrayList<WebNode>();
        this.parent = parent;
    }

    public WebNode copy() {
        WebNode node = new WebNode(this.element, null);
        node.children.addAll(this.children);
        return node;
    }

    private static WebNode buildTreeHelper(WebElement element, WebNode parent) {
        WebNode node = new WebNode(element, parent);
        List<WebElement> children = element.findElements(By.xpath("*"));
        for (WebElement child: children) {
            node.children.add(WebNode.buildTreeHelper(child, node));
        }
        return node;
    }

    public static WebNode buildTree(WebElement element) {
        return WebNode.buildTreeHelper(element, null);
    }

    public String toString() {
        return element.toString();
    }

    public boolean isDisplayed() {
        return element.isDisplayed();
    }
}
