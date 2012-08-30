package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.TextInputGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/25/12
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Password extends TextInputElement {

    public Password(WebNode node) {
        super(node);
    }

    @Override
    public String toString() {
        return getDescription() + " password field";
    }
}
