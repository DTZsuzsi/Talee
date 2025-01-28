package com.codecool.talee.mapper;

import com.codecool.talee.DTO.report.ReportDTO;
import com.codecool.talee.model.reports.Report;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);
    ReportDTO reportToReportDTO(Report report);

}
