package ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators;

import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.SubmitButton;

import java.util.Random;

/**
* Created with IntelliJ IDEA.
* User: karlicos
* Date: 8/30/12
* Time: 3:55 PM
* To change this template use File | Settings | File Templates.
*/
public class ButtonGenerator extends ActiveInputGenerator {
    private final String[] actions;
    private final Random random;

    public ButtonGenerator(SubmitButton button) {
        super(button);
        this.actions = button.getActions();
        this.random = new Random();
    }

    public ButtonGenerator(SubmitButton button, Random random) {
        super(button);
        this.actions = button.getActions();
        this.random = random;
    }

    @Override
    public String generate() {
        return actions[random.nextInt(actions.length)];
    }
}
