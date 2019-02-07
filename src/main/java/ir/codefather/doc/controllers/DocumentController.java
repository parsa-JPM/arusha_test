package ir.codefather.doc.controllers;

import ir.codefather.doc.models.Document;
import ir.codefather.doc.models.Keyword;
import ir.codefather.doc.repositories.DocumentRepo;
import ir.codefather.doc.repositories.KeywordRepo;
import ir.codefather.doc.transfers.SentDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DocumentController {

    @Autowired
    KeywordRepo keywordRepo;

    @Autowired
    DocumentRepo documentRepo;

    @PostMapping("/doc/save")
    @ResponseBody
    public SentDocument save(SentDocument document) {
        List<Keyword> keywords = checkAndGetKeywords(document.getLabels());
        for (Keyword k : keywords) {
            keywordRepo.save(k);
        }

        Document documentModel = new Document();
        documentModel.setName(document.getName());
        documentModel.setDate(document.getDate());
        documentModel.setKeywords(keywords);

        documentRepo.save(documentModel);

        return document;
    }


    /**
     * check keywords and make list of keywords that must assign to document
     *
     * @param labels list of labels that client sent
     * @return List<Keyword>
     */
    private List<Keyword> checkAndGetKeywords(List<String> labels) {
        List<Keyword> finalKeywords = new ArrayList<>();

        for (String label : labels) {
            Keyword keyword = keywordRepo.findKeywordByLabel(label);
            finalKeywords.add(checkExistKeyword(keyword, label));
        }

        return finalKeywords;
    }


    /**
     * if label already exists it return persist object
     *
     * @param keyword object was returned from DB
     * @param label   object that client sent
     * @return Keyword
     */
    private Keyword checkExistKeyword(Keyword keyword, String label) {
        if (keyword != null) {
            return keyword;
        } else {
            Keyword newKeyword = new Keyword();
            newKeyword.setLabel(label);
            return newKeyword;
        }
    }

    /**
     * this is just for test and didn't use in any other place
     */
    private void saveTest() {
        Document doc = new Document();
        doc.setDate(new Date());
        doc.setName("parsa");

        Keyword keyword = new Keyword();
        keyword.setLabel("panama");

        Keyword keyword1 = new Keyword();
        keyword1.setLabel("iran");

        List<Keyword> keywords = new ArrayList<>();
        keywords.add(keyword);
        keywords.add(keyword1);

        doc.setKeywords(keywords);

        keywordRepo.save(keyword);
        keywordRepo.save(keyword1);

        documentRepo.save(doc);
    }
}
