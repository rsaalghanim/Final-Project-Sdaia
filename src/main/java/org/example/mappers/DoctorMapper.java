package org.example.mappers;

import org.example.dto.DoctorDto;
import org.example.models.Doctors;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);



    DoctorDto toJobDto(Doctors d);


    Doctors toModel(DoctorDto dto);
}
