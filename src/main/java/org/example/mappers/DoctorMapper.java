package org.example.mappers;

import org.example.dto.DoctorDto;
import org.example.dto.DoctorDtoAll;
import org.example.models.Doctors;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);



    DoctorDto toDocDto(Doctors d);



   // Doctors toModel(DoctorDto dto);

    Doctors toModel(DoctorDtoAll dtoAll);
}
