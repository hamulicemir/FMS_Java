package FMS.tests;

//import bwi.prog.utils.TextIO;

import java.util.Locale;


public class FMSRunner {

	public static void main(String[] args) {
		
		Locale.setDefault(new Locale("US", "en"));
		//TextIO.putf("[FMSRunner] calling FMS.Main.main: ");
		FMS.app.Main.main(args);

	}

}
