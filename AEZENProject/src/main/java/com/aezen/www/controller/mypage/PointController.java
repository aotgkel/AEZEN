package com.aezen.www.controller.mypage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.aezen.www.repository.PointRepository;
import com.aezen.www.vo.PointVO;

@RestController
@RequestMapping("/point")
public class PointController {

    @Autowired
    private PointRepository pointRepository;

    // ? 포인트 내역 조회
    @GetMapping("/history/{id}")
    public List<PointVO> getPointHistory(@PathVariable String id) {
        return pointRepository.getPointHistory(id);
    }

    @PostMapping("/add")
    public int addPoint(@RequestBody PointVO point) {
        int result = pointRepository.insertPoint(point);
        if (result > 0) {
            pointRepository.updateUserCurrentPoint(point);
        }
        return result;
    }
    
    
}
