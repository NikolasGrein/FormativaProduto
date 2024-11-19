package com.produto.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.produto.entity.Produto;

@DataJpaTest
class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository produtoRepository;

	@DisplayName("Testando o Save")
	@Test
	void testSalvarRepository() {

		// Given / Arrange
		Produto produto1 = new Produto(null, "Sabonete", "Cheiroso", 1.50);

		// When / Act
		Produto saveProduto = produtoRepository.save(produto1);

		// Then / Assert
		assertNotNull(saveProduto);
		assertTrue(saveProduto.getId() > 0);

	}

	@DisplayName("Testando Get para todos Produtos")
	@Test
	void testGetAllRepository() {

		// Given / Arrange
		Produto produto1 = new Produto(null, "Sabonete", "Cheiroso", 1.50);

		Produto produto2 = new Produto(null, "Bolacha Maria", "20 unidades", 1.50);

		produtoRepository.save(produto1);
		produtoRepository.save(produto2);

		// When / Act
		List<Produto> produtoList = produtoRepository.findAll();

		// Then / Assert
		assertNotNull(produtoList);
		assertEquals(2, produtoList.size());

	}

	@DisplayName("Testando o Update")
	@Test
	void testUpdateProduto() {

		// Given / Arrange
		Produto produto1 = new Produto(null, "Bolacha Maria", "20 unidades", 1.50);

		produtoRepository.save(produto1);

		// When / Act
		Produto saveProduto = produtoRepository.findById(produto1.getId()).get();
		produto1.setNome("Sal 1KG");
		produto1.setDescricao("Tempeiro");

		Produto updateProduto = produtoRepository.save(saveProduto);

		// Then / Assert
		assertNotNull(updateProduto);
		assertEquals("Sal 1KG", updateProduto.getNome());
		assertEquals("Tempeiro", updateProduto.getDescricao());

	}

	@DisplayName("testando o Delete")
	@Test
	void testDeleteProduto() {

		// Given / Arrange
		Produto produto1 = new Produto(null, "Bolacha Maria", "20 unidades", 2.50);

		produtoRepository.save(produto1);

		// When / Act
		produtoRepository.deleteById(produto1.getId());

		Optional<Produto> produtoOptional = produtoRepository.findById(produto1.getId());

		// Then / Assert
		assertTrue(produtoOptional.isEmpty());

	}
}