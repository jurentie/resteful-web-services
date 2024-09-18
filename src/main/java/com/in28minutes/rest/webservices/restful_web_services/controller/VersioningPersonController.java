package com.in28minutes.rest.webservices.restful_web_services.controller;

import com.in28minutes.rest.webservices.restful_web_services.model.PersonV1;
import com.in28minutes.rest.webservices.restful_web_services.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Factors to Consider:
/*
- URI Pollution
- Misuse of HTTP Headers
- Caching
- Can we execute on the broswer?
- API Documentation
 */

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersion(){
        return new PersonV1("Steve Nicks");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersion(){
        return new PersonV2("Steve", "Nicks");
    }

    @GetMapping(path="/person", params="version=1")
    public PersonV1 getPersonVersion1(){
        return new PersonV1("Steve Nicks");
    }

    @GetMapping(path="/person", params="version=2")
    public PersonV2 getPersonVersion2(){
        return new PersonV2("Steve", "Nicks");
    }

    @GetMapping(path="/person/header", headers="X-API-VERSION=1")
    public PersonV1 getPersonVersionOne(){
        return new PersonV1("Steve Nicks");
    }

    @GetMapping(path="/person/header", headers="X-API-VERSION=2")
    public PersonV2 getPersonVersionTwo(){
        return new PersonV2("Steve", "Nicks");
    }

    @GetMapping(path="/person/accepts", produces="application/vnd.company.app-v1+json")
    public PersonV1 getOnePersonVersion(){
        return new PersonV1("Steve Nicks");
    }

    @GetMapping(path="/person/accepts", produces="application/vnd.company.app-v2+json")
    public PersonV2 getTwoPersonVersion(){
        return new PersonV2("Steve", "Nicks");
    }

}
