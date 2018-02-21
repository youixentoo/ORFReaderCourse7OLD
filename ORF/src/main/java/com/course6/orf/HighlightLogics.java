/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course6.orf;

import java.awt.Point;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.Utilities;

/**
 *
 * @author thijs
 */
public class HighlightLogics {

    /**
     * <a href="https://stackoverflow.com/questions/46005525/how-to-get-a-point-value-for-where-a-word-or-selection-in-a-jtextarea-is-on-th">Source</a><br><br>
     *
     * This method will return either boolean true or false if the text
     * contained under the current mouse pointer location within a
     * JTextComponent is Highlighted or not. If the text under the mouse is
     * found to be Highlighted then boolean true is returned. If the text under
     * the mouse pointer is not found to be highlighted then boolean false is
     * returned.<br><br>
     *
     * This method is not to be confused with the getSelectedUnderMouse()
     * method. Highlighted text and Selected text are two completely different
     * things. Highlighted Text can be text which is Highlighted (generally with
     * a specific color) and can be in many different locations throughout the
     * entire document whereas Selected Text is generally done by dragging the
     * mouse over a document location while holding the left mouse button making
     * the text SELECTED.<br><br>
     *
     * This method is generally run within a JTextComponent's MouseMove Event
     * but can be run from any JTextComponent's Mouse event which will provide
     * the current mouse coordinates within the supplied JTextArea Object.
     *
     * @param textComp (JTextComponent) The JTextComponent object to work on.
     * This can be the component variable name for either a JTextField,
     * JTextArea, JFormattedField, JPasswordField, JTextPane, and
     * JEditorPane.<br>
     *
     * @param textAreaMouseXLocation (Integer) The current X Location of the
     * mouse pointer. This value can be acquired from the JTextComponent's
     * MouseMove event like this <code>int x = evt.getX();</code> where evt is
     * the <b>java.awt.event.MouseEvent</b>
     * parameter variable.<br>
     *
     * @param textAreaMouseYLocation (Integer) The current Y Location of the
     * mouse pointer. This value can be acquired from the JTextComponent's
     * MouseMove event like this <code>int y = evt.getY();</code> where evt is
     * the <b>java.awt.event.MouseEvent</b>
     * parameter variable.<br>
     *
     * @return (Boolean) True if the text under the current mouse pointer
     * location within the supplied JTextComponent is Highlighted and false if
     * it is not.
     *
     *
     */
    public static boolean isHighlightededUnderMouse(JTextComponent textComp, int textAreaMouseXLocation, int textAreaMouseYLocation) {
        boolean isHighlighted = false;
        if (textComp.getText().isEmpty()) {
            return false;
        }

        Point pt = new Point(textAreaMouseXLocation, textAreaMouseYLocation);
        int pos = textComp.viewToModel(pt);

        Highlighter.Highlight[] allHighlights = textComp.getHighlighter().getHighlights();
        String strg = "";
        for (int i = 0; i < allHighlights.length; i++) {
            int start = (int) allHighlights[i].getStartOffset();
            int end = (int) allHighlights[i].getEndOffset();
            if (pos >= start && pos <= end) {
                isHighlighted = true;
                break;
            }
        }
        return isHighlighted;
    }

