package br.ce.wcaquino.tasks.apitest;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	
	@BeforeClass //Notation executa antes de carrega a classe. Tem que ser public static
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
	}

	@Test
	public void deveRetornarTarefas() {
		RestAssured.given()
			
		.when()
			.get("/todo")
		.then()
			
			.statusCode(200);
		
	}
	
	@Test
	public void deveIncluirTarefaComSucesso() {
		RestAssured.given()
			.body("{\"task\": \"Casa 34 é casa\",\"dueDate\": \"2021-10-02\"}")
			.contentType(ContentType.JSON) //falar para o RestAssured que no body foi um json
		.when()
			.post("/todo")
		.then()
			
			.statusCode(201);
		
	}
	
	@Test
	public void na0deveIncluirTarefaComDadosInavalidosSucesso() {
		RestAssured.given()
			.body("{\"task\": \"Casa 34 é casa\",\"dueDate\": \"2020-10-02\"}")
			.contentType(ContentType.JSON) //falar para o RestAssured que no body foi um json com data invalida
		.when()
			.post("/todo")
		.then()
			
			.statusCode(400)
			.body("message", CoreMatchers.is("Due date must not be in past"));
			
	}
	
	
	
}

