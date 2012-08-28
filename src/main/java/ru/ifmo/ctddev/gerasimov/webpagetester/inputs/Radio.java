package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.FiniteInputElement;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/25/12
 * Time: 7:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Radio extends FiniteInputElement { //TODO same behaviour as Checkbox TODO extract Radiogroup class?
    public Radio(WebNode node) {
        super(node, new String[]{"set", "unset"});
    }

    @Override
    public String toString() {
        return getDescription() + " radiobutton";
    }
}
