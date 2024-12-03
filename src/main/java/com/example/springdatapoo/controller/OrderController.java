package com.example.springdatapoo.controller;

import com.example.springdatapoo.model.Order;
import com.example.springdatapoo.model.OrderItem;
import com.example.springdatapoo.model.Product;
import com.example.springdatapoo.repository.OrderItemRepository;
import com.example.springdatapoo.service.OrderItemService;
import com.example.springdatapoo.service.OrderService;
import com.example.springdatapoo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controlador para gerenciamento de Pedidos.
 * Esta classe fornece métodos para lidar com as requisições HTTP relacionadas aos Pedidos.
 */
@Controller
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemService orderItemService;

    /**
     * Construtor da classe OrderController
     *
     * @param orderService o serviço para operações relacionadas a Pedidos
     * @param productService o serviço para operações relacionadas a Produtos
     * @param orderItemRepository o repositório para itens de pedidos
     * @param orderItemService o serviço para operações relacionadas a itens de pedidos
     */
    public OrderController(OrderService orderService, ProductService productService, OrderItemRepository orderItemRepository, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.productService = productService;
        this.orderItemRepository = orderItemRepository;
        this.orderItemService = orderItemService;
    }

    /**
     * Exibe a página de pedidos
     *
     * @param model o modelo para a view
     * @return o nome da view para exibir pedidos
     */
    @RequestMapping("/orders")
    public String viewOrders(Model model) {
        return viewOrdersPage(model, 1, "id", "asc");
    }

    /**
     * Exibe uma página específica de pedidos com paginação e ordenação
     *
     * @param model o modelo para a view
     * @param pageNum o número da página a ser exibida
     * @param sortField o campo pelo qual os pedidos serão ordenados
     * @param sortDir a direção da ordenação (ascendente ou descendente)
     * @return o nome da view para exibir pedidos
     */
    @RequestMapping("/orders_page/{pageNum}")
    public String viewOrdersPage(Model model, @PathVariable int pageNum,
                                   @Param("sortField") String sortField, @Param("sortDir") String sortDir) {
        Page<Order> page = orderService.listAllOrders(pageNum, sortField, sortDir);
        List<Order> listOrders = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("order", listOrders);
        return "orders";
    }

    /**
     * Exibe a página para criação de um novo pedido.
     *
     * @param model o modelo para a view
     * @return o nome da view para criar um novo pedido
     */
    @RequestMapping("/orders/new")
    public String showNewOrderPage(Model model) {
        Order order = new Order();
        model.addAttribute("order", order);
        model.addAttribute("products", orderService.findAllProducts());
        model.addAttribute("clients", orderService.findAllClients());
        return "new_order";
    }

    /**
     * Salva um pedido no banco de dados
     * após recalcular o novo valor total do pedido
     *
     * @param order o pedido a ser salvo
     * @param result o resultado da validação do formulário
     * @param attr atributos para redirecionamento
     * @return redireciona para a lista de pedidos
     */
    @RequestMapping(value = "/orders/save", method = RequestMethod.POST)
    public String saveOrder(@Valid @ModelAttribute("order") Order order,
                              BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            if (order.getId() == null) {
                return "new_order";
            }
        }

        List<OrderItem> items = orderItemService.findAll();
        for (OrderItem item : items){
            if (item.getOrder().getId().equals(order.getId())){
                orderItemService.delete(item.getId());
            }
        }

        orderService.save(order);

        for (OrderItem item : order.getOrderItemList()){
            if (item.getProduct() != null && item.getProduct().getId() != null){
                Product product = productService.findById(item.getProduct().getId());
                item.setProduct(product);
                item.setOrder(order);
                orderItemRepository.save(item);
            }
        }

        orderService.save(order);

        attr.addFlashAttribute("message", "Order saved successfully");
        return "redirect:/orders";
    }

    /**
     * Exibe a página para edição de um pedido
     *
     * @param id o ID do pedido a ser editado
     * @return o objeto ModelAndView com a view de edição do pedido
     */
    @RequestMapping("/orders/edit/{id}")
    public ModelAndView showEditOrderPage(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("edit_order");
        Order order = orderService.findById(id);
        mav.addObject("order", order);
        mav.addObject("products", orderService.findAllProducts());
        mav.addObject("clients", orderService.findAllClients());
        return mav;
    }

    /**
     * Exclui um pedido pelo seu ID
     *
     * @param id o ID do pedido a ser excluído
     * @return redireciona para a lista de pedidos
     */
    @RequestMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable(name = "id") long id) {
        List<OrderItem> items = orderItemService.findAll();
        for (OrderItem item : items){
            if (item.getOrder().getId() == id){
                orderItemService.delete(item.getId());
            }
        }
        orderService.delete(id);
        return "redirect:/orders";
    }
}
