package ru.moskalets.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.multipart.MultipartResolver;
import ru.moskalets.repository.*;
import ru.moskalets.service.CarService;
import ru.moskalets.service.UserService;
import javax.sql.DataSource;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService service;

    @MockBean
    private UserService userService;

    @MockBean
    private DataSource dataSource;

    @MockBean
    private BrandRepository brandRepository;

    @MockBean
    private MotorRepository motorRepository;

    @MockBean
    private CarbodyRepository carbodyRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private TransmissionRepository transmissionRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private MultipartResolver multipartResolver;

    @Test
    @WithMockUser(username = "1", roles = {"User"})
    public void testShowAllCars() throws Exception {
      this.mvc.perform(get("/cars/").accept(MediaType.TEXT_HTML))
              .andExpect(status().isOk())
              .andExpect(view().name("CarsView"));
    }

    @Test
    @WithMockUser(username = "1", roles = {"User"})
    public void testShowCarsWithImage() throws Exception {
        this.mvc.perform(get("/cars/showCarsWithImage").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("CarsView"));
    }

    @Test
    @WithMockUser(username = "1", roles = {"User"})
    public void testShowCarsLastDay() throws Exception {
        this.mvc.perform(get("/cars/showCarsLastDay").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("CarsView"));
    }

    @Test
    @WithMockUser(username = "1", roles = {"User"})
    public void testShowCarsWithBrand() throws Exception {
        this.mvc.perform(get("/cars/showCarsWithBrand").accept(MediaType.TEXT_HTML).param("brand", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("CarsView"));
    }

    @Test
    @WithMockUser(username = "1", roles = {"User"})
    public void testCreateCarGet() throws Exception {
        this.mvc.perform(get("/cars/createCar").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("CarCreateView"));
    }

    @Test
    @WithMockUser(username = "1", roles = {"User"})
    public void testCreateCarPost() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/cars/createCar")
                .file("file", "123".getBytes())
                .param("category", "0")
                .param("brand", "0")
                .param("carbody", "0")
                .param("motor", "0")
                .param("transmission", "0");
        this.mvc.perform(multipart)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cars/"));
    }

}