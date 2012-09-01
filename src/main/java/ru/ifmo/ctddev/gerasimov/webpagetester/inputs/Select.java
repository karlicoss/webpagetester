package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.generators.UniformFiniteInputGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/24/12
 * Time: 11:42 PM
 */
public class Select extends FiniteInputElement {
    public Select(WebNode node) {
        super(node, collectOptions(node));
    }

    private static String[] collectOptions(WebNode node) {
        List<WebNode> options = collectOptionsHelper(node);
        String[] optionStrings = new String[options.size()];
        for (int i = 0; i < options.size(); i++) {
            optionStrings[i] = options.get(i).element.getText();
        }
        return optionStrings;
    }

    private static List<WebNode> collectOptionsHelper(WebNode node) {
        List<WebNode> result = new ArrayList<WebNode>();
        if (node.element.getTagName().equals("option")) {
            result.add(node);
            return result;
        } else {
            for (WebNode child: node.children) {
                result.addAll(collectOptionsHelper(child));
            }
        }
        return result;
    }

    @Override
    public String getDescription() {
        List<WebNode> options = collectOptionsHelper(node);
        for (WebNode option: options) {
            if (option.element.getAttribute("selected") != null)
                return option.element.getText();
        }
        return super.getDescription();
    }

    @Override
    public String toString() {
        return getDescription() + " select";
    }

    public static boolean isSelect(WebNode node) {
        return node.element.getTagName().equals("select");
    }
}
