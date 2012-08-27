package ru.ifmo.ctddev.gerasimov.webpagetester;

import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/24/12
 * Time: 10:57 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class FiniteInputElement extends InputElement {
    String[] variants;

    protected FiniteInputElement(WebNode node, String description, String[] variants) {
        super(node, description);
        this.variants = variants;
    }

    @Override
    public String generate() {
        Random random = new Random();
        int i = random.nextInt(variants.length);
        return variants[i];
    }
}
