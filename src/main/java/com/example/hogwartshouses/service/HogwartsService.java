package com.example.hogwartshouses.service;

import com.example.hogwartshouses.model.HousePet;
import com.example.hogwartshouses.model.Room;
import com.example.hogwartshouses.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HogwartsService {
    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;

    public HogwartsService(StudentRepository studentRepository, RoomRepository roomRepository) {
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;

    }

    public Integer assignStudent(String name) {
        Student student = studentRepository.findStudentByFirstNameAndHasRoomFalse(name);
        Optional<Room> room = roomRepository.findRoomByGenderAndHouseType(student.getGender(), student.getHouseType());
        return room.isPresent() ? studentRepository.assignStudentToRoom(room.get().getId(),name) : null;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }


    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }


    public Integer findARoomForRatOwner(String name) {
        Student student = studentRepository.findStudentByFirstNameAndHasRoomFalse(name);
        Optional<Room> room = roomRepository.findRoomByGenderAndHouseType(student.getGender(),student.getHouseType());
        System.out.println(room.get());
        Optional<Boolean> cat = room.get().getStudents().stream()
                .map(student1 -> student1.getHousePet().equals(HousePet.CAT)).findFirst();
        System.out.println(cat.get().booleanValue()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
        if(cat.get().booleanValue() == false) {
            studentRepository.findById(room.get().getId());
            roomRepository.updateRoom(room.get().getId());
            return studentRepository.assignStudentToRoom(room.get().getId(), student.getFirstName());
        }else {
            return null;
        }
    }

}
