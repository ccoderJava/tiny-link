package cc.ccoder.tinylink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import cc.ccoder.tinylink.entity.TinyLink;

/**
 * <p>
 * 短链接映射持久化接口
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date TinyLinkRepository.java v1.0 2021/6/28 16:57
 */
public interface TinyLinkRepository extends JpaRepository<TinyLink, Long> {

    /**
     * 短链接查询
     *
     * @param tinyLink
     *            用户自定义短链接
     * @return 已存在的短链接映射
     */
    @Query(value = "select * from t_tiny_link where tiny_link = ?", nativeQuery = true)
    TinyLink queryTinyLink(String tinyLink);

    /**
     * 源长链接查询
     * 
     * @param originLinkSummary
     *            源长链接摘要
     * @return 已存在短链接映射
     */
    @Query(value = "select * from t_tiny_link where origin_link_summary = ?", nativeQuery = true)
    TinyLink loadOriginLinkSummary(String originLinkSummary);

    /**
     * 落库短链接映射
     * 
     * @param currentTinyLink
     *            短链接
     * @return 生效行数
     */
    @Query(
        value = "insert into t_tiny_link(origin_link,origin_link_summary,tiny_link,link_type,expired_time,create_time,update_time)"
            + " values(:#{#link.originLink},:#{#link.originLinkSummary},:#{#link.tinyLink},:#{#link.linkType},:#{#link.expiredTime},:#{#link.createTime},:#{#link.updateTime})",
        nativeQuery = true)
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    int create(@Param("link") TinyLink currentTinyLink);
}
