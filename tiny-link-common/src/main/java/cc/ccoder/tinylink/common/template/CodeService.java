package cc.ccoder.tinylink.common.template;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date CodeService.java v1.0 2021/6/28 17:59
 */
public interface CodeService {

    /**
     * 服务编码必须唯一
     * 
     * @return 服务编码
     */
    String getServiceCode();
}
