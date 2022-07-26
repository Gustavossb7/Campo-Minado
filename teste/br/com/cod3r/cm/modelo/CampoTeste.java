package br.com.cod3r.cm.modelo;

import br.com.cod3r.cm.excecao.ExplosaoException;
import br.com.cod3r.cm.modelo.Campo;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTeste {

	private Campo campo;
	
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3,3);
	}
	
	@Test
	void testeVizinhoRealDistanciaEsquerda() {
		Campo vizinho = new Campo(3,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		
	    assertTrue(resultado); 	
	}
	
	@Test
	void testeVizinhoRealDistanciaDireita() {
		Campo vizinho = new Campo(3,4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		
	    assertTrue(resultado); 	
	}
	
	@Test
	void testeVizinhoRealDistanciaEmCima() {
		Campo vizinho = new Campo(2,3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		
	    assertTrue(resultado); 	
	}
	
	@Test
	void testeVizinhoRealDistanciaEmbaixo() {
		Campo vizinho = new Campo(4,3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		
	    assertTrue(resultado); 	
	}
	
	@Test
	void testeVizinhoRealDistancia2() {
		Campo vizinho = new Campo(2,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		
	    assertTrue(resultado); 	
	}
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1,1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		
	    assertFalse(resultado); 	
	}
	
	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlterarMarcacao() {
		campo.alterarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAlterarMarcacaoDuasChamadas() {
		campo.alterarMarcacao();
		campo.alterarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAbrirNaoMinadoNaoMarcaod() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcaod() {
		campo.alterarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcaod() {
		campo.alterarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoNaoMarcaod() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
			});
	}
	
	@Test
	void testeAbrirComVizinhos1() {
		
		Campo Campo22 = new Campo(2,2);
		Campo Campo11 = new Campo(1,1);
		
		campo.adicionarVizinho(Campo22);
		Campo22.adicionarVizinho(Campo11);
		
		campo.abrir();
		
		
		assertTrue(Campo22.isAberto() && Campo11.isAberto());
	}
	
	@Test
	void testeAbrirComVizinhos2() {
		
		Campo Campo11 = new Campo(1,1);
		Campo Campo12 = new Campo(1,1);
		Campo12.minar();
		
		Campo Campo22 = new Campo(2,2);
		Campo22.adicionarVizinho(Campo11);
		Campo22.adicionarVizinho(Campo12);
		
		campo.adicionarVizinho(Campo22);
		campo.abrir();
		
		
		assertTrue(Campo22.isAberto() && Campo11.isFechado());
	}
}
