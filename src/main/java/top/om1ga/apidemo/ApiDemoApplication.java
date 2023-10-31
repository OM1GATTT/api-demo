package top.om1ga.apidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author  om1ga
 */
@RestController
@SpringBootApplication
public class ApiDemoApplication {

    String oldIot=null;
    public static void main(String[] args) {
        SpringApplication.run(ApiDemoApplication.class, args);
    }

    @GetMapping("/list")
    public List<String> getList(){
        return List.of("吃饭","睡觉","写代码");
    }

    @PostMapping("/iot")
    public String postIot(@RequestBody String iot){
        if(iot.isEmpty()){
            return oldIot;
        }
        if (!iot.equals(oldIot)){
            String temp = oldIot;
            oldIot = iot;
            return temp;
        }
        return oldIot;
    }
}
