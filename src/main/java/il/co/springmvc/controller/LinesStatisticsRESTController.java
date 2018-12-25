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
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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


    @PostMapping(value = "/uploadFilee")
    public @ResponseBody String uploadFileHandler(@RequestParam("name") String name,
                                                             @RequestParam("file") MultipartFile file) {

//		ModelAndView view = new ModelAndView("hello");
//		String message = "You successfully uploaded ";

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                absoluteFilePath = serverFile.getAbsolutePath();
//				view.addObject("name", name);
//				view.addObject("message", message);
                String str = "You successfully uploaded ";
                return str;
            } catch (Exception e) {
                String eGetMessage = e.getMessage();
                String rootPath = System.getProperty("catalina.home");
                if(eGetMessage.equals(rootPath + "/" + "tmpFiles" + " (Is a directory)")) {
                    eGetMessage = "the file! Fill the file name please!";
                }
//				message = ("You failed to upload " + eGetMessage);
//				view.addObject("message", message);
                String dd = "cdsc";
                return dd ;
            }
        } else {
//			message = "You failed to upload " + name + " because the file was empty.";
//			view.addObject("message", message);
            String ddd = "cdscssss";
            return ddd ;
        }
    }



}