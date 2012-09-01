package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.TextInputGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/25/12
 * Time: 7:18 PM
 */
public class Password extends TextInputElement {

    public Password(WebNode node) {
        super(node);
    }

    public static boolean isPassword(WebNode node) {
        return node.element.getTagName().equals("input") && node.element.getAttribute("type").equals("password");
    }

    @Override
    public String toString() {
        return getDescription() + " password field";
    }
}
