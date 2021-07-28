package br.com.south.TesteAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import br.com.south.core.BaseTest;

public class Restricoes extends BaseTest {

	@Test
	public void consultarRestricoesCPF() {
		given()
			.pathParam("cpf", "97093236014")
		.when()
			.get("/restricoes/{cpf}")
		.then()
			.log().all()
			.statusCode(200)
			.body("mensagem", is("O CPF 97093236014 tem problema"));
	}

	@Test
	public void naoPossuirestricoesCPF() {
		given()
			.pathParam("cpf", "99999999999")
		.when()
			.get("/restricoes/{cpf}")
		.then()
			.log().all()
			.statusCode(204);
	}
}
