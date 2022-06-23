package com.anaximel.bmanager.appsecurity.resource;

import com.anaximel.bmanager.appsecurity.domain.HttpResponse;
import com.anaximel.bmanager.appsecurity.domain.Worker;
import com.anaximel.bmanager.appsecurity.domain.WorkerQualification;
import com.anaximel.bmanager.appsecurity.domain.Workshop;
import com.anaximel.bmanager.appsecurity.impl.WorkerQualificationServiceImpl;
import com.anaximel.bmanager.appsecurity.repository.WorkerQualificationRepositoryInterface;
import com.anaximel.bmanager.appsecurity.service.WorkerQualificationServiceInterface;
import com.anaximel.bmanager.appsecurity.service.WorkerServiceInterface;
import com.anaximel.bmanager.appsecurity.service.WorkshopServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.function.EntityResponse;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(path="/backendResource")
@CrossOrigin(origins = "*")
public class WorkerResource {

    private WorkerServiceInterface workerServiceInterface;
    private WorkerQualificationServiceInterface workerQualificationServiceInterface;
    private WorkshopServiceInterface workshopServiceInterface;
;
    @Autowired
    WorkerResource(WorkerServiceInterface workerServiceInterface,
                   WorkerQualificationServiceInterface workerQualificationServiceInterface,
                   WorkshopServiceInterface workshopServiceInterface){
        this.workerServiceInterface = workerServiceInterface;
        this.workerQualificationServiceInterface = workerQualificationServiceInterface;
        this.workshopServiceInterface = workshopServiceInterface;
    }

    /*                               ************************                            */
    /*  ***************************   paths for Qualifications ***********************   */
    /*                               ************************                            */

    @PostMapping("/addNewQualification")
    public ResponseEntity<WorkerQualification> addNewQualification(@RequestParam("name") String name,
                                                                   @RequestParam("description") String description){
     WorkerQualification wq = this.workerQualificationServiceInterface.addNewWorkerQualification(name,description);
     return new ResponseEntity<>(wq,HttpStatus.OK);
    }


    @GetMapping("/getQualification/{id}")
    public ResponseEntity<WorkerQualification> getQualification(@PathVariable("id") int id){

        WorkerQualification wq = this.workerQualificationServiceInterface.getQualificationById(id) ;
        return new ResponseEntity<>(wq,HttpStatus.OK);
    }

    @GetMapping("/getQualifications")
    public ResponseEntity<List<WorkerQualification>> getQualifications(){
        List<WorkerQualification> wq =this.workerQualificationServiceInterface.getAllQualifications();
        return new ResponseEntity<>(wq,HttpStatus.OK);
    }



    /*                               ************************                            */
    /*  ***************************   paths for Workshops      ***********************   */
    /*                               ************************                            */


   @PostMapping("/addNewWorkshop")
    public ResponseEntity<Workshop> addNewWorkshop(@RequestParam("wsName") String wsName,
                                                   @RequestParam("wsImage") String wsImage,
                                                   @RequestParam("isActive") String wsIsActive,
                                                   @RequestParam("wsDescription") String wsDescription,
                                                   @RequestParam("wsWorkers") List<Worker> wsWorkers,
                                                   @RequestParam("workerQualification") List<WorkerQualification> wsQualifications){

       boolean booleanWsIsActive = Boolean.parseBoolean(wsIsActive);
       Workshop workshop = this.workshopServiceInterface.addNewWorkshop(wsName, wsImage, booleanWsIsActive, wsDescription,wsWorkers,wsQualifications);
       return new ResponseEntity<>(workshop,HttpStatus.OK);

   }


   @GetMapping("/getWorkshopById/{wsId}")
    public ResponseEntity<Workshop> getWorkshopById(@PathVariable("wsId") int wsId){

       Workshop workshop = this.workshopServiceInterface.getWorkshopId(wsId);
       return new ResponseEntity<>(workshop,HttpStatus.OK);
   }

   @GetMapping("/getWorkshops")
    public ResponseEntity<List<Workshop>> getAllWorkshops(){
       List<Workshop> workshops = this.workshopServiceInterface.getAllWorkshops();

       return new ResponseEntity<>(workshops,HttpStatus.OK);
   }

   @DeleteMapping("/deleteWorkshop/{wsId}")
    public ResponseEntity<HttpResponse> deleteWorkshop(@PathParam("wsId") int wsId){
       this.workshopServiceInterface.deleteWorkshop(wsId);
       return  workshopResponse(HttpStatus.OK,"Workshop deleted successfully");
   }


   private ResponseEntity<HttpResponse> workshopResponse(HttpStatus httpStatus,String message){
       HttpResponse returnBody = new HttpResponse(httpStatus.value(),httpStatus, httpStatus.getReasonPhrase(), message.toUpperCase());
       return new ResponseEntity<>(returnBody,HttpStatus.OK);
   }



}
