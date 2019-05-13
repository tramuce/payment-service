package br.com.microservices.paymentservice.app;

import br.com.microservices.paymentservice.app.model.Billet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Billet, Long> {
}
