package com.aezen.www.controller.mypage;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aezen.www.repository.AlertRepository;
import com.aezen.www.vo.AlertVO;

@RestController
@RequestMapping("/alert")
public class AlertController {

    @Autowired
    private AlertRepository alertRepository;

    // ? 알림 목록 조회
    @GetMapping("/history/{id}")
    public List<AlertVO> getAlertHistory(@PathVariable String id) {
        return alertRepository.selectAlertsByUserId(id);
    }

    // ? 개별 알림 삭제
    @DeleteMapping("/{alertNo}")
    public Map<String, Object> deleteAlert(@PathVariable int alertNo) {
        int result = alertRepository.deleteAlert(alertNo);
        Map<String, Object> res = new HashMap<>();
        res.put("success", result > 0);
        return res;
    }

    // ? 선택된 알림 삭제
    @PostMapping("/deleteSelected")
    public Map<String, Object> deleteSelectedAlerts(@RequestBody List<Integer> alertNos) {
        int deleted = 0;
        for (Integer no : alertNos) {
            deleted += alertRepository.deleteAlert(no);
        }
        Map<String, Object> res = new HashMap<>();
        res.put("success", deleted > 0);
        return res;
    }
}
