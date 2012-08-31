package ru.ifmo.ctddev.gerasimov.webpagetester.links;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/31/12
 * Time: 10:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinkFactory {
    protected static LinkFactory instance = new LinkFactory();

    protected LinkFactory() {

    }

    public static LinkFactory getInstance() {
        return instance;
    }

    public boolean isLink(WebNode node) {
        if (HrefLink.isHrefLink(node))
            return true;
        return false;
    }

    public Link makeLink(WebNode node) {
        if (HrefLink.isHrefLink(node))
            return new HrefLink(node);
        return null;
    }
}
