/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course6.orf.file;

import com.course6.orf.ORFGUI;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.exceptions.TranslationException;
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

    /**
     * Reads a Fasta file and places the headers in the file in the combobox
     *
     * @param file The file path of the fasta file you want to open
     * @param combobox The combobox in which the fasta headers get placed (even
     * if it only contains 1 header)
     */
    public static void setFastaHeaders(String file, JComboBox combobox) {
        Set<String> fastaHeaders;

        try {
            LinkedHashMap<String, DNASequence> readFastaDNASequence = FastaReaderHelper.readFastaDNASequence(new File(file));
            fastaHeaders = readFastaDNASequence.keySet();
            for (String header : fastaHeaders) {
                combobox.addItem(header);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Wrong file");
            JOptionPane.showMessageDialog(ORFGUI.getWindows()[0], "Incorrect file format");
        } catch (java.lang.NullPointerException exc) {
            System.out.println("Incorrect file format");
            JOptionPane.showMessageDialog(ORFGUI.getWindows()[0], "Incorrect file format");
        } catch (Exception exc) {
            String errorMes = "Unknown error occurred:" + System.lineSeparator() + System.lineSeparator();
            for (StackTraceElement what : exc.getStackTrace()) {
                errorMes += (what + System.lineSeparator());
            }
            JOptionPane.showMessageDialog(ORFGUI.getWindows()[0], errorMes);
            ErrorFile.savingErrors(exc.getStackTrace());
            exc.printStackTrace();
            System.out.println(System.lineSeparator() + exc);
        }

    }

    /**
     * Gets the DNA sequence from the file
     *
     * @param header The header that is selected from the combobox
     * @param file The file from which the header is from originally
     * @return The DNA sequence which belongs to the chosen header
     */
    public static String readHeader(String header, String file) {
        // Leest een fasta bestand in dat wordt meegegeven van de fileChooser
        String sequenceString = "";
        try {
            // Uses the fasta parser from Biojava
            LinkedHashMap<String, DNASequence> readFastaDNASequence = FastaReaderHelper.readFastaDNASequence(new File(file));
            DNASequence dnaSeq = readFastaDNASequence.get(header);
            sequenceString = dnaSeq.toString();
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Wrong file");
            JOptionPane.showMessageDialog(ORFGUI.getWindows()[0], "Incorrect file format");
        } catch (java.lang.NullPointerException exc) {
            System.out.println("Incorrect file format");
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

    /** Returns the currently selected item from a combobox
     * 
     * @param evt The <code>java.awt.event.ActionEvent</code> from the combobox
     * @return The selected item from the combobox
     */
    public static String comboboxSelectedItem(java.awt.event.ActionEvent evt) {
        JComboBox<String> combo = (JComboBox<String>) evt.getSource();
        String item = (String) combo.getSelectedItem();
        return item;
    }

    /**
     * Gets the Protein sequence from the selected reading frame.
     *
     * @param readingFrame The current reading frame (+1, +2, +3, -1, -2, -3)
     * @param sequenceString The DNA sequence
     * @return The protein sequence which belongs to the selected reading frame
     */
    public static ProteinSequence getReadingFrameSequence(String readingFrame, String sequenceString) {
        ProteinSequence proteinSequence = null;
        try {
            DNASequence dnaSequence = null;
            // readingFrame is where to start reading, on index 0, 1, or 2. Defined with the comboBox in "readingFramesComboBoxActionPerformed".
            if ("-1".equals(readingFrame) | "-2".equals(readingFrame) | "-3".equals(readingFrame)) {
                int readFrame = Integer.parseInt(readingFrame.replace("-", ""));
                DNASequence tempSequence = new DNASequence(sequenceString);
                // Needs to be reversed first
                SequenceView<NucleotideCompound> test = tempSequence.getReverseComplement();
                dnaSequence = new DNASequence(test.getSequenceAsString().substring(readFrame - 1));
            } else {
                int readFrame = Integer.parseInt(readingFrame.replace("+", ""));
                dnaSequence = new DNASequence(sequenceString.substring(readFrame - 1));
            }

            proteinSequence = dnaSequence.getRNASequence().getProteinSequence();

        } catch (CompoundNotFoundException ex) {
            System.out.println("Unknown compound in sequence");
            JOptionPane.showMessageDialog(ORFGUI.getWindows()[0], "Unknown compound in sequence");
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("SringIndexOutOfBounds in setReadingFrameSequence");
            ErrorFile.savingErrors(ex.getStackTrace());
        }catch(TranslationException ex){
            System.out.println("Incorrect file format");
            JOptionPane.showMessageDialog(ORFGUI.getWindows()[0], "Incorrect file format");
        }
        
        catch (Exception exc) {
            String errorMes = "Unknown error occurred:" + System.lineSeparator() + System.lineSeparator();
            for (StackTraceElement what : exc.getStackTrace()) {
                errorMes += (what + System.lineSeparator());
            }
            JOptionPane.showMessageDialog(ORFGUI.getWindows()[0], errorMes);
            ErrorFile.savingErrors(exc.getStackTrace());
            exc.printStackTrace();
        }
        return proteinSequence;

    }

}
