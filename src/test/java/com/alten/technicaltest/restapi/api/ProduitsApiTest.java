package com.alten.technicaltest.restapi.api;

import com.alten.technicaltest.restapi.dto.ProduitRequestDTO;
import com.alten.technicaltest.restapi.dto.ProduitResponseDTO;
import com.alten.technicaltest.restapi.mapper.ProduitMapper;
import com.alten.technicaltest.restapi.model.Produit;
import com.alten.technicaltest.restapi.service.ProduitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProduitsApiTest {

    @Mock
    private ProduitService produitService;

    @Mock
    private ProduitMapper produitMapper;

    @InjectMocks
    private ProduitsApi produitsApi;

    private Produit produit;
    private ProduitRequestDTO updateDTO;
    private ProduitRequestDTO requestDTO;
    private ProduitResponseDTO produitResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock request context for UriComponentsBuilder
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Centralized object creation
        produit = new Produit();
        produit.setId(1L);

        updateDTO = new ProduitRequestDTO();
        requestDTO = new ProduitRequestDTO();

        produitResponseDTO = new ProduitResponseDTO();
        produitResponseDTO.setId(1L);
    }

    @Test
    void testFindProductById_Success() {
        when(produitService.findById(1L)).thenReturn(produit);
        when(produitMapper.mapToProduitResponseDTO(produit)).thenReturn(produitResponseDTO);

        ResponseEntity<ProduitResponseDTO> response = produitsApi.findProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testFindProductById_NotFound() {
        when(produitService.findById(1L)).thenReturn(null);

        ResponseEntity<ProduitResponseDTO> response = produitsApi.findProductById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllProducts_Success() {
        List<Produit> produits = new ArrayList<>();
        produits.add(produit);
        when(produitService.findAll()).thenReturn(produits);
        when(produitMapper.mapToProduitResponseDTO(any(Produit.class))).thenReturn(produitResponseDTO);

        ResponseEntity<List<ProduitResponseDTO>> response = produitsApi.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void testGetAllProducts_NotFound() {
        when(produitService.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<ProduitResponseDTO>> response = produitsApi.getAllProducts();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAddNewProduct_Success() {
        when(produitMapper.toProduit(requestDTO)).thenReturn(produit);
        when(produitService.create(produit)).thenReturn(produit);

        ResponseEntity<Produit> response = produitsApi.addNewProduct(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testDeleteProduct_Success() {
        when(produitService.findById(1L)).thenReturn(produit);

        ResponseEntity<ProduitResponseDTO> response = produitsApi.deleteProduct1(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(produitService, times(1)).delete(1L);
    }

    @Test
    void testDeleteProduct_NotFound() {
        when(produitService.findById(1L)).thenReturn(null);

        ResponseEntity<ProduitResponseDTO> response = produitsApi.deleteProduct1(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testPatchUpdateProduct_Success() {
        when(produitService.findById(anyLong())).thenReturn(produit);
        when(produitService.updateProduct(anyLong(), any(ProduitRequestDTO.class))).thenReturn(produit);

        ResponseEntity<Produit> response = produitsApi.patchUpdateProduct(1L, updateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produit, response.getBody());

        verify(produitService, times(1)).findById(1L);
        verify(produitService, times(1)).updateProduct(1L, updateDTO);
    }

    @Test
    void testPatchUpdateProduct_NotFound() {
        when(produitService.findById(anyLong())).thenReturn(null);

        ResponseEntity<Produit> response = produitsApi.patchUpdateProduct(1L, updateDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(produitService, times(1)).findById(1L);
        verify(produitService, never()).updateProduct(anyLong(), any(ProduitRequestDTO.class));
    }
}
