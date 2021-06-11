package com.wuzz.demo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuzz.demo.model.JacksonTestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jsonproperty")
public class JsonPropertySerializationController {

    private static final Logger logger = LoggerFactory.getLogger(JsonPropertySerializationController.class);

    @Autowired
    ObjectMapper ObjectMapper;

    @RequestMapping(value = "/serialization", method = RequestMethod.GET)
    public JacksonTestModel serialization() throws JsonProcessingException {
        JacksonTestModel test = new JacksonTestModel();
        logger.info(ObjectMapper.writeValueAsString(test));

        return test;
    }
}

