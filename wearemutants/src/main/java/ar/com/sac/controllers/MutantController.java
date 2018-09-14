package ar.com.sac.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

import ar.com.sac.exceptions.ForbiddenException;
import ar.com.sac.human.Human;
import ar.com.sac.services.HumanService;

@RestController
public class MutantController {

	HumanService humanService; 
	
	@Autowired
	public MutantController(HumanService humanService) {
		this.humanService = humanService;
	}
	
    @RequestMapping(value = "/mutant", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void insertHuman(@RequestBody Human human){
    	humanService.insertHuman(human);
    }
    
    @RequestMapping(value="/stats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String stats() {
    	String stats = this.buildJsonResponse();
        return stats;
    } 
    
    private String buildJsonResponse(){
    	Gson gson = new Gson();
    	long countHumans = humanService.countHumans();
    	long countMutants = humanService.countMutants();
    	double ratio = countHumans == 0 ? 0 : countMutants/countHumans;
        JsonObject stats = new JsonObject();
		stats .addProperty("count_mutant_dna", countMutants);
		stats.addProperty("count_human_dna", countHumans);
		stats.addProperty("ratio", ratio);
		return gson.toJson(stats);
    }
}
