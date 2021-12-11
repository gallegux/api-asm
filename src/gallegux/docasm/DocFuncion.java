package gallegux.docasm;



import java.util.ArrayList;
import java.util.List;


/**

;; explicacion de la funcion
;; IN
;; registro: contador
;; OUT
;; registro: valor de vuelta
;; DEST: A BC DE HL
nombre_de_la_funcion::			(dos veces dos puntos!!)

 **/
public class DocFuncion 
implements IDoc
{
	
	private final static char RET = '\n';
	private final static int TIPO_DESC = 0;
	private final static int TIPO_IN = 1;
	private final static int TIPO_OUT = 2;
	private final static int TIPO_DEST = 3;
	private final static int TIPO_NOMPRE = 4;
	
	
	public boolean macro = false;
	public String nombre = null;
	public String descripcion = null;
	
	public List<Par> entradas = new ArrayList<>();
	public List<Par> salidas = new ArrayList<>();
	public String registrosModificados = null;

	private int tipoLinea = TIPO_DESC;
	private boolean completado = false;
	
	
	public void limpiar() {
		macro = false;
		nombre = null;
		descripcion = null;
		
		entradas = new ArrayList<>();
		salidas = new ArrayList<>();
		registrosModificados = null;
		
		tipoLinea = TIPO_DESC;
		completado = false;
	}
	
	

	
	
	
	public String toString() 
	{
		StringBuilder sentradas = new StringBuilder();
		StringBuilder ssalidas = new StringBuilder();
		
		if (entradas.size() > 0) {
			for (Par p: entradas) {
				sentradas.append(p);
				sentradas.append(RET);
			}
		}
		
		if (salidas.size() > 0) {
			for (Par p: salidas) {
				ssalidas.append(p);
				ssalidas.append(RET);
			}
		}
		
		return RET + nombre + RET + 
				descripcion + RET +
				"IN:" + RET + sentradas +
				"OUT:" + RET + ssalidas + 
				"Registros modificados: " + registrosModificados;
	}
	
	
	
	public String toHTML()
	{
		StringBuilder s = new StringBuilder();
		
		s.append( ToHTML.nombreElemento(nombre) );
		s.append( ToHTML.descripcionElemento(descripcion));
		s.append( ToHTML.lista("Entradas", entradas) );
		s.append( ToHTML.lista("Salidas", salidas) );
		s.append( ToHTML.registrosModificados(registrosModificados) );
		
		return s.toString();
	}
	
	
	
}
