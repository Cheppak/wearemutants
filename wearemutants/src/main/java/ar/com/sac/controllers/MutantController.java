package ar.com.sac.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.com.sac.exceptions.ForbiddenException;
import ar.com.sac.human.Human;
import ar.com.sac.mutant.MutantChecker;

@RestController
public class MutantController {

    @RequestMapping(value = "/mutant", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void mutant(@RequestBody Human human){
		if(!MutantChecker.isMutant(human.getDna())){
			throw new ForbiddenException();
		}
    }
    
}
