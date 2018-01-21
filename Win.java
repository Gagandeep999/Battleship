
public class Win {
	
	Position obj = new Position();
	
	public void ifWin(Position[][] p){
		for(int i=0;i<=7;i++){
			for(int j=0;j<=7;j++){
				if(!p[i][j].getIsCalled()){
					if(p[i][j].getOwner()=="Computer"){
						if(p[i][j].getType() == "Ship"){
							p[i][j].setDisplayValue("S");
						}
						else if(p[i][j].getType() == "Grenade"){
							p[i][j].setDisplayValue("G");
						}
					}
						if(p[i][j].getOwner()=="User"){
						if(p[i][j].getType() == "Ship"){
							p[i][j].setDisplayValue("s");
						}
						else if(p[i][j].getType() == "Grenade"){
							p[i][j].setDisplayValue("g");
						}
					}
				}
						if(p[i][j].getOwner()=="none"){
							p[i][j].setDisplayValue(" ");
						}
			}
		}
		//System.out.println();
		obj.showBoard(p);	
	}

}
