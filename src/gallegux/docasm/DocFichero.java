package gallegux.docasm;

import java.util.ArrayList;
import java.util.List;

/**
 * La documentacion empieza despues de la primera linea en blanco
 *
 */
public class DocFichero 
implements IDoc 
{

	
	public String nombre = null;
	public String descripcion = null;
	public List<DocConstante> constantes = new ArrayList<>();
	public List<DocVariable> variables = new ArrayList<>();
	public List<DocFuncion> funciones = new ArrayList<>();
	public List<DocMacro> macros = new ArrayList<>();

	
	public DocFichero(String _nombreFichero) {
		nombre = _nombreFichero;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public String toHTML() 
	{
		StringBuilder s = new StringBuilder();
		
		s.append( ToHTML.nombreFichero(nombre) );
		s.append( ToHTML.descripcionFichero(descripcion) );

		if (constantes.size() > 0) {
			s.append( ToHTML.seccionFichero("CONSTANTES") );
			for (DocConstante p: constantes) s.append(p.toHTML());
		}
		
		if (variables.size() > 0) {
			s.append( ToHTML.seccionFichero("VARIABLES") );
			for (DocVariable p: variables) s.append(p.toHTML());
		}
		
		if (funciones.size() > 0) {
			s.append( ToHTML.seccionFichero("FUNCIONES") );
			for (DocFuncion p: funciones) s.append(p.toHTML());
		}
		
		if (macros.size() > 0) {
			s.append( ToHTML.seccionFichero("MACROS") );
			for (DocMacro p: macros) s.append(p.toHTML());
		}
		
		return s.toString();
	}

	
	
}
