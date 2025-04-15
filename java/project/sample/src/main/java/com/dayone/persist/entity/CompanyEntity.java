package com.dayone.persist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "COMPANY")
@Getter // 멤버 변수들의 값을 읽어올 수 있는 게터 각각 생성
@ToString // 인스턴스 출력위해 편의를 높여주는 tostring을 오버라이드해서 생성해줌
@NoArgsConstructor // 생성자 메소드를 만들어줌. 생성자 argument가 하나도없는 생성자를 만들어줌.
public class CompanyEntity {

    @Id // 값을 ID로 쓴다는 것을 알려주는 어노테이션
    // id가 생성되는 규칙을 오토인크리먼트로 하기로 함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 중복이 되면 안됨
    @Column(unique = true)
    private String ticker;

    private String name;
}
