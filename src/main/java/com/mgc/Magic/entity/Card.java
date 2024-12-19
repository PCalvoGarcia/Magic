package com.mgc.Magic.entity;


import jakarta.persistence.*;

@Entity
public class Card {

    public static enum ManaType {
        WHITE,BLUE,GREEN,RED,BLACK,COLORLESS;
    }

    public static enum Type {
        ARTIFACT,CREATURE,ENCHANTMENT,EARTH,BLOODLINE,SORCERY,INSTANT;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ManaType manaType;

    private int manaCost;

    @Enumerated(EnumType.STRING) // Enum se almacena como texto en la base de datos
    @Column(nullable = false)
    private Type type;

    private String typeLine;
    private String expansionSymbol;
    private String skills;
    private String textEnvironment;
    private int force;
    private int resistance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public String getTextEnvironment() {
        return textEnvironment;
    }

    public void setTextEnvironment(String textEnvironment) {
        this.textEnvironment = textEnvironment;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExpansionSymbol() {
        return expansionSymbol;
    }

    public void setExpansionSymbol(String expansionSymbol) {
        this.expansionSymbol = expansionSymbol;
    }

    public String getTypeLine() {
        return typeLine;
    }

    public void setTypeLine(String typeLine) {
        this.typeLine = typeLine;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public ManaType getManaType() {
        return manaType;
    }

    public void setManaType(ManaType manaType) {
        this.manaType = manaType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
