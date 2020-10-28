package com.chiShoAutom.Models.Dto.XmlDto.ParseHelpXmlDto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement(name = "urls")
@XmlAccessorType(XmlAccessType.NONE)
public class MapUrls {

    @XmlElement(name = "url")
    List<String> urls = new ArrayList<>();
}
