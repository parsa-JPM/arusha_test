package ir.codefather.doc.repositories;

import ir.codefather.doc.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepo extends JpaRepository<Document,Long> {
}
