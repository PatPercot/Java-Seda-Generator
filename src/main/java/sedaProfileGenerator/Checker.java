package sedaProfileGenerator;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public final class Checker {
	private static final String DB_URL_PATTERN = "jdbc:postgresql://.*";
	private static final String BOOLEAN_TRUE = "true";
	private static final String BOOLEAN_FALSE = "false";
	
	private static final String ERROR_BOOLEAN = "true or false attendu";
	
	private Checker() {

	}

	public static void checkString(String arg) {
		if (StringUtils.isEmpty(arg)) {
			System.err.println("Un paramètre est nul ou vide.");
			throw new IllegalArgumentException("Un paramètre est nul ou vide.");
		}
	}

	public static void checkFile(String filename) {
		checkString(filename);
		File file = new File(filename);
		if (!file.exists()) {
			System.err.println("Le fichier " + filename + " n'existe pas.");
			throw new IllegalArgumentException("Le fichier " + filename + " n'existe pas.");
		}
		if (!file.canRead()) {
			System.err.println("Le fichier " + filename + " est illisible.");
			throw new IllegalArgumentException("Le fichier " + filename + " est illisible.");
		}
	}

	public static void checkFolder(String filename) {
		checkString(filename);
		File file = new File(filename);
		if (!file.exists()) {
			System.err.println(filename + " n'existe pas.");
			throw new IllegalArgumentException(filename + " n'existe pas.");
		}
		if (!file.isDirectory()) {
			System.err.println(filename + " n'est pas un dossier.");
			throw new IllegalArgumentException(filename + " n'est pas un dossier.");
		}
	}

	public static void checkDbUrl(String url) {
		checkString(url);
		Pattern p = Pattern.compile(DB_URL_PATTERN);
		Matcher m = p.matcher(url);
		if (!m.matches()) {
			System.err.println(url + " n'est pas de la forme : " + DB_URL_PATTERN);
			throw new IllegalArgumentException(url + " n'est pas de la forme : " + DB_URL_PATTERN);
		}
	}

	public static void checkParentFolder(String filename) {
		checkString(filename);
		File file = new File(filename);
		File parentFile = file.getParentFile();
		if (parentFile != null) {
			checkFolder(parentFile.getAbsolutePath());
		} // Sinon le fichier sera créé à la racine du lancement, donc le fichier pourra être créé.
	}
	
	public static void checkBoolean(String booleanTest) {
		
		if(!(StringUtils.equals(BOOLEAN_FALSE, booleanTest) || StringUtils.equals(BOOLEAN_TRUE, booleanTest))) {
			System.err.println(ERROR_BOOLEAN);
			throw new IllegalArgumentException(ERROR_BOOLEAN);
		}
	}

}
