package com.chiShoAutom.Services;

import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleAmountDecreasingException;
import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleEntitySaveUpdateException;
import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.chiShoAutom.HelpUtils.CustomExceptions.InsufficientProductAmountException;
import com.chiShoAutom.Models.ModelEnums.SupplyStatus;
import com.chiShoAutom.Models.Supply;
import com.chiShoAutom.Models.SupplyProvider;
import com.chiShoAutom.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SupplyService {

    Optional<Supply> findById(long id);

    Supply save(Supply supply,
                User user,
                int supplyProviderId,
                SupplyStatus supplyStatus,
                Long[] productLineIdInput,
                Integer[] productLineProductQua,
                Long[] productLineIdInputDependent,
                Integer[] productLineProductQuaDependent) throws ImpossibleEntitySaveUpdateException, ImpossibleAmountDecreasingException, ImpossibleRestUpdateEntityException, InsufficientProductAmountException;

    Page<Supply> findAllWithPagination(Pageable pageable);

    List<Supply> findAll();

    Page<Supply> findSuppliesBySupplyProvider(SupplyProvider supplyProvider,
                                              Pageable pageable);

    Page<Supply> findSuppliesBySupplyStatus(SupplyStatus supplyStatus,
                                            Pageable pageable);

    Page<Supply> findSuppliesBySupplyProviderAndSupplyStatus(SupplyProvider supplyProvider,
                                                             SupplyStatus supplyStatus,
                                                             Pageable pageable);

    Page<Supply> findSuppliesWithPagination(SupplyProvider supplyProvider,
                                            SupplyStatus supplyStatus,
                                            Pageable pageable);
}
