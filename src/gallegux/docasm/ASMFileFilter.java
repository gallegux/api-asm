package gallegux.docasm;


import java.io.File;
import java.io.FilenameFilter;


public class ASMFileFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		return (name.endsWith(".asm"));
	}

}
