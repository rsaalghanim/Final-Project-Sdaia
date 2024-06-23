package org.example.mappers;

import org.example.dto.ConsultationsDto;
import org.example.models.Consultations;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ConsultationMapper {
    ConsultationMapper INSTANCE = Mappers.getMapper(ConsultationMapper.class);


//    @Mapping(source = "departmentId", target = "deptId")
//    @Mapping(source = "departmentName", target = "deptName")
//    @Mapping(source = "locationId", target = "locId")
    ConsultationsDto toConsultDto(Consultations c);

//    @Mapping(target = "departmentId", source = "deptId")
//    @Mapping(target = "departmentName", source = "deptName")
//    @Mapping(target = "locationId", source = "locId")
    Consultations toModel(ConsultationsDto dto);
}
