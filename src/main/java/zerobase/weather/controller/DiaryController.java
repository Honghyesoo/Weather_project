package zerobase.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;
    @PostMapping("/create/diary")
    void createDiary(
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestBody String text){
        diaryService.createDiary(date,text);
    }

    // 하루 날씨 조회
    @GetMapping("/read/diary")
    List<Diary> readDiary(
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return diaryService.readDiary(date);
    }

    // 기간 날씨 조회
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }
    // text 수정
    @PutMapping("/update/diary")
    void updateDiary(
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestBody String text
    ){
        diaryService.updateDiary(date,text);
    }
    //날짜로 삭제
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam("date")
                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        System.out.println(date);
        diaryService.deleteDiary(date);
    }



}
