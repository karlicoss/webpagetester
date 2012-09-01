package ru.ifmo.ctddev.gerasimov.webpagetester.links;

import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/31/12
 * Time: 9:57 PM
 */
public class HrefLink extends Link {
    protected HrefLink(WebNode node) {
        super(node);
    }

    @Override
    public String getDescription() {
        return node.element.getText();
    }

    public static boolean isHrefLink(WebNode node) {
        if (node.element.getTagName().equals("a")) {
            String href = node.element.getAttribute("href");
            if (href != null && !node.element.getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
