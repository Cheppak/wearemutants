package ar.com.sac.mutant;

import org.apache.commons.lang3.StringUtils;

public class MutantChecker {

	private static final int MAX_CONCAT_LETTERS = 4;
	private static final int MAX_MUTANT_SEQUENCE = 2;
	private static final String VALID_SEQUENCE_LETTERS = "ATCG";
	
	/**
	 * Detecta si un humano es mutante basándose en su secuencia de ADN
	 * @param dna (NXN)
	 * @return boolean 
	 */
	public static boolean isMutant(String[] dna){
		
		if(!hasValidSequences(dna))
			return false;
		if(!hasValidNXNDnaMatrix(dna))
			return false;
		return checkDna(dna);
	}
	
	/**
	 * Es mutante, si encuentras más de "MAX_MUTANTS_SEQUENCE" secuencia de "MAX_CONCAT_LETTERS" letras
	 * iguales de forma oblicua, horizontal o vertical
	 * 
	 * @param dna
	 * @return
	 */
	private static boolean checkDna(String[] dna) {
		
		char[][] matrix = transformToNxNMatrix(dna);
		//REVIEW como negativo tiene que SUCCESS depende del orden del scaneo.
		return scan(matrix);
	}
	
	/**
	 * Escaneamos la matriz NxN en busca de los sequences.
	 * Primeramente se escanea de forma horizontal, luego vertical y oblicua
	 * @param matrix
	 * @return boolean
	 */
	private static boolean scan(char[][] matrix){

		// Horizontal Scan
		int mutantSequences = 0;
		for(int i=0; i < matrix[0].length; i++){
			for(int x=0; matrix[0].length > x+MAX_CONCAT_LETTERS;x++){
				if(matrix[i][x] == matrix[i][x+1] && matrix[i][x+1] == matrix[i][x+2] && matrix[i][x+2] == matrix[i][x+3]){ 
					// hemos encontrado una sequencia. Ademas hacemos corrimiento porque no queremos scanear lo que y escaneamos  
					x = x+4;
					if(++mutantSequences >= MAX_MUTANT_SEQUENCE)
						return true;
				}
			}
		}

		// Vertical Scan
		for(int j=0; j < matrix[0].length; j++){
			for(int x=0; matrix[0].length > x+MAX_CONCAT_LETTERS;x++){
				if(matrix[x][j] == matrix[x+1][j] && matrix[x+1][j] == matrix[x+2][j] && matrix[x+2][j]== matrix[x+3][j]){
					x = x+4;
					if(++mutantSequences >= MAX_MUTANT_SEQUENCE)
						return true;
				}
			}
		}
		
		return false;
	}
	
	///////////////////////// Internal Utils //////////////////////////////////////////
	
	private static char[][] transformToNxNMatrix(String[] dna){

		int n = dna.length;
		char[][] matrix =  new char[n][n] ;
		int i = 0;
		
		for (String sequence: dna) {
			matrix[i++] = sequence.toCharArray(); 
		}
		
		return matrix;
	}
	
	public static void printMatrix(String[] dna){
		
		char[][] matriz = transformToNxNMatrix(dna);
		
		for (int x=0; x < matriz.length; x++) {
			  System.out.print("");
			  for (int y=0; y < matriz[x].length; y++) {
			    System.out.print (matriz[x][y]);
			    if (y!=matriz[x].length-1) System.out.print("\t");
			  }
			  System.out.println("");
			}
	}
	
	//////////////////////// Internal Validations ///////////////////////////////
	
	/**
	 * Todas las sequencias deben contener caracteres validos.
	 * @param dna
	 */
	private static boolean hasValidSequences(String[] dna){
		
		for (String sequence: dna) {
			if(!StringUtils.containsOnly(sequence,VALID_SEQUENCE_LETTERS))
				return false;
		}
		return true;
	}
	
	/**
	 * La matriz debe ser NxN 
	 * @param dna
	 * @return
	 */
	private static boolean hasValidNXNDnaMatrix(String[] dna){
		
		for (String sequence: dna) {
			if(sequence.length() != dna.length)
				return false;
				}
		return true;
	} 	
}
