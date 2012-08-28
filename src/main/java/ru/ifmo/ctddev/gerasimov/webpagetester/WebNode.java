package ru.ifmo.ctddev.gerasimov.webpagetester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.Select;

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

    public boolean isInput() {
        String tagName = element.getTagName();
        if (tagName.equals("textarea")) {
            return true;
        } else if (tagName.equals("select")) {
            return true;
        } else if (tagName.equals("input")) {
            String type = element.getAttribute("type");
            if (type.equals("hidden")) {
                return false;
            } else if (type.equals("submit")) {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean isSubmit() {
        if (element.getTagName().equals("input") && element.getAttribute("type").equals("submit"))
            return true;
        if (element.getTagName().equals("button")) {
            //TODO Some generic method?
            String id = element.getAttribute("id");
            //String class_ = element.getAttribute("class");
            String type = element.getAttribute("type");
            String onclick = element.getAttribute("onclick");
            if (id != null && id.toLowerCase().contains("submit"))
                return true;
            if (type != null && type.toLowerCase().contains("submit"))
                return true;
            if (onclick != null && onclick.toLowerCase().contains("submit"))
                return true;
        }
        return false;
    }

    private Pair<List<WebNode>, Boolean> getFormsHelper() {
        List<WebNode> forms = new ArrayList<WebNode>();
        if (isInput() && isDisplayed()) {
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
