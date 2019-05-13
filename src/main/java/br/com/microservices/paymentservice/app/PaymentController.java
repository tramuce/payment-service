package br.com.microservices.paymentservice.app;

import br.com.microservices.paymentservice.app.dto.BilletRequestDto;
import br.com.microservices.paymentservice.app.dto.BilletResponseDto;
import br.com.microservices.paymentservice.app.dto.PaymentInformationDto;
import br.com.microservices.paymentservice.app.model.Billet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "payment", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("billet/emit")
    public BilletResponseDto emitBillet(@RequestBody @Valid BilletRequestDto billetRequestDto) {

        Billet billet = paymentService.emitBillet(modelMapper.map(billetRequestDto, Billet.class));

        return modelMapper.map(billet, BilletResponseDto.class);
    }

    @PutMapping("billet/{id}/pay")
    public BilletResponseDto payBillet(@PathVariable Long id, @RequestBody PaymentInformationDto paymentInformationDto) {

        Billet billet = paymentService.payBillet(id, paymentInformationDto.getPaidAt(), paymentInformationDto.getPaidAmount(), paymentInformationDto.getPaymentType());

        return modelMapper.map(billet, BilletResponseDto.class);
    }
}
