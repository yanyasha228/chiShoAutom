package com.chiShoAutom.Services;

import com.chiShoAutom.Dao.ProductDao;
import com.chiShoAutom.Dao.SupplyDependentDemandDao;
import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleAmountDecreasingException;
import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.chiShoAutom.HelpUtils.CustomExceptions.InsufficientProductAmountException;
import com.chiShoAutom.Models.Product;
import com.chiShoAutom.Models.ProductLine;
import com.chiShoAutom.Models.SupplyDependentDemand;
import com.chiShoAutom.Models.User;
import com.chiShoAutom.SyncUtils.ProductsBitrixRestSynchronizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplyDependentDemandServiceImpl implements SupplyDependentDemandService {

    @Autowired
    private SupplyDependentDemandDao demandDao;

    @Autowired
    private ProductsBitrixRestSynchronizer productsBitrixRestSynchronizer;

    @Autowired
    private ProductDao productDao;

    @Override
    public Optional<SupplyDependentDemand> findById(long id) {
        return demandDao.findById(id);
    }

    @Override
    public Optional<SupplyDependentDemand> findBySupplyId(long id) {
        return demandDao.findBySupplyId(id);
    }

    @Override
    public SupplyDependentDemand create(User user, List<ProductLine> productLines) throws InsufficientProductAmountException {
        validateProductAmountInProductLines(productLines);
        SupplyDependentDemand supplyDependentDemand = new SupplyDependentDemand();
        supplyDependentDemand.setProductLines(productLines);
        supplyDependentDemand.setUser(user);

        return supplyDependentDemand;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public SupplyDependentDemand save(SupplyDependentDemand demand) throws InsufficientProductAmountException, ImpossibleRestUpdateEntityException {

        if (Objects.isNull(demand) || Objects.isNull(demand.getSupply())) return null;

        validateProductAmountInProductLines(demand.getProductLines());

        SupplyDependentDemand retDemand = demandDao.save(demand);

        List<Product> productsForSync = retDemand.getProductLines().stream().map(ProductLine::getProduct).collect(Collectors.toList());

        productsBitrixRestSynchronizer.synchronizeProducts(productsForSync);

        return retDemand;
    }


    private void validateProductAmountInProductLines(List<ProductLine> productLines) throws InsufficientProductAmountException {


        for (ProductLine productLineForVal : productLines) {
            try {
                productLineForVal.getProduct().decreaseAmount(productLineForVal.getAmount());
            } catch (ImpossibleAmountDecreasingException e) {
                throw new InsufficientProductAmountException("Недостаточное количество товара: " + productLineForVal.getProduct().getName());
            }
        }

    }

    private void returnProductAmountBeforeDelete(List<ProductLine> productLines) {

        for (ProductLine productLineForVal : productLines) {
            productLineForVal.getProduct().increaseAmount(productLineForVal.getAmount());
            productDao.save(productLineForVal.getProduct());
        }

    }

}
