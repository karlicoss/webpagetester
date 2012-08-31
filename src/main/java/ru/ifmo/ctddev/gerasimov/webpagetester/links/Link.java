package ru.ifmo.ctddev.gerasimov.webpagetester.links;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/31/12
 * Time: 9:56 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Link {
    protected final WebNode node;

    protected Link(WebNode node) {
        this.node = node;
    }

    public abstract String getDescription();

    private static void getLinksHelper(WebNode node, List<WebNode> result) {
        if (LinkFactory.isLink(node)) {
            result.add(node);
            return;
        }
        for (WebNode child: node.children) {
            getLinksHelper(child, result);
        }
    }

    public static List<Link> getLinks(WebNode node) {
        List<WebNode> nodes = new ArrayList<WebNode>();
        getLinksHelper(node, nodes);
        List<Link> result = new ArrayList<Link>();

        for (WebNode wnode: nodes) {
            Link link = LinkFactory.makeLink(wnode);
            if (link != null) {
                result.add(link);
            }
        }
        return result;
    }
}
