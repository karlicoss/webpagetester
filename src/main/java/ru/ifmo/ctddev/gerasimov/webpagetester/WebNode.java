package ru.ifmo.ctddev.gerasimov.webpagetester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.InputElement;
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

    private Pair<List<WebNode>, Boolean> getFormsHelper() {
        List<WebNode> forms = new ArrayList<WebNode>();
        if (InputElement.isInput(this) && isDisplayed()) {
            return new Pair<List<WebNode>, Boolean>(forms, true);
        } else {
            boolean hasForms = false;
            boolean hasInputs = false;
            List<Pair<List<WebNode>, Boolean>> results = new ArrayList<Pair<List<WebNode>, Boolean>>();
            for (WebNode child: children) {
                Pair<List<WebNode>, Boolean> result = child.getFormsHelper();
                results.add(result);
                hasForms |= (result.first.size() > 0);
                hasInputs |= result.second;
            }
            if (hasForms) {
                for (int i = 0; i < children.size(); i++) {
                    WebNode child = children.get(i);
                    Pair<List<WebNode>, Boolean> result = results.get(i);
                    forms.addAll(result.first);
                    if (result.second) {
                        forms.add(child);
                    }
                }
                return new Pair<List<WebNode>, Boolean>(forms, false);
            } else {
                if (element.getTagName().equals("form") && hasInputs) {
                    forms.add(this);
                    return new Pair<List<WebNode>, Boolean>(forms, false);
                } else {
                    return new Pair<List<WebNode>, Boolean>(forms, hasInputs);
                }
            }
        }
    }

    public List<WebNode> getForms() {
        Pair<List<WebNode>, Boolean> result = getFormsHelper();
        // The only reason to result.second to be true is if there were no <form> forms at all
        if (result.second) {
            result.first.add(this);
        }
        return result.first;
    }
}
