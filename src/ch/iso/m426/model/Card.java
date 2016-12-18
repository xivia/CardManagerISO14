package ch.iso.m426.model;

import java.util.Iterator;
import java.util.List;

public class Card {
	
    public String name;
    public String[] types;
    public String[] subtypes;
    public String edition;
    public String manacost;
    public String ruletext;
    public String storytext;
    public Byte attackValue;
    public Byte defenceValue;
    
    public Card( String name, String[] types, String[] subtypes,
                      String edition, String manaCost, String ruleText,
                      String storyText, Byte attackValue, Byte defenceValue){
        this.name = name;
        this.types = types;
        this.subtypes = subtypes;
        this.edition = edition;
        this.manacost = manaCost;
        this.ruletext = ruleText;
        this.storytext = storyText;
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
        
        System.out.println(name + " " + manacost);
        System.out.println(concTypes + " " + concSubtypes + " " + edition);
        System.out.println(ruletext);
        System.out.println(storytext);
        System.out.println(attackValue + " " + defenceValue);
    }
}
