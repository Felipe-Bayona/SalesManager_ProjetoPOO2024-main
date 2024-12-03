package com.example.springdatapoo.controller;

import com.example.springdatapoo.model.Client;
import com.example.springdatapoo.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

/**
 * Controlador para gerenciamento de Clientes
 * Esta classe fornece métodos para lidar com as requisições HTTP relacionadas aos Clientes
 */
@Controller
public class ClientController {
    
    private final ClientService clientService;

    /**
     * Construtor da classe ClientController
     *
     * @param clientService o serviço para operações relacionadas a Clientes
     */
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Exibe a página de Clientes
     *
     * @param model o modelo para a view
     * @return o nome da view para exibir clientes
     */
    @RequestMapping("/clients")
    public String viewClients(Model model) {
        return viewClientsPage(model, 1, "id", "asc");
    }

    /**
     * Exibe uma página específica de clientes com paginação e ordenação
     *
     * @param model o modelo para a view
     * @param pageNum o número da página a ser exibida
     * @param sortField o campo pelo qual os clientes serão ordenados
     * @param sortDir a direção da ordenação (ascendente ou descendente)
     * @return o nome da view para exibir clientes
     */
    @RequestMapping("/clients_page/{pageNum}")
    public String viewClientsPage(Model model, @PathVariable int pageNum,
                                  @Param("sortField") String sortField, @Param("sortDir") String sortDir) {
        Page<Client> page = clientService.listAll(pageNum, sortField, sortDir);
        List<Client> listClients = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("client", listClients);
        return "clients";
    }

    /**
     * Exibe a página para criação de um novo cliente
     *
     * @param model o modelo para a view
     * @return o nome da view para criar um novo cliente
     */
    @RequestMapping("/clients/new")
    public String showNewClientPage(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "new_client";
    }

    /**
     * Salva um cliente no banco de dados
     *
     * @param client o cliente a ser salvo
     * @param result o resultado da validação do formulário
     * @param attr atributos para redirecionamento
     * @return redireciona para a lista de clientes
     */
    @RequestMapping(value = "/clients/save", method = RequestMethod.POST)
    public String saveClient(@Valid @ModelAttribute("client") Client client,
                              BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            if (client.getId() == null) {
                return "new_client";
            }
        }
        clientService.save(client);
        attr.addFlashAttribute("message", "Client saved successfully");
        return "redirect:/clients";
    }

    /**
     * Exibe a página para edição de um cliente
     *
     * @param id o ID do cliente a ser editado
     * @return o objeto ModelAndView com a view de edição do cliente
     */
    @RequestMapping("/clients/edit/{id}")
    public ModelAndView showEditClientPage(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("edit_client");
        Client client = clientService.findById(id);
        mav.addObject("client", client);
        return mav;
    }

    /**
     * Exclui um cliente pelo seu ID
     *
     * @param id o ID do cliente a ser excluído
     * @return redireciona para a lista de clientes
     */
    @RequestMapping("/clients/delete/{id}")
    public String deleteClient(@PathVariable(name = "id") long id) {
        clientService.delete(id);
        return "redirect:/clients";
    }
}
