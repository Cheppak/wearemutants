package ar.com.sac.services;

import ar.com.sac.human.Human;

public interface HumanService {

	public void insertHuman(Human human);
	public long countMutants();
	public long countHumans(); 
}
