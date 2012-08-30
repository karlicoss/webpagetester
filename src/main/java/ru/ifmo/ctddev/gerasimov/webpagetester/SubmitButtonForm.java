package ru.ifmo.ctddev.gerasimov.webpagetester;

import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.*;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/28/12
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class SubmitButtonForm extends Form {
    private final List<ActiveInputElement> active;
    private final List<ActiveInputGenerator> activeGenerators;
    private final List<PassiveInputElement> passive;
    private final List<PassiveInputGenerator> passiveGenerators;

    public SubmitButtonForm(WebNode form) {
        super(form);
        active = new ArrayList<ActiveInputElement>();
        activeGenerators = new ArrayList<ActiveInputGenerator>();
        passive = new ArrayList<PassiveInputElement>();
        passiveGenerators = new ArrayList<PassiveInputGenerator>();
        List<InputElement> inputs = getInputs();
        for (InputElement input: inputs) {
            if (input instanceof ActiveInputElement) {
                active.add((ActiveInputElement)input);
            } else {
                passive.add((PassiveInputElement)input);
            }
        }
        for (ActiveInputElement input: active) {
            if (input instanceof SubmitButton) {
                activeGenerators.add(new ButtonGenerator((SubmitButton)input));
            }
        }
        for (PassiveInputElement input: passive) {
            if (input instanceof FiniteInputElement) {
                passiveGenerators.add(new UniformFiniteInputGenerator((FiniteInputElement)input));
            } else {
                if (input instanceof Password) {
                    passiveGenerators.add(new UniformPasswordGenerator((Password)input, 8));
                } else {
                    passiveGenerators.add(new UniformTextGenerator((TextInputElement)input, 30));
                }
            }
        }
    }

    @Override
    public String generate() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < passive.size(); i++) {
            sb.append(passive.get(i).toString() + ": " + passiveGenerators.get(i).generate() + "\n");
        }
        int ai = random.nextInt(active.size());
        sb.append(active.get(ai).getDescription() + ": " + activeGenerators.get(ai).generate());
        return sb.toString();
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

