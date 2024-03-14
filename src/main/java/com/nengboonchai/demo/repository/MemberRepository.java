package com.nengboonchai.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nengboonchai.demo.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    
}