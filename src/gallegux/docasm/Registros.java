package gallegux.docasm;



public class Registros 
{
	
	private static String[] REGISTROS = {"a", 
			"bc", "b", "c", 
			"de", "d", "e", 
			"hl", "h", "l", 
			"sp",
			"ix", "ixh", "ixl",
			"iy", "iyh", "iyl",
			};
	
	
	public static String test(String s)
	{
		s = s.strip();
		
		for (String r: REGISTROS) {
			if (r.equals(s)) {
				return s.toUpperCase();
			}
		}
		
		return s;
	}
	

}
