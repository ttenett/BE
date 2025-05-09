package com.zerobase.weather.repository;


import com.zerobase.weather.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
// jpa형식으로 쓸수있게끔 jpa리포지토리<Diary 객체, id값 형식>
public interface DiaryRepository extends JpaRepository<Diary, Integer>{
    // JPA가 문자를 이용해 함수 생성
    List<Diary> findAllByDate(LocalDate date);

    // 날짜범위에 있는 다이어리를 가져오는 함수 - start와 enddate 사이의 모든걸 찾아줌.
    List<Diary> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    // select all이 아니라 맨 첫번째 특정 하나만 가져오는 함수
    Diary getFirstByDate(LocalDate date);

    // Test코드 통해 db상태가 변경되는걸 원치 않으면 test 이후 복구(극히 일부)
    @Transactional
    void deleteAllByDate(LocalDate date);

}
