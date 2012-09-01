package ru.ifmo.ctddev.gerasimov.webpagetester.links;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/31/12
 * Time: 9:56 PM
 */
public abstract class Link {
    protected final WebNode node;

    protected Link(WebNode node) {
        this.node = node;
    }

    public abstract String getDescription();

    private static void getLinksHelper(WebNode node, List<WebNode> result, LinkFactory factory) {
        if (factory.isLink(node)) {
            result.add(node);
            return;
        }
        for (WebNode child: node.children) {
            getLinksHelper(child, result, factory);
        }
    }

    public static List<Link> getLinks(WebNode node, LinkFactory factory) {
        List<WebNode> nodes = new ArrayList<WebNode>();
        getLinksHelper(node, nodes, factory);
        List<Link> result = new ArrayList<Link>();

        for (WebNode wnode: nodes) {
            Link link = factory.makeLink(wnode);
            if (link != null) {
                result.add(link);
            }
        }
        return result;
    }
}
