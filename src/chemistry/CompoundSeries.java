package chemistry;

/*
To do:

- CompoundSeries class should have a method to determine the products

 */

//example: H2O + CO2
public class CompoundSeries
{
	Compound[] compounds;
	
	public static void main(String[] args)
	{
//		CompoundSeries test = new CompoundSeries("H2O + CO2");
//		System.out.println(new CompoundSeries("H2O+CO2"));
//		System.out.println(new CompoundSeries("C(OH) + CO2"));
		System.out.println(new CompoundSeries("Calcium + Phosphorus"));
	}
	
	public CompoundSeries(Compound[] compounds)
	{
		this.compounds = compounds;
	}
	
	public CompoundSeries(Compound compound1, Compound compound2)
	{
		this(new Compound[] {compound1, compound2});
	}
	
	public CompoundSeries(String str)
	{
		try
		{
			if(str.replaceAll(" ?\\+ ?", "").equals(str))
				compounds = new Compound[] {new Compound(str)};
			else
			{
				String[] strArray = str.split(" ?\\+ ?");		// matches + with or without spaces
				
				compounds = new Compound[strArray.length];
				for(int i = 0; i < strArray.length; i++)
					compounds[i] = new Compound(strArray[i]);
				
//				for(int i = 0; i < compounds.length; i++)
//					System.out.print(compounds[i]);
				
			}
			System.out.println("new CompoundSeries: "+str);
		}
		catch(Exception e)
		{
			System.err.println("failed to create CompoundSeries: "+str);
		}
		
	}
	
	public String toString()
	{
		String out = "";
		for(int i = 0; i < compounds.length; i++)
			out += " + "+compounds[i];
		out = out.substring(3);
		return out;
	}
}
	