package ru.ifmo.ctddev.gerasimov.webpagetester.forms;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
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
    private final List<SubmitButton> submits;
    private final List<ActiveInputGenerator> submitGenerators;
    private final List<PassiveInputElement> passive;
    private final List<PassiveInputGenerator> passiveGenerators;

    public SubmitButtonForm(WebNode form) {
        super(form);
        submits = new ArrayList<SubmitButton>();
        submitGenerators = new ArrayList<ActiveInputGenerator>();
        passive = new ArrayList<PassiveInputElement>();
        passiveGenerators = new ArrayList<PassiveInputGenerator>();
        for (InputElement input: inputs) {
            if (input instanceof SubmitButton) {
                submits.add((SubmitButton) input);
            } else if (input instanceof PassiveInputElement) {
                passive.add((PassiveInputElement)input);
            }
        }
        for (SubmitButton input: submits) {
            if (input instanceof SubmitButton) {
                submitGenerators.add(new ButtonGenerator(input));
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
        int ai = random.nextInt(submits.size());
        sb.append(submits.get(ai).getDescription() + ": " + submitGenerators.get(ai).generate());
        return sb.toString();
    }

    private static boolean containsSubmitButton(WebNode node) {
        if (SubmitButton.isSubmit(node)) {
            return true;
        } else {
            for (WebNode child: node.children) {
                boolean contains = containsSubmitButton(child);
                if (contains) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isSubmitButtonForm(WebNode form) {
        return containsSubmitButton(form);
    }
}

