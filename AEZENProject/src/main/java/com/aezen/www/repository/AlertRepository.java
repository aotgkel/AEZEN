package com.aezen.www.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aezen.www.vo.AlertVO;

@Repository
public interface AlertRepository {

    // 특정 사용자 알림 조회
    List<AlertVO> selectAlertsByUserId(String id);

    // 개별 알림 삭제
    int deleteAlert(int alertNo);

    // 여러 개 알림 삭제
    int deleteSelectedAlerts(List<Integer> alertNos);

    // 알림 추가 (필요 시)
    int insertAlert(AlertVO alertVO);
}
