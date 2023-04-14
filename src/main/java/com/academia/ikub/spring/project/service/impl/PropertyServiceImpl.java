package com.academia.ikub.spring.project.service.impl;

import com.academia.ikub.spring.project.domain.dto.property.PropertyDTO;
import com.academia.ikub.spring.project.domain.dto.property.PropertyUpdateDTO;
import com.academia.ikub.spring.project.domain.dto.property.PropertyViewReqDTO;
import com.academia.ikub.spring.project.domain.dto.property.SoldPropertiesDTO;
import com.academia.ikub.spring.project.domain.entity.*;
import com.academia.ikub.spring.project.domain.exception.ResourceNotFoundException;
import com.academia.ikub.spring.project.domain.mapper.PropertyMapper;
import com.academia.ikub.spring.project.domain.mapper.PropertyViewReqMapper;
import com.academia.ikub.spring.project.repository.*;
import com.academia.ikub.spring.project.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final PropertyViewRequestRepository pvr;
    private final SoldPropertiesRepository soldPropertiesRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public Property findById(Integer id) {
        return propertyRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(
                String.format("Property with id %s not found",id)));
    }

    @Override
    public List<PropertyDTO> listAllProperties() {
        return propertyRepository.findAll()
                .stream()
                .map(PropertyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> getPropertiesByUserId(Integer id) {
        User u = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(
                String.format("User with id %s not found",id)));
        return u.getProperties()
               .stream().map(PropertyMapper::toDto)
               .collect(Collectors.toList());
    }

    @RolesAllowed({"ADMIN","SELLER"})
    @Override
    public PropertyDTO addProperty(PropertyDTO propertyDTO, Integer categoryId) {
        Category category= categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException(
                String.format("Category with id %s not found",categoryId)));
        Property p = PropertyMapper.toEntity(propertyDTO);
        p.setCategory(category);
        return PropertyMapper.toDto(propertyRepository.save(p));
    }

    @RolesAllowed({"ADMIN","SELLER"})
    @Override
    public PropertyUpdateDTO updateProperty(Integer id, PropertyUpdateDTO propertyDTO) {
        Property propertyToUpdate = propertyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("Property with id %s not found",id)));

        return PropertyMapper.toUpdateDto(propertyRepository.save(PropertyMapper.toUpdateEntity(propertyDTO,propertyToUpdate)));
    }

    @RolesAllowed({"ADMIN","SELLER"})
    @Override
    public Void deleteProperty(Integer id) {
        propertyRepository.findById(id)
                .ifPresentOrElse(u->propertyRepository.deleteById(id),()-> new ResourceNotFoundException(
                                String.format("Property with id %s not found",id)
                        ));
        return null;
    }

    @Override
    public List<PropertyDTO> getRelatedProperties(Integer id) {
        return propertyRepository.findById(id)
                .map(p-> propertyRepository.findByCategoryId(p.getCategory().getId()))
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("Property with id %s not found",id)))
                .stream().map(PropertyMapper::toDto)
                .limit(5)
                .collect(Collectors.toList());

    }

    @Override
    public List<PropertyDTO> findPropertyByCategory(Integer categoryId) {
        return propertyRepository.findByCategoryId(categoryId)
                .stream().map(PropertyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PropertyViewReqDTO requestPropertyView(Integer propertyId, PropertyViewReqDTO reqDTO) {
        PropertyViewRequest p = PropertyViewReqMapper.toEntity(reqDTO);
        p.setUser(userRepository.findById(reqDTO.getUserId()).orElseThrow(()-> new ResourceNotFoundException(
                String.format("User with id %s not found",reqDTO.getUserId()))));
        p.setProperty(propertyRepository.findById(propertyId).orElseThrow(()-> new ResourceNotFoundException(
                String.format("Property with id %s not found",propertyId))));
        return PropertyViewReqMapper.toDto(pvr.save(p));
    }

    @Override
    public List<PropertyViewReqDTO> getAllPropertyViewRequests() {
        return pvr.findAll().stream().map(PropertyViewReqMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PropertyViewReqDTO> getPropertyViewRequestsByUser(Integer userId) {
        User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException(
                String.format("User with id %s not found",userId)));
        List<PropertyViewReqDTO> reqDTOS = user.getRequests().stream()
                .map(PropertyViewReqMapper::toDto)
                .collect(Collectors.toList());
        return reqDTOS;
    }

    @RolesAllowed({"ADMIN","SELLER"})
    @Override
    public PropertyStatus setPropertyStatus(Integer id, String status) {
        propertyRepository.findById(id)
                .map(p-> {
                    p.setStatus(PropertyStatus.fromValue(status));
            return propertyRepository.save(p);
        });
        return null;
    }
    @RolesAllowed("ADMIN")
    @Override
    public List<SoldPropertiesDTO> listSoldProperties() {
        List<SoldPropertiesDTO> soldProperties = soldPropertiesRepository.findAll().stream()
                .map(soldProperties1 -> PropertyMapper.toDto(soldProperties1))
                .collect(Collectors.toList());
        return soldProperties;
    }

    @RolesAllowed("ADMIN")
    @Override
    public SoldPropertiesDTO addSoldProperty(Integer reqId) {
        PropertyViewRequest propertyViewRequest = pvr.findById(reqId).orElseThrow(()-> new ResourceNotFoundException(
                String.format("Request with id %s not found",reqId)));
        Double rate = 0.002 * propertyViewRequest.getProperty().getPrice();
        SoldProperties prop = new SoldProperties();
        prop.setCommissionRate(rate);
        prop.setRequest(propertyViewRequest);
        return PropertyMapper.toDto(soldPropertiesRepository.save(prop));
    }


}
