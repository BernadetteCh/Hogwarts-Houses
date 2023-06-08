package com.example.hogwartshouses.endpoint;


import com.example.hogwartshouses.model.*;
import com.example.hogwartshouses.service.HogwartsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class HogwartsEndpoints {
    private final HogwartsService hogwartsService;

    public HogwartsEndpoints(HogwartsService hogwartsService) {
        this.hogwartsService = hogwartsService;
    }

    @GetMapping("rooms")
    public List<Room> getAll(){
        return hogwartsService.findAllRooms();
    }

    @PostMapping("create-room")
    public Room saveRoom(@RequestBody Room room) {
        return hogwartsService.saveRoom(room);
    }

    @PostMapping("create-student")
    public Student saveStudent(@RequestBody Student student) {
        return hogwartsService.saveStudent(student);
    }

    @PutMapping("assignStudent/{name}")
    public Integer assignStudentToHouse(@PathVariable String name){
        return hogwartsService.assignStudent(name);
    }

    @PutMapping("rooms/rat-owners/{name}")
    public Integer assignRonToHouse(@PathVariable String name) {
       return hogwartsService.findARoomForRatOwner(name);
    }
}
