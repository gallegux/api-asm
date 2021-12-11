package gallegux.docasm;




public class Par 
{
	
	public String nombre = null;
	public String descripcion = null;
	
	
	public Par(String linea) 
	{
		int p = linea.indexOf('-');
		
		if (p == -1) {
			nombre = linea.trim();
		}
		else {
			nombre = linea.substring(0, p).trim();
			
			try {
				descripcion = linea.substring(p+1).trim();
			}
			catch (Exception ex) {}
		}
		
		nombre = Registros.test(nombre);
	}
	
	
	public String toString() {
		return nombre + ": " + descripcion;
	}
	
	
	public String toHTML() 
	{
		String s = "<div class='elelista'><span class='key'>" + nombre + "</span>";
		
		if (descripcion != null) {
			s += " &nbsp; <span class='value'>" + descripcion + "</span>";
		}
		s += "</div>";
		
		return s;
	}
	

}
