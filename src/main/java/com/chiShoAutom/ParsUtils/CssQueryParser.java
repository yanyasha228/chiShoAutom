package com.chiShoAutom.ParsUtils;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CssQueryParser {

    Optional<String> getFirstElementValue(String url, String cssQuery) throws IOException;

    Elements getElements(String url , String cssQuery) throws IOException;

    List<String> getElementsValues(String url, String cssQuery) throws IOException;
}
