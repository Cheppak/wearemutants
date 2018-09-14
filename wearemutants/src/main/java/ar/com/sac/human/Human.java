package ar.com.sac.human;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "human")
public class Human {
	
	private boolean mutant;
	
	/**
	 * Contiene cadenas NXN de 'A','T','C','G', <br>
	 * las cuales representa cada base nitrogenada del ADN.
	 */
	private String dna[];
	
	public String[] getDna() {
		return dna;
	}

	public void setDna(String dna[]) {
		this.dna = dna;
	}

	public boolean isMutant() {
		return mutant;
	}

	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}
	
}
