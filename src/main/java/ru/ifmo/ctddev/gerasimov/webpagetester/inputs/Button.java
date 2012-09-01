package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/31/12
 * Time: 6:10 PM
 */
public class Button extends ActiveInputElement {
    public Button(WebNode node) {
        super(node, new String[] {"click"});
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

    public static boolean isButton(WebNode node) {
        String tagName = node.element.getTagName();
        if (tagName.equals("button"))
            return true;
        else if (tagName.equals("input") && node.element.getAttribute("type").equals("submit")) {
            return true;
        }
        return false;
    }
}
