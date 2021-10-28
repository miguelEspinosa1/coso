package com.grupo4.categorias.service.impl;

import com.grupo4.categorias.exceptions.BadRequestException;
import com.grupo4.categorias.exceptions.ResourceNotFoundException;
import com.grupo4.categorias.model.CategoriaDTO;
import com.grupo4.categorias.service.CRUDService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

//TODO aplicar estas 2 lineas a todas clases de tests
@SpringBootTest(properties = "spring.profiles.active:test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoriaServiceTests {

    @Autowired
    private CRUDService<CategoriaDTO> categoriaService;

    private final CategoriaDTO categoriaPorCrear = new CategoriaDTO("Hotel", "807.105 hoteles", "https://via.placeholder.com/300");
    private final CategoriaDTO categoriaCreada = new CategoriaDTO(1L, "Hotel", "807.105 hoteles", "https://via.placeholder.com/300");
    private final CategoriaDTO categoriaPorActualizar = new CategoriaDTO(1L,"", "900.000 hoteles", "");
    private final CategoriaDTO categoriaActualizada = new CategoriaDTO(1L,"Hotel", "900.000 hoteles", "https://via.placeholder.com/300");

    @Test
    public void test01ObtenerTodasLasCategoriasEstaVacio() {
        assertEquals(0, categoriaService.consultarTodos().size());
    }

    @Test
    public void test02AgregarCategoria() throws BadRequestException {
        CategoriaDTO c = categoriaService.crear(categoriaPorCrear);
        assertEquals(categoriaCreada, c);
    }

    @Test
    public void test03ObtenerTodasLasCategorias() throws BadRequestException {
        categoriaService.crear(categoriaPorCrear);
        assertNotEquals(0, categoriaService.consultarTodos().size());
    }

    @Test
    public void test04EliminarCategoriaPorId() throws BadRequestException, ResourceNotFoundException {
        categoriaService.crear(categoriaPorCrear);
        assertNotEquals(0, categoriaService.consultarTodos().size());

        categoriaService.eliminar(1L);
        assertEquals(0, categoriaService.consultarTodos().size());
    }

    @Test
    public void test05NoSePuedeEliminarCategoriaPorIdSiEsMenorA1() {
        assertThrows(BadRequestException.class, () -> categoriaService.eliminar(0L));
    }

    @Test
    public void test06NoSePuedeEliminarCategoriaPorIdInexistente() {
        assertThrows(ResourceNotFoundException.class, () -> categoriaService.eliminar(1L));
    }

    @Test
    public void test07ActualizarCategoriaExistente() throws BadRequestException, ResourceNotFoundException {
        categoriaService.crear(categoriaPorCrear);
        CategoriaDTO dtoActualizado = categoriaService.actualizar(categoriaPorActualizar);
        assertEquals(categoriaActualizada, dtoActualizado);
    }

    @Test
    public void test08NoSePuedeActualizarCategoriaSinId() {
        assertThrows(BadRequestException.class, () -> categoriaService.actualizar(categoriaPorCrear));
    }

    @Test
    public void test09NoSePuedeActualizarCategoriaInexistente() {
        assertThrows(ResourceNotFoundException.class, () -> categoriaService.actualizar(categoriaPorActualizar));
    }
}