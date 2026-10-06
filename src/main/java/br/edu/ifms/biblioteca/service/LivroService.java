package br.edu.ifms.biblioteca.service;

import br.edu.ifms.biblioteca.entity.Livro;
import br.edu.ifms.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }
    public Livro cadastrar(Livro livro) {
        return livroRepository.save(livro);
    }
    public List<Livro> buscarTodos() {
        return livroRepository.findAll();
    }
    
    public Livro buscarPorId(Long id) {
        return livroRepository
                .findById(id)
                .orElse(null);
    }
    public Livro atualizar(Long id, Livro livro) {
        Livro livroExistente = buscarPorId(id);
    
        if (livroExistente == null) {
            return null;
        }
    
        livroExistente.setTitulo(livro.getTitulo());
        livroExistente.setAutor(livro.getAutor());
        livroExistente.setAnoPublicacao(livro.getAnoPublicacao());
    
        return livroRepository.save(livroExistente);
    }
    
    public boolean excluir(Long id) {
        if (!livroRepository.existsById(id)) {
            return false;
        }
    
        livroRepository.deleteById(id);
        return true;
    }
}