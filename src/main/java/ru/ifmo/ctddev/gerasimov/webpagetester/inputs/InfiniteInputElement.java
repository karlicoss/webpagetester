package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.InfiniteInputGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/24/12
 * Time: 10:58 PM
 */
public abstract class InfiniteInputElement extends PassiveInputElement {

    protected InfiniteInputElement(WebNode node) {
        super(node);
    }
}
