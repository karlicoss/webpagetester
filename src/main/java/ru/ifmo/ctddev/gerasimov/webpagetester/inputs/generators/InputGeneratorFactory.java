package ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators;

import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.*;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 9/1/12
 * Time: 3:13 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class InputGeneratorFactory {
    protected static InputGeneratorFactory instance;

    protected InputGeneratorFactory() {

    }

    public static InputGeneratorFactory getInstance() {
        return instance;
    }

    public abstract InputGenerator getGenerator(InputElement input);
}
