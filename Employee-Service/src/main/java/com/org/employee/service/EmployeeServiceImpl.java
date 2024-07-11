package com.org.employee.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.org.employee.addressClient.ApiClient;
import com.org.employee.entity.Employee;
import com.org.employee.repository.EmployeeRepository;
import com.org.employee.request.EmployeeRequest;
import com.org.employee.response.AddressResponse;
import com.org.employee.response.EmployeeAddressResponse;
import com.org.employee.response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    private EmployeeRepository employeeRepository;
    
//    @Autowired
//    private ApiClient apiClient;

    @Autowired
    private Environment environment;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @Autowired
    private DiscoveryClient client;


    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {

        Employee employee = mapEmployeeRequestToEmployee(employeeRequest);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeResponse employeeResponse = mapEmployeeToEmployeeResponse(savedEmployee);
        return employeeResponse;
    }

    private EmployeeResponse mapEmployeeToEmployeeResponse(Employee employee)
    {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(employee.getId());
        employeeResponse.setName(employee.getName());
        employeeResponse.setEmail(employee.getEmail());
        employeeResponse.setAge(employee.getAge());
        return employeeResponse;
    }

    private Employee mapEmployeeRequestToEmployee(EmployeeRequest employeeRequest)
    {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setAge(employeeRequest.getAge());
        return employee;
    }

    @Override
    public EmployeeResponse getEmployee(int id)
    {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        EmployeeResponse employeeResponse = mapEmployeeToEmployeeResponse(employee);
        return employeeResponse;
    }

    @Override
    public List<EmployeeResponse> getAllEmployees()
    {
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        for(Employee e:employees) {
            EmployeeResponse employeeResponse = mapEmployeeToEmployeeResponse(e);
            employeeResponseList.add(employeeResponse);
        }
        return employeeResponseList;
    }

    @Override
    public String deleteEmployee(int id)
    {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeRepository.delete(employee);
        return "Employee Deleted Successfully";
    }

    @Override
    public EmployeeResponse updateEmployee(int id, EmployeeRequest employeeRequest)
    {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName((!employeeRequest.getName().isBlank())?employeeRequest.getName():employee.getName());
        employee.setEmail((!employeeRequest.getEmail().isBlank())?employeeRequest.getEmail()
                :employee.getEmail());
        employee.setAge((employeeRequest.getAge()!=0)?employeeRequest.getAge():employee.getAge());

        employeeRepository.save(employee);
        EmployeeResponse employeeResponse = mapEmployeeToEmployeeResponse(employee);
        return employeeResponse;
    }

    @Override
    public EmployeeAddressResponse getEmployeewithAddressById(int id)
    {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Not Found ---> "+id));
        EmployeeResponse employeeResponse = mapEmployeeToEmployeeResponse(employee);
        System.out.println(employeeResponse+" ----> "+environment.getProperty("server.port"));

//        AddressResponse addressResponse = apiClient.getAddress(id).getBody();  //Feign Client

//        AddressResponse addressResponse = restTemplate
//        .getForObject("http://localhost:8082/address/get/{id}", AddressResponse.class, id); // RestTemplate


        List<ServiceInstance> instances = client.getInstances("ADDRESS-SERVICE");
        ServiceInstance instance = instances.get(0);
        String url = instance.getUri() + "/address/get/{id}";

        AddressResponse addressResponse = webClient
                .get()
                .uri(url,id)
                .retrieve()
                .bodyToMono(AddressResponse.class)
                .block();  //Web flux

        System.out.println(addressResponse);

        EmployeeAddressResponse employeeAddressResponse = new EmployeeAddressResponse();
        employeeAddressResponse.setEmployeeResponse(employeeResponse);
        employeeAddressResponse.setAddressResponse(addressResponse);
        return employeeAddressResponse;
    }
}