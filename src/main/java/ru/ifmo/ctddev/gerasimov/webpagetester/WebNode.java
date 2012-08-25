package ru.ifmo.ctddev.gerasimov.webpagetester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

    public WebNode(WebElement element) {
        this.element = element;
        this.children = new ArrayList<WebNode>();
    }

    public static WebNode buildTree(WebElement element) {
        WebNode node = new WebNode(element);
        List<WebElement> children = element.findElements(By.xpath("*"));
        for (WebElement child: children) {
            node.children.add(WebNode.buildTree(child));
        }
        return node;
    }

    public String toString() {
        return element.toString();
    }

    public boolean isBlock() {
        //System.out.println(element.getTagName());
        if (element.getTagName().equals("form"))
            return true;
        return false;
    }

    public boolean isInput() {
        return false;//TODO
    }

    public List<WebNode> getBlocks() {
        List<WebNode> result = new ArrayList<WebNode>();
        if (isBlock()) {
            result.add(this);
            return result;
        }
        for (WebNode node: children) {
            result.addAll(node.getBlocks());
        }
        return result;
    }
}
