package com.codecool.repository;

import com.codecool.model.reports.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository  extends JpaRepository<Report, Long> {
}
