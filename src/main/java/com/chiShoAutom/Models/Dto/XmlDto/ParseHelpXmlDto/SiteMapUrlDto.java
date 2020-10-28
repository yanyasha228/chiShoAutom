package com.chiShoAutom.Models.Dto.XmlDto.ParseHelpXmlDto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement(name = "url")
@XmlAccessorType(XmlAccessType.NONE)
public class SiteMapUrlDto {

    @XmlElement(name = "loc")
    private String loc;
    @XmlElement(name = "lastmod")
    private String lastmod;
    @XmlElement(name = "changefreq")
    private String changefreq;
    @XmlElement(name = "priority")
    private float priority;

}
