package org.example.mappers;

import org.example.dto.MedicalReportsDto;
import org.example.models.MedicalReports;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface MedicalReportsMapper {
    MedicalReportsMapper INSTANCE = Mappers.getMapper(MedicalReportsMapper.class);


//    @Mapping(source = "departmentId", target = "deptId")
//    @Mapping(source = "departmentName", target = "deptName")
//    @Mapping(source = "locationId", target = "locId")
    MedicalReportsDto toMedReportsDto(MedicalReports m);

//    @Mapping(target = "departmentId", source = "deptId")
//    @Mapping(target = "departmentName", source = "deptName")
//    @Mapping(target = "locationId", source = "locId")
    MedicalReports toModel(MedicalReportsDto dto);
}
