package il.co.springmvc.controller;

import il.co.springmvc.entities.LinesStatistics;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import il.co.springmvc.services.LinesStatisticsServices;
import il.co.springmvc.util.LongestAndShortestWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        List<LinesStatistics> listLines = service.listLines();
        String result = "Parse OK! REST is working!)";
        return new ResponseEntity(listLines, HttpStatus.OK);
    }

    @RequestMapping(value = "/listLinesRequest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<LinesStatistics> listLinesRequest(){
        List<LinesStatistics> listLines = service.listLines();
        return listLines;
    }



}