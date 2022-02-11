package com.anaximel.bmanager.appsecurity.resource;

import com.anaximel.bmanager.appsecurity.domain.Car;
import com.anaximel.bmanager.appsecurity.domain.CarBrand;
import com.anaximel.bmanager.appsecurity.domain.CarModel;
import com.anaximel.bmanager.appsecurity.domain.HttpResponse;
import com.anaximel.bmanager.appsecurity.exception.domain.RegistrationNumberException;
import com.anaximel.bmanager.appsecurity.service.CarBrandService;
import com.anaximel.bmanager.appsecurity.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(path = {"/backendResource/car"})
@CrossOrigin(origins = "*")
public class CarResource {

    private CarService carService;
    private CarBrandService carBrandService;


    @Autowired
    public CarResource(CarService carService,CarBrandService carBrandService){

        this.carService = carService;
        this.carBrandService = carBrandService;
    }

    //carRegNumber, carBrand, carBrand,
    //        carOwner, carFirstReg, carColor, carSold, carIsActive

    @PostMapping("/add")
    public ResponseEntity<Car> addNewCar(@RequestParam("carRegNumber") String carRegNumber,
                                         @RequestParam("carBrand") String carBrand,
                                         @RequestParam("carModel") String carModel,
                                         @RequestParam("carOwner") String carOwner,
                                         @RequestParam("carFirstReg") String carFirstReg,
                                         @RequestParam("carColor") String carColor,
                                         @RequestParam("carSold") String carSold,
                                         @RequestParam("carIsActive") String carIsActive
                                        ) throws RegistrationNumberException, IOException {
             System.out.print(carFirstReg);
        System.out.print(carColor);
             LocalDate carFReg = LocalDate.parse(carFirstReg);
        Car car = carService.addNewCar(carRegNumber,carBrand,carModel,carOwner,carFReg,carColor,Boolean.parseBoolean(carSold),Boolean.parseBoolean(carIsActive));
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Car> updateCar(@RequestParam("currentCarRegNr") String currentCarRegNr,
                                         @RequestParam("carRegNumber") String newCarRegNumber,
                                         @RequestParam("carBrand") String newCarBrand,
                                         @RequestParam("carModel") String newCarModel,
                                         @RequestParam("carOwner") String newCarOwner,
                                         @RequestParam("carFirstReg") String newCarFirstReg,
                                         @RequestParam("carColor") String newCarColor,
                                         @RequestParam("carSold") String  newCarSold,
                                         @RequestParam("carIsActive") String newCarIsActive) throws RegistrationNumberException,IOException{

        LocalDate carFuReg = LocalDate.parse(newCarFirstReg);

        Car updatedCar = carService.updateCar(currentCarRegNr,newCarRegNumber,newCarBrand,newCarModel,newCarOwner,carFuReg ,newCarColor,Boolean.parseBoolean(newCarSold),Boolean.parseBoolean(newCarIsActive));

        return new ResponseEntity<>(updatedCar,HttpStatus.OK);

    }

    @GetMapping("/list")
    public ResponseEntity<List<Car>> getAllCars(){
        List<Car> cars  = carService.getCars();
        return new ResponseEntity<>(cars,HttpStatus.OK);
    }

    @DeleteMapping("delete/{carRegNumber}")
    public ResponseEntity<HttpResponse> deleteCar(@PathVariable("carRegNumber") String carRegNumber){
        carService.deleteCar(carRegNumber);

        return carResponse(HttpStatus.OK,"Car deleted successfully");
    }

    private ResponseEntity<HttpResponse> carResponse(HttpStatus httpStatus,String message){
        HttpResponse returnBody = new HttpResponse(httpStatus.value(),httpStatus, httpStatus.getReasonPhrase().toUpperCase(),message.toUpperCase());
        return new ResponseEntity<>(returnBody,httpStatus);
    }

    @PostMapping("/newCarBrand")
    public ResponseEntity<CarBrand> addNewBrand(@RequestParam("nb") String name, HttpServletRequest request)throws RegistrationNumberException,IOException{

        Enumeration enumeration = request.getParameterNames();

        while(enumeration.hasMoreElements()){
            //String parameterName = enumeration.nextElement();
            System.out.println(enumeration.nextElement().toString());
        }

        CarBrand carBrand = this.carBrandService.addNewCarBrand(name);
        return new ResponseEntity<>(carBrand,HttpStatus.OK);

    }

    @GetMapping("/listCarBrands")
   public ResponseEntity<List<CarBrand>> getAllBrands(){
        List<CarBrand> carBrands = this.carBrandService.getAllCarBrands();
        return new ResponseEntity<>(carBrands,HttpStatus.OK);
    }

   @PostMapping("/newCarModel")
    public ResponseEntity<CarModel> addNewCarModel(@RequestParam("newModel") String newModel,
                                                   @RequestParam("selectedBrand") String selectedBrand)throws IOException{

        CarModel carModel = this.carBrandService.updateBrand(selectedBrand,newModel);
        return new ResponseEntity<>(carModel,HttpStatus.OK);



   }





}
