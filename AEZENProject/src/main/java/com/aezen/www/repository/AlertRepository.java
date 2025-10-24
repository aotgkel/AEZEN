package com.aezen.www.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aezen.www.vo.AlertVO;

@Repository
public interface AlertRepository {

    // Ư�� ����� �˸� ��ȸ
    List<AlertVO> selectAlertsByUserId(String id);

    // ���� �˸� ����
    int deleteAlert(int alertNo);

    // ���� �� �˸� ����
    int deleteSelectedAlerts(List<Integer> alertNos);

    // �˸� �߰� (�ʿ� ��)
    int insertAlert(AlertVO alertVO);
}
