package br.edu.ifms.biblioteca.controller;

import br.edu.ifms.biblioteca.service.LivroService;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ifms.biblioteca.entity.Livro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }
    @PostMapping
    public ResponseEntity<Livro> cadastrar(@RequestBody Livro livro) {
        Livro livroSalvo = livroService.cadastrar(livro);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(livroSalvo);
    }
    @GetMapping
    public List<Livro> buscarTodos() {
    return livroService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        Livro livro = livroService.buscarPorId(id);

        if (livro == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(livro);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(
        @PathVariable Long id,
        @RequestBody Livro livro) {

    Livro livroAtualizado = livroService.atualizar(id, livro);

    if (livroAtualizado == null) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean excluido = livroService.excluir(id);

    if (!excluido) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.noContent().build();
}
}