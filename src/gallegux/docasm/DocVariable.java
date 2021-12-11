package gallegux.docasm;



/**
 
;; explicacion de la variable
nombre_variable:				(solo 1 vez dos puntos)
 
 **/
public class DocVariable implements IDoc
{
	
	public String nombre = "";
	public String descripcion = "";
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toHTML() 
	{
		StringBuilder s = new StringBuilder();
		
		s.append( ToHTML.nombreElemento(nombre) );
		s.append( ToHTML.descripcionElemento(descripcion));
		
		return s.toString();
	}

}
