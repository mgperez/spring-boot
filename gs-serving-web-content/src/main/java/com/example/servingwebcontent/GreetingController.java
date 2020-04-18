package com.example.servingwebcontent;

import com.example.servingwebcontent.error.ErrorHolder;
import com.example.servingwebcontent.model.RESTServer;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GreetingController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RESTServer mRESTServer;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        String employess = getEmployees();
        //model.addAttribute("name", name);
        model.addAttribute("name", getEmployees());
        return "greeting";
    }

    private String getEmployees() {
        //final String uri = "http://localhost:8080/springrestexample/employees.xml";

        String uri = new String("http://" + mRESTServer.getHost() +
                mRESTServer.getContextPath());
        try {
            String result = restTemplate.getForObject(uri, String.class);

            System.out.println(result);
            return result+": ("+uri+")";

        } catch (HttpClientErrorException e) {
            /**
             *
             * If we get a HTTP Exception display the error message
             */
            log.error("error:  " + e.getResponseBodyAsString());

            ObjectMapper mapper = new ObjectMapper();
            ErrorHolder eh = null;
            try {
                eh = mapper.readValue(e.getResponseBodyAsString(), ErrorHolder.class);
            } catch (IOException ignored) {
            }

            log.error("error:  " + eh.getErrorMessage());

        } catch (Exception e) {
            log.error("error:  " + e.getMessage());

        }

        return null;
    }

}
