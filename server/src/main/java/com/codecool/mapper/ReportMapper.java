package com.codecool.mapper;

import com.codecool.DTO.report.ReportDTO;
import com.codecool.model.reports.Report;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);
    Report reportDTOToReport(ReportDTO reportDTO);
    ReportDTO reportToReportDTO(Report report);

}
