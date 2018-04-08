package omnivaService;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceRepositoryImpl {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private BloomFilterManager bloomFilterManager;

    @GetMapping("invoiceId={id}")
    public Invoice getCorrectInvoice(@Length(min = 10, max = 25) @PathVariable Long id) {

        invoiceRepository.findAll().forEach(t -> bloomFilterManager.getFilter().put(t));

        Invoice sample = new Invoice(id);
        sample.setPayStatus(true);

        if (!bloomFilterManager.getFilter().mightContain(sample)) {
            sample.setPayStatus(false);
            return sample;
        } else {
            return invoiceRepository.findByPayStatusTrueAndInvoiceId(id);
        }
    }
}
