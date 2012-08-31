package ru.ifmo.ctddev.gerasimov.webpagetester.forms;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.*;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.*;
import ru.ifmo.ctddev.gerasimov.webpagetester.utils.Pair;

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
public class InputAndClickButtonForm extends Form {
    private final List<Button> buttons;
    private final List<ActiveInputGenerator> activeGenerators;
    private final List<PassiveInputElement> passive;
    private final List<PassiveInputGenerator> passiveGenerators;

    public InputAndClickButtonForm(WebNode form) {
        super(form);
        buttons = new ArrayList<Button>();
        activeGenerators = new ArrayList<ActiveInputGenerator>();
        passive = new ArrayList<PassiveInputElement>();
        passiveGenerators = new ArrayList<PassiveInputGenerator>();
        for (InputElement input: inputs) {
            if (input instanceof Button) {
                buttons.add((Button) input);
            } else if (input instanceof PassiveInputElement) {
                passive.add((PassiveInputElement)input);
            }
        }
        for (Button input: buttons) {
            activeGenerators.add(new ButtonGenerator(input));
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
    public List<Pair<InputElement, String>> generate() {
        Random random = new Random();
        List<Pair<InputElement, String>> result = new ArrayList<Pair<InputElement, String>>();
        for (int i = 0; i < passive.size(); i++) {
            result.add(new Pair<InputElement, String>(passive.get(i), passiveGenerators.get(i).generate()));
        }
        int ai = random.nextInt(buttons.size());
        result.add(new Pair<InputElement, String>(buttons.get(ai), activeGenerators.get(ai).generate()));
        return result;
    }

    private static boolean containsButton(WebNode node) {
        if (Button.isButton(node) && node.isDisplayed()) {
            return true;
        } else {
            for (WebNode child: node.children) {
                boolean contains = containsButton(child);
                if (contains) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isInputAndClickButtonForm(WebNode form) {
        return containsButton(form);
    }
}

