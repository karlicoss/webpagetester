package ru.ifmo.ctddev.gerasimov.webpagetester;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/24/12
 * Time: 11:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Select extends FiniteInputElement {
    public final List<String> variants;

    public Select(WebNode node, String description) {
        super(node, description);
        //TODO traverse node and set variants variable
    }

    @Override
    public String generate() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
