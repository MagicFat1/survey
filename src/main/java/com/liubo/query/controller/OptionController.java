package com.liubo.query.controller;

import com.liubo.query.entity.Option;
import com.liubo.query.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/option")
public class OptionController {
    
    @Autowired
    private OptionService optionService;

    /**
     * 依次按问卷、题目、选项排序的顺序展示所有选项
     * @return
     */
    @GetMapping("/query")
    public List<Option> index(){
        return optionService.findAll();
    }
    
    @PostMapping("/create")
    public int create(@RequestBody Option option) {
        return optionService.create(option);
    }

    @PostMapping("/update")
    public int updateById(@RequestBody Option option) {
        return optionService.update(option);
    }

    @PostMapping("/delete")
    public int deleteById(@RequestBody Map<String,Integer> map) {
        return optionService.delete(map.get("id"));
    }

    /**
     *
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public Option detail(@RequestBody Map<String, Integer> map) {
        return optionService.detail(map.get("id"),map.get("questionId"),map.get("orderby"));
    }
}
