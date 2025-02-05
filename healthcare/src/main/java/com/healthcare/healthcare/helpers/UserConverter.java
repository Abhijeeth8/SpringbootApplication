package com.healthcare.healthcare.helpers;

import com.healthcare.healthcare.DTOs.UserDTO;
import com.healthcare.healthcare.Entities.UserT;
import com.healthcare.healthcare.Utility.ModelMapperConfig;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter {
    private ModelMapper modelMapper;

    public UserDTO toUserDTO(UserT user){
        return modelMapper.map(user, UserDTO.class);
    }
    public UserT toUser(UserDTO userDTO){
        return modelMapper.map(userDTO, UserT.class);
    }


}
