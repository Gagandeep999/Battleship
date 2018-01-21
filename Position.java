
public class Position {
	
	private String type;
	private String owner;
	private String displayValue;
	private boolean isCalled;
	
	//default constructor
	public Position(){
		type = "null";
		displayValue = "_";
		isCalled = false;
		owner = "none";
	}
	public void setType(String p){
		if((p=="S")||(p=="s"))
			type="Ship";
		if((p=="G")||(p=="g"))
			type="Grenade";
	}
	public String getType(){
		return type;
	}
	public void setOwner(String p){
		if((p=="S")||(p=="G"))
			owner="User";
		if((p=="s")||(p=="g"))
			owner="Computer";
	}
	public String getOwner(){
		return owner;
	}
	public void setDisplayValue(String p){
		this.displayValue = p;
	}
	public String getDisplayValue(){
		return displayValue;
	}
	public void setIsCalled(boolean p){
		if(p==true)
			isCalled = true;
		else
			isCalled = false;			
	}
	public boolean getIsCalled(){
		return isCalled;
	}
	public boolean chkCoordinate(String position){	
		int a = position.charAt(0)-65;
		int b = position.charAt(1)-49;
		if((a>=0)&&(a<8)&&(b>=0)&&(b<8))
		{
			return true;
		}
		else
		{
			System.out.println("sorry, coordinates outside the grid. try again.");
			return false;
		}
	}
	public int row(String position){
		int a = position.charAt(0)-65;
		return a;
	}
	public int column(String position){
		int b = position.charAt(1) - 49;
		return b;
	}
	public void showBoard(Position[][] p){
		for(int i=0;i<=7;i++){
			System.out.println();
			System.out.print("\t\t");
			for(int j=0;j<=7;j++){
				System.out.print(p[i][j].displayValue+"  ");
			}
		}
	}
}
