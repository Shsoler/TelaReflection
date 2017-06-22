package Objetos;

import java.util.ArrayList;
import java.util.List;

public class CasaDAO {

	List<Casa> lista = new ArrayList<Casa>();
	
	public String[][] listar(){
		Casa casa = new Casa(1,"Rua a",10,"Casa");
		lista.add(casa);
		casa = new Casa(2, "Rua b", 20,"Casa");
		lista.add(casa);
		casa = new Casa(3, "Rua C", 30,"Predio");
		lista.add(casa);
		String[][] casas = new String[lista.size()][4];
		int counter = 0;
		for (Casa cas : lista) {
			casas[counter][0] = cas.getId().toString();
			casas[counter][1] = cas.getEndereco();
			casas[counter][2] = cas.getNum().toString();
			casas[counter][3] = cas.getTipo(); 
			counter++;
		}
		return casas;
		
	}
	public void cadastrar(Casa casa){
		lista.add(casa);
		System.out.println(casa.getId()+casa.getEndereco()+casa.getNum()+casa.getTipo()	);
	}
	
}
