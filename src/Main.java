import Objetos.Casa;
import Objetos.CasaDAO;
import Objetos.Cliente;
import telaGenerator.MemoriaGenerator;

public class Main {

	public static void main(String[] args) {
	Cliente cli = new Cliente();
	
	Casa ca = new Casa(1,"",1,"Predio");
	CasaDAO casa = new CasaDAO();
	MemoriaGenerator mg = new MemoriaGenerator();
	//System.out.println(casa.getClass().getName());
	mg.Gerador(ca);
	}
}
