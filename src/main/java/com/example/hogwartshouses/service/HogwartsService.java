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
        Student student = studentRepository.findByNameAndHasRoomNot(name);
        Optional<Long> roomId = roomRepository.findFirstBy(student.getGender(), student.getHouseType());
        studentRepository.findById(roomId.get());
        return studentRepository.findById(roomId.get(), name);
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
        Student student = studentRepository.findByNameAndHasRoomNot(name);
        Optional<Room> room = roomRepository.findRoomBy(student.getGender(),student.getHouseType());
        Optional<Boolean> cat = room.get().getStudents().stream()
                .map(student1 -> student1.getHousePet().equals(HousePet.CAT)).findFirst();
        if(cat.get().booleanValue() == false) {
            studentRepository.findById(room.get().getId());
            return studentRepository.findById(room.get().getId(), name);
        }else {
            return null;
        }
    }
}
