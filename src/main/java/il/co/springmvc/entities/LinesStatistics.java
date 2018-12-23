package il.co.springmvc.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * LinesStstistics represent domain class of the SpringMVCApp project.
 * @author Artem Meleshko
 * @version 1.0 2017
 */
@Entity
@Table(name="stat")
public class LinesStatistics {
	
	
	private Integer line_id;
	private String line;
	private String longest_word;
	private String shortest_word;
	private Integer line_length;
    private Integer average_w_length;
    
    /**
     * Constructs and Initializes LinesStatistics.
     * @param line_id
     * @param line
     * @param longest_word
     * @param shortest_word
     * @param line_length
     * @param average_w_length
     */
    public LinesStatistics(Integer line_id, String line, String longest_word, String shortest_word, Integer line_length, Integer average_w_length){
        this.line_id = line_id;
        this.line = line;
        this.longest_word = longest_word;
        this.shortest_word = shortest_word;
        this.line_length = line_length;
        this.average_w_length = average_w_length;
    }
    
    public LinesStatistics(){}

   
    
    /**
	 * @return the line_id
	 */
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="line_id")
	public Integer getLine_id() {
		return line_id;
	}

	/**
	 * @param line_id the line_id to set
	 */
	public void setLine_id(Integer line_id) {
		this.line_id = line_id;
	}
	
	/**
	 * @return the line
	 */
	@Column(name="line")
	public String getLine() {
		return line;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(String line) {
		this.line = line;
	}

	/**
	 * @return the longest_word
	 */
	@Column(name="longest_word")
	public String getLongest_word() {
		return longest_word;
	}

	/**
	 * @param longest_word the longest_word to set
	 */
	public void setLongest_word(String longest_word) {
		this.longest_word = longest_word;
	}

	/**
	 * @return the shortest_word
	 */
	@Column(name="shortest_word")
	public String getShortest_word() {
		return shortest_word;
	}

	/**
	 * @param shortest_word the shortest_word to set
	 */
	public void setShortest_word(String shortest_word) {
		this.shortest_word = shortest_word;
	}

	/**
	 * @return the line_length
	 */
	@Column(name="line_length")
	public Integer getLine_length() {
		return line_length;
	}

	/**
	 * @param line_length the line_length to set
	 */
	public void setLine_length(Integer line_length) {
		this.line_length = line_length;
	}

	/**
	 * @return the average_w_length
	 */
	@Column(name="average_w_length")
	public Integer getAverage_w_length() {
		return average_w_length;
	}

	/**
	 * @param average_w_length the average_w_length to set
	 */
	public void setAverage_w_length(Integer average_w_length) {
		this.average_w_length = average_w_length;
	}

	@Override
    public String toString(){
        return "LineStatistic [lineId=" + line_id + ", longestWord=" + longest_word + ", shortestWord=" + shortest_word + 
                ", lineLength=" + line_length + ", awerageWordLength=" + average_w_length + "]";
    }
    
    /**
     * Returns a hash code value for the object.
     */
    @Override
    public int hashCode() {
        Integer result = 17;
        result = result*37 + longest_word.hashCode();
        result = result*37 + shortest_word.hashCode();
        result = result*37 + line_length.hashCode();
        result = result*37 + average_w_length.hashCode();
        return result;
    }
    
    /**
     * Indicates whether some other object is "equal to" this one.
     */
    @Override
    public boolean equals(Object s){
        if (s == this)
            return true;
        if (!(s instanceof LinesStatistics))
            return false;
        LinesStatistics other = (LinesStatistics)s;
        return (Objects.equals(longest_word, other.longest_word) && Objects.equals(shortest_word, other.shortest_word) 
                && Objects.equals(line_length, other.line_length) && Objects.equals(average_w_length, other.average_w_length));
    }

}
