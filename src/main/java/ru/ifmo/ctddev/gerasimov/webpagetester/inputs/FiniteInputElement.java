package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.FiniteInputGenerator;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/24/12
 * Time: 10:57 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class FiniteInputElement extends PassiveInputElement {
    final protected String[] variants;

    public final String[] getVariants() {
        return variants;
    }

    protected FiniteInputElement(WebNode node, String[] variants) {
        super(node);
        this.variants = variants;
    }
}
