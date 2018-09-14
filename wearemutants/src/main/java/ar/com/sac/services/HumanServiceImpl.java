package ar.com.sac.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.sac.exceptions.ForbiddenException;
import ar.com.sac.human.Human;
import ar.com.sac.mutant.MutantChecker;
import ar.com.sac.repository.HumanRepository;

@Service("humanService")
@Transactional
public class HumanServiceImpl implements HumanService {

	private HumanRepository humanRepository;

	@Autowired
	public HumanServiceImpl(HumanRepository humanRepository){
		this.humanRepository = humanRepository;
	}

	public void insertHuman(Human human) {
		boolean isMutant = MutantChecker.isMutant(human.getDna());
		human.setMutant(isMutant);
   		if(!isMutant) {
   			humanRepository.insertHuman(human);
   			throw new ForbiddenException();   			
   		}
   		humanRepository.insertMutant(human);
	}
	
	public long countHumans() {
		return this.humanRepository.countHumans();
	}

	public long countMutants() {
		return this.humanRepository.countMutants();
	}
}