    /**
     * <a href="https://stackoverflow.com/questions/46005525/how-to-get-a-point-value-for-where-a-word-or-selection-in-a-jtextarea-is-on-th">Source</a><br><br>
     *
     * This method will return either boolean true or false if the text
     * contained under the current mouse pointer location within a
     * JTextComponent is Selected or not. If the text under the mouse is found
     * to be Selected then boolean true is returned. If the text under the mouse
     * pointer is NOT found to be Selected then boolean false is
     * returned.<br><br>
     *
     * This method is not to be confused with the getHighlightedUnderMouse()
     * method. Highlighted text and Selected text are two completely different
     * things. Highlighted Text can be text which is Highlighted (generally with
     * a specific color) and can be in many different locations throughout the
     * entire document whereas Selected Text is generally done by dragging the
     * mouse over a document location while holding the left mouse button making
     * the text SELECTED.<br><br>
     *
     * This method is generally run within a JTextComponent MouseMove Event but
     * can be run from any JTextComponent Mouse event which will provide the
     * current mouse coordinates within the supplied JTextComponent Object.
     *
     * @param textComp (JTextComponent) The JTextComponent object to work on.
     * This can be the component variable name for either a JTextField,
     * JTextArea, JFormattedField, JPasswordField, JTextPane, and
     * JEditorPane.<br>
     *
     * @param textAreaMouseXLocation (Integer) The current X Location of the
     * mouse pointer. This value can be acquired from the JTextComponent's
     * MouseMove event like this <code>int x = evt.getX();</code> where evt is
     * the <b>java.awt.event.MouseEvent</b>
     * parameter variable.<br>
     *
     * @param textAreaMouseYLocation (Integer) The current Y Location of the
     * mouse pointer. This value can be acquired from the JTextComponent's
     * MouseMove event like this <code>int y = evt.getY();</code> where evt is
     * the <b>java.awt.event.MouseEvent</b>
     * parameter variable.<br>
     *
     * @return (Boolean) True if the text under the current mouse pointer
     * location within the supplied JTextComponent is Selected and false if it
     * is not.
     */
    public static boolean isSelectededUnderMouse(JTextComponent textComp, int textAreaMouseXLocation,
            int textAreaMouseYLocation) {
        boolean isSelected = false;
        if (textComp.getText().isEmpty()) {
            return false;
        }

        Point pt = new Point(textAreaMouseXLocation, textAreaMouseYLocation);
        int pos = textComp.viewToModel(pt);

        int start = textComp.getSelectionStart();
        int end = textComp.getSelectionEnd();
        if (pos >= start && pos <= end) {
            isSelected = true;
        }
        return isSelected;
    }

    /**
     * <a href="https://stackoverflow.com/questions/46005525/how-to-get-a-point-value-for-where-a-word-or-selection-in-a-jtextarea-is-on-th">Source</a><br><br>
     *
     * This method will return the "Selected" text contained under the mouse
     * pointer location within a JTextComonent.<br><br>
     *
     * This method is not to be confused with the getHighlightedUnderMouse()
     * method. Highlighted text and Selected text are two completely different
     * things. Highlighted Text can be text which is Highlighted (generally with
     * a specific color) and can be in many different locations throughout the
     * entire document whereas Selected Text is generally done by dragging the
     * mouse over a document location while holding the left mouse button making
     * the text SELECTED.<br><br>
     *
     * This method is generally run within a Text Component's MouseMove Event
     * but can be run from any Text Component's Mouse event which will provide
     * the current mouse coordinates within the supplied Text Component Object.
     *
     * @param textComp (JTextComponent) The JTextComponent object to work on.
     * This can be the component variable name for either a JTextField,
     * JTextArea, JFormattedField, JPasswordField, JTextPane, and
     * JEditorPane.<br>
     *
     * @param textAreaMouseXLocation (Integer) The current X Location of the
     * mouse pointer. This value can be acquired from the Text Component's
     * MouseMove event like this <code>int x = evt.getX();</code> where evt is
     * the <b>java.awt.event.MouseEvent</b>
     * parameter variable.<br>
     *
     * @param textAreaMouseYLocation (Integer) The current Y Location of the
     * mouse pointer. This value can be acquired from the Text Component's
     * MouseMove event like this <code>int y = evt.getY();</code> where evt is
     * the <b>java.awt.event.MouseEvent</b>
     * parameter variable.<br>
     *
     * @return (String) The current selected string located under the current
     * mouse location.
     */
    public static String getSelectededUnderMouse(JTextComponent textComp, int textAreaMouseXLocation,
            int textAreaMouseYLocation) {
        String selectedText = "";
        if (textComp.getText().isEmpty()) {
            return selectedText;
        }

        Point pt = new Point(textAreaMouseXLocation, textAreaMouseYLocation);
        int pos = textComp.viewToModel(pt);

        int start = textComp.getSelectionStart();
        int end = textComp.getSelectionEnd();
        int selectedLength = (end - start);
        if (pos >= start && pos <= end) {
            try {
                selectedText = textComp.getText(start, selectedLength);
            } catch (BadLocationException ex) {
                // Ignore!!!
            }
        }
        return selectedText;
    }

