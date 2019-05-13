package br.com.microservices.paymentservice.app;

import br.com.microservices.paymentservice.app.dto.PaymentType;
import br.com.microservices.paymentservice.app.model.Billet;
import br.com.microservices.paymentservice.app.model.BilletStatusEnum;
import br.com.microservices.paymentservice.exception.NotFoundException;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Billet emitBillet(Billet billet) {
        billet.setStatus(BilletStatusEnum.OPENED);
        return paymentRepository.save(billet);
    }

    public Billet payBillet(Long id, LocalDateTime paidAt, Double paidAmount, PaymentType paymentType) {
        Optional<Billet> optBillet = paymentRepository.findById(id);

        if(!optBillet.isPresent()) {
            throw new NotFoundException("validation.entity.with.field.not.found", "resource.billet",
                    "field.id", id.toString());
        }

        Billet billet = optBillet.get();
        billet.setStatus(BilletStatusEnum.PAID);
        billet.setPaidAt(paidAt);
        billet.setPaidAmount(paidAmount);
        billet.setPaymentType(paymentType);

        return paymentRepository.save(billet);
    }
}
