package ch.iso.m426.model;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Card {
    public int id;
    public String name;
    public String[] types;
    public String edition;
    public String color;
    public String manaCost;
    public String ruleText;
    public String storyText;
    public String artistName;
    public Byte attackValue;
    public Byte defenceValue;

    public Card( int id, String name, String[] types,  String edition, String color, String manaCost,
                 String ruleText, String storyText, String artistName, Byte attackValue, Byte defenceValue){

        this.id = id;
        this.name = name;
        this.types = types;
        this.edition = edition;
        this.color = color;
        this.manaCost = manaCost;
        this.ruleText = ruleText;
        this.storyText = storyText;
        this.artistName = artistName;
        this.attackValue = attackValue;
        this.defenceValue = defenceValue;
    }



    public Card( String name, String[] types,  String edition, String color, String manaCost,
                 String ruleText, String storyText, String artistName, Byte attackValue, Byte defenceValue){
        this(-1,name,types,edition,color,manaCost,ruleText,storyText,artistName,attackValue,defenceValue);
    }


    public void printCardInfo(){
        String concTypes = "";

        for(String type : types){
            concTypes += type;
        }

        System.out.println();
        System.out.println(name + " " + manaCost);
        System.out.println(concTypes);
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
