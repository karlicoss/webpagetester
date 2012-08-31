package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.ButtonGenerator;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.UniformFiniteInputGenerator;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.UniformTextGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/28/12
 * Time: 11:22 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class InputElement {
    protected final WebNode node;

    protected InputElement(WebNode node) {
        this.node = node;
    }

    public String getDescription() {
        String title = node.element.getAttribute("title");
        if (title != null && !title.isEmpty()) {
            return title;
        }

        WebNode current = node;
        while (current != null) {
            String text = current.element.getText();
            if (text.isEmpty()) {
                current = current.parent;
            } else {
                return text;
            }
        }

        return null;
    }

    public boolean isDisplayed() {
        return node.element.isDisplayed();
    }

    @Override
    public String toString() {
        return "Input element " + getDescription();
    }

}
