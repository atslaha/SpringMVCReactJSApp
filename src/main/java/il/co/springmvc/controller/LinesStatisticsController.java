package il.co.springmvc.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import il.co.springmvc.entities.LinesStatistics;
import il.co.springmvc.services.LinesStatisticsServices;
import il.co.springmvc.util.LongestAndShortestWord;

/**
 * @author Artem Meleshko
 * @version 1.0.1 2018
 *
 */
@Controller
@RequestMapping(value= {"/" ,"lines"})
public class  LinesStatisticsController {
	
	private String absoluteFilePath;
	
	@Autowired
	LinesStatisticsServices service;
	
	/**
	 * Path of the File on the server.
	 */
	@Autowired
	LongestAndShortestWord longestAndShortestWord;
	
	
	/**
	 * 
	 * @return base page hello.jsp
	 */
	@RequestMapping(value= {"/", "/page" } , method = RequestMethod.GET)
	public ModelAndView getPage(){
		ModelAndView view = new ModelAndView("hello");
		System.out.println("in controller get");
				return view;
	}

	/**
	 * Upload single file using Spring Controller
	 */
	@CrossOrigin
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> uploadFileHandler(@RequestParam("file") MultipartFile file) {
		String name = "DefaultFileName";

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
				String str = "You've successfully uploaded the file!";
				String strJson = new Gson().toJson(str);
				return new ResponseEntity(strJson, HttpStatus.OK);
			} catch (Exception e) {
				String eGetMessage = e.getMessage();
				String rootPath = System.getProperty("catalina.home");
				if(eGetMessage.equals(rootPath + "/" + "tmpFiles" + " (Is a directory)")) {
					eGetMessage = "the file! Fill the file name please!";
				}
				String message = ("You failed to upload " + eGetMessage);
				String messageJson = new Gson().toJson(message);
				return new ResponseEntity(messageJson, HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			String message = "You failed to upload " + name + " because the file was empty.";
			String messageJson = new Gson().toJson(message);
			return new ResponseEntity(messageJson, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * Parse the File.
	 */
	@CrossOrigin
	@RequestMapping(value = "/parse", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> handleParse(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
		LinesStatistics line = new LinesStatistics();
        File file = null;
        if (absoluteFilePath == null) {
        	String messageEx = "File not found! Please download the file!";
        	String messageExJson = new Gson().toJson(messageEx);
			return new ResponseEntity(messageExJson,HttpStatus.EXPECTATION_FAILED);
        }else {
        	file = new File(absoluteFilePath);
            System.out.println(file);
        
        Scanner input;
        try {
            input = new Scanner(file);
            while(input.hasNext()) {
                String nextToken = input.nextLine();
                System.out.println(nextToken);
                line.setLine(nextToken);
                String lw = longestAndShortestWord.longestWord(nextToken);
                line.setLongest_word(lw);
                System.out.println("LONGEST WORD : "+lw);
                String sw = longestAndShortestWord.shortestWord(nextToken);
                line.setShortest_word(sw);;
                System.out.println("SHORTEST WORD : "+sw);
                Integer lineLength = nextToken.length();
                line.setLine_length(lineLength);;
                System.out.println("Line length = "+lineLength);
                Integer average = longestAndShortestWord.avewrageWordLength(nextToken);
                line.setAverage_w_length(average);;
                System.out.println("Average word length = " + average + "\n");
                service.createLineStatistic(line);
           }
           input.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        
	}
        System.out.println("in parse");
		String messageExJson = new Gson().toJson("File was parsed!");
		return new ResponseEntity(messageExJson,HttpStatus.OK);
    }
	
	/**
     * This method will delete a line by it's line_id value.
     */
	@CrossOrigin
    @RequestMapping(value = { "/delete-{line_id}-line" }, method = RequestMethod.GET)
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer line_id) {
        LinesStatistics lineStatistic = service.findId(line_id);
        service.deleteById(lineStatistic);
    	//service.deleteEmployeeBySsn(id);   /delete-{line_id}-line /delete-line/{line_id} "redirect:/listLinesRequest"
		String messageJson = new Gson().toJson("File was deleted!");
		return new ResponseEntity(messageJson,HttpStatus.OK);

    }

	/**
	 * This method will return a list of lines in JSON.
	 */
	@CrossOrigin
	@RequestMapping(value = "/listLinesRequest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	String listLinesRequest(){
		List<LinesStatistics> listLines = service.listLines();
		String listLinesJson = new Gson().toJson(listLines);
		return listLinesJson;
	}

}
