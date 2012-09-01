package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.InputGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/29/12
 * Time: 12:53 AM
 */
public abstract class ActiveInputElement extends InputElement {
    protected final String[] actions;

    protected ActiveInputElement(WebNode node, String[] actions) {
        super(node);
        this.actions = actions;
    }

    public String[] getActions() { //TODO Pretty similar to FiniteInputElement
        return new String[] {"click"};
    }
}
