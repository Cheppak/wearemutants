package ar.com.sac.mutant;

import ar.com.sac.human.Human;
import junit.framework.TestCase;

public class MutantCheckerTestCase extends TestCase{

	// Test facilitado por enunciado (caso base)
	public void testIsMutant(){
		Human mutant = new Human();
		mutant.setDna(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
		assertTrue(MutantChecker.isMutant(mutant.getDna()));
	}
	
	public void testIsMutantHorizontalScan(){
		Human mutant = new Human();
		mutant.setDna(new String[]{"AAAAAA","CCCCCC","TTATCT","CTTACG","CCTATA","TCACTG"});
		assertTrue(MutantChecker.isMutant(mutant.getDna()));
	}

	public void testIsMutantVerticalScan(){
		Human mutant = new Human();
		mutant.setDna(new String[]{"AAAAAA","CCCCCC","TTATCT","CTTACG","CCTATA","TCACTG"});
		assertTrue(MutantChecker.isMutant(mutant.getDna()));
	}
	
	public void testIsNotMutant(){
		Human human = new Human();
		human.setDna(new String[]{"ATGGGA","CAGTGC","ATATGG","AGAAGT","CACCTA","TCAATG"});
		assertFalse(MutantChecker.isMutant(human.getDna()));
	}
	
	public void testIsNotMutantBecouseHasBadSequenceLetter(){
		Human crazyHuman = new Human();
		crazyHuman.setDna(new String[]{"ATGGGA","CAXXGC","ATATGG","AGAAGT","CACCTA","TCAATG"});
		assertFalse(MutantChecker.isMutant(crazyHuman.getDna()));
	}
	
	public void testIsNotMutantBecouseHasBadNxNDnaMatrix(){
		Human crazyHuman = new Human();
		crazyHuman.setDna(new String[]{"A","CAGTGC","ATAG","AGAAGT","CACCTA"});
		assertFalse(MutantChecker.isMutant(crazyHuman.getDna()));
	}	
	
}
