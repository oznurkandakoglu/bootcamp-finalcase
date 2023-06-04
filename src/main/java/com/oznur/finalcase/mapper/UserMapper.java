package com.oznur.finalcase.mapper;

import com.oznur.finalcase.dto.UserDTO;
import com.oznur.finalcase.dto.UserRegisterRequest;
import com.oznur.finalcase.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO convertToUserDTO(User user);
    User convertToUser(UserRegisterRequest userRegisterRequest);
    List<UserDTO> convertToUserDTOList(List<User> userList);
}
