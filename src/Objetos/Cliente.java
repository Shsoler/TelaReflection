package Objetos;

import annotations.Campos;
import annotations.MapaDAO;
import annotations.Tela;
import annotations.Validacao;
import enume.TipoEntrada;
@MapaDAO(caminhoDao="Objetos.ClienteDAO",gravarDao="cadastrar",listaDao="listar")
@Tela
public class Cliente {
	@Campos(nomeCampo="Id",ativo=false)
	private Integer id;
	@Campos
	private String nome;
	@Validacao(caminhoJar="file:///home/sergio/ValidarString.jar",nomeClasse="ValidarString",nomeMetodo="Validar")
	@Campos(tipoEntrada=TipoEntrada.PASSWORD)
	private String senha;
	@Campos
	private Integer idade;
	@Campos(altura=200,largura=200,tipoEntrada=TipoEntrada.TEXTAREA,nomeCampo="Observações")
	private String obs;
	@Campos(nomeCampo="Sexo",tipoEntrada=TipoEntrada.RADIOBUTTON,opcoesSelecao={"F","M"})
	private String sexo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Cliente(Integer id, String nome, String senha, Integer idade, String obs, String sexo) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.idade = idade;
		this.obs = obs;
		this.sexo = sexo;
	}
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	
	

}
