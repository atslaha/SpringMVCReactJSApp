package il.co.springmvc.util;

import org.springframework.stereotype.Service;

/**Finds longest and shortest word in the String line. 
 * @author Artem Meleshko
 * @version 1.0 2017
 */
@Service
public class LongestAndShortestWord {
	public String longestWord(String str){
        if (str == null)
            return null;
        String lw = "";
        int l = 0;
        String words[]=str.split(" ");
        for(String word:words){
                if(word.length()>l){
                        lw=word;
                        l = word.length();
                }
        }
        return lw;
    }
    
    public String shortestWord(String str){
        if (str == null)
            return null;
        String sw = "";
        int s=str.length();
        String words[]=str.split(" ");
        for(String word:words){
                if(word.length()<s){
                        sw=word;
                        s = word.length();
                }
        }
        return sw;
    }
    
    public Integer avewrageWordLength(String str){
        if (str == null)
            return null;
        int count = 0, sumLength = 0;
        String words[]=str.split(" ");
        for(String word:words){
                sumLength += word.length();
                count++;
        }
        Integer averageWordLength = sumLength/count;
        return averageWordLength;
    }

}
