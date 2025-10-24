package com.aezen.www.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aezen.www.vo.PointVO;

@Repository
public interface PointRepository {

    // Ư�� ������ ����Ʈ ���� ��ȸ
    List<PointVO> getPointHistory(String id);

    // (�߰�) ����Ʈ ���� / ��� ��� ����
    int insertPoint(PointVO point);
    
    int deletePoint(int pointNo);
    int updateUserCurrentPoint(PointVO point);
}
