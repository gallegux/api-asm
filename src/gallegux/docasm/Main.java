package gallegux.docasm;



import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class Main 
{
	
	
	static List<String> pathFicheros = new ArrayList<>();
	static List<String> nombresFicheros = new ArrayList<>();
	static HashMap<String, String> ficheroHTML = new HashMap<>();
	
	
	public static void main(String...arg)
	{
		System.out.println("\nArgumentos: <directorio_base> <directorio1> [directorio2] [dir3] ... [dirN]\n");
		System.out.println("\tSe genera el fichero doc/api.html");
		
		try {
			getFicheros(arg);
			StringBuilder htmlNavegacion = new StringBuilder("<ul>\n");
			StringBuilder htmlContenido = new StringBuilder();
			int contadorFicheros = 0;
			
			for (int i = 0; i < pathFicheros.size(); i++) {
				String f = pathFicheros.get(i);
				System.out.println(f);
				DocFichero df = ProcesadorASM.crearDocFichero(f, nombresFicheros.get(i));
				contadorFicheros++;
				
				String enlace = "<li><a href='#fichero" + contadorFicheros + "'>" 
				+ nombresFicheros.get(i) + "</a></li>\n";
				htmlNavegacion.append(enlace);
				
				htmlContenido.append("<div id='fichero");
				htmlContenido.append(contadorFicheros);
				htmlContenido.append("'>");
				htmlContenido.append( df.toHTML() );
				htmlContenido.append("</div>\n");

			}
			htmlNavegacion.append("</ul>");
			
			Path p = Path.of("doc/api.html");
			try {
				Files.delete(p);
			}
			catch (Exception ex) {}
			Files.write(p, htmlCompleto(htmlNavegacion, htmlContenido).getBytes(), StandardOpenOption.CREATE);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	static void getFicheros(String...dirs) throws Exception
	{
		String dirBase = dirs[0];
		
		for (int d = 1; d < dirs.length; d++) {
			String dir = dirs[d];
			File fdir = new File(dir);
			File[] files = fdir.listFiles(new ASMFileFilter());
			
			if (files.length > 0) {
				for (File file: files) {
					pathFicheros.add(file.getPath());
					
					String nf = file.getPath().replace(dirBase + "\\", "");
					nombresFicheros.add(nf);
				}
			}
		}
	}
	
	
	
//	static String crearIdDiv(String id)
//	{
//		return fichero.replaceAll(":", "").replaceAll("/", "").replaceAll("\\\\", "");
//	}
	
	
	
	static String head()
	{
		return "<head>" +
				"<title>ASM API</title>" +
				"<link rel='stylesheet' type='text/css' href='estilos.css'>" +
				"</head>\n";
	}
	
	
	
	static String htmlCompleto(StringBuilder enlaces, StringBuilder contenido)
	{
		String html = "<html>\r\n"
				+ "\r\n"
				+ "<head>\r\n"
				+ "<title>ASM API</title>\r\n"
				+ "<link rel='stylesheet' type='text/css' href='estilos.css'>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "\r\n"
				+ "<h1>\r\n"
				+ "<center>ASM API</center>\r\n"
				+ "</h1>\r\n"
				+ "\r\n"
				+ "<div id=\"NAV\">\r\n"
				+ "{navegacion}\r\n"
				+ "</div>\r\n"
				+ "\r\n"
				+ "<div id=\"CONT\">\r\n"
				+ "{contenido}\r\n"
				+ "</div>\r\n"
				+ "\r\n"
				+ "</body>\r\n"
				+ "\r\n"
				+ "</html>";
				
		html = html.replace("{navegacion}", enlaces);
		html = html.replace("{contenido}", contenido);
				
		return html;
	}
	
	

}
