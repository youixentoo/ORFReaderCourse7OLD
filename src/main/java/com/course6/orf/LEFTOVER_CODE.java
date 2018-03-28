/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course6.orf;

/** Backup code IGNORE
 *
 * @author thijs
 */
public class LEFTOVER_CODE {
    /*
    
     // Searches and highlights all the ORFs
    private void patternMatcher(ProteinSequence sequence) {
        int matches = 0;
        String seq = sequence.toString();
        Matcher matcher;
//        highlighting(m, m);

//        Pattern pattern = Pattern.compile("M[^\\*]{100,}");
//        matcher = pattern.matcher(seq);
//
//        while (matcher.find()) {
////            matcher.group();
//            int start = matcher.start();
//            int end = matcher.end();
//            highlighting(start, end, new Color(64, 224, 208));
//            matches++;
//        }
        Pattern pat = Pattern.compile("\\*[^\\*]{100,}");
        matcher = pat.matcher(seq);

        while (matcher.find()) {
            int start = matcher.start() + 1;
            int end = matcher.end();
            highlighting(start, end, new Color(64, 224, 208));
            matches++;
        }
        totalORFsLabel.setText(String.valueOf(matches));
    }

    // Highlighting
    private void highlighting(int start, int end, Color color) {
        try {
            seqTextPane.getHighlighter().addHighlight(start, end, new DefaultHighlighter.DefaultHighlightPainter(color));
        } catch (BadLocationException ex) {
            Logger.getLogger(ORFGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ***********
    
    private void readingFrames(java.awt.event.ActionEvent evt) {
        JComboBox<String> combo = (JComboBox<String>) evt.getSource();
        readingFrame = (String) combo.getSelectedItem();

        if (!sequenceString.isEmpty()) {
            setReadingFrameSequence();
        }
    }

    // Gets the DNA string form the file
    private void readFile(String file) {
        // Leest een fasta bestand in dat wordt meegegeven van de fileChooser
        try {
            LinkedHashMap<String, DNASequence> readFastaDNASequence = FastaReaderHelper.readFastaDNASequence(new File(file));
//            System.out.println(readFastaProteinSequence);
            sequenceString = (readFastaDNASequence.get(((readFastaDNASequence.keySet()).toArray()[0]).toString())).toString();
            setReadingFrameSequence();
        } catch (IOException ex) {
            Logger.getLogger(ORFGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Wrong file");
            JOptionPane.showMessageDialog(this, "Incorrect file format");
        } catch (Exception exc) {
            String errorMes = "Unknown error occurred:" + System.lineSeparator() + System.lineSeparator();
            for (StackTraceElement what : exc.getStackTrace()) {
                errorMes += (what + System.lineSeparator());
            }
            JOptionPane.showMessageDialog(this, errorMes);
            exc.printStackTrace();
        }
    }

    // Gets the Protein sequence from the selected reading frame.
    private void setReadingFrameSequence() {
        try {
            DNASequence dnaSequence = null;
            // readingFrame is where to start reading, on index 0, 1, or 2. Defined with the comboBox in "readingFramesComboBoxActionPerformed".
            if ("-1".equals(readingFrame) | "-2".equals(readingFrame) | "-3".equals(readingFrame)) {
                int readFrame = Integer.parseInt(readingFrame.replace("-", ""));
                DNASequence tempSequence = new DNASequence(sequenceString);
                SequenceView<NucleotideCompound> test = tempSequence.getReverseComplement();
                dnaSequence = new DNASequence(test.getSequenceAsString().substring(readFrame - 1));
            } else {
                int readFrame = Integer.parseInt(readingFrame.replace("+", ""));
                dnaSequence = new DNASequence(sequenceString.substring(readFrame - 1));
            }

            ProteinSequence proteinSequence = dnaSequence.getRNASequence().getProteinSequence();
            proteinLength = proteinSequence.getLength();
//            System.out.println(dnaFasta);
            seqTextPane.setText(proteinSequence.toString());
            PanelHighlighter.patternMatcher(proteinSequence, totalORFsLabel, seqTextPane);

        } catch (CompoundNotFoundException ex) {
            Logger.getLogger(ORFGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("SringIndexOutOfBounds in setReadingFrameSequence");
            ex.printStackTrace();
        }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    */
}
