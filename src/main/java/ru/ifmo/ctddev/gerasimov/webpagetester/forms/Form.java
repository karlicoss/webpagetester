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

    protected Form(WebNode form, InputElementFactory inputFactory) {
        this.form = form;
        this.inputs = getInputs(form, inputFactory);
    }

    public abstract List<Pair<InputElement, String>> generate();

    private static void getInputNodes(WebNode node, List<WebNode> result, InputElementFactory inputFactory) {
        if (inputFactory.isInput(node) && node.isDisplayed()) {
            result.add(node);
            return;
        } else {
            for (WebNode child: node.children) {
                getInputNodes(child, result, inputFactory);
            }
        }
    }

    private static List<InputElement> getInputs(WebNode node, InputElementFactory inputFactory) {
        List<WebNode> nodes = new ArrayList<WebNode>();
        getInputNodes(node, nodes, inputFactory);
        List<InputElement> inputs = new ArrayList<InputElement>();
        for (WebNode wnode: nodes) {
            inputs.add(inputFactory.makeInput(wnode));
        }
        return inputs;
    }

    private static Pair<List<WebNode>, Boolean> getFormNodesHelper(WebNode node, InputElementFactory inputFactory) {
        List<WebNode> forms = new ArrayList<WebNode>();
        if (inputFactory.isInput(node) && node.isDisplayed()) {
            return new Pair<List<WebNode>, Boolean>(forms, true);
        } else {
            boolean hasForms = false;
            boolean hasInputs = false;
            List<Pair<List<WebNode>, Boolean>> results = new ArrayList<Pair<List<WebNode>, Boolean>>();
            for (WebNode child: node.children) {
                Pair<List<WebNode>, Boolean> result = getFormNodesHelper(child, inputFactory);
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

    public static List<Form> getForms(WebNode node, FormFactory formFactory, InputElementFactory inputFactory) {
        Pair<List<WebNode>, Boolean> result = getFormNodesHelper(node, inputFactory);
        // The only reason to result.second to be true is if there were no <form> forms at all
        if (result.second) {
            result.first.add(node);
        }
        List<Form> answer = new ArrayList<Form>();
        for (WebNode wnode: result.first) {
            Form form = formFactory.makeForm(wnode.copy(), inputFactory);
            if (form != null)
                answer.add(form);
        }
        return answer;
    }
}
