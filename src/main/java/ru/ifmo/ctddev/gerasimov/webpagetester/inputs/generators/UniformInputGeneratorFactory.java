package ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators;

import ru.ifmo.ctddev.gerasimov.webpagetester.Config;
import ru.ifmo.ctddev.gerasimov.webpagetester.NotSupportedException;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.*;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 9/1/12
 * Time: 2:59 AM
 */
public class UniformInputGeneratorFactory extends InputGeneratorFactory {
    private static InputGeneratorFactory instance = new UniformInputGeneratorFactory();

    protected UniformInputGeneratorFactory() {
        super();
    }

    public static InputGeneratorFactory getInstance() {
        return instance;
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
                    return new UniformPasswordGenerator((Password)input, Config.getInstance().getInt("password.maxlength"));
                } else if (input instanceof TextInputElement) {
                    return new UniformTextGenerator((TextInputElement)input, Config.getInstance().getInt("text.maxlength"));
                }
            }
        }

        throw new NotSupportedException("UniformInputGenerator.getGenerator", input.toString());
    }

}
