package ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators;

import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.*;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 9/1/12
 * Time: 2:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class UniformInputGeneratorFactory extends InputGeneratorFactory {
    static {
        instance = new UniformInputGeneratorFactory();
    }

    @Override
    public InputGenerator getGenerator(InputElement input) {
        if (input instanceof ActiveInputElement) {
            if (input instanceof Button) {
                return new ButtonGenerator((Button)input);
            }
        } else if (input instanceof PassiveInputElement) {
            if (input instanceof FiniteInputElement) {
                return new UniformFiniteInputGenerator((FiniteInputElement)input);
            } else if (input instanceof InfiniteInputElement) {
                if (input instanceof Password) {
                    return new UniformPasswordGenerator((Password)input, 5);
                } else if (input instanceof TextInputElement) {
                    return new UniformTextGenerator((TextInputElement)input, 10);
                }
            }
        }
        return null;//TODO throw some exception
    }

    protected UniformInputGeneratorFactory() {

    }

}
