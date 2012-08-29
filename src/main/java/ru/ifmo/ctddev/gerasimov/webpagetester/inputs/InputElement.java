package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/28/12
 * Time: 11:22 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class InputElement {
    protected WebNode node;

    protected InputElement(WebNode node) {
        this.node = node;
    }

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
        if (SubmitButton.isSubmit(node))
            return new SubmitButton(node);
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
        return null;
    }

    public static boolean isInput(WebNode node) {
        //TODO how to keep consistent with makeInput method?
        if (SubmitButton.isSubmit(node))
            return true;
        String tagName = node.element.getTagName();
        if (tagName.equals("textarea")) {
            return true;
        } else if (tagName.equals("select")) {
            return true;
        } else if (tagName.equals("input")) {
            String type = node.element.getAttribute("type");
            System.err.println(type);
            if (type.equals("checkbox")) {
                return true;
            } else if (type.equals("password")) {
                return true;
            } else if (type.equals("text")) {
                return true;//TODO Text class
            } else if (type.equals("radio")) {
                return true;
            } else {
                //TODO file, image and HTML5 elements
            }
        }
        return false;
    }
}
