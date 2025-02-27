package ru.moskalets.controller;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.moskalets.model.CarSpring;
import ru.moskalets.service.CarService;
import ru.moskalets.service.UserService;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/cars")
public class CarController {

    private static final Logger LOG = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAllCars(ModelMap model) {
        model.put("brands", this.carService.findAllBrands());
        model.put("cars", this.carService.findAllCars());
    return "CarsView";
}

    @RequestMapping(value = "showCarsWithImage", method = RequestMethod.GET)
    public String showCarsWithImage(ModelMap model) {
        model.addAttribute("cars", this.carService.findAllCarsWithImage());
        model.put("brands", this.carService.findAllBrands());
        return "CarsView";
    }

    @RequestMapping(value = "showCarsLastDay", method = RequestMethod.GET)
    public String showCarsLastDay(ModelMap model) {
        model.addAttribute("cars", this.carService.findAllCarsLastDay());
        model.addAttribute("brands", this.carService.findAllBrands());
        return "CarsView";
    }

    @RequestMapping(value = "showCarsWithBrand", method = RequestMethod.GET)
    public String showCarsWithBrand(@RequestParam("brand") int brand, ModelMap model) {
        model.addAttribute("cars", this.carService.findAllCarsWithBrand(brand));
        model.addAttribute("brands", this.carService.findAllBrands());
        return "CarsView";
    }

    @RequestMapping(value = "createCar", method = RequestMethod.GET)
    public String createCar(ModelMap model) {
        model.addAttribute("categories", this.carService.findAllCategoryesSpring());
        model.addAttribute("brands", this.carService.findAllBrands());
        model.addAttribute("carbodyes", this.carService.findAllCarbodyesSpring());
        model.addAttribute("motors", this.carService.findAllMotorsSpring());
        model.addAttribute("transmissions", this.carService.findAllTransmissionsSpring());
        return "CarCreateView";
    }

    @RequestMapping(value = "createCar", method = RequestMethod.POST)
    public String createCar(@RequestParam("file") MultipartFile file,
                          @RequestParam(value = "category") int category,
                          @RequestParam("brand") int brand,
                          @RequestParam("carbody") int carbody,
                          @RequestParam("motor") int motor,
                          @RequestParam("transmission") int transmission
    ) throws IOException {
        String imageBase64 = Base64.encode(file.getBytes());
        Date d = new Date(System.currentTimeMillis());
        this.carService.addCarSpring(new CarSpring(
                this.carService.findCategorySpring(category),
                this.carService.findBrandSpring(brand),
                this.carService.findCarbodySpring(carbody),
                this.carService.findMotorSpring(motor),
                this.carService.findTransmissionSpring(transmission),
                imageBase64,
                false,
                this.userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()),
                new Date(System.currentTimeMillis()).getTime()
        ));
        return "redirect:/cars/";
    }
}
