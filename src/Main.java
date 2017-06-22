import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import Objetos.Casa;
import Objetos.CasaDAO;
import Objetos.Cliente;
import telaGenerator.MemoriaGenerator;

public class Main {

	public static void main(String[] args) {
	Cliente cli = new Cliente();
	String teste = "Sergio";
	Casa ca = new Casa(1,"",1,"Predio");
	//CasaDAO casa = new CasaDAO();
	MemoriaGenerator mg = new MemoriaGenerator();
	//System.out.println(casa.getClass().getName());
	mg.Gerador(cli);

	}
}
