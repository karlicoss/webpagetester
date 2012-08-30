package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.ActiveInputGenerator;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/29/12
 * Time: 12:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class SubmitButton extends ActiveInputElement {

    public SubmitButton(WebNode node) {
        super(node);
    }

    public String[] getActions() { //TODO Pretty similar to FiniteInputElement
        return new String[] {"click"};
    }

    @Override
    public String getDescription() {
        String value = node.element.getAttribute("value");
        if (value != null && !value.isEmpty())
            return value;
        return super.getDescription();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return getDescription() + " button";
    }

    public static boolean isSubmit(WebNode node) {
        if (node.element.getTagName().equals("input") && node.element.getAttribute("type").equals("submit"))
            return true;
        if (node.element.getTagName().equals("button")) {
            //TODO Some generic method?
            String id = node.element.getAttribute("id");
            //String class_ = element.getAttribute("class");
            String type = node.element.getAttribute("type");
            String onclick = node.element.getAttribute("onclick");
            if (id != null && id.toLowerCase().contains("submit"))
                return true;
            if (type != null && type.toLowerCase().contains("submit"))
                return true;
            if (onclick != null && onclick.toLowerCase().contains("submit"))
                return true;
        }
        return false;
    }
}