    /**
     * <a href="https://stackoverflow.com/questions/46005525/how-to-get-a-point-value-for-where-a-word-or-selection-in-a-jtextarea-is-on-th">Source</a><br><br>
     *
     * This method will return the "Highlighted" text contained under the mouse
     * pointer location within a JTextComponent.<br><br>
     *
     * This method is not to be confused with the getSelectedUnderMouse()
     * method. Highlighted text and Selected text are two completely different
     * things. Highlighted Text can be text which is Highlighted (generally with
     * a specific color) and can be in many different locations throughout the
     * entire document whereas Selected Text is generally done by dragging the
     * mouse over a document location while holding the left mouse button making
     * the text SELECTED.<br><br>
     *
     * This method is generally run within a JTextComponent MouseMove Event but
     * can be run from any JTextComponent Mouse event which will provide the
     * current mouse coordinates within the supplied JTextComponent Object.
     *
     * @param textComp (JTextComponent) The JTextComponent object to work on.
     * This can be the component variable name for either a JTextField,
     * JTextArea, JFormattedField, JPasswordField, JTextPane, and
     * JEditorPane.<br>
     *
     * @param textAreaMouseXLocation (Integer) The current X Location of the
     * mouse pointer. This value can be acquired from the JTextComponent's
     * MouseMove event like this <code>int x = evt.getX();</code> where evt is
     * the <b>java.awt.event.MouseEvent</b>
     * parameter variable.<br>
     *
     * @param textAreaMouseYLocation (Integer) The current Y Location of the
     * mouse pointer. This value can be acquired from the JTextComponent's
     * MouseMove event like this <code>int y = evt.getY();</code> where evt is
     * the <b>java.awt.event.MouseEvent</b>
     * parameter variable.<br>
     *
     * @return (String) The current highlighted string located under the current
     * mouse location.
     */
    public static String getHighlightededUnderMouse(JTextComponent textComp, int textAreaMouseXLocation,
            int textAreaMouseYLocation) {
        if (textComp.getText().isEmpty()) {
            return "";
        }

        Point pnt = new Point(textAreaMouseXLocation, textAreaMouseYLocation);
        int pos = textComp.viewToModel(pnt);

        String highlightedText = "";
        Highlighter.Highlight[] allHighlights = textComp.getHighlighter().getHighlights();
        if (allHighlights.length > 0) {
            for (int i = 0; i < allHighlights.length; i++) {
                int start = (int) allHighlights[i].getStartOffset();
                int end = (int) allHighlights[i].getEndOffset();
                int hlStringLength = (end - start);
                if (pos >= start && pos <= end) {
                    try {
                        highlightedText = textComp.getText(start, hlStringLength);
                        break;
                    } catch (BadLocationException ex) {
                        // Ignore!!!
                    }
                }

            }
        }
        return highlightedText;
    }

