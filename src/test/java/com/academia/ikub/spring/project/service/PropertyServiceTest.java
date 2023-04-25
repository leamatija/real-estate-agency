package com.academia.ikub.spring.project.service;

import com.academia.ikub.spring.project.BaseTest;
import com.academia.ikub.spring.project.domain.dto.property.PropertyDTO;
import com.academia.ikub.spring.project.domain.dto.property.PropertyUpdateDTO;
import com.academia.ikub.spring.project.domain.dto.user.UserDTO;
import com.academia.ikub.spring.project.domain.entity.Category;
import com.academia.ikub.spring.project.domain.entity.Property;
import com.academia.ikub.spring.project.domain.entity.User;
import com.academia.ikub.spring.project.domain.exception.ResourceNotFoundException;
import com.academia.ikub.spring.project.repository.CategoryRepository;
import com.academia.ikub.spring.project.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PropertyServiceTest extends BaseTest {

    @Autowired
    private PropertyService toTest;

    @MockBean
    private UserService userService;
    @MockBean
    private PropertyRepository propertyRepository;
    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void test_findPropertyById_ok() {
        Mockito.doReturn(Optional.of(new Property())).when(propertyRepository).findById(Mockito.anyInt());
        Property out = toTest.findById(1);
        assertNotNull(out);
    }

    @Test
    public void test_findPropertyById_ko() {
        Mockito.doThrow(new ResourceNotFoundException("Property not found"))
                .when(propertyRepository).findById(Mockito.anyInt());
        Throwable throwable = assertThrows(Throwable.class, () -> toTest.findById(1));
        assertEquals(ResourceNotFoundException.class, throwable.getClass());
    }
    @Test
    public void test_findAllProperties_ok(){
        List<Property> properties = new ArrayList<>();
        Mockito.doReturn(properties).when(propertyRepository).findAll();
        List<PropertyDTO> out =toTest.listAllProperties();
        assertNotNull(out);
    }

    @Test
    public void test_updateProperty_ok(){
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_SELLER"));
        Mockito.doReturn(Optional.of( new Property())).when(propertyRepository).findById(Mockito.anyInt());
        PropertyUpdateDTO fakeUpdate= new PropertyUpdateDTO();
        fakeUpdate.setStatus("FOR_RENT");
        Mockito.doReturn(fakeUpdate).when(propertyRepository).save(Mockito.any());
        PropertyUpdateDTO out = toTest.updateProperty(1,fakeUpdate);
        assertNotNull(out);

    }

    @Test
    public void test_deleteProperty_ok() {
        Mockito.doNothing().when(propertyRepository).deleteById(Mockito.anyInt());
        toTest.deleteProperty(1);
    }























}