package com.produto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.produto.entity.Produto;
import com.produto.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@SpringBootTest // Carrega o contexto completo do Spring para testes
@Transactional
class ProdutoServiceTest {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoRepository produtoRepository;

	@BeforeEach
	void setUp() {
		produtoRepository.deleteAll(); // Limpa o banco de dados antes de cada teste

// Garante que as banco de dados operacoes no serao revertidas apos cada teste

	}


	@DisplayName("Testando salvar Produto")
	@Test
	void testSalvarProduto() {
		Produto produto = new Produto(null, "Sabonete", "Cheiroso", 1.50);

		Produto resultado = produtoService.salvarProduto(produto);

		assertNotNull(resultado);
		assertEquals("Sabonete", resultado.getNome());
		assertTrue(resultado.getId() > 0);

	}

	@DisplayName("Testando listar todos os Produtos")
	@Test
	void testListarTodos() {
		Produto produtol = new Produto(null, "Sabonete", "Cheiroso", 1.50);
		Produto produto2 = new Produto(null, "Bolacha Maria", "20 unidades", 2.50);

		produtoService.salvarProduto(produtol);
		produtoService.salvarProduto(produto2);

		List<Produto> resultado = produtoService.listarTodos();

		assertNotNull(resultado);
		assertEquals(2, resultado.size());

	}

	@DisplayName("Testando buscar Hóspede por ID")
	@Test
	void testBuscarPorId() {
		Produto produto = new Produto(null, "Sabonete", "Cheiroso", 1.50);

		Produto salvo = produtoService.salvarProduto(produto);
		Optional<Produto> resultado = produtoService.buscarPorId(salvo.getId());

		assertTrue(resultado.isPresent());
		assertEquals("Sabonete", resultado.get().getNome());

	}

	@DisplayName("Testando atualizar Hóspede")
	@Test
	void testAtualizarProduto() {
		Produto produto = new Produto(null, "Sabonete", "Cheiroso", 1.50);
		Produto salvo = produtoService.salvarProduto(produto);

		salvo.setNome("Sal 1KG");
		salvo.setDescricao("Tempeiro");

		Produto atualizado = produtoService.atualizarProduto(salvo);

		assertNotNull(atualizado);
		assertEquals("Sal 1KG", atualizado.getNome());
		assertEquals("Tempeiro", atualizado.getDescricao());

	}

	@DisplayName ("Testando deletar Hóspede")
	@Test
	void testDeletarProduto () {
		Produto produto = new Produto (null, "Sabonete", "Cheiroso", 1.50);

		Produto salvo = produtoService.salvarProduto (produto) ;
		produtoService.deletarProduto (salvo.getId());
	
		Optional<Produto> resultado = produtoService.buscarPorId(salvo.getId() );

		assertTrue(resultado.isEmpty ());

	}
}
