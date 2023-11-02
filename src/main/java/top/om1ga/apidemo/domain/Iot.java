package top.om1ga.apidemo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author OM1GA
 * @version 1.0
 * @date 2023年11月02日 20:40
 * @description Iot
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Iot {
    private Long id;
    private String data;
    private String time;
}
