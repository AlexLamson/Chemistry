package chemistry;

import java.util.ArrayList;
import java.util.Arrays;

public class MiniCompound
{
	public static String[] gases = {"H2", "N2", "O2", "F2", "Cl2", "CO2", "SO2"};
	public static String[] ions = {"C2H3O2", "NH4", "AtO3", "AtO2", "BrO3", "BrO2", "CO3", "CO2", "ClO3", "ClO2", "CrO4", "CrO3", "CN", "Cr2O7", "H2PO4", "H2PO3", "HCO3", "HCO2", "HCrO4", "HCrO3", "HCr2O7", "HPO4", "HPO3", "HSiO3", "HSiO2", "HSO4", "HSO3", "OH", "AtO", "BrO", "ClO", "IO", "IO3", "IO2", "NO3", "NO2", "C2O4", "AtO4", "BrO4", "ClO4", "IO4", "MnO4", "PO4", "PO3", "SiO3", "SiO2", "SO4", "SO3"};
	public static int[] ionCharges = {-1,1,-1,-1,-1,-1,-2,-2,-1,-1,-2,-2,-1,-2,-1,-1,-1,-1,-1,-1,-1,-2,-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-1,-1,-1,-1,-1,-3,-3,-2,-2,-2,-2};
	public enum StateOfMatter { UNKNOWN, SOLID, LIQUID, GAS, AQUEOUS }		// not used yet
	
	public Element[] elements;
	public int quantity;
	public StateOfMatter state = StateOfMatter.UNKNOWN;
	
	public static void main(String[] args)
	{
		MiniCompound methane = new MiniCompound("CH");
		methane.balanceSubscripts();
		System.out.println(methane);
		
//		MiniCompound test = getEmipiricalFormula(new MiniCompound("CaOH").elements, new double[]{13.5, 10.8, 0.675});
//		System.out.println(test);
		
//		System.out.println("is CO2 an ion? "+new MiniCompound("CO2").isIon());
//		System.out.println(new MiniCompound("CO2"));
//		System.out.println("Charge of NH4 is "+new MiniCompound("NH4").getCharge());
//		System.out.println(new MiniCompound("C"));
		
//		for(int i = 0; i < ions.length; i++)
//			System.out.println(ions[i]+"\t"+ionCharges[i]);
	}
	
	public MiniCompound(Element[] elements, int quantity)
	{
		this.elements = elements;
		this.quantity = quantity;
//		System.out.println("new MiniCompound: "+toString());
	}
	
	public MiniCompound(String str)
	{
		if(str.startsWith("("))
		{
			int breakPos = 0;
			for(int i = str.length()-1; i > 0; i--)
			{
				char ch = str.charAt(i);
				if(ch == ')')
				{
					breakPos = i+1;
					break;
				}
			}
			
			String numString = str.substring(breakPos);
			quantity = Integer.parseInt(numString);
			str = str.substring(1, str.length()-numString.length()-1);
		}
		else
			quantity = 1;
		
		ArrayList<Integer> ePosList = new ArrayList<Integer>();
		
		for(int i = 0; i < str.length(); i++)
		{
			char ch = str.charAt(i);
			if(Character.isLetter(ch) && Character.isUpperCase(ch))
				ePosList.add(new Integer(i));	//here's the start of an Element
		}
		
		elements = new Element[ePosList.size()];
		for(int i = 0; i < ePosList.size(); i++)
		{
			if(i != ePosList.size()-1)
				elements[i] = new Element(str.substring(ePosList.get(i), ePosList.get(i+1)));
			else
				elements[i] = new Element(str.substring(ePosList.get(i), str.length()));
		}
	}
	
	/*
	set a to the positive, b to the negative
	if the current net is pos, increase b, else increase a
	if the net changes sign, but isn't 0, increase the other
	continue flipping until 0 is reached
	 */
	public void balanceSubscripts()
	{
		if(elements.length == 2)
		{
			int a = elements[0].getCharge(), b = elements[1].getCharge();
			if((a > 0 && b > 0) || (a < 0 && b < 0))
			{
				if(a == 4)
					a *= -1;
				else if(b == 4)
					b *= -1;
			}
			int pos = b, neg = a;
			int posCoeff = 1, negCoeff = 1;
			
			if(a > 0 && b < 0)
			{
				pos = a;
				neg = b;
			}
			
			int net = (posCoeff*pos) + (negCoeff*neg);
			
			while(net != 0)
			{
				while(net > 0)		// while net positive
				{
					negCoeff++;
					net = (posCoeff*pos) + (negCoeff*neg);
				}
				
				while(net < 0)		// while net negative
				{
					posCoeff++;
					net = (posCoeff*pos) + (negCoeff*neg);
				}
			}
			
			// set the quantities of the elements to the corresponding coefficients
			if(a == pos)
			{
				elements[0].quantity = posCoeff;
				elements[1].quantity = negCoeff;
			}
			else
			{
				elements[0].quantity = negCoeff;
				elements[1].quantity = posCoeff;
			}
		}
	}
	
