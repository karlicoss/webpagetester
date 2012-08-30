package ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators;

import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.Password;

import java.util.Random;

/**
* Created with IntelliJ IDEA.
* User: karlicos
* Date: 8/30/12
* Time: 3:47 PM
* To change this template use File | Settings | File Templates.
*/
public class UniformPasswordGenerator extends UniformTextInputGenerator {

    public UniformPasswordGenerator(Password password, int length) {
        super(password, "abcdef123#".toCharArray(), length);
    }

    public UniformPasswordGenerator(Password password, int length, Random random) {
        super(password, "abcdef123#".toCharArray(), length, random);
    }
}
