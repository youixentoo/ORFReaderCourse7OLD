# -*- coding: utf-8 -*-
"""
Created on Tue Feb 27 10:43:19 2018

@author: thijs
"""

from Bio.Blast import NCBIWWW, NCBIXML
from sys import argv

script, first = argv

def main(arg):
    getBLAST(arg)
    
    
def getBLAST(searchSequence):
    BLASTResultAsXML = NCBIWWW.qblast(program='blastp',database='nr',expect='0.0001',sequence=searchSequence,matrix_name='BLOSUM62')
    
    BLASTData = NCBIXML.parse(BLASTResultAsXML)
    
    maxEValue = 0.0001
    maxResults = 1
    
    i = 0
    
    for BLASTResult in BLASTData:
        for alignment in BLASTResult.alignments:
            for hsp in alignment.hsps:
                if hsp.expect < maxEValue and maxResults < 2:
                    # Header van het BLAST resultaat
                    header = str(alignment.title)
                    # Naam organisme
                    name = header.split('[', 1)[1].split(']')[0].split('>')[0]
                    protein = header.split('|')[4].split('[')[0]
                    accession= alignment.title.split('|')[3]
                    eValue = hsp.expect
                    identity = hsp.identities
                    queryCov = float(hsp.identities)/ float(len(hsp.query)) * float(100)
                    score = hsp.score
                    bits = hsp.bits
                    
                    data = str(name)+"$"+str(protein)+"$"+str(accession)+"$"+str(eValue)+"$"+str(identity)+"$"+str(queryCov)+"$"+str(score)+"$"+str(bits)
                    print(data)
                    maxResults += 1
                    
                if maxResults >= 2:
                    break
            i += 1
        if i == 1:
            break
    
   
    
main(first)
