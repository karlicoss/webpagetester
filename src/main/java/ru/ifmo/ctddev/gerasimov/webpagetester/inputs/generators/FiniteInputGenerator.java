package ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators;

import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.FiniteInputElement;

/**
* Created with IntelliJ IDEA.
* User: karlicos
* Date: 8/30/12
* Time: 3:44 PM
* To change this template use File | Settings | File Templates.
*/
public abstract class FiniteInputGenerator extends PassiveInputGenerator {
    protected final String[] variants;

    protected FiniteInputGenerator(FiniteInputElement input) {
        this.variants = input.getVariants();
    }
}
