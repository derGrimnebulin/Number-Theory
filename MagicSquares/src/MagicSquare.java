import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MagicSquare {
	private ArrayList<ArrayList<String>> square;
	private boolean isMagic = true;
	private int magicNumber = -1;
	private int size;
	
	
	public int getMagicNumber(){
		return magicNumber;
	}
	
	public boolean isMagic(){
		return isMagic;
	}
	
	public int size(){
		return size;
	}
	
	public ArrayList<String> get(int i){
		//
		return square.get(i);
	}
	
	
	private void testSum(int sum){
		//Auxiliary method which checks that the sum is equal to the magic number.
		//Should only be called by other methods within class MagicSquare.
		if (magicNumber == -1){
			magicNumber = sum;
		} else {
			if (sum != magicNumber){
				isMagic=false;
			}
		}
	}
	
	//Constructor.
	public MagicSquare(String path) throws IOException{
		square = fillSquare(path);
		size = square.size();
		
		//determine the magic square property.
		this.testRows();
		this.testColumns();
		this.testDiagonals();
		
		//non-magic squares don't have magic numbers.
		if (this.isMagic == false){
			magicNumber = -1;
		}
		
	}
	

	public void testRows(){
		//each row should sum to the magic number.
		for (int i = 0 ; i<this.size(); i++){
			int sum = 0;
			for (int j = 0 ; j<this.size(); j++){
				sum += Integer.valueOf(this.get(i).get(j));
			}
			this.testSum(sum);
			if (this.isMagic()==false){
				break;
			}
		}
	}	
	
	public void testColumns(){
		//pretty much the same method as checkRows().
		for (int i = 0 ; i < this.size() ; i++){
			int sum = 0;
			for (int j = 0 ; j < this.size() ; j++){
				sum += Integer.valueOf(this.get(j).get(i));
			}
			this.testSum(sum);
			if (this.isMagic()==false){
				break;
			}
		}
	}
	
		
	public void testDiagonals(){
		//must test both main diagonals
		int sum = 0;
		for (int i = 0 ; i < this.size() ; i++){
			sum += Integer.valueOf(this.get(i).get(i));
		}
		this.testSum(sum);
		
		//reset for the next diagonal.
		sum = 0;
		for (int i = (this.size()-1) ; i >= 0 ; i--){
			sum += Integer.valueOf(this.get((this.size()-1)-i).get(i));
		}
		this.testSum(sum);
	}	
	
	
  public ArrayList<ArrayList<String>> fillSquare(String path) throws IOException{
  	ArrayList<ArrayList<String>> square = new ArrayList<ArrayList<String>>();
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line = null;
		
		//retrieve each line from the file
		while ( ( line = reader.readLine() ) != null){
			
			//resolve each line into a list of strings
			ArrayList<String> row = new ArrayList<String>();
  		for (String s : line.split("\t")){
  			row.add(s);
  		}
  		
  		//check that each row doesn't contain only a non-printable character
  		if (line.length()>1){
  			//add it to the square
  			square.add(row);
  		}
		}
		reader.close();
		return square;
	}
	
	
  public static void main(String[] args) throws IOException{
  	String[] paths = {"./src/Luna.txt", "./src/Mercury.txt"};
  	
  	for (String path : paths){
  		MagicSquare square = new MagicSquare(path);
  		
  		if (square.isMagic==true){
  			System.out.println("Congradulations! "+path+" is indeed a Magic Square");
  			System.out.println("Its Magic Number is "+square.getMagicNumber()+"!");
  			
  		} else {
  			System.out.println("I'm terribly sorry, but "+path+" is not a magic square. Better luck next time. :(");
  			
  		}
  	}
  }
}

