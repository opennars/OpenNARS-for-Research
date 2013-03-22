package nars.main_nogui;

public class CommandLineParameters {

	public static void decode(String[] args, ReasonerBatch r) {
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if( "--silence".equals(arg) ) {
				arg = args[++i];
				r.getSilenceValue().set( Integer.parseInt(arg) );
			}
		}
	}
}
