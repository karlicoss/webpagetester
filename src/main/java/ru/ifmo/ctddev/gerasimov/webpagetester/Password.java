package ru.ifmo.ctddev.gerasimov.webpagetester;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/25/12
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Password extends TextInputElement {

    public Password(WebNode node, String description) {
        super(node, description);
    }

    @Override
    public String generate() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) { //TODO UPPER BOUND
            sb.append((char)('a' + r.nextInt(26))); //TODO Code generator
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return description + " password field";
    }
}
