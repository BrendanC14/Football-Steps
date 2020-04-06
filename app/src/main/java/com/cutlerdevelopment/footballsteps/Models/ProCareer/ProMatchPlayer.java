package com.cutlerdevelopment.footballsteps.Models.ProCareer;

/**
 * This object is used in a match. The OfflinePlayers are converted into a copy of the ProMatchPlayer to be used to play.
 */
public class ProMatchPlayer {

    public ProMatchPlayer(ProAIPlayer player) {

        this.position = player.getPosition();
        this.currTeamID = player.getCurrTeamID();
        this.firstName = player.getFirstName();
        this.surname = player.getSurname();
    }

    public ProMatchPlayer(UserPlayer player) {

        this.position = player.getPosition();
        this.currTeamID = player.getCurrTeamID();
        this.firstName = player.getFirstName();
        this.surname = player.getSurname();
    }

    private int position;
    public int getPosition() { return this.position; }

    private int currTeamID;
    public int getCurrTeamID() { return this.currTeamID; }

    private String firstName;
    public String getFirstName() { return this.firstName; }

    private String surname;
    public String getSurname() { return this.surname; }

    private int saves;
    public int getSaves() { return this.saves; }
    public void addSave() { this.saves++; }

    private int tackles;
    public int getTackles() { return this.tackles; }
    public void addTackle() { this.tackles++; }

    private int blocks;
    public int getBlocks() { return this.blocks; }
    public void addBlock() { this.blocks++; }

    private int passes;
    public int getPasses() { return this.passes; }
    public void addPass() { this.passes++; }

    private int shots;
    public int getShots() { return this.shots; }
    public void addShot() { this.shots++; }

    private int goals;
    public int getGoals() { return this.goals; }
    public void addGoal() { this.goals++; }
}
