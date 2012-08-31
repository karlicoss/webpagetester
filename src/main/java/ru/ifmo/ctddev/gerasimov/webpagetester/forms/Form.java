package ru.ifmo.ctddev.gerasimov.webpagetester.forms;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.InputElement;
import ru.ifmo.ctddev.gerasimov.webpagetester.inputs.InputElementFactory;
import ru.ifmo.ctddev.gerasimov.webpagetester.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/28/12
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Form {
    protected final WebNode form;
    protected final List<InputElement> inputs;

    protected Form(WebNode form) {
        this.form = form;
        this.inputs = getInputs(form);
    }

    public abstract List<Pair<InputElement, String>> generate();

    public static Form makeForm(WebNode form) {
        if (InputAndClickButtonForm.isInputAndClickButtonForm(form))
            return new InputAndClickButtonForm(form);
        //TODO other submit methods
        //TODO some pattern to let easy submit methods adding
        return null;
    }

    private static void getInputNodes(WebNode node, List<WebNode> result) {
        if (InputElementFactory.isInput(node) && node.isDisplayed()) {
            result.add(node);
            return;
        } else {
            for (WebNode child: node.children) {
                getInputNodes(child, result);
            }
        }
    }

    private static List<InputElement> getInputs(WebNode node) {
        List<WebNode> nodes = new ArrayList<WebNode>();
        getInputNodes(node, nodes);
        List<InputElement> inputs = new ArrayList<InputElement>();
        for (WebNode wnode: nodes) {
            inputs.add(InputElementFactory.makeInput(wnode));
        }
        return inputs;
    }

    private static Pair<List<WebNode>, Boolean> getFormNodesHelper(WebNode node) {
        List<WebNode> forms = new ArrayList<WebNode>();
        if (InputElementFactory.isInput(node) && node.isDisplayed()) {
            return new Pair<List<WebNode>, Boolean>(forms, true);
        } else {
            boolean hasForms = false;
            boolean hasInputs = false;
            List<Pair<List<WebNode>, Boolean>> results = new ArrayList<Pair<List<WebNode>, Boolean>>();
            for (WebNode child: node.children) {
                Pair<List<WebNode>, Boolean> result = getFormNodesHelper(child);
                results.add(result);
                hasForms |= (result.first.size() > 0);
                hasInputs |= result.second;
            }
            if (hasForms) {
                for (int i = 0; i < node.children.size(); i++) {
                    WebNode child = node.children.get(i);
                    Pair<List<WebNode>, Boolean> result = results.get(i);
                    forms.addAll(result.first);
                    if (result.second) {
                        forms.add(child);
                    }
                }
                return new Pair<List<WebNode>, Boolean>(forms, false);
            } else {
                if (node.element.getTagName().equals("form") && hasInputs) {
                    forms.add(node);
                    return new Pair<List<WebNode>, Boolean>(forms, false);
                } else {
                    return new Pair<List<WebNode>, Boolean>(forms, hasInputs);
                }
            }
        }
    }

    public static List<Form> getForms(WebNode node) {
        Pair<List<WebNode>, Boolean> result = getFormNodesHelper(node);
        // The only reason to result.second to be true is if there were no <form> forms at all
        if (result.second) {
            result.first.add(node);
        }
        List<Form> answer = new ArrayList<Form>();
        for (WebNode wnode: result.first) {
            Form form = Form.makeForm(wnode.copy());
            if (form != null)
                answer.add(form);
        }
        return answer;
    }
}
