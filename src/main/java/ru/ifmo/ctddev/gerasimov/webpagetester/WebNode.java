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
    //private static int counter = 0;

    public WebNode(WebElement element) {
        this.element = element;
        this.children = new ArrayList<WebNode>();
    }

    public static WebNode buildTree(WebElement element) {
        //counter++;
        //System.err.println(counter);
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
        if (!element.isDisplayed())
            return false;
        String tagName = element.getTagName();
        if (tagName.equals("textarea")) {
            return true;
        } else if (tagName.equals("select")) {
            return true;
        } else if (tagName.equals("input")) {
            String type = element.getAttribute("type");
            return !type.equals("hidden");
        }
        return false;
    }

    public String getDescription() {
        String title = element.getAttribute("title");
        if (title != null && !title.isEmpty()) {
            return title;
        }

        WebElement current = element;
//        System.err.println("Processing " + element);
        while (!current.getTagName().equals("body")) {
//            System.err.println("Current tag: " + current);
            String text = current.getText();
//            System.err.println("Current text: " + text);
            if (text.isEmpty()) {
                current = current.findElement(By.xpath(".."));
            } else {
                return text;
            }
        }

        return null;
    }

    private Pair<List<WebNode>, Boolean> pblocks() {
        List<WebNode> blocks = new ArrayList<WebNode>();
        if (isInput()) {
            return new Pair<List<WebNode>, Boolean>(blocks, true);
        } else {
            boolean hasBlock = false;
            boolean hasInputs = false;
            List<Pair<List<WebNode>, Boolean>> results = new ArrayList<Pair<List<WebNode>, Boolean>>();
            for (WebNode child: children) {
                Pair<List<WebNode>, Boolean> result = child.pblocks();
                results.add(result);
                hasBlock |= (result.first.size() > 0);
                hasInputs |= result.second;
            }
            if (hasBlock) {
                for (int i = 0; i < children.size(); i++) {
                    WebNode child = children.get(i);
                    Pair<List<WebNode>, Boolean> result = results.get(i);
                    blocks.addAll(result.first);
                    if (result.second) {
                        blocks.add(child);
                    }
                }
                return new Pair<List<WebNode>, Boolean>(blocks, false);
            } else {
                if (isBlock()) {
                    blocks.add(this);
                }
                return new Pair<List<WebNode>, Boolean>(blocks, hasInputs);
            }
        }
    }

    public List<WebNode> getBlocks() {
        Pair<List<WebNode>, Boolean> result = pblocks();
        //TODO will the case result.second == true be present?
        return result.first;
    }
}
