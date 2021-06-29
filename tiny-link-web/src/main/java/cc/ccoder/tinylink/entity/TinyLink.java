package cc.ccoder.tinylink.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date TinyLink.java v1.0  2021/6/28 16:24
 */
@Data
@ToString
@Entity
@Table(name = "t_tiny_link")
public class TinyLink implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long linkId;

    private String originLink;

    private String originLinkSummary;

    private String tinyLink;

    private String linkType;

    private LocalDateTime expiredTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
