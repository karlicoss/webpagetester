package ru.ifmo.ctddev.gerasimov.webpagetester;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
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

    @Override
    public String toString() {
        return "Input element " + description;
    }

    public static InputElement makeInput(WebNode node) {
        String description = node.getDescription();

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
                return new Password(node, description);
            } else if (type.equals("text")) {
                return new Textarea(node, description);//TODO Text class
            } else if (type.equals("radio")) {
                return new Radio(node, description);
            } else {
                //TODO file, image and HTML5 elements
            }
        }
        return null; //TODO
    }

    public static List<InputElement> getInputs(WebNode node) {
        List<InputElement> result = new ArrayList<InputElement>();
        if (node.isInput()) {
            result.add(InputElement.makeInput(node));
        } else {
            for (WebNode child: node.children) {
                result.addAll(InputElement.getInputs(child));
            }
        }
        //TODO Postprocessing? Merging radiobuttons?
        return result;
    }
}
