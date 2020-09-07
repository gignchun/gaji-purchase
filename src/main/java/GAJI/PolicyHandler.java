package GAJI;

import GAJI.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @Autowired
    PurchaseRepository purchaseRepository;
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCancelled(@Payload PaymentCanceled paymentCanceled)
    {
        if(paymentCanceled.isMe()){
            Optional<Purchase> purchaseOptional = purchaseRepository.findById(paymentCanceled.getPurchaseId());
            Purchase purchase = purchaseOptional.get();
            purchase.setStatus(paymentCanceled.getStatus());
            purchaseRepository.save(purchase);
        }

    }


}
