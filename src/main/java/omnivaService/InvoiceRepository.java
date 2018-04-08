package omnivaService;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Service;

@RepositoryRestResource(collectionResourceRel = "invoice", path = "invoice")
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    Invoice findByPayStatusTrueAndInvoiceId(@Param("invoiceId") Long invoiceId);
}