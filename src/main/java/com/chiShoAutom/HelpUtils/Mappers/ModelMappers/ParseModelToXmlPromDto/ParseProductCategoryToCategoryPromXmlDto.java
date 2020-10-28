package com.chiShoAutom.HelpUtils.Mappers.ModelMappers.ParseModelToXmlPromDto;

import com.chiShoAutom.HelpUtils.Mappers.ModelMappers.Mapper;
import com.chiShoAutom.Models.Dto.XmlDto.PromXmlDto.CategoryPromXmlDto;
import com.chiShoAutom.Models.ParseModels.ParseProductCategory;
import com.chiShoAutom.Services.ParseModelsServices.ParseProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class ParseProductCategoryToCategoryPromXmlDto implements Mapper<ParseProductCategory, CategoryPromXmlDto> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ParseProductCategoryService parseProductCategoryService;

    @Override
    public ParseProductCategory toEntity(CategoryPromXmlDto dto) {
        if (Objects.isNull(dto)) return null;
        ParseProductCategory parseProductCategory = mapper.map(dto, ParseProductCategory.class);
        if (!Objects.isNull(parseProductCategory.getId()) && parseProductCategory.getId() != 0) {

            Optional<ParseProductCategory> parseProductCategoryOld = parseProductCategoryService.findById(parseProductCategory.getId());
            if (parseProductCategoryOld.isPresent()) {
                Optional<ParseProductCategory> parseProductCategoryParent = parseProductCategoryService.findById(Long.valueOf(dto.getParentId()));
                parseProductCategoryParent.ifPresent(parseProductCategory::setParentCategory);
            }
        }
        return parseProductCategory;
    }

    @Override
    public CategoryPromXmlDto toDto(ParseProductCategory entity) {
        if(Objects.isNull(entity)) return null;
        CategoryPromXmlDto categoryPromXmlDto = mapper.map(entity,CategoryPromXmlDto.class);
//        categoryPromXmlDto.setParentId(entity.getParentCategory().getId());
        return null;
    }

}
