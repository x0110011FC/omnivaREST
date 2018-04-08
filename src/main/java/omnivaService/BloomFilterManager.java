package omnivaService;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import org.springframework.stereotype.Component;

@Component
public class BloomFilterManager {
    private int expectedInsertions = 500000000;
    private double falsePositiveProbability = 0.01;

    Funnel<Invoice> invoiceFunnel = new Funnel<Invoice>() {
        @Override
        public void funnel(Invoice invoice, PrimitiveSink primitiveSink) {
            primitiveSink
                    .putLong(invoice.getInvoiceId())
                    .putBoolean(invoice.getPayStatus());
        }
    };

    private BloomFilter<Invoice> filter = BloomFilter.create(invoiceFunnel, expectedInsertions, falsePositiveProbability);

    public BloomFilter<Invoice> getFilter() {
        return filter;
    }
}
