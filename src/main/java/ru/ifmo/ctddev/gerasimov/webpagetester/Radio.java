package ru.ifmo.ctddev.gerasimov.webpagetester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/25/12
 * Time: 7:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Radio extends FiniteInputElement { //TODO same behaviour as Checkbox TODO extract Radiogroup class?
    public Radio(WebNode node, String description) {
        super(node, description, new String[]{"set", "unset"});
    }

    @Override
    public String toString() {
        return description + " radiobutton";
    }
}
