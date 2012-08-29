package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/24/12
 * Time: 11:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Textarea extends TextInputElement {

    public Textarea(WebNode node) {
        super(node);
    }

    @Override
    public String generate() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append((char)('a' + r.nextInt(26)));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getDescription() + " textarea";
    }
}
