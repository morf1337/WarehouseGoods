package my.testprojects.SpringPractice.controllers;

import my.testprojects.SpringPractice.models.Product;
import my.testprojects.SpringPractice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
        private final ProductService productService;

        @Autowired
        public HelloController(ProductService productService) {
            this.productService = productService;
        }

        @GetMapping("/products")
        public String showAllProducts(Model model) {
            model.addAttribute("products", productService.getAllProducts());
            return "products";
        }

        @GetMapping("/add-new")
        public String showAddNewProductForm(Model model) {
            model.addAttribute("product", new Product());
            return "add-new"; // Возвращает страницу с формой добавления нового продукта
        }

        @PostMapping("/add")
        public String addProduct(@ModelAttribute Product product) {
            productService.saveProduct(product); // Вызываем метод сервиса для сохранения продукта
            return "redirect:/products"; // Перенаправляем на страницу с продуктами после добавления
        }

        @GetMapping("/delete")
        public String showDeleteProductForm() {
            return "delete";
        }

        @PostMapping("/delete")
        public String deleteProduct(@RequestParam("id") Long id) {
            productService.deleteProduct(id);
            return "redirect:/products";
        }
}
