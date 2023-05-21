package com.example.hogwartshouses.endpoint;


import com.example.hogwartshouses.model.*;
import com.example.hogwartshouses.service.RoomRepository;
import com.example.hogwartshouses.service.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class endpoints {

    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;

    public endpoints(RoomRepository roomRepository, StudentRepository studentRepository) {
        this.roomRepository = roomRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("empty-rooms")
    public List<Room> getAllEmptyRooms(){
        return roomRepository.findAll();
    }

    @PostMapping("create-room")
    public Room saveRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @PostMapping("create-student")
    public Student saveStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("assignStudent/{name}")
    public Integer assignStudentToHouse(@PathVariable String name){
        Student student = studentRepository.findByNameAndHasRoomNot(name);
        Optional<Long> roomId = roomRepository.findFirstBy(student.getGender(), student.getHouseType());
        studentRepository.findById(roomId.get());
        return studentRepository.findById(roomId.get(), name);
    }

    @PutMapping("rooms/rat-owners/{name}")
    public Integer assignRonToHouse(@PathVariable String name) {
        Student student = studentRepository.findByNameAndHasRoomNot(name);
        Optional<Room> room = roomRepository.findRoomBy(student.getGender(),student.getHouseType());
        Optional<Boolean> cat = room.get().getStudents().stream()
                .map(student1 -> student1.getHousePet().equals(HousePet.CAT)).findFirst();
       System.out.println(cat.get().booleanValue()+ "!!!!!!!!!!!!!!!!!!!!");
        if(cat.get().booleanValue() == false) {
            studentRepository.findById(room.get().getId());
            return studentRepository.findById(room.get().getId(), name);
        }else {
            return null;
        }
    }
}
