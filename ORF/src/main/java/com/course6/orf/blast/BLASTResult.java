/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course6.orf.blast;

/**
 *
 * @author thijs
 */
public class BLASTResult {
    /*
    hitEValue = hit.findtext(".//Hsp/Hsp_evalue")
        hitID = hit.findtext(".//Hit_id")
        hitDef = hit.findtext(".//Hit_def")
        hitLength = hit.findtext(".//Hit_len")
        hitBitScore = hit.findtext(".//Hsp/Hsp_bit-score")
        hitScore = hit.findtext(".//Hsp/Hsp_score")
        hitIdentity = hit.findtext(".//Hsp/Hsp_identity")
        hitPositive = hit.findtext(".//Hsp/Hsp_positive")
        hitGaps = hit.findtext(".//Hsp/Hsp_gaps")
        hitAlignLength = hit.findtext(".//Hsp/Hsp_align-len")
    */
    private int resultNumber;
    private double eValue;
    private String referenceID;
    private String definition;
    private int length;
    private float bitScore;
    private int score;
    private int identity;
    private int positive;
    private int gaps;
    private int alignLength;

    public BLASTResult(){
    }
    
    /**
     *
     * @param resultNumber Number of the BLAST result
     * @param referenceID The reference ID, Hit_id in xml
     * @param definition The definition, Hit_def in xml
     * @param length The length of result, Hit_len in xml
     * @param bitScore The bit score, Hsp_bit-score in xml
     * @param score The score, Hsp_score in xml
     * @param identity The identity, Hsp_indentity in xml
     * @param positive The positives, Hsp_positive in xml
     * @param gaps Number of gaps, Hsp_gaps in xml
     * @param alignLength Length of the alignment, Hsp_align-len in xml
     * @param eValue The E-Value, Hsp_evalue in xml
     */
    public BLASTResult(int resultNumber, String referenceID, String definition, int length, float bitScore, int score, int identity, int positive, int gaps, int alignLength, double eValue) {
        this.resultNumber = resultNumber;
        this.eValue = eValue;
        this.referenceID = referenceID;
        this.definition = definition;
        this.length = length;
        this.bitScore = bitScore;
        this.score = score;
        this.identity = identity;
        this.positive = positive;
        this.gaps = gaps;
        this.alignLength = alignLength;
    }
    
    @Override
    public String toString(){
        return   "BLAST number: "+resultNumber+System.lineSeparator()
                +"Reference ID: "+referenceID+System.lineSeparator()
                +"Definition: "+definition+System.lineSeparator()
                +"E-Value: "+eValue+System.lineSeparator()
                +"Length: "+length+System.lineSeparator()
                +"Bit Score: "+bitScore+System.lineSeparator()
                +"Score: "+score+System.lineSeparator()
                +"Identity: "+identity+System.lineSeparator()
                +"Positives: "+positive+System.lineSeparator()
                +"Gaps: "+gaps+System.lineSeparator()
                +"Alignment Length: "+alignLength+System.lineSeparator();
    }
    
}
