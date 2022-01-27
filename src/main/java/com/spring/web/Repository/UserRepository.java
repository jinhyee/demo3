package com.spring.web.Repository;

import com.spring.web.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(String id);

    // 회원가입 시, 아이디 중복 확인
    @Query("select count(u.id) from User u where u.id = :id ")
    public int idCheck(String id);

    //회원정보 목록
    Optional<User> findByCode(Long code);

    //주소 검색 시, 건물 표출
    //public
}