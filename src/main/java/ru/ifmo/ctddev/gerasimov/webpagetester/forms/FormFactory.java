package ru.ifmo.ctddev.gerasimov.webpagetester.forms;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.InputElementFactory;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.UniformInputGeneratorFactory;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 9/1/12
 * Time: 2:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class FormFactory {
    protected static FormFactory instance = new FormFactory();

    protected FormFactory() {

    }

    public static FormFactory getInstance() {
        return instance;
    }

    public boolean isForm(WebNode form) {
        if (InputAndClickButtonForm.isInputAndClickButtonForm(form))
            return true;
        return false;
    }

    public Form makeForm(WebNode form, InputElementFactory inputFactory) {
        if (InputAndClickButtonForm.isInputAndClickButtonForm(form))
            return new InputAndClickButtonForm(form, inputFactory, UniformInputGeneratorFactory.getInstance());
        //TODO other submit methods like EnterPressForm?
        return null;
    }
}
