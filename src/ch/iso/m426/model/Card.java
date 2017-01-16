package ch.iso.m426.model;

import com.sun.deploy.util.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypes() {
        String types = Arrays.stream(this.types).collect(Collectors.joining(", "));
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String getSubtypes() {
        String types = Arrays.stream(this.subtypes).collect(Collectors.joining(", "));
        return types;
    }

    public void setSubtypes(String[] subtypes) {
        this.subtypes = subtypes;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public String getRuleText() {
        return ruleText;
    }

    public void setRuleText(String ruleText) {
        this.ruleText = ruleText;
    }

    public String getStoryText() {
        return storyText;
    }

    public void setStoryText(String storyText) {
        this.storyText = storyText;
    }

    public Byte getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(Byte attackValue) {
        this.attackValue = attackValue;
    }

    public Byte getDefenceValue() {
        return defenceValue;
    }

    public void setDefenceValue(Byte defenceValue) {
        this.defenceValue = defenceValue;
    }
}
