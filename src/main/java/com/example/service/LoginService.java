package com.example.service;

import org.springframework.stereotype.Service;

import com.example.domain.Administrator;
import com.example.repository.EmployeeRepository;

@Service
public class LoginService {

  private EmployeeRepository employeeRepository;

  public LoginService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public Administrator login(
      String mailAddress,
      String password) {
    return employeeRepository.findByMailAddressAndPassword(mailAddress, password);
  }

}
