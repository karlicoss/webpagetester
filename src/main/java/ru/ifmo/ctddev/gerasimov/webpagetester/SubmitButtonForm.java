package ru.ifmo.ctddev.gerasimov.webpagetester;

import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.SubmitButton;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/28/12
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class SubmitButtonForm extends Form {
    private WebNode submitButton;

    public SubmitButtonForm(WebNode form, WebNode submitButton) {
        super(form);
        this.submitButton = submitButton;
    }

    @Override
    public String submit() {
        return null;
    }

    private static WebNode getSubmitButtonHelper(WebNode node) {
        if (SubmitButton.isSubmit(node)) {
            return node;
        } else {
            for (WebNode child: node.children) {
                WebNode submit = getSubmitButtonHelper(child);
                if (submit != null) {
                    return submit;
                }
            }
        }
        return null;
    }

    public static WebNode getSubmitButton(WebNode form) {
        return getSubmitButtonHelper(form);
    }
}

