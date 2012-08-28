package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;

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
    protected WebNode node;

    protected InputElement(WebNode node) {
        this.node = node;
    }

    public abstract String generate();

    public String getDescription() {
        String title = node.element.getAttribute("title");
        if (title != null && !title.isEmpty()) {
            return title;
        }

        WebNode current = node;
        while (!current.element.getTagName().equals("body")) {
            String text = current.element.getText();
            if (text.isEmpty()) {
                current = current.parent;
            } else {
                return text;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Input element " + getDescription();
    }

    public static InputElement makeInput(WebNode node) {
        String tagName = node.element.getTagName();
        if (tagName.equals("textarea")) {
            return new Textarea(node);
        } else if (tagName.equals("select")) {
            return new Select(node);
        } else if (tagName.equals("input")) {
            String type = node.element.getAttribute("type");
            if (type.equals("checkbox")) {
                return new Checkbox(node);
            } else if (type.equals("password")) {
                return new Password(node);
            } else if (type.equals("text")) {
                return new Textarea(node);//TODO Text class
            } else if (type.equals("radio")) {
                return new Radio(node);
            } else {
                //TODO file, image and HTML5 elements
            }
        }
        return null; //TODO
    }
}
