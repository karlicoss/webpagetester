package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.UniformFiniteInputGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/25/12
 * Time: 7:32 PM
 */
public class Radio extends FiniteInputElement { //TODO extract Radiogroup class?
    public Radio(WebNode node) {
        super(node, new String[]{"set", "unset"});
    }

    @Override
    public String toString() {
        return getDescription() + " radiobutton";
    }

    public static boolean isRadio(WebNode node) {
        return node.element.getTagName().equals("input") && node.element.getAttribute("type").equals("radio");
    }
}
