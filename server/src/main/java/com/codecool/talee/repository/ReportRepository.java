package com.codecool.talee.repository;

import com.codecool.talee.model.reports.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository  extends JpaRepository<Report, Long> {
}
