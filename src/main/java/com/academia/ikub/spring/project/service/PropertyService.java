package com.academia.ikub.spring.project.service;

import com.academia.ikub.spring.project.domain.dto.property.PropertyDTO;
import com.academia.ikub.spring.project.domain.dto.property.PropertyUpdateDTO;
import com.academia.ikub.spring.project.domain.dto.property.PropertyViewReqDTO;
import com.academia.ikub.spring.project.domain.dto.property.SoldPropertiesDTO;
import com.academia.ikub.spring.project.domain.entity.Property;
import com.academia.ikub.spring.project.domain.entity.PropertyStatus;

import java.util.List;

public interface PropertyService {

    Property findById(Integer id);
    List<PropertyDTO> listAllProperties();
    List<PropertyDTO> getPropertiesByUserId(Integer id);
    PropertyDTO addProperty (PropertyDTO propertyDTO,Integer categoryId);
    PropertyUpdateDTO updateProperty (Integer id,PropertyUpdateDTO propertyDTO);
    Void deleteProperty (Integer id);
    List<PropertyDTO> getRelatedProperties(Integer id);
    List<PropertyDTO> findPropertyByCategory(Integer categoryId);
    List<PropertyDTO> findAllByPrice(Double price);
    List<PropertyDTO> findAllByLocation(String location);
    PropertyViewReqDTO requestPropertyView (Integer propertyId, PropertyViewReqDTO reqDTO);
    List<PropertyViewReqDTO> getAllPropertyViewRequests();
    List<PropertyViewReqDTO> getPropertyViewRequestsByUser (Integer userId);
    PropertyStatus setPropertyStatus (Integer id, String status);
    List<SoldPropertiesDTO> listSoldProperties ();
    SoldPropertiesDTO addSoldProperty(Integer id);
}
