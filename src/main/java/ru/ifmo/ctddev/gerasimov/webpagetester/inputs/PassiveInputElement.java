package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.InputGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/24/12
 * Time: 10:55 PM
 */
public abstract class PassiveInputElement extends InputElement {

    protected PassiveInputElement(WebNode node) {
        super(node);
    }

}
