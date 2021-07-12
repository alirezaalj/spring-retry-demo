package ir.alirezaalijani.spring.retry.demo.webservice.endpoint;

import ir.alirezaalijani.spring.retry.demo.WebServiceConfigData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    private final WebServiceConfigData webServiceConfigData;

    public HelloController(WebServiceConfigData webServiceConfigData) {
        this.webServiceConfigData = webServiceConfigData;
    }

    @GetMapping("")
    public ResponseEntity<?> helloMessage(){
        return ResponseEntity.ok(webServiceConfigData.getHelloMessage());
    }
}
