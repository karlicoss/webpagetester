package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.TextInputGenerator;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.UniformTextInputGenerator;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/24/12
 * Time: 11:01 PM
 */
public class Textarea extends TextInputElement {
    public Textarea(WebNode node) {
        super(node);
    }

    public static boolean isTextarea(WebNode node) {
        return node.element.getTagName().equals("textarea");
    }

    @Override
    public String toString() {
        return getDescription() + " textarea";
    }
}
