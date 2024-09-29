package com.login.scotia.service.impl;

import com.login.scotia.config.ex.LoginException;
import com.login.scotia.config.ex.UserNotFoundInDataBase;
import com.login.scotia.config.ex.UserNotRegisteredException;
import com.login.scotia.config.util.Constant;
import com.login.scotia.config.util.LoginScotiaProperties;
import com.login.scotia.dto.ResponseUserDTO;
import com.login.scotia.entity.Teacher;
import com.login.scotia.repository.TeacherRepository;
import com.login.scotia.service.AuthService;
import com.login.scotia.specification.TeacherSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RestTemplate restTemplate;
    private final LoginScotiaProperties affiliationProperties;
    private final TeacherRepository teacherRepository;


    public Map<String, Object> login(String username, String password) {
        try {
            Teacher user = teacherRepository.findOne(TeacherSpecifications.byIdentification(username))
                    .orElseThrow(() -> new UserNotFoundInDataBase(Constant.USER_NOT_FOUND_LOGIN));

            HttpHeaders headers = new HttpHeaders();
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            map.add(Constant.CLIENT_ID, affiliationProperties.getClientId());
            map.add(Constant.CLIENT_SECRET, affiliationProperties.getClientSecret());
            map.add(Constant.USERNAME, username);
            map.add(Constant.PASSWORD, password);
            map.add(Constant.GRANT_TYPE, Constant.PASSWORD);

            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    affiliationProperties.getKeycloakUrl(),
                    HttpMethod.POST,
                    new HttpEntity<>(map, headers),
                    new ParameterizedTypeReference<>() {
                    }
            );
            Map<String, Object> tokenInfo = response.getBody();

            if (tokenInfo != null) {
                Optional<Teacher> userConsult = teacherRepository.findOne(TeacherSpecifications.byIdentification(username));
                if (userConsult.isPresent()) {
                    ResponseUserDTO responseUserDTO = new ResponseUserDTO();
                    BeanUtils.copyProperties(userConsult.get(), responseUserDTO);
                    BeanUtils.copyProperties(userConsult.get(), responseUserDTO.getTypeContract());
                    tokenInfo.put(Constant.USER_INFO, responseUserDTO);
                }
            }
            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error(Constant.PASSWORD_INCORRECT, e);
            if (HttpStatus.UNAUTHORIZED.equals(e.getStatusCode())) {
                throw new UserNotRegisteredException(Constant.PASSWORD_INCORRECT);
            }
            throw new LoginException(Optional.ofNullable(e.getMessage()).orElse(Constant.PASSWORD_INCORRECT));
        }
    }

}
