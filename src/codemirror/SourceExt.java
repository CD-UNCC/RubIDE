package codemirror;

public class SourceExt {
	public static String sourceExtension(String ext) {
		switch (ext) {
			case "java":
				return "x-java";
			case "cs":
				return "x-csharp";
			case "rb":
				return "x-ruby";
		}
		
		return "x-ruby";
	}
	
	public static String codemirrorMode(String ext) {
		switch (ext) {
			case "java":
			case "cs":
			case "c++":
			case "c":
				return "clike";
			case "rb":
				return "ruby";
		}
		
		return "ruby";
	}
}
