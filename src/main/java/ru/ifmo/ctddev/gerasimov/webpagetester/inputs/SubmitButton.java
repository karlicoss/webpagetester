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
public class SubmitButton extends Button {

    public SubmitButton(WebNode node) {
        super(node);
    }

    public static boolean isSubmit(WebNode node) {
        if (!Button.isButton(node))
            return false;

        String id = node.element.getAttribute("id");
        String type = node.element.getAttribute("type");
        String onclick = node.element.getAttribute("onclick");
        if (id != null && id.toLowerCase().contains("submit"))
            return true;
        if (type != null && type.toLowerCase().contains("submit"))
            return true;
        if (onclick != null && onclick.toLowerCase().contains("submit"))
            return true;

        return false;
    }
}
