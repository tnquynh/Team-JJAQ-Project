package View;

public class SizeOfProject {
	private String displayValue;
	private Double sortValue;
	
	public SizeOfProject(Double s, Double c, Double t) {
		sortValue = (s*t+c)/100;
		
		if(sortValue <= 10) {
			displayValue = "Low";
		}else if(sortValue <= 20) {
			displayValue = "Medium";
		}else {
			displayValue = "High";
		}
	}
	
	public String getDisplay() {
		return displayValue;
	}
	
	public void update(Double s, Double t, Double c) {
		sortValue = (s*t+c)/100;
		
		if(sortValue <= 10) {
			displayValue = "Low";
		}else if(sortValue <= 20) {
			displayValue = "Medium";
		}else {
			displayValue = "High";
		}
	}
	
	public Double getSort() {
		return sortValue;
	}

}
