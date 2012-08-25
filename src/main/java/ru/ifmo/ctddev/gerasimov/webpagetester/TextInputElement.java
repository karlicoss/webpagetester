package ru.ifmo.ctddev.gerasimov.webpagetester;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/25/12
 * Time: 7:13 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class TextInputElement extends InfiniteInputElement {

    protected TextInputElement(WebNode node, String description) {
        super(node, description);
    }

}
