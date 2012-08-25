package ru.ifmo.ctddev.gerasimov.webpagetester;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/24/12
 * Time: 10:58 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class InfiniteInputElement extends InputElement {
    protected InfiniteInputElement(WebNode node, String description) {
        super(node, description);
    }
}
