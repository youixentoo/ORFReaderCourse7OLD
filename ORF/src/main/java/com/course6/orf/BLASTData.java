/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course6.orf;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thijs
 */
public class BLASTData {

    public static List<BLASTResult> getBLASTResult(String BLASTProgram, String BLASTDatabase, String sequence, String eValueCutOff, String maxListSize, String BLASTMatrix, String maxAlignments) throws IOException {
        //String[] BLASTArgs = {BLASTProgram, BLASTDatabase, sequence, eValueCutOff, maxListSize, BLASTMatrix, maxAlignments};
        //System.out.println(Arrays.toString(BLASTArgs));
        List<BLASTResult> BLASTList = new ArrayList<>();

        Process p = Runtime.getRuntime().exec(new String[]{"python", System.getProperty("user.dir") + File.separator + "ORFFinderBLAST.py", BLASTProgram, BLASTDatabase, sequence, String.valueOf(eValueCutOff), String.valueOf(maxListSize), BLASTMatrix, String.valueOf(maxAlignments)});
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String ret;
        while ((ret = in.readLine()) != null) {
            if ("BLAST".equals(ret.split(" ")[0])) {
                BLASTList.add(splitter(ret.split("\\$")));
            }
        }
        return BLASTList;
    }

    private static BLASTResult splitter(String[] temp) {
        int blastid = Integer.parseInt((temp[0].split(":"))[1].replace(" ", ""));
        String hitID = (temp[1].split(":"))[1].replace(" ", "");
        String hitDef = (temp[2].split(":"))[1].replace(" ", "");
        int hitLen = Integer.parseInt((temp[3].split(":"))[1].replace(" ", ""));
        float hitBitScore = Float.parseFloat((temp[4].split(":"))[1].replace(" ", ""));
        int hitScore = Integer.parseInt((temp[5].split(":"))[1].replace(" ", ""));
        int hitIden = Integer.parseInt((temp[6].split(":"))[1].replace(" ", ""));
        int hitPosi = Integer.parseInt((temp[7].split(":"))[1].replace(" ", ""));
        int hitGaps = Integer.parseInt((temp[8].split(":"))[1].replace(" ", ""));
        int hitAlignLen = Integer.parseInt((temp[9].split(":"))[1].replace(" ", ""));
        double eVal = Double.parseDouble((temp[10].split(":"))[1].replace(" ", ""));

        BLASTResult tempObjectBLAST = new BLASTResult(blastid, hitID, hitDef, hitLen, hitBitScore, hitScore, hitIden, hitPosi, hitGaps, hitAlignLen, eVal);
        return tempObjectBLAST;
    }

    public static Map<String, List<BLASTResult>> addToBLASTMap(String sequenceKey, List<BLASTResult> BLASTResults, Map<String, List<BLASTResult>> BLASTMap) {
        if (BLASTMap.containsKey(sequenceKey)) {
            List<BLASTResult> tempList = BLASTMap.get(sequenceKey);
            tempList = BLASTResults;
        } else {
            BLASTMap.put(sequenceKey, BLASTResults);
        }

        /*
        if (hostVirusInfo.containsKey(v.getHostTaxID())) {
                String value = (String) hostVirusInfo.get(v.getHostTaxID());
                value = value + "," + v.getVirusTaxID();
                hostVirusInfo.put(v.getHostTaxID(), value);
            } else {
                hostVirusInfo.put(v.getHostTaxID(), v.getVirusTaxID());
            }
         */
        return BLASTMap;
    }
}
