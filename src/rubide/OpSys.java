package rubide;

public final class OpSys {
	private static String OS = null;
	private static String JavaVersion = null;
	private static String JavaClassPath = null;
	
	public static String getOS(){
		if(OS == null){
			OS = System.getProperty("os.name");
		}
		return OS;
	}
	public static String getJavaVersion(){
		if(JavaVersion == null){
			JavaVersion = System.getProperty("java.version");
		}
		return JavaVersion;
	}
	public static String getJavaClassPath(){
		if(JavaClassPath == null){
			JavaClassPath = System.getProperty("java.class.path");
		}
		return JavaClassPath;
	}
	public static boolean isWindows(){
		return getOS().toLowerCase().contains("windows");
	}
	public static boolean isUnix(){
		return getOS().toLowerCase().contains("unix");
	}
	public static boolean isMac(){
		return getOS().toLowerCase().contains("os x");
	}
	public static boolean isLinux(){
		return getOS().toLowerCase().contains("linux");
	}

}
