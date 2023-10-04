package com.spruceid.didkitexample.controller;

import com.spruceid.DIDKit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

@RestController
public class VersionController {

    @RequestMapping("/version")
    public String version() {
        return DIDKit.getVersion();
    }
    
    @GetMapping(value = "/.well-known/{jsonfile}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Object getBeers(@PathVariable("jsonfile") String jsonfile) {
        Resource resource = new ClassPathResource(jsonfile);
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(resource.getInputStream(), Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
