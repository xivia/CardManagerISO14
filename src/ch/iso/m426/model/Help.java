package ch.iso.m426.model;

public class Help {
private String helpText;
   public Help(){}

   public Help(String helpText){
	   this.setHelpText(helpText);
   }
	public String getHelpText() {
		return helpText;
	}


	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

}
