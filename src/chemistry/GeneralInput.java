package chemistry;

import java.util.Scanner;

public class GeneralInput
{
	public static void main(String[] args)
	{
		while (true)
		{
		    Scanner keyIn = new Scanner(System.in);
		    String c = "J";
		    
	        c = keyIn.nextLine();
		    if (c.equals("exit") || c.equals("quit"))
		        break;
	        
	        if(c.contains("->"))		//is reaction (Reaction)
	        {
	        	Reaction reaction = new Reaction(c);
	        	System.out.println(reaction);
	        }
	        else if(c.contains("+"))	//is reaction without products (CompoundSeries)
	        {
	        	CompoundSeries compoundSeries = new CompoundSeries(c);
	        	System.out.println(compoundSeries);
	        }
	        else						//is compound (Compound)
	        {
	        	Compound compound = new Compound(c);
	        	
	        	if(compound.isElement())
	        	{
	        		Element element = new Element(c);
	        		System.out.println(element.properties());
	        	}
	        	else
	        	{
	        		System.out.println(compound);
	        	}
	        }
		}
	}
}
