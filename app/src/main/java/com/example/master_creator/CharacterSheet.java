package com.example.master_creator;

public class CharacterSheet {
    private int defense;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int stamina;
    private int pointdevie;
    private int wisdom;
    private int charisme;
    private String nom;
    private boolean attaque;
    private int weapon;



    public CharacterSheet(int defense,int strength,int dexterity,int intelligence,int stamina,int pointdevie,int wisdom,int charisme,boolean attaque,int weapon, String nom){
        this.defense = defense;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.stamina = stamina;
        this.pointdevie = pointdevie;
        this.wisdom = wisdom;
        this.charisme = charisme;
        this.attaque = attaque;
        this.weapon = weapon;
        this.nom = nom;
    }




    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisme() {
        return charisme;
    }

    public void setCharisme(int charisme) {
        this.charisme = charisme;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isAttaque() {
        return attaque;
    }

    public void setAttaque(boolean attaque) {
        this.attaque = attaque;
    }

    public int getWeapon() {
        return weapon;
    }

    public void setWeapon(int weapon) { this.weapon = weapon; }

    public int getDefense() {
        return defense;
    }

    public int getPointdevie() {
        return pointdevie;
    }

    public void setPointdevie(int pointdevie) {
        this.pointdevie = pointdevie;
    }

    public String getNom() {
        return nom;
    }



}
