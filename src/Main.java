import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		/*
		 * Escribir un programa en Java que lea un fichero de texto con varias lineas y
		 * cuente cuantas vocales hay de cada tipo (A,E,I,O,U,a,e,i,o,u). Para almacenar
		 * el numero de vocales se utilizara un mapa. Dicha informacion se mostrara por
		 * pantalla ordenada alfabeticamente por vocal, primero las mayusculas, luego
		 * las minusculas. Imprimir por pantalla tanto el contenido del fichero como el
		 * mapa.
		 */

		// contarNumerosConsonantes("texto.txt");
		// convertirTextoMayusculas("texto.txt");

		try (FileReader fr = new FileReader("texto.txt")) {
			HashMap<Character, Integer> dic = new HashMap<>();
			char vocales[] = { 'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u' };
			char caracter;
			int codigoCaracter;
			while ((codigoCaracter = fr.read()) != -1) {
				// calculamos la cantidad de números que hay

				caracter = (char) codigoCaracter;
				for (char i : vocales) {
					if (caracter == i) {
						if (dic.containsKey(caracter)) {
							// clave--valor
							dic.put(caracter, dic.get(caracter) + 1);
						} else {
							int contadorVocales = 1;
							dic.put(caracter, contadorVocales);
						}

					}

				}
			}

			for (Character i : dic.keySet()) {
				System.out.println("Clave: " + i + " Valor: " + dic.get(i));
			}
			System.out.println("Imprimir en orden Ascendente:");
			imprimeAsc(dic);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void contarNumerosConsonantes(String fichero) {
		int contadorConsonantes = 0;
		int contadorVocales = 0;
		int contadorNumeros = 0;

		try (FileReader fr = new FileReader(fichero)) {
			int caracter;
			int vocales[] = { 65, 69, 73, 79, 85, 97, 101, 105, 111, 117 };
			boolean esVocal;
			while ((caracter = fr.read()) != -1) {
				// calculamos la cantidad de números que hay
				if (caracter >= 48 && caracter <= 57) {
					contadorNumeros++;
					// cantidad de vocales y consonantes
				} else if ((caracter >= 65 && caracter <= 90) || (caracter >= 97 && caracter <= 122)) {
					esVocal = false;
					for (int i = 0; i < vocales.length && !esVocal; i++) {
						if (caracter == vocales[i]) {
							esVocal = true;
						}
					}

					if (esVocal) {
						contadorVocales++;
					} else {
						contadorConsonantes++;
					}
				}

			}
		} catch (IOException e) {
			System.out.println("Error");
		}
		System.out.println("Hay " + contadorNumeros + " números");
		System.out.println("Hay " + contadorConsonantes + " consonantes");
		System.out.println("Hay " + contadorVocales + " vocales");
	}

	public static void convertirTextoMayusculas(String fichero) {

		/* Quitar los espacios y convertir el texto a mayusculas */
		try (FileReader fr = new FileReader(fichero); FileWriter fw = new FileWriter("texto2.txt")) {
			int caracter;
			String resultado = "";
			while ((caracter = fr.read()) != -1) {
				// si esta entre 97 y 122 estan en minusculas
				// para pasar una letra a mayuscula se le resta 32
				if (caracter >= 97 && caracter <= 122) {
					resultado += ((char) (caracter - 32));
				} else if (caracter != ' ') {
					resultado += (char) (caracter);
				}

			}
			fw.write(resultado);
		} catch (IOException e) {
			System.out.println("Error");
		}
		System.out.println("Fichero nuevo creado");

	}

	public static void imprimeAsc(Map<Character, Integer> diccionario) {
		if (diccionario.isEmpty())
			System.out.println("El mapa está vacio");
		else {
			TreeSet<Entry<Character, Integer>> treeSet = null;
			treeSet = new TreeSet<Entry<Character, Integer>>(new Ascendente());
			treeSet.addAll(diccionario.entrySet());
			Iterator<Entry<Character, Integer>> it = treeSet.iterator();
			
			while (it.hasNext())
				System.out.println(it.next());
		}
	}

}
