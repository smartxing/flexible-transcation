package com.liangbo.xing.flexibletranscation.mapper;

import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDoExample;
import java.util.List;

public interface TranscationMsgDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_transcation_msg
     *
     * @mbggenerated Mon Dec 25 10:55:07 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_transcation_msg
     *
     * @mbggenerated Mon Dec 25 10:55:07 CST 2017
     */
    int insert(TranscationMsgDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_transcation_msg
     *
     * @mbggenerated Mon Dec 25 10:55:07 CST 2017
     */
    int insertSelective(TranscationMsgDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_transcation_msg
     *
     * @mbggenerated Mon Dec 25 10:55:07 CST 2017
     */
    List<TranscationMsgDo> selectByExample(TranscationMsgDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_transcation_msg
     *
     * @mbggenerated Mon Dec 25 10:55:07 CST 2017
     */
    TranscationMsgDo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_transcation_msg
     *
     * @mbggenerated Mon Dec 25 10:55:07 CST 2017
     */
    int updateByPrimaryKeySelective(TranscationMsgDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_transcation_msg
     *
     * @mbggenerated Mon Dec 25 10:55:07 CST 2017
     */
    int updateByPrimaryKey(TranscationMsgDo record);
}