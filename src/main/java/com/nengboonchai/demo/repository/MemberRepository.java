package com.nengboonchai.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nengboonchai.demo.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    
}