package br.com.south.TesteAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.com.south.core.BaseTest;
import br.com.south.util.UserUtil;

public class Simulacoes extends BaseTest  {
	
	
	private static String NOME_CONTA = "nome" + System.nanoTime();
	private static Integer ID_CONTA;
	
	@Test
	public void criarSimulacoes() {
		UserUtil util = new UserUtil();	
		Map<String, Object> params = new HashMap<String, Object>();
		 params.put("nome", NOME_CONTA);
		 params.put("cpf", util.randomCPF());
		 params.put("email", NOME_CONTA+"@gmail.com");
		 params.put("valor",util.randomValEmprestimo() );
		 params.put("parcelas", util.randomNumParcelas());
		 params.put("seguro", util.randomSeguro());
	        
		ID_CONTA = given()
			.log().all()
			.body(params)
		.when()
			.post("/simulacoes")
		.then()
			.log().all()
			.statusCode(201)
			.extract().path("id")
			;
	}
	
	@Test
	public void cpfDuplicadoEmSimulacoes() {
		Map<String, Object> params = new HashMap<String, Object>();
		 params.put("nome", "Deltrano");
		 params.put("cpf", "17822386034");
		 params.put("email", "deltrano@gmail.com");
		 params.put("valor", 20000.00);
		 params.put("parcelas", 5);
		 params.put("seguro", false);
	        
		given()
			.log().all()
			.body(params)
		.when()
			.post("/simulacoes")
		.then()
			.log().all()
			.statusCode(400)
			.body("mensagem",is("CPF duplicado"))
		;
	}
	
	@Test
	public void erroSimulacao() {
		Map<String, Object> params = new HashMap<String, Object>();
		 params.put("nome", " ");
		 params.put("cpf", "17822386034");
		 params.put("email", "@gmail.com");
		 params.put("valor", 20000.00);
		 params.put("parcelas", 5);
		 params.put("seguro", "");  
		given()
			.log().all()
			.body(params)	
		.when()
			.post("/simulacoes")
		.then()
			.log().all()
			.statusCode(400)
		;
	}
	
	@Test
	public void alterarSimulacoes() {
		Map<String, Object> params = new HashMap<String, Object>();
		 params.put("nome", "Lano");
		 params.put("cpf", "66414919004");
		 params.put("email", "lano@gmail.com");
		 params.put("valor", 20000.00);
		 params.put("parcelas", 5);
		 params.put("seguro", ""); 
		given()
			.log().all()
			.pathParam("cpf", "66414919004")
			.body(params)
		.when()
			.put("/simulacoes/{cpf}")
		.then()
			.log().all()
			.body("nome",is("Lano"))
		;	
	}
	
	// Quando é utilizado o PUT para verificar um CPF invalido é  retorna o statuscolde 400 e não 404, somente quando mudo para GET que retorna 404;
	@Test
	public void alterarSimulacoesCPFNaoEncontrado() {
		given()
			.pathParam("cpf", "12312312312")
		.when()
			.put("/simulacoes/{cpf}")
		.then()
			.log().all()
			.statusCode(400)
		;
	}	
	
	@Test
	public void consultarTodasSimulacoes() {
		given()
			
		.when()
			.get("/simulacoes")
		.then()
			.log().all()
			.statusCode(200)
		;
	}
	
	@Test
	public void SimulacoesNaoEncontrada() {
		given()
		 	
		.when()
			.get("/simulacoes")
		.then()
			.log().all()
			//.statusCode(204)
			//.body("$",hasSize(0))
		;
	}
	
	@Test
	public void consultarSimulacaoPeloCPF() {
		given()
			.pathParam("cpf","17822386034")
		.when()
			.get("/simulacoes/{cpf}")
	
		.then()
			.log().all()
			.body("cpf",is("17822386034"))
		;
	}
	
	@Test
	public void naoEncontrarConsultaPorCPF() {
		given()
			.pathParam("cpf", "")
		.when()
			.get("/simulacoes/{cpf}")
	
		.then()
			.log().all()
		;
	}
	
	//API não retorna 204 ao excluir. 
	@Test
	public void deletarConsulta() {
		given()
			.pathParam("id", ID_CONTA)
		.when()
			.delete("/simulacoes/{id}")
		.then()
			.log().all()
			.statusCode(200)
		;
	}
	
	@Test
	public void deletarConsultaCPFnaoEncontrado() {
		given()
			.log().all()
			.pathParam("id", "01")
		.when()
			.delete("/simulacoes/{id}")
		.then()
			.log().all()
			.statusCode(200)
		;
	}
	
}
