package ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators;

import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.*;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 9/1/12
 * Time: 3:13 AM
 */
public abstract class InputGeneratorFactory {

    protected InputGeneratorFactory() {

    }

    public abstract InputGenerator getGenerator(InputElement input);
}
