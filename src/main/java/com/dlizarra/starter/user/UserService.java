package com.dlizarra.starter.user;

import java.util.List;

import com.dlizarra.starter.role.RoleName;

public interface UserService {

	void createUser(UserDto user);

	void updateUser(UserDto user);

	void deleteUser(Integer id);

	UserDto getUser(Integer id);

	List<UserDto> getUsers();

}
