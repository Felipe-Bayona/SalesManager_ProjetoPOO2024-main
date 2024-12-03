package com.example.springdatapoo.service;


import com.example.springdatapoo.model.Client;
import com.example.springdatapoo.model.Order;
import com.example.springdatapoo.model.Product;
import com.example.springdatapoo.repository.ClientRepository;
import com.example.springdatapoo.repository.OrderRepository;
import com.example.springdatapoo.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Classe de Serviço para gerenciar a Entidade Order
 * Contém métodos para efetuar operações CRUD, Paginação e Ordenação em Pedidos
 * Recupera Produtos e Clientes associados
 */
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    /**
     * Construtor da classe OrderService
     *
     * @param orderRepository  o repositório para acesso aos dados de Pedido
     * @param productRepository o repositório para acesso aos dados de Produto
     * @param clientRepository  o repositório para acesso aos dados de Cliente
     */
    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * Recupera uma lista paginada e ordenada de todos os Pedidos
     *
     * @param pageNum   o número da página a ser recuperada
     * @param sortField o campo pelo qual ordenar
     * @param sortDir   a direção da ordenação (ascendente ou decrescente)
     * @return uma Página de Pedidos
     */
    public Page<Order> listAllOrders(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        return orderRepository.findAll(pageable);
    }

    /**
     * Recupera uma lista de todos os Produtos
     *
     * @return a lista de Produtos
     */
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Recupera uma lista de todos os Clientes
     *
     * @return a lista de Clientes
     */
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Salva um pedido no banco de dados
     * após recalcular o novo preço total do pedido
     *
     * @param order o pedido a ser salvo
     */
    public void save(Order order) {
        order.recalculateTotalPrice();
        orderRepository.save(order);
    }

    /**
     * Procura um pedido por seu ID
     * @param id o ID do pedido a ser procurado
     * @return o pedido encontrado com esse ID
     */
    public Order findById(long id) {
        return orderRepository.findById(id).get();
    }

    /**
     * Exclui um pedido por seu ID
     * @param id o ID do pedido a ser excluído
     */
    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
