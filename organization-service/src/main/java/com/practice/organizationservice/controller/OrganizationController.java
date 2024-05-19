package com.practice.organizationservice.controller;

import com.practice.organizationservice.dto.OrganizationDto;
import com.practice.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/organizations")
public class OrganizationController {

    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(
            @RequestBody OrganizationDto organizationDto) {
        OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    //Get By Code
    @GetMapping("{code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(
            @PathVariable("code")  String organizationcode) {
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationcode);
        return new ResponseEntity<>(organizationDto,HttpStatus.OK);
    }
}
