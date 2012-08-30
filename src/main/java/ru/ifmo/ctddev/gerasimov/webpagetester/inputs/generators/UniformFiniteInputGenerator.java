package ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators;

import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.FiniteInputElement;

import java.util.Random;

/**
* Created with IntelliJ IDEA.
* User: karlicos
* Date: 8/30/12
* Time: 3:46 PM
* To change this template use File | Settings | File Templates.
*/
public class UniformFiniteInputGenerator extends FiniteInputGenerator {
    private final Random random;

    public UniformFiniteInputGenerator(FiniteInputElement input) {
        super(input);
        this.random = new Random();
    }

    public UniformFiniteInputGenerator(FiniteInputElement input, Random random) {
        super(input);
        this.random = random;
    }

    @Override
    public String generate() {
        return variants[random.nextInt(variants.length)];
    }
}
