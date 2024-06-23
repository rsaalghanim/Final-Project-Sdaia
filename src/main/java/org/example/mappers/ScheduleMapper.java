package org.example.mappers;

import org.example.dto.SchedulesDto;
import org.example.models.Schedules;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ScheduleMapper {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);


//    @Mapping(source = "departmentId", target = "deptId")
//    @Mapping(source = "departmentName", target = "deptName")
//    @Mapping(source = "locationId", target = "locId")
    SchedulesDto toScheduleDto(Schedules s);

//    @Mapping(target = "departmentId", source = "deptId")
//    @Mapping(target = "departmentName", source = "deptName")
//    @Mapping(target = "locationId", source = "locId")
    String toModel(SchedulesDto dto);
}
