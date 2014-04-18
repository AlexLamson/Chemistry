package chemistry;

/*

To do:

use the methods created in the Test class
remove all methods that do the same thing with different arguments (do the same with the minimum amount of info)

prefer methods like Element.isDiatomic(new Element("He2").protons) over new Element("He2").isDiatomic()
 	this makes it more pleasant when stealing methods from the Element class to use in other classes

*/

public class Element implements Comparable<Element>
{
	public static final String[] eName = {"Vacuum", "Hydrogen", "Helium", "Lithium", "Beryllium", "Boron", "Carbon", "Nitrogen","Oxygen","Fluorine","Neon","Sodium","Magnesium","Aluminum","Silicon","Phosphorus","Sulfur","Chlorine","Argon","Potassium","Calcium","Scandium","Titanium","Vanadium","Chromium","Manganese","Iron","Cobalt","Nickel","Copper","Zinc","Gallium","Germanium","Arsenic", "Selenium", "Bromine","Krypton","Rubidium","Strontium","Yttrium","Zirconium","Niobium","Molybdenum","Technetium","Ruthenium","Rhodium","Palladium","Silver","Cadmium","Indium","Tin","Antimony","Tellurium","Iodine","Xenon","Cesium","Barium","Lanthanum","Cerium","Praseodymium","Neodymium","Promethium","Samarium","Europium","Gadolinium","Terbium","Dysprosium","Holmium","Erbium","Thulium","Ytterbium","Lutetium","Hafnium","Tantalum","Tungsten","Rhenium","Osmium","Iridium","Platinum","Gold","Mercury","Thallium","Lead","Bismuth","Polonium","Astatine","Radon","Francium","Radium","Actinium","Thorium","Protactinium","Uranium","Neptunium","Plutonium","Americium","Curium","Berkelium","Californium","Einsteinium","Fermium","Mendelevium","Nobelium","Lawrencium","Rutherfordium","Dubnium","Seaborgium","Bohrium","Hassium","Meitnerium","Darmstadtium","Roentgenium","Ununbium","Ununtrium","Ununquadium","Ununpentium","Ununhexium","Ununseptium","Ununoctium"};
		//						   J is void (error handling)
	public static final String[] eSymbol = {"J",  "H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb", "Te", "I", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Ac", "Th", "Pa", "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm", "Md", "No", "Lr", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds", "Rg", "Cn", "Uut", "Fl", "Uup", "Lv", "Uus", "Uuo"};
		//    Remember, mass and weight are the same thing
	public static final double[] eMass =   {0, 1.0079, 4.0026, 6.941, 9.0122, 10.811, 12.0107, 14.0067, 15.9994, 18.9984, 20.1797, 22.9897, 24.305, 26.9815, 28.0855, 30.9738, 32.065, 35.453, 39.948, 39.0983, 40.078, 44.9559, 47.867, 50.9415, 51.9961, 54.938, 55.845, 58.9332, 58.6934, 63.546, 65.39, 69.723, 72.64, 74.9216, 78.96, 79.904, 83.8, 85.4678, 87.62, 88.9059, 91.224, 92.9064, 95.94, 98, 101.07, 102.9055, 106.42, 107.8682, 112.411, 114.818, 118.71, 121.76, 127.6, 126.9045, 131.293, 132.9055, 137.327, 138.9055, 140.116, 140.9077, 144.24, 145, 150.36, 151.964, 157.25, 158.9253, 162.5, 164.9303, 167.259, 168.9342, 173.04, 174.967, 178.49, 180.9479, 183.84, 186.207, 190.23, 192.217, 195.078, 196.9665, 200.59, 204.3833, 207.2, 208.9804, 209, 210, 222, 223, 226, 227, 232.0381, 231.0359, 238.0289, 237, 244, 243, 247, 247, 251, 252, 257, 258, 259, 262, 261, 262, 266, 264, 277, 268, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public static final double[] eDensity = {0, 8.988E-5, 1.785E-4, 0.534, 1.85, 2.34, 2.267, 0.0012506, 0.001429, 0.001696, 8.999E-4, 0.971, 1.738, 0, 2.3296, 1.82, 2.067, 0.003214, 0.0017837, 0.862, 1.54, 2.989, 4.54, 6.11, 7.15, 7.44, 7.874, 8.86, 8.912, 8.933, 7.134, 5.907, 5.323, 5.776, 4.809, 3.122, 0.003733, 1.532, 2.64, 4.469, 6.506, 8.57, 10.22, 11.5, 12.37, 12.41, 12.02, 10.501, 8.69, 7.31, 7.287, 6.685, 6.232, 4.93, 0.005887, 0, 3.594, 6.145, 6.77, 6.773, 7.007, 7.26, 7.52, 5.243, 7.895, 8.229, 8.55, 8.795, 9.066, 9.321, 6.965, 9.84, 13.31, 16.654, 19.25, 21.02, 22.61, 22.65, 21.46, 19.282, 13.5336, 11.85, 11.342, 9.807, 9.32, 7.0, 0.00973, 1.87, 5.5, 10.07, 11.72, 15.37, 18.95, 20.25, 19.84, 13.69, 13.51, 14.79, 15.1, 13.5, 0, 0, 0, 9.84, 18.1, 39.0, 35.0, 37.0, 41.0, 35.0, 21.46, 19.282, 13.5336, 11.85, 11.342, 9.807, 9.32, 0, 0, };
	public static final int[] eGas =      {1, 7, 8, 9, 17};
	public static final int[] eDiatomic = {1, 7, 8, 9, 17, 35, 53};
	public static final int[] eActivitySeries = {1, 3, 9, 11, 12, 13, 17, 19, 20, 24, 26, 28, 29, 30, 35, 47, 50, 53, 78, 79, 80, 82};
	
	public static final int[][] periodicTable = {
	{1,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  2}, 					//yep, two hydrogens
	{3,   4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  5,  6,  7,  8,  9, 10}, 
	{11, 12,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 13, 14, 15, 16, 17, 18},
	{19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36},
	{37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54},
	{55, 56, 57, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86},
	{87, 88, 89, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118},
	{0,   0, 0, 0, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71},							//f block
	{0,   0, 0, 0, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103}};					//f block
	
	public int protons;		//number of protons
	public int quantity;	//number of atoms
	public int charge;		//charge of the element
	public boolean useSetCharge = false;	//if true, return the int charge when getting the charge; otherwise, use what the charge would be if it was unspecified
	
	public static void main(String[] args)
	{
//		System.out.println(new Element("Helium2"));
//		System.out.println(new Element("zinc").isHigherOnActivitySeries(new Element("iron")));
		
		System.out.println(new Element("He2{2-}"));
		
//		makeTable();
	}
	
	public Element(int protons, int quantity, int charge)
	{
		this.protons = protons;
		this.quantity = quantity;
		this.charge = charge;
	}
	
	public Element(String str)
	{
		str = str.trim();
		String letters = getLetters(str);
		protons = getProtons(letters);
		quantity = getQuantity(str);
		charge = getCharge(str, protons);
	}
	
	public String getLetters(String str)
	{
		int breakPos = 0;
		for(int i = 0; i < str.length(); i++)	//get the first letters in the string
		{
			char ch = str.charAt(i);
			if(!Character.isLetter(ch))
			{
				breakPos = i;
				break;
			}

			if(i == str.length()-1)
				breakPos = i+1;
		}
		
		//and return the letters found
		return str.substring(0, breakPos);
	}
	
	public int getProtons(String str)
	{
		for(int i = 0; i < eSymbol.length; i++)	//look for symbols that match the string
			if(str.equals(eSymbol[i]))
				return i;
		
		str = str.toLowerCase();
		for(int i = 0; i < eName.length; i++)	//look for names that match the string
			if(str.equals(eName[i].toLowerCase()))
				return i;
		
		return 0;
	}
	
	public int getQuantity(String str)
	{
		int lettersLength = getLetters(str).length();
		
		if(str.length() == lettersLength)		//if the string is too short for a quantity
			return 1;							//default to 1
		
		if(Character.isDigit(str.charAt(lettersLength)))	//if there are numbers after the letters
		{
			int breakPos = 0;
			for(int i = lettersLength; i < str.length(); i++)	//get all the numbers
			{
				char ch = str.charAt(i);
				if(!Character.isDigit(ch))
				{
					breakPos = i;
					break;
				}
				
				if(i == str.length()-1)
					breakPos = i+1;
			}
			
			//and return the numbers found
			return Integer.parseInt(str.substring(lettersLength, breakPos));
		}
		
		return 1;
	}
	
	public int getOrbitalNum()
	{
		int row = getRow(), col = getCol();
		
		if(col == 1 || col == 2)
			return 2;
		else if(col >= 13 && col <= 18)
			return 6;
		else if(col >= 3 && col <= 12)
			return 10;
		else if(row == 8 || row == 9)
			return 14;
		else
		{
			System.err.print("getOrbitalNum - Can't find a element with "+protons+" protons!");
			return 0;
		}
	}
	
	public char getOrbitalLetter()
	{
		int oNum = getOrbitalNum();
		if(oNum == 2)
			return 's';
		else if(oNum == 6)
			return 'p';
		else if(oNum == 10)
			return 'd';
		else if(oNum == 14)
			return 'f';
		
		System.err.println("getOrbitalLetter - Orbital number is out of bounds");
		return '?';
	}
	
	public boolean isCharged()
	{
		char orbital = getOrbitalLetter();
		if(orbital == 'd' || orbital == 'f')
			return false;
		return true;
	}
	
	public int getDefaultCharge()
	{
		int col = getCol();
		if(col == 0 || col == 1)
			return col;
		else if(col == 13)
				return 3;
		else if(col == 14)
			return 4;		//remember that this could be plus or minus 4
		else if(col == 15)
			return -3;
		else if(col == 16)
			return -2;
		else if(col == 17)
			return -1;
		return 0;
	}
	
	public int getCharge(String str, int protons)
	{
		int bracketPos = 0;	//find location of the bracket, if there is one
		for(int i = 0; i < str.length(); i++)
		{
			if(str.charAt(i) == '{')
			{
				bracketPos = i;
				break;
			}
		}
		
		int defaultCharge = getDefaultCharge();		//determine what the charge would be if it isn't set
		
		
		if(bracketPos != 0)	//if there are brackets (ie, charge is given)
		{
			int charge = 0;
			if(Character.isDigit(str.charAt(bracketPos+1)))	//if there are numbers
			{
				int breakPos = 0;
				for(int i = bracketPos+1; i < str.length(); i++)		//get all of the numbers
				{
					char ch = str.charAt(i);
					if(!Character.isDigit(ch))
					{
						breakPos = i;
						break;
					}
				}
				
				//set charge to numbers found
				charge = Integer.parseInt(str.substring(bracketPos+1, breakPos));
			}
			if(str.charAt(str.length()-2) == '-')	//if a negative sign is found
				charge *= -1;						//make charge negative
			
			useSetCharge  = (charge != defaultCharge);
			
			return charge;
		}
		else
		{
			if(!isCharged())
			{
				System.err.println("getCharge - This element's charge is weird!");
				return 0;
			}
			
			useSetCharge = false;
			
			return defaultCharge;
		}
	}
	
	public int getCharge()
	{
		if(useSetCharge)
			return charge;
		return getDefaultCharge();
	}
	
	public String getName()
	{
		return eName[protons];
	}
	
	public double getMass()
	{
		return eMass[protons];
	}
	
	public String getSymbol()
	{
		return eSymbol[protons];
	}
	
	public double getDensity()
	{
		return eDensity[protons];
	}
	
	public boolean isGas()
	{
		for(int i = 0; i < eGas.length; i++)
			if(eGas[i] == protons)
				return true;
		return false;
	}
	
	public boolean isDiatomic()
	{
		for(int i = 0; i < eDiatomic.length; i++)
			if(eDiatomic[i] == protons)
				return true;
		return false;
	}
	
	public int[] getPos()
	{
		if(protons == 1)
			return new int[] {0, 0};
		
		int thisRow = 0, thisCol = 0;
		boolean breakOut = false;
		for(int row = 0; row < periodicTable.length; row++)
		{
			for(int col = 0; col < periodicTable[0].length; col++)
			{
				if(periodicTable[row][col] == protons)
				{
					thisRow = row;
					thisCol = col;
					breakOut = true;
					break;
				}
			}
			if(breakOut)
				break;
		}
		return new int[] {thisRow, thisCol};
	}
	
	public int getRow()
	{
		return getPos()[0]+1;
	}
	
	public int getCol()
	{
		return getPos()[1]+1;
	}
	
	public boolean isMetal()
	{
		int row = getRow(), col = getCol();
		if(getOrbitalLetter() == 'p' && col <= 6 && row >= col-11)
			return true;
		return false;
	}
	
	public boolean isHigherOnActivitySeries(Element element)
	{
		if((this.isMetal() && !element.isMetal()) || (!this.isMetal() && element.isMetal()))
			System.err.println("isHigherOnActivitySeries - One element is a metal and the other isn't!");
		
		if(this.equals(element))
			System.err.println("isHigherOnActivitySeries - Both elements are the same!\t"+this+"\t"+element);
		
		boolean foundThisFirst = false;
		for(int i = 0; i < eActivitySeries.length; i++)
		{
			if(eActivitySeries[i] == protons)
			{
				foundThisFirst = true;
				break;
			}
			
			if(eActivitySeries[i] == element.protons)
				break;
		}
		return foundThisFirst;
	}
	
	public static void makeTable()
	{
		for(int row = 0; row < periodicTable.length; row++)
		{
			for(int col = 0; col < periodicTable[0].length; col++)
			{
				String symb = eSymbol[periodicTable[row][col]];
				
				if(!symb.equals(eSymbol[0]))
				{
					System.out.print(symb);
					for(int i = 0; i < 3-symb.length(); i++)
						System.out.print(" ");
				}
				else
					System.out.print("   ");
			}
			System.out.println();
			if(row == 6)
				System.out.println();
		}
	}
	
	public String toString()
	{
//		return properties(this);
		
		String quantityString = "";
		if(quantity != 1)
			quantityString = quantity+"";
		
		String chargeString = "";
		if(useSetCharge)
			chargeString = "{"+charge+"}";
		
		return getSymbol()+quantityString+chargeString;
	}
	
	public String properties()
	{
		String gasString = "";
		if(isGas())
			gasString = " gas";
		return protons+"  "+getSymbol()+"  "+getName()+gasString+"  Mass: "+getMass()+"  Density: "+getDensity();
	}
	
	public int compareTo(Element element)
	{
		int myCharge = this.getCharge(getName(), protons);
		int otherCharge = element.getCharge();
		
		if (myCharge == otherCharge)
            return 0;
        else if (myCharge > otherCharge)
            return 1;
        else
            return -1;
	}
	
	public boolean equals(Object obj)
	{
		if(obj instanceof Element)
			if(protons == ((Element)obj).protons && quantity == ((Element)obj).quantity)
				return true;
		return false;
	}
}
