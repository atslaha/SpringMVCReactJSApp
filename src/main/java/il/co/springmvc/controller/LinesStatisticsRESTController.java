package il.co.springmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import il.co.springmvc.entities.LinesStatistics;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import il.co.springmvc.services.LinesStatisticsServices;
import il.co.springmvc.util.LongestAndShortestWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artem Meleshko
 * @version 1.0 2018
 *
 */
@RestController
@RequestMapping(value= {"/api"})
public class LinesStatisticsRESTController {

    private String absoluteFilePath;

    @Autowired
    LinesStatisticsServices service;

    /**
     * Path of the File on the server.
     */
    @Autowired
    LongestAndShortestWord longestAndShortestWord;

    @GetMapping(value={"/getTestTransaction"})
    public ResponseEntity<?> getTestMassege() {
        String result = "Transaction OK! REST is working!)";
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping(value={"/parse"})
    public ResponseEntity<?> parseFile(){

        String result = "Parse OK! REST is working!)";
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping(value={"/listLinesRest"})
    public ResponseEntity<?> listLinesRest(){
        List<LinesStatistics> listLines = (ArrayList) service.listLines();
        String result = "Parse OK! REST is working!)";
        ObjectMapper mapper = new ObjectMapper();
//        String listLinesJson = "Empy Json Request";

        String listLinesJson = new Gson().toJson(listLines);
        System.out.println("List Json:  "+listLinesJson);

        //String [] listLinesArray = (String[]) listLines.toArray();
//        try {
//            listLinesJson = mapper.writeValueAsString(listLines);
//            System.out.println("List Json:  "+listLinesJson);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        return new ResponseEntity( listLinesJson, HttpStatus.OK);
    }

    @RequestMapping(value = "/listLinesRequest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String listLinesRequest(){
        List<LinesStatistics> listLines = service.listLines();
        String listLinesJson = new Gson().toJson(listLines);

        return listLinesJson;
    }



}