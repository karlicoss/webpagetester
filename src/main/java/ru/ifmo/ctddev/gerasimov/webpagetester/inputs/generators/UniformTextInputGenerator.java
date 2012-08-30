package ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators;

import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.TextInputElement;

import java.util.Random;

/**
* Created with IntelliJ IDEA.
* User: karlicos
* Date: 8/30/12
* Time: 3:48 PM
* To change this template use File | Settings | File Templates.
*/
public abstract class UniformTextInputGenerator extends TextInputGenerator {
    private final char[] allowedChars;
    private final int length;
    private final Random random;

    public UniformTextInputGenerator(TextInputElement input, char[] allowedChars, int length) {
        super(input);
        this.allowedChars = allowedChars;
        this.length = length;
        this.random = new Random();
    }

    public UniformTextInputGenerator(TextInputElement input, char[] allowedChars, int length, Random random) {
        super(input);
        this.allowedChars = allowedChars;
        this.length = length;
        this.random = random;
    }

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(allowedChars[random.nextInt(allowedChars.length)]);
        }
        return sb.toString();
    }
}
