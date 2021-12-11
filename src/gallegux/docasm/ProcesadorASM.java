package gallegux.docasm;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ProcesadorASM 
{
	
	private final static int MODO_IN = 1;
	private final static int MODO_OUT = 2;
	private final static int MODO_OTRO = 0;
	private final static int MODO_FICHERO = 3;
	
	
	static String nombre = null;
	static String valor = null;
	static String descripcion = null;
	static List<String> lentradas = new ArrayList<>();
	static List<String> lsalidas = new ArrayList<>();
	static String regsModificados = "";
	static int modo = MODO_OTRO;

	
	public static DocFichero crearDocFichero(String ficheroASM, String nombreFichero)
	throws IOException
	{
		Path p = Path.of(ficheroASM);
		List<String> lineas = Files.readAllLines(p);
		
		DocFichero docFichero = new DocFichero(nombreFichero);
		modo = MODO_FICHERO;
		
		
		for (int numLinea = 0; numLinea < lineas.size(); numLinea++) {
			String linea = lineas.get(numLinea);
			String linea2 = linea.stripTrailing().toLowerCase();		
			//System.out.println("[" + linea + "]");
			
			if (linea.isBlank()) {
				reset();
			}
			else if (linea2.equals(";; in")) {
				modo = MODO_IN;
			}
			else if (linea2.equals(";; out")) {
				modo = MODO_OUT;
			}
			else if (linea2.startsWith(";; mod:")) {
				regsModificados = linea.replace(";; mod:", "").strip();
			}
			else if (linea2.startsWith(";; dest:")) {
				regsModificados = linea.replace(";; dest:", "").strip();
			}
			else if (linea2.startsWith(";; mod -")) {
				regsModificados = linea.replace(";; mod -", "").strip();
			}
			else if (linea2.startsWith(";; dest:")) {
				regsModificados = linea.replace(";; dest -", "").strip();
			}
			else if (linea.startsWith(";;")) {
				if (modo == MODO_FICHERO) {
					if (descripcion == null) {
						docFichero.descripcion = linea.substring(2).strip();
					}
					else {
						docFichero.descripcion += "<br>" + linea.substring(2).strip();
					}
				}
				else if (modo == MODO_IN) {
					lentradas.add( linea.replace(";;", "") );
				}
				else if (modo == MODO_OUT) {
					lsalidas.add( linea.replace(";;", "") );
				}
				else {
					if (descripcion == null) {
						descripcion = linea.replace(";;", "").strip();
					}
					else {
						descripcion += "<br>" + linea.replace(";;", "").strip();
					}
				}
			}
			else if (linea.startsWith(";")) {
				// minicomentario ignorado
			}
			else if (linea.startsWith(".") && linea2.indexOf(':') > 0) {
				int dospuntos = linea.indexOf(':');
				nombre = linea.substring(1, dospuntos);
				
				DocVariable d = new DocVariable();
				d.nombre = nombre;
				d.descripcion = descripcion;
				
				docFichero.variables.add(d);
				reset();
			}
			else if (linea.startsWith("macro")) {
				int espacio = linea.indexOf(' ');
				nombre = linea.substring(6).strip();
				
				espacio = nombre.indexOf(' ');
				String argumentos = null;
				
				try {
					argumentos = nombre.substring(espacio).strip();
					nombre = nombre.substring(0, espacio).strip();
				}
				catch (Exception ex) {}
				
				DocMacro d = new DocMacro();
				d.nombre = nombre;
				d.argumentos = argumentos;
				d.descripcion = descripcion;
				d.entradas = crearListaPares(lentradas);
				d.salidas = crearListaPares(lsalidas);
				d.registrosModificados = regsModificados;
				
				docFichero.macros.add(d);
				reset();
			}
			else if (linea2.contains("equ")) {
				int equ = linea2.indexOf("equ");
				nombre = linea.substring(0, equ).trim();
				valor = linea.substring(equ+3).trim();
				
				DocConstante d = new DocConstante();
				d.nombre = nombre;
				d.descripcion = descripcion;
				d.valor = valor;
				
				docFichero.constantes.add(d);
				reset();
			}
			else if (linea.startsWith("; ")) {}
			else if (linea.startsWith("ds ")) {}
			else if (linea.startsWith("db ")) {}
			else if (linea.startsWith("dw ")) {}
			else {
				try {
					// funcion
					int dospuntos = linea.indexOf(':');
					nombre = linea.substring(0, dospuntos);
					
					DocFuncion d = new DocFuncion();
					d.nombre = nombre;
					d.descripcion = descripcion;
					d.entradas = crearListaPares(lentradas);
					d.salidas = crearListaPares(lsalidas);
					d.registrosModificados = regsModificados;
					
					docFichero.funciones.add(d);
					reset();
				}
				catch (Exception ex)  {}
			}
		}
		return docFichero;
	}
	
	
	
	private static void reset()
	{
		nombre = null;
		descripcion = null;
		valor = null;
		lentradas = new ArrayList<>();
		lsalidas = new ArrayList<>();
		regsModificados = null;
		modo = MODO_OTRO;
	}

	
	
	private static ArrayList<Par> crearListaPares(List<String> l)
	{
		ArrayList<Par> lp = new ArrayList<>();
		
		for (String s: l) {
			//System.out.println("PAR " + l);
			lp.add( new Par(s) );
		}
		
		return lp;
	}
	
	

}
