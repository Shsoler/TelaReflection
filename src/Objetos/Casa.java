package Objetos;

import annotations.Campos;
import annotations.MapaDAO;
import annotations.Tela;
import enume.TipoEntrada;
@Tela
@MapaDAO(caminhoDao="Objetos.CasaDAO",listaDao="listar",gravarDao="cadastrar")
public class Casa {

	public Casa(Integer id, String endereco, Integer num, String tipo) {
		super();
		this.id = id;
		this.endereco = endereco;
		this.num = num;
		this.tipo = tipo;
	}
	public Casa() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Campos(nomeCampo="Código")
	private Integer id;
	@Campos(nomeCampo="Endereco")
	private String endereco;
	@Campos(nomeCampo="Numero")
	private Integer num;
	@Campos(nomeCampo="Tipo",opcoesSelecao={"Predio","Casa"},tipoEntrada=TipoEntrada.RADIOBUTTON)
	private String tipo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
