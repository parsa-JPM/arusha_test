package ir.codefather.doc.repositories;

import ir.codefather.doc.models.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepo extends JpaRepository<Keyword, Long> {
    Keyword findKeywordByLabel(String label);
}
