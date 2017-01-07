package ch.iso.m426.model;

public class Card {
	
    public String name;
    public String[] types;
    public String[] subtypes;
    public String edition;
    public String manaCost;
    public String ruleText;
    public String storyText;
    public Byte attackValue;
    public Byte defenceValue;
    
    public Card( String name, String[] types, String[] subtypes,
                      String edition, String manaCost, String ruleText,
                      String storyText, Byte attackValue, Byte defenceValue){
        this.name = name;
        this.types = types;
        this.subtypes = subtypes;
        this.edition = edition;
        this.manaCost = manaCost;
        this.ruleText = ruleText;
        this.storyText = storyText;
        this.attackValue = attackValue;
        this.defenceValue = defenceValue;
    }
    
    public void printCardInfo(){
        String concTypes = "";
        String concSubtypes = "";
        
        for(String type : types){
            concTypes += type;
        }
        for(String subType : subtypes){
            concSubtypes += subType;
        }
        
        System.out.println(name + " " + manaCost);
        System.out.println(concTypes + " " + concSubtypes + " " + edition);
        System.out.println(ruleText);
        System.out.println(storyText);
        System.out.println(attackValue + " " + defenceValue);
    }
}
