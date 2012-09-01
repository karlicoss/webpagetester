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
 */
public class InputAndClickButtonForm extends Form {
    private final List<Button> buttons;
    private final List<InputGenerator> activeGenerators;
    private final List<PassiveInputElement> passive;
    private final List<InputGenerator> passiveGenerators;

    public InputAndClickButtonForm(WebNode form, InputElementFactory inputFactory, InputGeneratorFactory inputGeneratorFactory) {
        super(form, inputFactory);
        buttons = new ArrayList<Button>();
        activeGenerators = new ArrayList<InputGenerator>();
        passive = new ArrayList<PassiveInputElement>();
        passiveGenerators = new ArrayList<InputGenerator>();
        for (InputElement input: inputs) {
            if (input instanceof Button) {
                buttons.add((Button) input);
            } else if (input instanceof PassiveInputElement) {
                passive.add((PassiveInputElement)input);
            }
        }
        for (Button input: buttons) {
            activeGenerators.add(inputGeneratorFactory.getGenerator(input));
        }
        for (PassiveInputElement input: passive) {
            passiveGenerators.add(inputGeneratorFactory.getGenerator(input));
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

