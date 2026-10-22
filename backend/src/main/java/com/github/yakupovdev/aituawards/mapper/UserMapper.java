package com.github.yakupovdev.aituawards.mapper;

import com.github.yakupovdev.aituawards.model.dto.UserDto;
import com.github.yakupovdev.aituawards.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

    UserDto toDto(User user);
}
