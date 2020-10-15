package com.chiShoAutom.RestControllers;

import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleAmountDecreasingException;
import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleEntitySaveUpdateException;
import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.chiShoAutom.Models.ModelEnums.CorrectionType;
import com.chiShoAutom.Models.Product;
import com.chiShoAutom.Models.User;
import com.chiShoAutom.RestServices.PromRestServices.ProductRestPromService;
import com.chiShoAutom.Services.CorrectionService;
import com.chiShoAutom.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRestPromService productRestService;

    @Autowired
    private CorrectionService correctionService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("add")
    public void addProduct(@RequestParam Long id) throws ImpossibleEntitySaveUpdateException {

        if (id > 0) {
            if (!productService.findById(id).isPresent()) {
                Optional<Product> productRest = productRestService.getProductById(id);
                if (productRest.isPresent()) {
                    productService.save(productRest.get());
                }
            }
        }
    }


    @GetMapping("all")
    public List<Product> productList() {
        return productService.findAll();
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("{id}/correctAmount")
    public void correctProductAmount(@AuthenticationPrincipal User user
            , @PathVariable Long id,
                                     @RequestParam Integer amount,
                                     @RequestParam String description) throws ImpossibleEntitySaveUpdateException, ImpossibleAmountDecreasingException, ImpossibleRestUpdateEntityException {
        correctionService.saveStrictCorrection(user, CorrectionType.MANUAL, description, id, amount);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("{id}/edit")
    public void edit(@PathVariable Optional<Long> id, @RequestParam Optional<Float> wholeSalePrice) throws ImpossibleEntitySaveUpdateException {
        if (id.isPresent()) {
            Optional<Product> productOpt = productService.findById(id.get());
            if (productOpt.isPresent() && wholeSalePrice.isPresent()) {
                Product product = productOpt.get();
                product.setWholeSalePrice(wholeSalePrice.get());
                productService.save(product);

            }
        }
    }

//    @PostMapping("{id}/amount/decrease")
//    public void decreaseProductAmount(){
//    }

    @GetMapping(params = "nonFullProductName")
    public List<Product> getProductsByNonFullName(@RequestParam(value = "nonFullProductName") String nonFullNameString) {

        return productService.findProductsByNonFullProductNameRegardlessOfTheWordsOrder(nonFullNameString);

    }

    @GetMapping(params = "productName")
    public Product getProductByName(@RequestParam(value = "productName") String productName) {

        return productService.findProductByName(productName).orElse(null);
    }


    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable Long id) {

        return productService.findById(id).orElse(null);
    }

}
