package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/31/12
 * Time: 4:55 PM
 */
public class Text extends TextInputElement {
    protected Text(WebNode node) {
        super(node);
    }

    public static boolean isText(WebNode node) {
        return node.element.getTagName().equals("input") && node.element.getAttribute("type").equals("text");
    }
}
