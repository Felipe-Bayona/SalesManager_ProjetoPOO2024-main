package com.example.springdatapoo.service;

import com.example.springdatapoo.model.Product;
import com.example.springdatapoo.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe de Serviço para gerenciar a Entidade Product
 * Contém métodos para efetuar operações CRUD, Paginação e Ordenação em Produtos
 */
@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * Construtor para ProductService
     *
     * @param productRepository o repositório para acessar dados dos produtos
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Recupera uma lista paginada e ordenada de todos os produtos
     *
     * @param pageNum o numero da pagina
     * @param sortField o campo pelo qual ordenar
     * @param sortDir a direção de ordenação (ascendente ou decrescente)
     * @return uma página de produtos
     */
    public Page<Product> listAll(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        return productRepository.findAll(pageable);
    }

    /**
     * Salva um produto no banco de dados
     *
     * @param product o produto a ser salvo
     */
    public void save(Product product) {
        productRepository.save(product);
    }

    /**
     * Procura um produto por seu ID
     *
     * @param id o ID do produto a ser procurado
     * @return o produto encontrado com esse ID
     */
    public Product findById(long id) {
        return productRepository.findById(id).get();
    }

    /**
     * Exclui um produto por seu ID
     *
     * @param id o ID do produto a ser Excluído
     */
    public void delete(long id) {
        productRepository.deleteById(id);
    }

}
