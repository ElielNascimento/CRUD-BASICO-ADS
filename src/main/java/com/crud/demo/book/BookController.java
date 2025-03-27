package com.crud.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping
    private ResponseEntity<List<BookModel>> listarLivros(){
       List<BookModel> list = bookService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    private ResponseEntity<BookModel> criarLivro(@RequestBody BookDTO bookDTO){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(bookDTO.getId()).toUri();
        BookModel response = bookService.criarLivro(bookDTO.transformaParaObjeto());
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deletarLivro(@PathVariable Long id){
        bookService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<BookModel> update(@PathVariable Long id, @RequestBody BookModel bookModel){
         BookModel response  = bookService.update(id, bookModel);
        return ResponseEntity.ok().body(response);
    }

}
