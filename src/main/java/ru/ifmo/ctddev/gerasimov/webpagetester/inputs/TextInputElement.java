package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.TextInputGenerator;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/25/12
 * Time: 7:13 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class TextInputElement extends InfiniteInputElement {
    public TextInputElement(WebNode node) {
        super(node);
    }

    @Override
    public String toString() {
        return getDescription() + " text field";
    }
}
