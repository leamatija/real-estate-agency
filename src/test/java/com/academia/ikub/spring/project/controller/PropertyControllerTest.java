package com.academia.ikub.spring.project.controller;

import com.academia.ikub.spring.project.BaseTest;
import com.academia.ikub.spring.project.domain.dto.property.*;
import com.academia.ikub.spring.project.domain.dto.user.UserUpdateDTO;
import com.academia.ikub.spring.project.domain.entity.Property;
import com.academia.ikub.spring.project.domain.entity.PropertyStatus;
import com.academia.ikub.spring.project.domain.entity.User;
import com.academia.ikub.spring.project.service.CategoryService;
import com.academia.ikub.spring.project.service.PropertyService;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PropertyControllerTest extends BaseTest {
    @MockBean
    private PropertyService propertyService;
    @MockBean
    private CategoryService categoryService;

    @Test
    public void test_findPropertyById_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        Property fakeProperty = new Property();
        fakeProperty.setStatus(PropertyStatus.FOR_SALE);
        doReturn(fakeProperty).when(propertyService).findById(any());
        mockMvc.perform(MockMvcRequestBuilders.get("/properties/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_updateProperty_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new PropertyUpdateDTO()).when(propertyService).updateProperty(anyInt(),any());
        mockMvc.perform(MockMvcRequestBuilders.put("/properties/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString((new PropertyUpdateDTO()))))
        .andExpect(status().isOk());
    }

    @Test
    public void test_deleteProperty_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doNothing().when(propertyService).deleteProperty(any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/properties/delete/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_requestPropertyView_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_BUYER"));
        doReturn(new PropertyViewReqDTO()).when(propertyService).requestPropertyView(anyInt(),any());
        mockMvc.perform(MockMvcRequestBuilders.post("/properties/1/viewRequests")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(new PropertyViewReqDTO())))
                .andExpect(status().isOk());
    }
    @Test
    public void test_getRelatedProperties_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_BUYER"));
        List<PropertyDTO> fakeList=new ArrayList<>();
        doReturn(fakeList).when(propertyService).getRelatedProperties(anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/properties/1/related")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test_findPropertyByCategory_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_BUYER"));
        List<PropertyDTO> fakeList=new ArrayList<>();
        doReturn(fakeList).when(propertyService).findPropertyByCategory(anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/properties/categories/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getAllPropertyViewRequests_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        List<PropertyViewReqDTO> fakeRequests = new ArrayList<>();
        doReturn(fakeRequests).when(propertyService).getAllPropertyViewRequests();
        mockMvc.perform(MockMvcRequestBuilders.get("/properties//admin/viewRequests"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getPropertyViewRequestsByUser_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        List<PropertyViewReqDTO> fakeRequests = new ArrayList<>();
        doReturn(fakeRequests).when(propertyService).getPropertyViewRequestsByUser(anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/properties//admin/viewRequests/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_listSoldProperties_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        List<SoldPropertiesDTO> fakeSales = new ArrayList<>();
        doReturn(fakeSales).when(propertyService).listSoldProperties();
        mockMvc.perform(MockMvcRequestBuilders.get("/properties/admin/soldProperties"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_addCategory_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new CategoryDTO()).when(categoryService).addCategory(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/properties/category/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(new CategoryDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_deleteCategory_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doNothing().when(categoryService).deleteCategory(any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/properties/category/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_listAllCategories_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_BUYER"));
        List<CategoryDTO> fakeCategories = new ArrayList<>();
        doReturn(fakeCategories).when(categoryService).listAllCategories();
        mockMvc.perform(MockMvcRequestBuilders.get("/properties/categories"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_addSoldProperty_ok()throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new SoldPropertiesDTO()).when(propertyService).addSoldProperty(anyInt());
        mockMvc.perform(MockMvcRequestBuilders.post("/properties//admin/viewRequests/1/sold")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(new SoldPropertiesDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_findByLocation_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_BUYER"));
        List<PropertyDTO> fakeList=new ArrayList<>();
        doReturn(fakeList).when(propertyService).findAllByLocation(anyString());
        mockMvc.perform(MockMvcRequestBuilders.get("/properties/location/any"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_findByPrice_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_BUYER"));
        List<PropertyDTO> fakeList=new ArrayList<>();
        doReturn(fakeList).when(propertyService).findAllByPrice(anyDouble());
        mockMvc.perform(MockMvcRequestBuilders.get("/properties/price/2223"))
                .andExpect(status().isOk());
    }
    @Test
    public void test_listAllProperties_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_BUYER"));
        List<PropertyDTO> fakeList=new ArrayList<>();
        doReturn(fakeList).when(propertyService).listAllProperties();
        mockMvc.perform(MockMvcRequestBuilders.get("/properties/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getPropertiesByUserId_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        List<PropertyDTO> fakeList=new ArrayList<>();
        doReturn(fakeList).when(propertyService).getPropertiesByUserId(anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/properties/1/list"))
                .andExpect(status().isOk());
    }

    }


