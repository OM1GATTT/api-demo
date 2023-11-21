package top.om1ga.apidemo.controller;

import org.springframework.web.bind.annotation.*;
import top.om1ga.apidemo.domain.Iot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author OM1GA
 * @version 1.0
 * @date 2023年11月21日 19:43
 * @description IotController
 */
@RestController
@RequestMapping("/iot")
public class IotController {

    long i = 0;

    LinkedList<Iot> list = new LinkedList<>();
    @GetMapping("/list")
    public List<String> getList(){
        return List.of("吃饭","睡觉","写代码");
    }

    @PostMapping("/write")
    public synchronized String postIot(@RequestBody String iot){
        if (iot.isEmpty()){
            throw new IllegalArgumentException("参数异常！");
        }
        try{
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            list.add(Iot.builder().id(i).data(iot+" data: "+date).time(date).build());
            i++;
            if (list.size()>100){
                list.stream().limit(1).forEach(list::remove);
            }
        }catch (Exception e){
            throw new IllegalArgumentException("内部错误！");
        }
        return "200";
    }

    @PostMapping("/read/new")
    public String readNew(){
        return list.getLast().getData();
    }

    @PostMapping("/read/list")
    public String readList(){
        LinkedList<Iot> elements = new LinkedList<>(list.subList(Math.max(list.size() - 10, 0), list.size()));
        HashMap<Long,String> iotHashMap = new HashMap<>();
        elements.forEach(iot -> iotHashMap.put(iot.getId(),iot.getData()));
        return iotHashMap.toString();
    }
}
