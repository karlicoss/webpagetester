package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/24/12
 * Time: 10:57 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class FiniteInputElement extends PassiveInputElement {
    String[] variants;

    protected FiniteInputElement(WebNode node, String[] variants) {
        super(node);
        this.variants = variants;
    }

    @Override
    public String generate() {
        Random random = new Random();
        int i = random.nextInt(variants.length);
        return variants[i];
    }
}
