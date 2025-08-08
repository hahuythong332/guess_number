package inmobi.guess_number;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api")
public class BaseController {

    @GetMapping("test")
    public String test() {
        return "hello world";
    }

}
