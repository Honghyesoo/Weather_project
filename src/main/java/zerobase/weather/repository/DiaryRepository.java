package zerobase.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Diary;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary,Integer>{
    List<Diary> findAllByDate(LocalDate date); //하루 날씨 조회
    List<Diary> findAllByDateBetween(LocalDate startDate, LocalDate endDate); //기간 날씨 조회

    Diary getFirstByDate(LocalDate date); // 조건에 있는 diary 중에 맨 첫번째 가져오기

    void deleteAllByDate(LocalDate date);

}
