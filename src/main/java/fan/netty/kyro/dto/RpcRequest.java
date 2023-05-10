package fan.netty.kyro.dto;

import lombok.*;

/**
 * 客户端请求实体类
 *
 * @author Fan
 * @since 2023/5/4 16:49
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RpcRequest {

    private String interfaceName;

    private String methodName;
}
