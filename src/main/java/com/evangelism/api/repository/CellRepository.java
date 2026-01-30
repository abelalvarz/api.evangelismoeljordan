package com.evangelism.api.repository;

import com.evangelism.api.entity.Cell;
import com.evangelism.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CellRepository extends JpaRepository<Cell, UUID> {
    Optional<Cell> findByTeacherOrSecretary(User teacher, User secretary);

    @Query("SELECT c FROM Cell c WHERE c.teacher = :user OR c.secretary = :user")
    Optional<Cell> findByTeacherOrSecretary(User user);
}
