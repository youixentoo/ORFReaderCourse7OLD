/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course6.orf;

import java.awt.Color;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import org.biojava.nbio.core.sequence.ProteinSequence;

/**
 *
 * @author thijs
 */
public class PanelHighlighter {
    // Searches and highlights all the ORFs
    public static void patternMatcher(ProteinSequence sequence, JLabel label, JEditorPane editPane) {
        int matches = 0;
        String seq = sequence.toString();
        Matcher matcher;

        Pattern pat = Pattern.compile("\\*[^\\*]{100,}");
        matcher = pat.matcher(seq);

        while (matcher.find()) {
            int start = matcher.start() + 1;
            int end = matcher.end();
            highlighting(start, end, new Color(64, 224, 208), editPane);
            matches++;
        }
        label.setText(String.valueOf(matches));
    }

    // Highlighting
    private static void highlighting(int start, int end, Color color, JEditorPane editPane) {
        try {
            editPane.getHighlighter().addHighlight(start, end, new DefaultHighlighter.DefaultHighlightPainter(color));
        } catch (BadLocationException ex) {
            Logger.getLogger(ORFGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
