from Bio.Blast import NCBIWWW, NCBIXML
import xml.etree.cElementTree as xmlParser
from sys import argv


def main(arg):
	BLAST(arg)


def BLAST(arg):
	BLASTResultAsXML = NCBIWWW.qblast(program=arg[1],database=arg[2],sequence=arg[3],expect=arg[4],hitlist_size=arg[5],matrix_name=arg[6],alignments=arg[7])
	
	#tree = xmlParser.XML(open('D:/blasttest.xml').read())
	tree = xmlParser.parse(BLASTResultAsXML)
	hits = tree.findall(".//BlastOutput_iterations/Iteration/Iteration_hits/")
	
	
	for index, hit in enumerate(hits):
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
		print("BLAST result: "+str(index)+"$HitID: "+str(hitID)+"$HitDef: "+str(hitDef)+"$HitLength: "+str(hitLength)+"$HitBitScore: "+str(hitBitScore)+"$HitScore: "+str(hitScore)+"$HitIdentity: "+str(hitIdentity)+"$HitPositive: "+str(hitPositive)+"$HitGaps: "+str(hitGaps)+"$HitAlignLength: "+str(hitAlignLength)+"$E-value: "+str(hitEValue)+"$")


main(argv)