package ar.com.sac.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ar.com.sac.human.Human;

@Repository
public class HumanRepositoryImpl implements HumanRepository {

    private static final String HUMANS_COLLECTION = "we_are_humans";
	private static final String MUTANTS_COLLECTION = "we_are_mutants";
	private final MongoOperations mongoOperations;

    @Autowired
    public HumanRepositoryImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
    
    public void insertHuman(Human human) {
    	this.mongoOperations.save(human, HUMANS_COLLECTION);
    }

    public void insertMutant(Human human) {
    	this.mongoOperations.save(human, MUTANTS_COLLECTION);
    }
    
    public long countHumans() {
    	return this.mongoOperations.count(new Query(),  HUMANS_COLLECTION);
    }

    public long countMutants() {
    	return this.mongoOperations.count(new Query(), MUTANTS_COLLECTION);
    }
}
