package br.com.gui.api.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.gui.api.dominio.Produtor;

public interface ProdutorRepositorio extends JpaRepository<Produtor, Integer> {

	@Transactional(readOnly = true)
	Optional<Produtor> findFirstByNome(String nome);

	@Transactional(readOnly = true)
	@Query(value = "SELECT produtor FROM Produtor produtor JOIN produtor.filmes filme WHERE filme.vencedor = true GROUP BY produtor.id")
	List<Produtor> somenteProdutoresComVitorias();
}
