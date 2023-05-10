package fan.netty.kyro.dto;

import lombok.*;

/**
 * 服务端响应实体类
 *
 * @author Fan
 * @since 2023/5/4 16:51
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class RpcResponse {

    private String message;
}
