package cc.ccoder.tinylink.common.request;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date AbstractRequest.java v1.0 2021/6/28 20:16
 */
public abstract class AbstractRequest {

    private static final long serialVersionUID = 1L;

    /** 扩展参数 */
    private Map<String, String> extension;

    /** 客户端标识 */
    private String clientId;

    public boolean containsExtension(String key) {
        if (extension == null) {
            return false;
        } else {
            return extension.containsKey(key);
        }
    }

    public String getExtension(String key) {
        if (extension == null) {
            return null;
        } else {
            return extension.get(key);
        }
    }

    public void putExtension(String key, String value) {
        if (this.extension == null) {
            this.extension = new HashMap<String, String>();
        }
        this.extension.put(key, value);
    }

    public Map<String, String> getExtension() {
        return extension;
    }

    public void setExtension(Map<String, String> extension) {
        this.extension = extension;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
