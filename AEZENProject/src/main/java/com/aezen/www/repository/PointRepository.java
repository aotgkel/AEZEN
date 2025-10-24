package com.aezen.www.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aezen.www.vo.PointVO;

@Repository
public interface PointRepository {

    // 특정 유저의 포인트 내역 조회
    List<PointVO> getPointHistory(String id);

    // (추가) 포인트 적립 / 사용 기록 삽입
    int insertPoint(PointVO point);
    
    int deletePoint(int pointNo);
    int updateUserCurrentPoint(PointVO point);
}
