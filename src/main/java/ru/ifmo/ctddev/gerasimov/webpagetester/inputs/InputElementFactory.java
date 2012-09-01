package ru.ifmo.ctddev.gerasimov.webpagetester.inputs;

import ru.ifmo.ctddev.gerasimov.webpagetester.InconsistencyException;
import ru.ifmo.ctddev.gerasimov.webpagetester.NotSupportedException;
import ru.ifmo.ctddev.gerasimov.webpagetester.WebNode;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 8/31/12
 * Time: 4:51 PM
 */
public class InputElementFactory {
    protected static InputElementFactory instance = new InputElementFactory();

    protected InputElementFactory() {

    }

    public static InputElementFactory getInstance() {
        return instance;
    }

    public boolean isInput(WebNode node) {
        if (SubmitButton.isSubmit(node))
            return true;
        if (Button.isButton(node))
            return true;
        if (Textarea.isTextarea(node))
            return true;
        if (Text.isText(node))
            return true;
        if (Checkbox.isCheckbox(node))
            return true;
        if (Radio.isRadio(node))
            return true;
        if (Password.isPassword(node))
            return true;
        if (Select.isSelect(node))
            return true;

        //TODO file, image and HTML5 elements
        return false;
    }

    public InputElement makeInput(WebNode node) {
        //TODO how to keep consistent with isInput method? Use reflection?
        if (SubmitButton.isSubmit(node))
            return new SubmitButton(node);
        if (Button.isButton(node))
            return new Button(node);
        if (Textarea.isTextarea(node))
            return new Textarea(node);
        if (Text.isText(node))
            return new Text(node);
        if (Checkbox.isCheckbox(node))
            return new Checkbox(node);
        if (Radio.isRadio(node))
            return new Radio(node);
        if (Password.isPassword(node))
            return new Password(node);
        if (Select.isSelect(node))
            return new Select(node);
        //TODO postprocessing of radio buttons?

        if (isInput(node))
            throw new InconsistencyException("InputElementFactory.makeInput", "InputElementFactory.isInput", node.toString());
        else
            throw new NotSupportedException("InputEleemntFactory.makeInput", node.toString());
    }
}
