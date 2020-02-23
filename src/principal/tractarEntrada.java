package principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import literals.literalMongoDB;

public class tractarEntrada {
	// I see, you're finally awake... you were trying to cross the border weren't you? you dam grone... don't you dare looking to me like that and go back to the back of the bus where you belong.
	private File _f, _t, _l;
	private String _entrada, _error, _log, _lang;
	private tipusEntrada _tipusEntrada;
	private String[] _parametres;
	private boolean _sortir, _logswitch, _done;
	private BufferedWriter _bw;
	private BufferedReader _br;
	private literalMongoDB _litm;

	public enum tipusEntrada {
		GOTO, GOLAST, LIST, UP, INFOFILE, INFODIR, HELP, CREATEDIR, CREATEFILE, SORTBY, DELETEDIR, DELETEFILE, ERROR,
		EXIT, LOG, CLEARLOG, LOAD
	}

	public enum tipusOrdenacio {
		NAME, DATE,
	}

	public tractarEntrada() {
		this._sortir = false;
		this._logswitch = true;
		this._log = "";
		this._lang = "CAT";// idioma per default
		this._litm = new literalMongoDB();
		this._logm = new logMongo();
	}

	public void carregarObjecte(String entrada) {// aquesta funció llegeix l'entrada i controla que l'instrucció
													// segueixi la notació, si es així, es selecciona la instrucció i es
													// pasa a executar per a durle a terme
		this._entrada = entrada;
		entrada = entrada.toLowerCase();
		this._done = true;
		if (entrada != null && !entrada.isEmpty()) {
			String[] elements = entrada.split(" ");
			switch (elements[0]) {
			case "goto":
				if (elements.length == 2) {
					this._parametres = new String[1];
					this._parametres[0] = elements[1];
					this._tipusEntrada = tipusEntrada.GOTO;
				} else {
					errorentrada();
				}
				break;
			case "golast":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.GOLAST;
				} else {
					errorentrada();
				}
				break;
			case "list":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.LIST;
				} else {
					errorentrada();
				}
				break;
			case "up":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.UP;
				} else {
					errorentrada();
				}
				break;
			case "infofile":
				if (elements.length == 2) {
					this._parametres = new String[1];
					this._parametres[0] = elements[1];
					this._tipusEntrada = tipusEntrada.INFOFILE;
				} else {
					errorentrada();
				}
				break;
			case "infodir":
				if (elements.length == 2) {
					this._parametres = new String[1];
					this._parametres[0] = elements[1];
					this._tipusEntrada = tipusEntrada.INFODIR;
				} else {
					errorentrada();
				}
				break;
			case "help":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.HELP;
				} else {
					errorentrada();
				}
				break;
			case "createdir":
				if (elements.length >= 2) {
					this._parametres = new String[elements.length];
					for (int i = 0; i < this._parametres.length - 1; i++) {
						this._parametres[i] = elements[i + 1];
					}
					this._tipusEntrada = tipusEntrada.CREATEDIR;
				} else {
					errorentrada();
				}
				break;
			case "createfile":
				if (elements.length >= 2) {
					this._parametres = new String[elements.length];
					for (int i = 0; i < this._parametres.length - 1; i++) {
						this._parametres[i] = elements[i + 1];
					}
					this._tipusEntrada = tipusEntrada.CREATEFILE;
				} else {
					errorentrada();
				}
				break;
			case "sortby":
				if (elements.length == 2) {
					this._parametres = new String[1];
					this._parametres[0] = elements[1];
					this._tipusEntrada = tipusEntrada.SORTBY;
				} else {
					errorentrada();
				}
				break;
			case "deletedir":
				if (elements.length >= 2) {
					this._parametres = new String[elements.length];
					for (int i = 0; i < this._parametres.length - 1; i++) {
						this._parametres[i] = elements[i + 1];
					}
					this._tipusEntrada = tipusEntrada.DELETEDIR;
				} else {
					errorentrada();
				}
				break;
			case "deletefile":
				if (elements.length >= 2) {
					this._parametres = new String[elements.length];
					for (int i = 0; i < this._parametres.length - 1; i++) {
						this._parametres[i] = elements[i + 1];
					}
					this._tipusEntrada = tipusEntrada.DELETEFILE;
				} else {
					errorentrada();
				}
				break;
			case "log":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.LOG;
				} else {
					errorentrada();
				}
				break;
			case "clearlog":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.CLEARLOG;
				} else {
					errorentrada();
				}
				break;
			case "load":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.LOAD;
				} else {
					errorentrada();
				}
				break;
			case "error":
				this._tipusEntrada = tipusEntrada.ERROR;
				break;
			case "exit":
				this._tipusEntrada = tipusEntrada.EXIT;
				break;
			default:
				this._tipusEntrada = tipusEntrada.ERROR;
				break;
			}
		} else {
			;
			System.out.println("No has intrdüit text");
		}
		executar();
	}

	private void executar() {// executar es una funció que agafa l'enum tipusEntrada i executa la que
								// correspongui, dins cada case es tracta l'insrtrucció en si i les possibles
								// errates que no siguin de notació
		String[] paraules = this._entrada.split(" ");
		switch (this._tipusEntrada) {
		case GOTO:
			if (paraules[1].indexOf(':') != -1) {
				this._t = new File(paraules[1]);
				dircorrecte();
			} else {
				if (this._f != null) {
					this._t = new File(this._f.getAbsolutePath() + "//" + paraules[1]);
				} else {
					this._t = new File(paraules[1]);
				}
				dircorrecte();
			}
			break;
		case GOLAST:
			if (this._l != null) {
				this._f = this._l;
				System.out.println(this._f.getAbsolutePath());
			} else {
				this._litm.query(this._lang, "darrerdirectorinoestroba");
			}
			break;
		case LIST:
			if (this._f != null) {
				File[] arxius = this._f.listFiles();
				for (int i = 0; i < arxius.length; i++) {
					if (arxius[i].isDirectory()) {
						this._litm.query(this._lang, "Directori");
						System.out.print(arxius[i].getName() + "\n");
					}
					if (arxius[i].isFile()) {
						this._litm.query(this._lang, "Fitxer");
						System.out.print(arxius[i].getName() + "\n");
					}
				}
			} else {
				System.out.println("Escull un directori o arxiu primer");
			}
			break;
		case UP:
			if (this._f != null) {
				updatelast();
				this._f = this._f.getAbsoluteFile().getParentFile();
				System.out.println(this._f.getAbsolutePath());
			} else {
				System.out.println("Escull un directori o arxiu primer");
			}
			break;
		case INFOFILE:
			this._t = new File(paraules[1]);
			if (this._t.isFile()) {
				Path p = Paths.get(this._f.getPath());
				try {
					BasicFileAttributes atr = Files.readAttributes(p, BasicFileAttributes.class);
					System.out.println("\nArxiu: " + this._f.getName() + "\nTamany " + atr.size()
							+ "\nMoment de Creació: " + atr.creationTime() + "\nData de l'últim accés: "
							+ atr.lastAccessTime() + "\nData de l'última modificació: " + atr.lastModifiedTime());
					updatelast();
					this._f = this._t;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("L'arxiu especificat es un directori o no existeix");
			}
			break;
		case INFODIR:
			this._t = new File(paraules[1]);
			if (this._t.isDirectory()) {
				Path p = Paths.get(this._f.getPath());
				try {
					BasicFileAttributes atr = Files.readAttributes(p, BasicFileAttributes.class);
					System.out.println("\nArxiu: " + this._f.getName() + "\nTamany " + atr.size()
							+ "\nMoment de Creació: " + atr.creationTime() + "\nData de l'últim accés: "
							+ atr.lastAccessTime() + "\nData de l'última modificació: " + atr.lastModifiedTime());
					updatelast();
					this._f = this._t;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("L'arxiu especificat no es un directori o no existeix");
			}
			break;
		case CREATEDIR:
			for (int i = 1; i < paraules.length; updatelast(), i++) {
				this._f = new File(paraules[1]);
				if (!this._f.exists()) {
					this._f.mkdirs();
					this._litm.query(this._lang, "creatcorrectament");
				} else {
					this._litm.query(this._lang, "jaexisteix");
				}
			}
			break;
		case CREATEFILE:
			for (int i = 1; i < paraules.length; updatelast(), i++) {
				this._f = new File(paraules[1]);
				if (!this._f.exists()) {
					try {
						this._f.createNewFile();
						this._litm.query(this._lang, "creatcorrectament");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					this._litm.query(this._lang, "jaexisteix");
				}
			}
			break;
		case SORTBY:
			break;
		case DELETEDIR:
			for (int i = 1; i < paraules.length; updatelast(), i++) {
				this._f = new File(paraules[1]);
				this._f.delete();
			}
			break;
		case DELETEFILE:
			for (int i = 1; i < paraules.length; updatelast(), i++) {
				this._f = new File(paraules[1]);
				this._f.delete();
			}
			break;
		case LOG:
			this._logswitch ^= true;
			if (this._logswitch) {
				this._litm.query(this._lang, "logactivat");
			} else {
				this._litm.query(this._lang, "logdesactivat");
			}
			break;
		case CLEARLOG:
			this._log = "";
			try {
				new File("log.txt").createNewFile();
				this._logm.clearlog();
				this._done = false;
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case LOAD:
			File f = new File("load.txt");
			if (f.exists()) {
				this._litm.query(this._lang, "carregantfitxer");
				load();
			} else {
				this._litm.query(this._lang, "notrobatfitxercarregar");
			}
			break;
		case HELP:
			imprimirhelp();
			break;
		case EXIT:
			this._litm.close();
			this._logm.close();
			this._sortir = true;
			break;
		case ERROR:
			System.out.println("Error a l'instrucció");
			this._done = false;
			break;
		default:
			break;
		}
		if (this._done) {
			this._log = this._log + this._entrada + "\n";
			updatelog();
		}
	}

	private void errorentrada() {// errorEntrada es una funció que s'utilitza cada vegada que es dóna un error a
									// tractarObjecte, posa l'instrucció com a Error i mostra un missatge
		this._tipusEntrada = tipusEntrada.ERROR;
		this._error = "Nombre de parametres incorrecte";
	}

	private void dircorrecte() {// dircorrecte es una funció que comprova que el directori existeix, si es aixi
								// l'imprimeix, pero si no mostra un error
								// també escriu el log a la base de dades mitjançant this._lh.log
		if (this._t.exists()) {
			updatelast();
			this._f = this._t;
			System.out.println(this._t.getAbsolutePath());
		} else {
			this._litm.query(this._lang, "noexisteix");
		}
	}

	private void updatelast() {// updatelast actualitza l'historial de fitxers, per quan es vulgui utilitzar
								// l'instrucció GOLAST
		this._l = this._f;
	}

	private void imprimirhelp() {// imprimirhelp imprimeix per pantalla l'ajuda d'instruccions per a HELP
		System.out.println("GOTO, Acepta 1 paràmetre. (Anar a una ruta)"
				+ "\nGOLAST, Sense paràmetres (Anar al darrer directori visitat)"
				+ "\nLIST, Sense paràmetres (Llistar elements del directori)"
				+ "\nUP,  Sense paràmetres (Anar al directori pare)"
				+ "\nINFOFILE, 1 paràmetre. (informació del fitxer indicat)"
				+ "\nINFODIR, 1 paràmetre (informació del directori indicat)"
				+ "\nHELP, Sense paràmetres (Pintar per pantalla totes els comandaments)"
				+ "\nCREATEDIR, 1 o més paràmetres (crear un directori)"
				+ "\nCREATEFILE, 1 o més paràmetres (crear un fitxer)"
				+ "\nSORTBY, 1 paràmetre (Com el LIST, però ordenant els fitxers per algun criteri. I mostrar la informació d’aquest criteri, exemple. Si ordenam per data de modificació, mostrar la data de modificació)"
				+ "\nDELETEDIR, 1 o més paràmetres (eliminar el o els directoris indicats)"
				+ "\nDELETEFILE, 1 o més paràmetres (eliminar el o els fitxers indicats)"
				+ "\nLOG, 1 paràmetre, activa o desactiva la funció d'enmagatzemar instruccions al LOG"
				+ "\nCLEARLOG, 1 paràmetre, neteja l'arxiu LOG"
				+ "\nLOAD, 1 paràmetre, carrega totes les intrucciones enmagatzemades a l'arxiu load.txt");
	}

	private void updatelog() {// updatelog es una funció que es llança cada vegada que s'ha d'actualitzar el
								// log, obre el bufferedwriter, escriu les noves instruccions i el tanca
		try {
			this._bw = new BufferedWriter(new FileWriter("log.txt"));
			this._bw.write(this._log);
			this._bw.close();
			this._logm.log(this._entrada);//Tractada log
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void load() {// load es dóna a l'instruccio LOAD, carrega el fitxer txt i comença a executar
							// les linies
		try {
			this._br = new BufferedReader(new FileReader("load.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			String linea;
			while ((linea = this._br.readLine()) != null)
				carregarObjecte(linea);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
