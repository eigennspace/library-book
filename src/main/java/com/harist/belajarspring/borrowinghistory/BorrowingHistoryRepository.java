package com.harist.belajarspring.borrowinghistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingHistoryRepository extends JpaRepository<BorrowingHistory, Long> {
}
