package gallegux.docasm;

import java.util.List;

public class ToHTML 
{
	
	
	public static String lista(String nombreLista, List<Par> pp)
	{
		if (pp.size() == 0) return "";
		
		StringBuilder s = new StringBuilder();
		
		s.append("<div class='atVCFM'>");
		s.append(nombreLista);
		s.append("</div>");
		
		for (Par p: pp) {
			s.append(p.toHTML());
		}
		
		return s.toString();
	}
	
	
	
	public static String nombreElemento(String nombre)
	{
		return "<div class='eleFich'>" + nombre + "</div>";
	}
	
	
	
	public static String constante(String nombre, String valor)
	{
		return "<div class='eleFich'>" + nombre + " &nbsp; = <span class='valCte'>" + valor + "</span></div>\n";
	}
	
	
	
	public static String macro(String nombreMacro, String args)
	{
		StringBuilder s = new StringBuilder();
		
		s.append("<div class='eleFich'>");
		s.append(nombreMacro);

		if (args != null) {
			s.append(" &nbsp; <span class='valCte'>");
			s.append(args);
			s.append("</span>");
		}
		s.append("</div>\n");
		
		return s.toString();
	}
	
	
	
	public static String descripcionFichero(String desc)
	{
		if (desc == null) return "";
		
		StringBuilder s = new StringBuilder();
		
		s.append("<div class='descFich'>");
		s.append(desc);
		s.append("</div>\n");

		return s.toString();
	}
	
	
	public static String descripcionElemento(String desc)
	{
		if (desc == null) return "";
		
		StringBuilder s = new StringBuilder();
		
		s.append("<div class='descEleFich'>");
		s.append(desc.replace("<-", "&larr;"));
		s.append("</div>\n");

		return s.toString();
	}
	
	
	public static String registrosModificados(String regsMod)
	{
		if (regsMod == null) return "";
		
		StringBuilder s = new StringBuilder();
		
		s.append("<div class='atVCFM'>Registros modificados:<span class='listregs'> &nbsp; ");
		s.append(regsMod.toUpperCase());
		s.append("</span></div>\n");

		return s.toString();
	}
	
	
	public static String seccionFichero(String nombreSeccion)
	{
		return "<div class='seccionFich'>" + nombreSeccion + "</div>\n";
	}
	
	
	public static String nombreFichero(String nombre)
	{
		return "<div class='fich'>" + nombre + "</div>\n";
	}
	
	

}
