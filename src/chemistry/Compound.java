package chemistry;

import java.util.ArrayList;

//example: 2Co2(SO4)5
public class Compound
{
	public MiniCompound[] miniCompounds;
	public int quantity;

	public static void main(String[] args)
	{
//		System.out.println(new Compound("2Co2(SO4)2(NH4)3"));
//		System.out.println(new Compound("2Mn2(SO4)5"));
		System.out.println(new Compound("2Co2(SO4)"));
		System.out.println(new Compound("2CO2(SO4)2(Ne4)3"));
//		System.out.println(new Compound("C"));
	}

	public Compound(MiniCompound[] miniCompounds, int quantity)
	{
		this.miniCompounds = miniCompounds;
		this.quantity = quantity;
//		System.out.println("new Compound: "+toString());
	}

	public Compound(Element[] elements, int quantity)
	{
		this(new MiniCompound[] { new MiniCompound(elements, 1) }, quantity);
	}
	
	public Compound(String str)
	{
		this(getMiniCompounds(str), getQuantity(str));
	}
	
	public static MiniCompound[] getMiniCompounds(String str)
	{
		MiniCompound[] miniCompounds;
		
		String[] firstNum = getFirstNums(str);
		str = firstNum[0];
		
		String[] strArray = str.split("\\(");
		ArrayList<String> testArray = new ArrayList<String>();

		for (int i = 0; i < strArray.length; i++)
		{
			int end = strArray[i].indexOf(')');

			if (!(end == -1))
			{
				String[] parenthesis = strArray[i].split("\\)");

				if (end == strArray[i].length() - 1) // if "123)" in parentheses
				{
					testArray.add(parenthesis[0]);
				} else
				// if "12)34"
				{
					if (parenthesis.length >= 2)
					{
						String[] miniFirstNum = getFirstNums(parenthesis[1]);
						
						boolean isNumberNotChemical = true;
						for(int j = 0; j < miniFirstNum[0].length()-1; j++)
						{
							if(!Character.isDigit(miniFirstNum[0].charAt(i)))
							{
								isNumberNotChemical = false;
								break;
							}
						}
						
						if(isNumberNotChemical)
						{
							parenthesis[0] = "("+parenthesis[0]+")"+Integer.parseInt(miniFirstNum[0]);
						}
						else
						{
							parenthesis[1] = miniFirstNum[0];
							System.out.println(parenthesis[1]+" = test[1]");
							if (!parenthesis[1].equals(""))
								testArray.add(parenthesis[1]);
						}
					}

					testArray.add(parenthesis[0]);
				}
			} else
			// if "123" (no parentheses)
			{
				if (!strArray[i].equals(""))
					testArray.add(strArray[i]);
			}
		}

		// fill array out with the values of the testArray (excluding null and
		// "" elements)
		String[] out = new String[testArray.size()];
		for (int i = 0; i < testArray.size(); i++)
			out[i] = testArray.get(i);

		miniCompounds = new MiniCompound[out.length];
		for (int i = out.length-1; i >= 0; i--)
			miniCompounds[i] = new MiniCompound(out[out.length-1-i]);
		
		return miniCompounds;
	}
	
	public static int getQuantity(String str)
	{
		int quantity = 1;
		String[] firstNum = getFirstNums(str);
		quantity = Integer.parseInt(firstNum[1]);
		return quantity;
	}
	
	public static String[] getFirstNums(String str)
	{
		if(str.length() == 1)
			if(Character.isDigit(str.charAt(0)))
				return new String[] { str, str.charAt(0) + "" };
		
		// if there is a specified quantity, set quantity to that number
		int quantity = 1;
		if (Character.isDigit(str.charAt(0)))
		{
			int end = 0;
			for (int i = 0; i < str.length(); i++)
			{
				if (!Character.isDigit(str.charAt(i)))
				{
					end = i;
					break;
				}
			}
			quantity = Integer.parseInt(str.substring(0, end));
			str = str.substring(end);
		}
		
//		System.out.println("getFirstNums: " + str+" -> "+quantity);
		
		String[] out = { str, quantity + "" };
		return out;
	}
	
	public boolean isElement()
	{
		if(miniCompounds.length == 1 && miniCompounds[0].isElement())
			return true;
		return false;
	}
	
	public void setQuantities()
	{
		if(isElement())
		{
			int protons = miniCompounds[0].elements[0].protons;
			
			for(int i = 0; i < Element.eDiatomic.length; i++)
			{
				if(protons == Element.eDiatomic[i])
				{
					miniCompounds[0].elements[0].quantity = 2;
				}
			}
		}
		else if(miniCompounds.length == 1)
		{
			
		}
	}
	
	public String toString()
	{
		String out = "";
		if (quantity > 1)
			out += quantity;
		if (miniCompounds != null && miniCompounds.length > 0)
		{
			for (int i = miniCompounds.length - 1; i >= 0; i--)
				out += miniCompounds[i];
		}
		else
		{
			if (miniCompounds == null)
				System.err.println("miniCompounds is null");
			else if (miniCompounds.length <= 0)
				System.err.println("miniCompounds is empty");
			out = "An error occurred";
		}
		
		return out;
	}
}
