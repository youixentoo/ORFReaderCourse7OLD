/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course6.orf.file;

import com.course6.orf.ORFGUI;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.io.FastaReaderHelper;
import org.biojava.nbio.core.sequence.template.SequenceView;

/**
 *
 * @author thijs
 */
public class ReadingFileAndFrames {

    public static void setFastaHeaders(String file, JComboBox combobox){
        Set<String> fastaHeaders = new LinkedHashSet<>();
        
        try{
            LinkedHashMap<String, DNASequence> readFastaDNASequence = FastaReaderHelper.readFastaDNASequence(new File(file));
            fastaHeaders = readFastaDNASequence.keySet();
            for(String header:fastaHeaders){
                combobox.addItem(header);
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Wrong file");
            JOptionPane.showMessageDialog(ORFGUI.getWindows()[0], "Incorrect file format");
        } catch (Exception exc) {
            String errorMes = "Unknown error occurred:" + System.lineSeparator() + System.lineSeparator();
            for (StackTraceElement what : exc.getStackTrace()) {
                errorMes += (what + System.lineSeparator());
            }
            JOptionPane.showMessageDialog(ORFGUI.getWindows()[0], errorMes);
            ErrorFile.savingErrors(exc.getStackTrace());
            exc.printStackTrace();
        }
  
    }
    
    
    // Gets the DNA string form the file
    public static String readHeader(String header, String file) {
        // Leest een fasta bestand in dat wordt meegegeven van de fileChooser
        String sequenceString = "";
        try {
            LinkedHashMap<String, DNASequence> readFastaDNASequence = FastaReaderHelper.readFastaDNASequence(new File(file));
            DNASequence dnaSeq = readFastaDNASequence.get(header);
            sequenceString = dnaSeq.toString();
        } catch (IOException ex) {
            Logger.getLogger(ORFGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Wrong file");
            JOptionPane.showMessageDialog(ORFGUI.getWindows()[0], "Incorrect file format");
        } catch (Exception exc) {
            String errorMes = "Unknown error occurred:" + System.lineSeparator() + System.lineSeparator();
            for (StackTraceElement what : exc.getStackTrace()) {
                errorMes += (what + System.lineSeparator());
            }
            JOptionPane.showMessageDialog(ORFGUI.getWindows()[0], errorMes);
            ErrorFile.savingErrors(exc.getStackTrace());
            exc.printStackTrace();
        }
        return sequenceString;
    }

    // What reading frame needs to be shown
    public static String comboboxSelectedItem(java.awt.event.ActionEvent evt) {
        JComboBox<String> combo = (JComboBox<String>) evt.getSource();
        String item = (String) combo.getSelectedItem();
        return item;
    }

    // Gets the Protein sequence from the selected reading frame.
    public static ProteinSequence getReadingFrameSequence(String readingFrame, String sequenceString) {
        ProteinSequence proteinSequence = null;
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

            proteinSequence = dnaSequence.getRNASequence().getProteinSequence();

        } catch (CompoundNotFoundException ex) {
            Logger.getLogger(ORFGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("SringIndexOutOfBounds in setReadingFrameSequence");
            ex.printStackTrace();
        } catch (Exception ex) {
            ErrorFile.savingErrors(ex.getStackTrace());
        }
        return proteinSequence;

    }

}
