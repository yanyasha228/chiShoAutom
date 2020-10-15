package com.chiShoAutom.Controllers;


import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleAmountDecreasingException;
import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleEntitySaveUpdateException;
import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.chiShoAutom.HelpUtils.CustomExceptions.InsufficientProductAmountException;
import com.chiShoAutom.Models.ModelEnums.SupplyStatus;
import com.chiShoAutom.Models.Supply;
import com.chiShoAutom.Models.User;
import com.chiShoAutom.Services.ProductService;
import com.chiShoAutom.Services.SupplyProviderService;
import com.chiShoAutom.Services.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@PreAuthorize("hasAuthority('STOREKEEPER')")
@RequestMapping("/supplies/create")
public class SuppliesCreateController {

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplyProviderService supplyProviderService;


//    private static final Logger logger = Logger.getLogger(OrdersEditController.class);


    @GetMapping
    public String createOrder(Model model, @RequestParam(required = false) Optional<Long> productId) {


        model.addAttribute("transProduct", productService.findById(productId.orElse(0L)));
        model.addAttribute("supply", new Supply());

        model.addAttribute("supplyProviders", supplyProviderService.findAll());

        return "suppliesCreate";


    }

    @PostMapping("submit")
    public String createOrderSubmit(@ModelAttribute Supply supply,
                                    @AuthenticationPrincipal User user,
                                    @RequestParam Integer supplyProviderId,
                                    @RequestParam SupplyStatus supplyStatus,
                                    @RequestParam Long[] productLineId,
                                    @RequestParam Integer[] productLineProductQua,
                                    @RequestParam(required = false) Long[] productLineIdInputDependent,
                                    @RequestParam(required = false) Integer[] productLineProductQuaDependent) throws ImpossibleEntitySaveUpdateException, ImpossibleAmountDecreasingException, ImpossibleRestUpdateEntityException, InsufficientProductAmountException {

        supplyService.save(supply,
                user,
                supplyProviderId,
                supplyStatus,
                productLineId,
                productLineProductQua,
                productLineIdInputDependent,
                productLineProductQuaDependent);


        return "redirect:/supplies";

    }

    @ExceptionHandler(value = {ImpossibleEntitySaveUpdateException.class,
            ImpossibleAmountDecreasingException.class,
            InsufficientProductAmountException.class})
    public String handleOrderManipulationException(Model model, Exception ex) {
//        logger.error(ex.toString());

        model.addAttribute("error", "Ошибка : " + ex.getMessage());

        return "error";
    }
}