    public static BeginAndEnd getHighlightededItemUnderMouseInfo(JTextComponent textComp, int textAreaMouseXLocation, int textAreaMouseYLocation) {
        if (textComp.getText().isEmpty()) {return null;}

        Point pnt = new Point(textAreaMouseXLocation, textAreaMouseYLocation);
        int pos = textComp.viewToModel(pnt);

        BeginAndEnd location = new BeginAndEnd();
        Highlighter.Highlight[] allHighlights = textComp.getHighlighter().getHighlights();
        if (allHighlights.length > 0) {
            for (int i = 0; i < allHighlights.length; i++) {
                int start = (int) allHighlights[i].getStartOffset();
                int end = (int) allHighlights[i].getEndOffset();
                if (pos >= start && pos <= end) {
                    try {
                        location.setStart(start);
                        location.setEnd(end);
                        break;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        }
        return location;

    }

    /**
     * <a href="https://stackoverflow.com/questions/46005525/how-to-get-a-point-value-for-where-a-word-or-selection-in-a-jtextarea-is-on-th">Source</a><br><br>
     *
     * This method would normally be called within a JTextComponent's MouseMove
     * Event.
     *
     * @param textComp (JTextComponent) The JTextComponent object to work on.
     * This can be the component variable name for either a JTextField,
     * JTextArea, JFormattedField, JPasswordField, JTextPane, and
     * JEditorPane.<br>
     *
     * @param textAreaMouseXLocation (Integer) The current X Location of the
     * mouse pointer. This value can be acquired from the JTextComponent's
     * MouseMove event like this <code>int x = evt.getX();</code> where evt is
     * the <b>java.awt.event.MouseEvent</b>
     * parameter variable.<br>
     *
     * @param textAreaMouseYLocation (Integer) The current Y Location of the
     * mouse pointer. This value can be acquired from the JTextComponent's
     * MouseMove event like this <code>int y = evt.getY();</code> where evt is
     * the <b>java.awt.event.MouseEvent</b>
     * parameter variable.<br>
     *
     * @return (String) The current character located under the current mouse
     * location.
     */
    public static String getCharUnderMouse(JTextComponent textComp, int textAreaMouseXLocation,
            int textAreaMouseYLocation) {
        String character = "";
        Point pt = new Point(textAreaMouseXLocation, textAreaMouseYLocation);
        int pos = textComp.viewToModel(pt);
        try {
            character = textComp.getDocument().getText(pos, 1);
        } catch (BadLocationException ex) {
            // Ignore!! 
        }
        return character;
    }

    /**
     * <a href="https://stackoverflow.com/questions/46005525/how-to-get-a-point-value-for-where-a-word-or-selection-in-a-jtextarea-is-on-th">Source</a><br><br>
     *
     * This method would normally be called within a JTextComponent's MouseMove
     * Event.
     *
     * @param textComp (JTextArea) The JTextComponent object to work on. This
     * can be the component variable name for either a JTextField, JTextArea,
     * JFormattedField, JPasswordField, JTextPane, and JEditorPane.<br>
     *
     * @param textAreaMouseXLocation (Integer) The current X Location of the
     * mouse pointer. This value can be acquired from the JTextComponent's
     * MouseMove event like this <code>int x = evt.getX();</code> where evt is
     * the <b>java.awt.event.MouseEvent</b>
     * parameter variable.<br>
     *
     * @param textAreaMouseYLocation (Integer) The current Y Location of the
     * mouse pointer. This value can be acquired from the JTextComponent's
     * MouseMove event like this <code>int y = evt.getY();<code> where evt is the
     * <b>java.awt.event.MouseEvent</b>
     * parameter variable.<br>
     *
     * @return (String) The current Word located under the current mouse
     * location.
     */
    public static String getWordUnderMouse(JTextComponent textComp, int textAreaMouseXLocation,
            int textAreaMouseYLocation) {
        Point pt = new Point(textAreaMouseXLocation, textAreaMouseYLocation);
        int pos = textComp.viewToModel(pt);
        String word = "";
        try {
            Document doc = textComp.getDocument();
            if (pos > 0 && (pos >= doc.getLength() || Character.isWhitespace(doc.getText(pos, 1).charAt(0)))) {
                // if the next character is a white space then use 
                // the word on the left side..
                pos--;
            }
            // get the word from current position
            final int begOffs = Utilities.getWordStart(textComp, pos);
            final int endOffs = Utilities.getWordEnd(textComp, pos);
            word = textComp.getText(begOffs, endOffs - begOffs);
        } catch (BadLocationException ex) {
            // Ignore this exception!!!
        }
        return word;
    }
}


