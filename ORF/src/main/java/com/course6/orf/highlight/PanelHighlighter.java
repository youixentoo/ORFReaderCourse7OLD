/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course6.orf.highlight;

import com.course6.orf.ORFGUI;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
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

    /**Searches and highlights all the ORFs in a JEditorPane, at a minimal length of 100
     *
     * @param sequence The protein sequence in which the ORF's need to be highlighted
     * @param label The Label in which to amount of found ORF's will be displayed
     * @param editPane The JEditorpane in which the highlights get done and the sequence is displayed
     * @return A <code>ArrayList</code> containing the locations of the ORF's
     */
    public static List<ORFLocation> patternMatcher(ProteinSequence sequence, JLabel label, JEditorPane editPane) {
        int matches = 0;
        List<ORFLocation> orfs = new ArrayList<>();
        String seq = sequence.toString();
        Matcher matcher;

        // Regex for searching the ORF's
        Pattern pat = Pattern.compile("M[^\\*]{100,}");
        matcher = pat.matcher(seq);

        while (matcher.find()) {
            int start = matcher.start() + 1;
            int end = matcher.end();
            highlighting(start, end, new Color(64, 224, 208), editPane);
            orfs.add(new ORFLocation(start, end));
            matches++;
        }
        label.setText(String.valueOf(matches));
        
        return orfs;
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
