package com.chiShoAutom.ParsUtils;

import org.checkerframework.checker.nullness.Opt;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.MalformedInputException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class CssQueryParserImpl implements CssQueryParser {


    private static final Logger log = LoggerFactory.getLogger(CssQueryParser.class);

    public CssQueryParserImpl() {
    }

    @Override
    public Elements getElements(String url, String cssQuery) throws IOException {
        Optional<Document> doc = getDocument(url);

        return doc.map(document -> document.select(cssQuery)).orElse(null);

    }

    @Override
    public Optional<Document> getDocument(String url) throws IOException {

        Document doc = null;

        try {
            doc = Jsoup.connect(url).timeout(30000).ignoreHttpErrors(true).get();
        } catch (SocketTimeoutException socketTimeoutException) {
            log.error("Timeout socket try again FIRST ATTEMPT");
            try {
                doc = Jsoup.connect(url).timeout(30000).ignoreHttpErrors(true).get();
            } catch (SocketTimeoutException sE) {
                log.error("Timeout socket try again SECOND ATTEMPT");
                try {
                    doc = Jsoup.connect(url).timeout(30000).ignoreHttpErrors(true).get();
                } catch (SocketTimeoutException sE2) {
                    return Optional.empty();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Returning null");
            return Optional.empty();
        }

        return Optional.of(doc);

    }

    @Override
    public Optional<String> getFirstElementValue(Document document, String cssQuery) throws IOException {

        if (Objects.nonNull(document)) {

            Elements ells = document.select(cssQuery);

            Element ell = ells.first();

            if (ell != null) return Optional.ofNullable(ell.text());
        }
        return Optional.empty();
    }

    public Optional<String> getFirstElementValue(String url, String cssQuery) throws IOException {

        Optional<Document> doc = getDocument(url);

        if (doc.isPresent()) {

            Elements ells = doc.get().select(cssQuery);

            Element ell = ells.first();

            if (ell != null) return Optional.ofNullable(ell.text());
        }

        return Optional.empty();
    }

    @Override
    public List<String> getElementsValues(String url, String cssQuery) throws IOException {
        return null;
    }


}
