package ru.ifmo.ctddev.gerasimov.webpagetester;

import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.InputElement;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.PassiveInputElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/28/12
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Form {
    public final WebNode form;

    protected Form(WebNode form) {
        this.form = form;
    }

    public abstract String submit();

    public static Form makeForm(WebNode form) {
        WebNode button = SubmitButtonForm.getSubmitButton(form);
        if (button != null) {
            return new SubmitButtonForm(form, button);
        }
        //TODO other submit methods
        //TODO some pattern to let easy submit methods adding
        return null;
    }

    private List<InputElement> getInputsHelper(WebNode node) {
        List<InputElement> result = new ArrayList<InputElement>();
        if (InputElement.isInput(node) && node.isDisplayed()) {
            result.add(InputElement.makeInput(node));
        } else {
            for (WebNode child: node.children) {
                result.addAll(getInputsHelper(child));
            }
        }
        //TODO Postprocessing? Merging radiobuttons?
        return result;
    }

    public List<InputElement> getInputs() {
        return getInputsHelper(form);
    }
}
