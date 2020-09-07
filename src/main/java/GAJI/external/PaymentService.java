
package GAJI.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="Payment", url="http://localhost:8084")
public interface PaymentService {

    @RequestMapping(method= RequestMethod.POST, path="/payments")
    public void doPayment(@RequestBody Payment payment);

}