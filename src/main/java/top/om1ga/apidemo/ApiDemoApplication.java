package top.om1ga.apidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.om1ga.apidemo.domain.Iot;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author  om1ga
 */
@RestController
@SpringBootApplication
public class ApiDemoApplication {

    long i = 0;

    LinkedList<Iot> list = new LinkedList<>();
    public static void main(String[] args) {
        SpringApplication.run(ApiDemoApplication.class, args);
    }

    @GetMapping("/list")
    public List<String> getList(){
        return List.of("吃饭","睡觉","写代码");
    }

    @PostMapping("/iot/write")
    public synchronized String postIot(@RequestBody String iot){
        if (iot.isEmpty()){
            throw new IllegalArgumentException("参数异常！");
        }
        try{
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            list.add(Iot.builder().id(i).data(iot+" data: "+date).time(date).build());
            i++;
            if (list.size()>5){
                list.stream().limit(1).forEach(list::remove);
            }
        }catch (Exception e){
            throw new IllegalArgumentException("内部错误！");
        }
        return "200";
    }

    @PostMapping("/iot/read/new")
    public String readNew(){
        return list.getLast().getData();
    }

    @PostMapping("/iot/read/list")
    public String readList(){
        LinkedList<Iot> elements = new LinkedList<>(list.subList(Math.max(list.size() - 10, 0), list.size()));
        HashMap<Long,String> iotHashMap = new HashMap<>();
        elements.forEach(iot -> iotHashMap.put(iot.getId(),iot.getData()));
        return iotHashMap.toString();
    }
}
