package gallegux.docasm;

import java.util.ArrayList;

/**

;; explicacion de la macro, que hace...
;; IN
;; ....
;; MOD: 
macro nombre_macro 

 **/
public class DocMacro 
implements IDoc 
{
	
	
	public String nombre = "";
	public String argumentos = null;
	public String descripcion = null;
	public ArrayList<Par> entradas = new ArrayList<>();
	public ArrayList<Par> salidas = new ArrayList<>();
	public String registrosModificados = null;
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public String toHTML() 
	{
		StringBuilder s = new StringBuilder();
		
		s.append( ToHTML.macro(nombre, argumentos) );
		s.append( ToHTML.descripcionElemento(descripcion));
		s.append( ToHTML.lista("Par&aacute;metros", entradas) );
		s.append( ToHTML.lista("Salidas", entradas) );
		s.append( ToHTML.registrosModificados(registrosModificados) );
		
		return s.toString();
	}

	
	
}
