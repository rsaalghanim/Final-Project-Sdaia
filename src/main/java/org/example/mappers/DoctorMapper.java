package org.example.mappers;

import org.example.dto.DoctorDto;
import org.example.models.Doctors;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);


//    @Mapping(source = "departmentId", target = "deptId")
//    @Mapping(source = "departmentName", target = "deptName")
//    @Mapping(source = "locationId", target = "locId")
    DoctorDto toJobDto(Doctors d);

//    @Mapping(target = "departmentId", source = "deptId")
//    @Mapping(target = "departmentName", source = "deptName")
//    @Mapping(target = "locationId", source = "locId")
    Doctors toModel(DoctorDto dto);
}
