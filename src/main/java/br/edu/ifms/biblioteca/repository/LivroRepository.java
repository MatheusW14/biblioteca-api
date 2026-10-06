package br.edu.ifms.biblioteca.repository;

import br.edu.ifms.biblioteca.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}