package graphicalBattleShip;

import javax.swing.JButton;

public class GridButtons extends JButton {
	
	private String type;
	private String player;
	private String displayValue;
	private boolean isCalled;
	
	public GridButtons(){
		super();
		type = null;
		player = null;
		displayValue = " ";
		isCalled = false;		
	}
	
	public void setType(String type){
		if((type.equals("S-P1"))||(type.equals("S-P2"))){
			this.type = "Ship";
		}
		if((type.equals("G-P1"))||(type.equals("G-P2"))){
			this.type = "Grenade";
		}
	}
	public String getType() {
		return type;		
	}
	public void setPlayer(String player){
		if((player.equals("S-P1"))||(player.equals("G-P1"))){
			this.player = "Player1";
		}
		if((player.equals("S-P2"))||(player.equals("G-P2"))){
			this.player = "Player2";
		}
	}
	public String getPlayer() {
		return player;		
	}
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;		
	}
	public String getDisplayValue() {
		return this.displayValue;
	}
	public void setIsCalled(boolean isCalled) {
		this.isCalled = isCalled;		
	}
	public boolean getIsCalled() {
		return this.isCalled;		
	}

}
