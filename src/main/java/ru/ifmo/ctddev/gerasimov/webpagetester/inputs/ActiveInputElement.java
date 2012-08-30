package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.InputGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/29/12
 * Time: 12:53 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ActiveInputElement extends InputElement {

    protected ActiveInputElement(WebNode node) {
        super(node);
    }

}
