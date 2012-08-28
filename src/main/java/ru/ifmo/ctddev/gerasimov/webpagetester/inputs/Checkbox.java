package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/25/12
 * Time: 7:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Checkbox extends FiniteInputElement {
    public Checkbox(WebNode node) {
        super(node, new String[]{"set", "unset"});
    }

    @Override
    public String toString() {
        return getDescription() + " checkbox";
    }
}
