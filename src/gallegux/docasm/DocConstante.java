package gallegux.docasm;


public class DocConstante 
implements IDoc
{
	
	public String descripcion = null;
	public String nombre = null;
	public String valor = null;
	
	
	public String toString() {
		return nombre + " = " + descripcion + " (" + valor + ")";
	}
	
	
	public String toHTML() 
	{
		StringBuilder s = new StringBuilder();
		
		s.append( ToHTML.constante(nombre, valor) );
		s.append( ToHTML.descripcionElemento(descripcion));
		
		return s.toString();
	}

}
