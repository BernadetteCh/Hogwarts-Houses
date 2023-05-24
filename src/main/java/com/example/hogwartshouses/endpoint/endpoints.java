package com.example.hogwartshouses.endpoint;


import com.example.hogwartshouses.model.*;
import com.example.hogwartshouses.service.HogwartsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class endpoints {
    private final HogwartsService studentService;

    public endpoints(HogwartsService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("empty-rooms")
    public List<Room> getAllEmptyRooms(){
        return studentService.findAllRooms();
    }

    @PostMapping("create-room")
    public Room saveRoom(@RequestBody Room room) {
        return studentService.saveRoom(room);

    }

    @PostMapping("create-student")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);

    }

    @PutMapping("assignStudent/{name}")
    public Integer assignStudentToHouse(@PathVariable String name){
        return studentService.assignStudent(name);
    }

    @PutMapping("rooms/rat-owners/{name}")
    public Integer assignRonToHouse(@PathVariable String name) {
       return studentService.findARoomForRatOwner(name);
    }
}
