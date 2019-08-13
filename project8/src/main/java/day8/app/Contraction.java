package day8.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Contraction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message="word may not be empty")
	@Size(min=3, message="word must be at least three characters")
	private String wordOne;
	
	@NotEmpty(message="this can be only one thing, you see")
	@Pattern(regexp="[']", message="please make this an apostrophe")
	private String apostrophe;
	
	@NotEmpty(message="word may not be empty")
	@Size(min=3, message="word must be at least three characters")
	private String wordTwo;
	
	@NotEmpty(message="but this is the whole point!")
	@Pattern(regexp=".+['].+", message="there must be an apostrophe")
	private String result;
	
	@Min(0)
	@Max(9)
	@NotNull
	private int usability;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWordOne() {
		return wordOne;
	}

	public void setWordOne(String wordOne) {
		this.wordOne = wordOne;
	}

	public String getApostrophe() {
		return apostrophe;
	}

	public void setApostrophe(String apostrophe) {
		this.apostrophe = apostrophe;
	}

	public String getWordTwo() {
		return wordTwo;
	}

	public void setWordTwo(String wordTwo) {
		this.wordTwo = wordTwo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getUsability() {
		return usability;
	}

	public void setUsability(int usability) {
		this.usability = usability;
	}

	@Override
	public String toString() {
		return "Contraction [id=" + id + ", wordOne=" + wordOne + ", apostrophe=" + apostrophe + ", wordTwo=" + wordTwo
				+ ", result=" + result + ", usability=" + usability + "]";
	}
}
