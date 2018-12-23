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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import il.co.springmvc.entities.LinesStatistics;
import il.co.springmvc.services.LinesStatisticsServices;
import il.co.springmvc.util.LongestAndShortestWord;

/**
 * @author Artem Meleshko
 * @version 1.0 2017
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
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody ModelAndView uploadFileHandler(@RequestParam("name") String name, 
									@RequestParam("file") MultipartFile file) {
		
		ModelAndView view = new ModelAndView("hello");
		String message = "You successfully uploaded ";
		
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
				view.addObject("name", name);
				view.addObject("message", message);
				return view;
			} catch (Exception e) {
				String eGetMessage = e.getMessage();
				String rootPath = System.getProperty("catalina.home");
				if(eGetMessage.equals(rootPath + "/" + "tmpFiles" + " (Is a directory)")) {
					eGetMessage = "the file! Fill the file name please!";
				}
				message = ("You failed to upload " + eGetMessage);
				view.addObject("message", message);
				return view;
			}
		} else {
			message = "You failed to upload " + name + " because the file was empty.";
			view.addObject("message", message);
			return view;
		}
	}
	
	/**
	 * Parse the File.
	 */
	@RequestMapping(value = "/parse", method = RequestMethod.GET)
    public @ResponseBody ModelAndView handleParse(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
		ModelAndView view = new ModelAndView("hello");
		LinesStatistics line = new LinesStatistics();
        File file = null;
        if (absoluteFilePath == null) {
        	view.addObject("messageEx", "File not found! Please download the file!");
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
        return view;     
    }
	
	/**
     * This method will list all existing lines in the DB.
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listEmployees(ModelMap model) {
 
        List<LinesStatistics> listLines = service.listLines();
        model.addAttribute("listLines", listLines);
        return "hello";
    }
    
    /**
     * This method will delete a line by it's line_id value.
     */
    @RequestMapping(value = { "/delete-{line_id}-line" }, method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable Integer line_id) {
        LinesStatistics lineStatistic = service.findId(line_id);
        service.deleteById(lineStatistic);
    	//service.deleteEmployeeBySsn(id);
        return "redirect:/list";
    }


}
