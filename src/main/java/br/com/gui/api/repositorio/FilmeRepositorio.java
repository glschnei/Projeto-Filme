package br.com.gui.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gui.api.dominio.Filme;

public interface FilmeRepositorio extends JpaRepository<Filme, Integer> {

}
