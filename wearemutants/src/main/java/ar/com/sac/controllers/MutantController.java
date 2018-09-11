package ar.com.sac.controllers;

import java.util.Arrays;

import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ar.com.sac.exceptions.ForbiddenException;
import ar.com.sac.human.Human;
import ar.com.sac.mutant.MutantChecker;

@RestController
public class MutantController {

    @RequestMapping(value = "/mutant", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void mutant(@RequestBody Human human){
    	
    	MongoDatabase db = new MongoClient().getDatabase("we_are_mutants");
    	Document document = new Document();
    	document.put("dna", Arrays.toString(human.getDna()));
	
    	if(!MutantChecker.isMutant(human.getDna())){
			MongoCollection<Document> collection = db.getCollection("human");
			collection.insertOne(document);
			throw new ForbiddenException();
		}else {
		       MongoCollection<Document> collection = db.getCollection("mutant");
		       collection.insertOne(document);
		}
    }
    
    @RequestMapping(value="/stats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String stats() {
    	MongoDatabase db = new MongoClient().getDatabase("we_are_mutants");
    	MongoCollection<Document> humans = db.getCollection("human");
        MongoCollection<Document> mutants = db.getCollection("mutant");
        Gson gson = new Gson();
        JsonObject stats = new JsonObject();
        long countMutants = mutants.count();
        long countHumans = humans.count();
        double ratio = countMutants/countHumans;
        stats.addProperty("count_mutant_dna", countMutants);
		stats.addProperty("count_human_dna", countHumans);
		stats.addProperty("ratio", ratio);
        return gson.toJson(stats);
    } 
}
