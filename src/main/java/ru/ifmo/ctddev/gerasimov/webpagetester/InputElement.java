package ru.ifmo.ctddev.gerasimov.webpagetester;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/24/12
 * Time: 10:55 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class InputElement {
    public final String description;

    protected InputElement(WebNode node, String description) {
        this.description = description;
    }

    public abstract String generate();


    public static InputElement makeInput(WebNode node, String description) {
        String tagName = node.element.getTagName();
        if (tagName.equals("textarea")) {
            return new Textarea(node, description);
        } else if (tagName.equals("select")) {
            return new Select(node, description);
        } else if (tagName.equals("input")) {
            String type = node.element.getAttribute("type");
            if (type.equals("checkbox")) {
                return new Checkbox(node, description);
            } else if (type.equals("password")) {

            }
        }
        return null; //TODO
    }

    public static boolean isInput(WebElement element) {
        String tagName = element.getTagName();
        return tagName.equals("input") || tagName.equals("select") || tagName.equals("textarea");
    }

    public static List<InputElement> getInputs(WebNode node) {
        if (node.isInput()) {
            //TODO extract getDescription method
            String title = node.element.getAttribute("title");
            if (title != null) {

            }
        }
    }
}
