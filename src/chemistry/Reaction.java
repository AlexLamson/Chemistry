package chemistry;

/*
To do:

- In the MiniCompound class, use enum for the state of matter
	- add constructors to be able to take a state
	- make a method to catch the "(aq)" etc. on the end of the string

- CompoundSeries class should have a method to determine the products

- In the Reaction class, have a method check which elements to switch in the replacement reaction

 */

//example: CH4 + 2O2 -> CO2 + 2H2O
public class Reaction
{
	public enum ReactionType { SYNTHESIS, SINGLEREPLACEMENT, DOUBLEREPLACEMENT, DECOMPOSITION, UNKNOWN }
	
	public CompoundSeries reactants;
	public CompoundSeries products;
	
	public static void main(String[] args)
	{
		Reaction test = new Reaction("CH4 + 2O2 -> CO2 + 2H2O");
		System.out.println(test.toString());
	}
	
	public Reaction(CompoundSeries reactants, CompoundSeries products)
	{
		this.reactants = reactants;
		this.products = products;
//		System.out.println("new Reaction: "+toString());
	}
	
	public Reaction(CompoundSeries reactants)
	{
		this.reactants = reactants;
		this.products = getProducts(reactants);
	}
	
	public Reaction(String str)
	{
		this(getReactants(str), getProducts(str));
	}
	
	public static CompoundSeries getReactants(String str)
	{
		int arrowPos = str.indexOf("->");
		CompoundSeries reactants = new CompoundSeries(str.substring(0,arrowPos).trim());
		return reactants;
	}
	
	public static CompoundSeries getProducts(String str)
	{
		int arrowPos = str.indexOf("->");
		CompoundSeries products = new CompoundSeries(str.substring(arrowPos+2).trim());
		return products;
	}
	
	public static CompoundSeries getProducts(CompoundSeries reactants)
	{
		//https://www.youtube.com/watch?v=mOZYjugbHnM
		
		return null;	//placeholder until you get here
	}
	
	public ReactionType getReactionType()
	{
		if(reactants.compounds.length == 2)
		{
			if(reactants.compounds[0].isElement() && reactants.compounds[1].isElement())		//2 elements
				return ReactionType.SYNTHESIS;
			else if(reactants.compounds[0].isElement() && !reactants.compounds[1].isElement() || 
					!reactants.compounds[0].isElement() && reactants.compounds[1].isElement())	//element + compound
				return ReactionType.SINGLEREPLACEMENT;
			else if(!reactants.compounds[0].isElement() && !reactants.compounds[1].isElement())	//2 compounds
				return ReactionType.DOUBLEREPLACEMENT;
		}
		else if(reactants.compounds.length == 1)
		{
			if(!reactants.compounds[0].isElement())	//1 compound
				return ReactionType.DECOMPOSITION;
		}
		return ReactionType.UNKNOWN;
	}
	
	public void balance()
	{
		//http://balance.vacau.com/balance.htm#links
	}
	
	// this method would set the quantity of each Compound in the 2 CompoundSeries
	public void redox()
	{
		
	}
	
	public String toString()
	{
		return reactants+" -> "+products;
	}
}
