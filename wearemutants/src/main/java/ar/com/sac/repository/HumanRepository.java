package ar.com.sac.repository;

import ar.com.sac.human.Human;

public interface HumanRepository {

	public void insertHuman(Human human);
	public void insertMutant(Human human);
	public long countHumans();
	public long countMutants();
}
