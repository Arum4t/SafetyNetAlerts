package com.safetynetalerts.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LoggingController {

    private static final Logger log= LoggerFactory.getLogger(LoggingController.class);

    @ResponseBody
    @RequestMapping(path = "/")
    public String home() {

        log.trace("This is TRACE");
        log.debug("This is DEBUG");
        log.info("This is INFO");
        log.warn("This is WARN");
        log.error("This is ERROR");

        return "Hi, show loggings in the console or file!";
    }

}