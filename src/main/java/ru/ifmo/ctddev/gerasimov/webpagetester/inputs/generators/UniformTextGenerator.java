package ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators;

import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.TextInputElement;

import java.util.Random;

/**
* Created with IntelliJ IDEA.
* User: karlicos
* Date: 8/30/12
* Time: 3:53 PM
* To change this template use File | Settings | File Templates.
*/
public class UniformTextGenerator extends UniformTextInputGenerator {
    public UniformTextGenerator(TextInputElement input, int length) {
        super(input, "abcdef123ABCDEF".toCharArray(), length);
    }

    public UniformTextGenerator(TextInputElement input, int length, Random random) {
        super(input, "abcdef123ABCDEF".toCharArray(), length, random);
    }
}
