package com.example.springdatapoo.service;

import com.example.springdatapoo.model.Client;
import com.example.springdatapoo.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Classe de Serviço para gerenciar a Entidade Client
 * Contém métodos para efetuar operações CRUD, Paginação e Ordenação em Clientes
 */
@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;

    /**
     * Construtor da classe ClientService
     *
     * @param clientRepository o repositório para acesso aos dados de Cliente
     */
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Recupera uma lista paginada e ordenada de todos os Clientes
     *
     * @param pageNum o número da página a ser recuperada
     * @param sortField o campo pelo qual ordenar
     * @param sortDir a direção da ordenação (ascendente ou descendente)
     * @return uma página de Clientes
     */
    public Page<Client> listAll(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        return clientRepository.findAll(pageable);
    }

    /**
     * Salva um Cliente no banco de dados
     *
     * @param client o Cliente a ser salvo
     */
    public void save(Client client) {
        clientRepository.save(client);
    }

    /**
     * Procura um cliente por seu ID
     * @param id o ID do cliente a ser procurado
     * @return o cliente encontrado com esse ID
     */
    public Client findById(long id) {
        return clientRepository.findById(id).get();
    }

    /**
     * Exclui um Cliente pelo seu ID
     *
     * @param id o ID do Cliente a ser excluído
     */
    public void delete(long id) {
        clientRepository.deleteById(id);
    }
}