/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course6.orf.python;

import com.course6.orf.file.ErrorFile;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thijs
 */
public class ORFFinderPython {

    public static void createBLASTScript() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(System.getProperty("user.dir") + "/ORFFinderBLAST.py");
            fileWriter.write("try:\n"
                    + "\tfrom Bio.Blast import NCBIWWW\n"
                    + "\timport xml.etree.cElementTree as xmlParser\n"
                    + "\tfrom sys import argv\n"
                    + "except Exception as exc:\n"
                    + "\tprint(exc.with_traceback)"
                    + "\n"
                    + "\n"
                    + "def main(arg):\n"
                    + "\ttry:\n"
                    + "\t\tBLAST(arg)\n"
                    + "\texcept Exception as exc:\n"
                    + "\t\tprint(exc.with_traceback)\n"
                    + "\n"
                    + "\n"
                    + "def BLAST(arg):\n"
                    + "\tBLASTResultAsXML = NCBIWWW.qblast(program=arg[1],database=arg[2],sequence=arg[3],expect=arg[4],hitlist_size=arg[5],matrix_name=arg[6],alignments=arg[7])\n"
                    + "\t\n"
                    + "\t#tree = xmlParser.XML(open('D:/blasttest.xml').read())\n"
                    + "\ttree = xmlParser.parse(BLASTResultAsXML)\n"
                    + "\thits = tree.findall(\".//BlastOutput_iterations/Iteration/Iteration_hits/\")\n"
                    + "\t\n"
                    + "\t\n"
                    + "\tfor index, hit in enumerate(hits):\n"
                    + "\t\thitEValue = hit.findtext(\".//Hsp/Hsp_evalue\")\n"
                    + "\t\thitID = hit.findtext(\".//Hit_id\")\n"
                    + "\t\thitDef = hit.findtext(\".//Hit_def\")\n"
                    + "\t\thitLength = hit.findtext(\".//Hit_len\")\n"
                    + "\t\thitBitScore = hit.findtext(\".//Hsp/Hsp_bit-score\")\n"
                    + "\t\thitScore = hit.findtext(\".//Hsp/Hsp_score\")\n"
                    + "\t\thitIdentity = hit.findtext(\".//Hsp/Hsp_identity\")\n"
                    + "\t\thitPositive = hit.findtext(\".//Hsp/Hsp_positive\")\n"
                    + "\t\thitGaps = hit.findtext(\".//Hsp/Hsp_gaps\")\n"
                    + "\t\thitAlignLength = hit.findtext(\".//Hsp/Hsp_align-len\")\n"
                    + "\t\tprint(\"BLAST result: \"+str(index)+\"$HitID: \"+str(hitID)+\"$HitDef: \"+str(hitDef)+\"$HitLength: \"+str(hitLength)+\"$HitBitScore: \"+str(hitBitScore)+\"$HitScore: \"+str(hitScore)+\"$HitIdentity: \"+str(hitIdentity)+\"$HitPositive: \"+str(hitPositive)+\"$HitGaps: \"+str(hitGaps)+\"$HitAlignLength: \"+str(hitAlignLength)+\"$E-value: \"+str(hitEValue)+\"$\\n\")"
                    + "\n"
                    + "\n"
                    + "main(argv)");
            fileWriter.close();
        } catch (Exception ex) {
            ErrorFile.savingErrors(ex.getStackTrace());
        }
    }
}
