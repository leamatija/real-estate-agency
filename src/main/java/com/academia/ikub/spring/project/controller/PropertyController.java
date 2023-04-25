package com.academia.ikub.spring.project.controller;

import com.academia.ikub.spring.project.domain.dto.property.*;
import com.academia.ikub.spring.project.domain.entity.PropertyStatus;
import com.academia.ikub.spring.project.domain.mapper.PropertyMapper;
import com.academia.ikub.spring.project.service.CategoryService;
import com.academia.ikub.spring.project.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<List<PropertyDTO>> listAllProperties(){
        return ResponseEntity.ok(propertyService.listAllProperties());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> findById (@PathVariable Integer id){
        PropertyDTO p = PropertyMapper.toDto(propertyService.findById(id));
        return ResponseEntity.ok(p);
    }
    @GetMapping("/{price}")
    public ResponseEntity<List<PropertyDTO>> findByCity(@PathVariable Long price){
        return ResponseEntity.ok(propertyService.findAllByPrice(price));
    }
    @GetMapping("/{location}")
    public ResponseEntity<List<PropertyDTO>>findByLocation(@PathVariable String location){
        return ResponseEntity.ok(propertyService.findAllByLocation(location));
    }

    @GetMapping("/{userId}/list")
    public ResponseEntity<List<PropertyDTO>> getPropertiesByUserId(@PathVariable Integer userId){
        return ResponseEntity.ok(propertyService.getPropertiesByUserId(userId));
    }
    @PostMapping("/add/{categoryId}")
    public ResponseEntity<PropertyDTO> addProperty (@RequestBody PropertyDTO propertyDTO,@PathVariable Integer categoryId ){
        return ResponseEntity.ok(propertyService.addProperty(propertyDTO,categoryId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PropertyUpdateDTO> updateProperty (@PathVariable Integer id, @RequestBody PropertyUpdateDTO updateDTO){
        return ResponseEntity.ok(propertyService.updateProperty(id,updateDTO));
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Integer id){
        propertyService.deleteProperty(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}/related")
    public ResponseEntity<List<PropertyDTO>> getRelatedProperties (@PathVariable Integer id){
        return ResponseEntity.ok(propertyService.getRelatedProperties(id));
    }
    @GetMapping("/{id}/status")
    public ResponseEntity<PropertyStatus> setPropertyStatus (@PathVariable Integer id, @RequestParam String status){
        return ResponseEntity.ok(propertyService.setPropertyStatus(id,status));
    }
    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<List<PropertyDTO>> findPropertyByCategory(@PathVariable Integer categoryId){
        return ResponseEntity.ok(propertyService.findPropertyByCategory(categoryId));
    }
    @PostMapping("/{propertyId}/viewRequests")
    public ResponseEntity<PropertyViewReqDTO> requestPropertyView (@PathVariable Integer propertyId, @RequestBody PropertyViewReqDTO reqDTO){
        return ResponseEntity.ok(propertyService.requestPropertyView(propertyId,reqDTO));
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/admin/viewRequests/{reqId}/sold")
    public ResponseEntity<SoldPropertiesDTO>  addSoldProperty (@PathVariable Integer reqId){
        return ResponseEntity.ok(propertyService.addSoldProperty(reqId));
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/admin/viewRequests")
    public ResponseEntity<List<PropertyViewReqDTO>> getAllPropertyViewRequests(){
        return ResponseEntity.ok(propertyService.getAllPropertyViewRequests());
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/admin/viewRequests/{userId}")
    public ResponseEntity<List<PropertyViewReqDTO>> getPropertyViewRequestsByUser (@PathVariable Integer userId){
        return ResponseEntity.ok(propertyService.getPropertyViewRequestsByUser(userId));
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/admin/soldProperties")
    public ResponseEntity<List<SoldPropertiesDTO>> listSoldProperties(){
        return ResponseEntity.ok(propertyService.listSoldProperties());
    }

    @PostMapping("/category/add")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> allCategories(){
        return ResponseEntity.ok(categoryService.listAllCategories());
    }


    
}
