package Objetos;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
private List<Cliente> lista = new ArrayList<Cliente>();

public String[][] listar(){
	Cliente cliente = new Cliente(1,"sergio","senha",23,"obs","M");
	lista.add(cliente);
	String[][] clientes = new String[lista.size()][6];
	int counter = 0;
	for (Cliente cli : lista) {
		if(cli.getId() == null)
		clientes[counter][0] = "0";
		else
		clientes[counter][0] = cli.getId().toString();
		clientes[counter][1] = cli.getNome();
		clientes[counter][2] = cli.getSenha();
		clientes[counter][3] = cli.getObs(); 
		clientes[counter][4] = cli.getIdade().toString();
		clientes[counter][5] = cli.getSexo();
		counter++;
	}
	return clientes;
	
}
public void cadastrar(Cliente cliente){
	lista.add(cliente);
	//System.out.println(casa.getId()+casa.getEndereco()+casa.getNum()+casa.getTipo()	);
}

}


