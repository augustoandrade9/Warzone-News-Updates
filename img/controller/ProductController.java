package br.puc.tis3.controller;

import br.puc.tis3.model.Product;
import br.puc.tis3.model.dto.ProductDeleteDTO;
import br.puc.tis3.model.dto.ProductDTO;
import br.puc.tis3.model.response.Response;
import br.puc.tis3.service.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/product/")
@CrossOrigin(originPatterns = {"localhost:3000/", "http://localhost:3000/"})
public class ProductController {

    /* Dependency Injection */

    private final ProductService PRODUCT_SERVICE;

    /* Constructor */

    public ProductController(ProductService PRODUCT_SERVICE) {
        this.PRODUCT_SERVICE = PRODUCT_SERVICE;
    }

    /* Methods */

    @PostMapping
    public ResponseEntity<?> _insertProduct(
            @RequestBody ProductDTO dto
    ){

        if(dto == null)
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));

        Response response = PRODUCT_SERVICE._insertProduct(dto);

        return ResponseEntity.status(response.getStatus_code()).body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> _deleteProduct(
            @RequestBody ProductDeleteDTO dto
    ){
        if(dto == null)
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));

        Response response = PRODUCT_SERVICE._deleteProduct(dto);

        return ResponseEntity.status(response.getStatus_code()).body(response);
    }

    @PutMapping
    public ResponseEntity<?> _changeProduct(
            @RequestBody ProductDTO dto
    ){
        if(dto == null)
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));

        Response response = PRODUCT_SERVICE._changeProduct(dto);

        return ResponseEntity.status(response.getStatus_code()).body(response);
    }

    @GetMapping
    public ResponseEntity<?> _getAllProdcuts(){

        List<Product> products = PRODUCT_SERVICE._getAllProducts();

        if(products.isEmpty())
            return new ResponseEntity<>(HttpStatusCode.valueOf(204));

        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> _getProdcutsById(
            @PathVariable UUID id
    ){

        Optional<Product> product = PRODUCT_SERVICE._getProductById(id);

        if(product.isEmpty())
            return new ResponseEntity<>(HttpStatusCode.valueOf(204));

        return ResponseEntity.ok(product.get());
    }

}
