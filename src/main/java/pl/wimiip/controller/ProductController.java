package pl.wimiip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wimiip.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nishi on 2016-05-27.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping
    public Map<String, Object> list() {
        Map<String, Object> model = new HashMap<>();
        model.put("products", productService.getAllProducts());
        return model;
    }

    @RequestMapping("/all")
    public Map<String, Object> allProducts() {
        Map<String, Object> model = new HashMap<>();
        model.put("products", productService.getAllProducts());
        return model;
    }

    @RequestMapping("/{category}")
    public Map<String, Object> getProductsByCategory(@PathVariable("category") String productCategory) {
        Map<String, Object> model = new HashMap<>();
        model.put("products", productService.getProductsByCategory(productCategory));
        return model;
    }

    @RequestMapping("/filter/{ByCriteria}")
    public Map<String, Object> getProductsByFilter(@MatrixVariable(pathVar = "ByCriteria")Map<String, List<String>> filterParams) {
        Map<String, Object> model = new HashMap<>();
        model.put("products", productService.getProductsByFilter(filterParams));
        return model;
    }

}
