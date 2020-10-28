package com.chiShoAutom.Models.Dto.XmlDto.ParseHelpXmlDto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement(name = "urls")
@XmlAccessorType(XmlAccessType.NONE)
public class UrlDto {

    @XmlElement(name = "urlMain")
    private String urlMain;
    @XmlElement(name = "urlInf")
    private String urlInfo;

}
