package com.example.springdatapoo.controller;

import com.example.springdatapoo.model.Product;
import com.example.springdatapoo.service.ProductService;
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
 * Controlador para gerenciamento de Produtos
 * Esta classe fornece métodos para lidar com as requisições HTTP relacionadas aos Produtos
 */
@Controller
public class ProductController {

    private final ProductService productService;

    /**
     * Construtor da classe ProductController.
     *
     * @param productService o serviço para operações relacionadas a Produtos
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Exibe a página de produtos.
     *
     * @param model o modelo para a view
     * @return o nome da view para exibir produtos
     */
    @RequestMapping("/products")
    public String viewProducts(Model model) {
        return viewProductsPage(model, 1, "id", "asc");
    }

    /**
     * Exibe uma página específica de produtos com paginação e ordenação.
     *
     * @param model o modelo para a visão
     * @param pageNum o número da página a ser exibida
     * @param sortField o campo pelo qual os produtos serão ordenados
     * @param sortDir a direção da ordenação (ascendente ou descendente)
     * @return o nome da view para exibir produtos
     */
    @RequestMapping("/products_page/{pageNum}")
    public String viewProductsPage(Model model, @PathVariable int pageNum,
                                   @Param("sortField") String sortField, @Param("sortDir") String sortDir) {
        Page<Product> page = productService.listAll(pageNum, sortField, sortDir);
        List<Product> listProducts = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("product", listProducts);
        return "products";
    }

    /**
     * Exibe a página para criação de um novo produto.
     *
     * @param model o modelo para a view
     * @return o nome da view para criar um novo produto
     */
    @RequestMapping("/products/new")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    /**
     * Salva um produto no banco de dados.
     *
     * @param product o produto a ser salvo
     * @param result o resultado da validação do formulário
     * @param attr atributos para redirecionamento
     * @return redireciona para a lista de produtos
     */
    @RequestMapping(value = "/products/save", method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            if (product.getId() == null) {
                return "new_product";
            }
        }
        productService.save(product);
        attr.addFlashAttribute("message", "Product saved successfully");
        return "redirect:/products";
    }

    /**
     * Exibe a página para edição de um produto.
     *
     * @param id o ID do produto a ser editado
     * @return o objeto ModelAndView com a view de edição do produto
     */
    @RequestMapping("/products/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = productService.findById(id);
        mav.addObject("product", product);
        return mav;
    }

    /**
     * Exclui um produto pelo seu ID.
     *
     * @param id o ID do produto a ser excluído
     * @return redireciona para a lista de produtos
     */
    @RequestMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") long id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
