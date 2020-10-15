package com.chiShoAutom;

import com.chiShoAutom.Dao.BitrixDealDemandDao;
import com.chiShoAutom.Dao.ManDaos.GenProductManipulationDao;
import com.chiShoAutom.FilesUtils.ProductStoreCsvCorrectionsUploader;
import com.chiShoAutom.HelpUtils.CustomExceptions.ProductXmlCatalogInaccessibilityException;
import com.chiShoAutom.Models.BitrixModels.BitrixDeal;
import com.chiShoAutom.Models.Dto.XmlDto.PromXmlDto.CatalogXmlPromDto;
import com.chiShoAutom.Models.Dto.XmlDto.PromXmlDto.ProductXmlPromDto;
import com.chiShoAutom.Models.HelpModels.GenProductManipulation;
import com.chiShoAutom.Models.NonPersistentModels.Receipt;
import com.chiShoAutom.ReceiptUtils.ReceiptBuilder;
import com.chiShoAutom.RestServices.BitrixRestServices.DealRestBitrixService;
import com.chiShoAutom.SyncUtils.RozetkaCatalogXmlSynchronizer;
import com.chiShoAutom.SyncUtils.WholeSalePricesCsvImporter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceiptUtilsTest {

    @Autowired
    private ProductStoreCsvCorrectionsUploader productStoreCsvCorrectionsUploader;

    @Autowired
    private ReceiptBuilder receiptBuilder;

    @Autowired
    private DealRestBitrixService dealRestBitrixService;

    @Autowired
    private WholeSalePricesCsvImporter wholeSalePricesCsvImporter;

    @Autowired
    private RozetkaCatalogXmlSynchronizer rozetkaCatalogXmlSynchronizer;

    @Autowired
    private BitrixDealDemandDao dealDemandDao;

    @Autowired
    private GenProductManipulationDao genProductManipulationDao;

    @Test
    public void testWhole() {
        String url = "/home/yanyasha228/Downloads/priceList.csv";

        wholeSalePricesCsvImporter.importPrices(url);
    }

    @Test
    public void receiptBuilderTest() {

        Optional<BitrixDeal> bitrixDeal;

        Receipt receipt;

        bitrixDeal = dealRestBitrixService.get(3492);

        if (bitrixDeal.isPresent()) {
            receipt = receiptBuilder.build(bitrixDeal.get());
        }

        int i = 0;


    }

    @Test
    public void mod() {

    }

    @Test
    public void modelModel() {

        List<GenProductManipulation> list = genProductManipulationDao.findAllByProductId(21114963L);

        Pageable pageable = new PageRequest(1, 10);
        Page<GenProductManipulation> page = genProductManipulationDao.findAllByProductIdWithPagination(21114963L, pageable);
        int i = 9;
    }

    @Test
    public void checkUnmarshal() throws ProductXmlCatalogInaccessibilityException {

        CatalogXmlPromDto catalogXmlPromDto = rozetkaCatalogXmlSynchronizer.generate();
        List<ProductXmlPromDto> prods = catalogXmlPromDto.getShopXmlPromDto().getOffersList();
    }


}
