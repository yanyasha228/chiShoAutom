package com.chiShoAutom.RestControllers;


import com.chiShoAutom.Services.SupplyProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/supplies/providers")
public class SupplyProvidersRestController {

    @Autowired
    private SupplyProviderService supplyProviderService;

    @PostMapping("delete")
    private void deleteSupplyProvider(@RequestParam Integer id) {
        supplyProviderService.delete(id);
    }


}
