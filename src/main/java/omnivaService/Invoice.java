package omnivaService;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.util.concurrent.atomic.AtomicLong;

@Data
@Region("Invoice")
public class Invoice {

    private static AtomicLong COUNTER = new AtomicLong(0L);

    @JsonIgnore
    @Id
    private Long id;

    @NonNull
    @JsonIgnore
    private Long invoiceId;
    @JsonIgnore
    private String firstName;
    @JsonIgnore
    private String lastName;
    private Boolean payStatus;
    @JsonIgnore
    private String description;

    @PersistenceConstructor
    public Invoice(Long invoiceId) {
        this.id = COUNTER.incrementAndGet();
        this.invoiceId = invoiceId;
    }
    @PersistenceConstructor
    public Invoice(Long invoiceId, String firstName, String lastName, Boolean payStatus, String description) {
        this.id = COUNTER.incrementAndGet();
        this.invoiceId = invoiceId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.payStatus = payStatus;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Boolean payStatus) {
        this.payStatus = payStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Invoice " + invoiceId + " payment status " + payStatus;
    }

}
