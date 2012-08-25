package ru.ifmo.ctddev.gerasimov.webpagetester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/25/12
 * Time: 7:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Checkbox extends FiniteInputElement {
    private List<String> variants = new ArrayList<String>();
    {
        variants.add("set");
        variants.add("unset");
    }

    public Checkbox(WebNode node, String description) {
        super(node, description);
    }

    @Override
    public String generate() {
        Random random = new Random();
        int i = random.nextInt(variants.size());
        return variants.get(i);
    }
}
