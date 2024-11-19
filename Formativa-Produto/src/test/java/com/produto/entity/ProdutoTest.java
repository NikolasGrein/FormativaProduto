package com.produto.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.produto.entity.Produto;

class ProdutoTest {

	private Produto produto;
	
	@BeforeEach
	void setUp () {
		//Arrange
		produto = new Produto(1L,"Sabonete", "Cheiroso", 1.50);
	}
	
	@Test
	@DisplayName("Testando o getter e setter do campo ID")
	void testId() {
		//Act
		produto.setId(2L);
		//Assert
		assertEquals(2L, produto.getId());
	}
	
	@Test
	@DisplayName("Testando o getter e setter do campo nome")
	void testNome() {
		//Act
		produto.setNome("Bolacha Maria");
		//Assert
		assertEquals("Bolacha Maria", produto.getNome());
	}
	
	@Test
	@DisplayName("Testando o getter e setter do campo descricao")
	void testDescricao() {
		//Act
		produto.setDescricao("20 unidades");
		//Assert
		assertEquals("20 unidades", produto.getDescricao());
	}
	
	@Test
	@DisplayName("Testando o getter e setter do campo preco")
	void testPreco() {
		//Act
		produto.setPreco(5.0);
		//Assert
		assertEquals(5.0, produto.getPreco());
	}
	
	@Test
	@DisplayName("Testando o construtor com todos os argumentos")
	void testConstrutorAll() {
		//Act
		Produto novoProduto = new Produto(3L, "Sal 1KG", "Tempeiro", 8.00);
		//Assertion
		assertAll("novoProduto",
				()-> assertEquals (3L, novoProduto.getId()),
				()-> assertEquals ("Sal 1KG", novoProduto.getNome()),
				()-> assertEquals ("Tempeiro", novoProduto.getDescricao()),
				()-> assertEquals (8.00, novoProduto.getPreco()));
	}

}