	public int getCharge()
	{
		if(isIon())
		{
			int pos = 0;
			for(int i = 0; i < ions.length; i++)
			{
				if(this.equals(new MiniCompound(ions[i])))
				{
					pos = i;
					break;
				}
			}
			return ionCharges[pos];
//			System.err.println("getCharge - this is an ion, but there isn't a corresponding charge");
		}
		
		int overAllCharge = 0;
		for(int i = 0; i < elements.length; i++)
			overAllCharge += elements[i].getCharge();
		return overAllCharge;
	}
	
	public boolean isIon()
	{
		for(int i = 0; i < ions.length; i++)
			if(this.equals(new MiniCompound(ions[i])))
				return true;
		return false;
	}
	
	public boolean isGas()
	{
		boolean isGas = false;
		for(int i = 0; i < gases.length; i++)
		{
			if(this.equals(new MiniCompound(gases[i])))
			{
				isGas = true;
				break;
			}	
		}
		return isGas;
	}
	
	public static int round(double num)
	{
		double precision = 0.1;		//0 < precision < 0.5
		int rounded = 0;
		if((int)(num + precision) != (int)num)
			rounded = (int)(num + precision);
		else if((int)(num - precision) != (int)num)
			rounded = (int)num;
		return rounded;
	}
	
	public static MiniCompound getEmipiricalFormula(Element[] elements, double[] percents)
	{
		//divide each percentage by the atomic weight of each corresponding element
		double lowest = 1000;
		for(int i = 0; i < percents.length; i++)
		{
			percents[i] /= elements[i].getMass();
			if(percents[i] < lowest)
				lowest = percents[i];
		}
		
		System.out.println("lowest: "+lowest);
		
		//divide by the lowest value in the array (probably oxygen)
		for(int i = 0; i < percents.length; i++)
		{
			percents[i] /= lowest;
			System.out.println("value "+i+": "+percents[i]);
		}
		
		//determine the multiplier
		int multiplier = 1;
		for(int i = multiplier; true; i++)
		{
			boolean works = true;
			for(int j = 0; j < percents.length; j++)
			{
				double testNum = percents[j]*(double)i;
				if(round(testNum) == 0)
				{
					works = false;
					break;
				}
			}
			
			if(works)
			{
				multiplier = i;
				break;
			}
		}
		
		System.out.println("Multiplier: "+multiplier);
		
		for(int i = 0; i < percents.length; i++)
			elements[i].quantity = round(percents[i] * multiplier);
		
		return new MiniCompound(elements, 1);
		
	}
	
	public StateOfMatter getState()
	{
		for(int i = 0; i < gases.length; i++)
			if(new MiniCompound(gases[i]).equals(this))
				return StateOfMatter.GAS;
		return StateOfMatter.UNKNOWN;
	}
	
	public boolean isElement()
	{
		if(elements.length == 1)
			return true;
		return false;
	}
	
	public boolean isHydroCarbon()
	{
		if(elements.length == 2)
			if((elements[0].protons == 1 && elements[1].protons == 6) || 
					(elements[1].protons == 1 && elements[0].protons == 6))
				return true;
		return false;
	}
	
	public void setQuantities()
	{
		if(!isIon())		//if its not an ion, the charges must add to zero
		{
			int totalCharge = getCharge();
			
			for(int i = 0; i < elements.length; i++)
			{
				
			}
			
			
		}
	}
	
	public boolean equals(Object obj)
	{
		if(obj instanceof MiniCompound)
		{
			MiniCompound miniCompound = ((MiniCompound)obj);
			ArrayList<Element> copy = new ArrayList<Element>(miniCompound.elements.length);		//populate ArrayList
			copy.addAll(Arrays.asList(miniCompound.elements));
			
			boolean matchFound = true;
			for(int i = 0; i < elements.length; i++)
			{
				boolean subMatchFound = false;
				for(int j = 0; j < copy.size(); j++)
				{
					if(elements[i].equals(copy.get(j)))
					{
						subMatchFound = true;
						copy.remove(j);					//remove the first match you find to prevent double-matching
						break;
					}
				}
				if(!subMatchFound)
				{
					matchFound = false;
					break;
				}	
			}
			
			if(copy.size() > 0)		//if there are more elements in miniCompound than this
				matchFound = false;
			
			return matchFound;
		}
		return false;
	}
	
	public String toString()
	{
//		System.out.println("elements length: "+elements.length);
//		System.out.println("elements quantity: "+Element.properties(elements[0]));
		
		String out = "";
		for(int i = 0; i < elements.length; i++)
			out += elements[i].toString();
		
		if(quantity > 1)
			out = "("+out+")"+quantity;
		
		return out;
	}
}
