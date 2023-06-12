package com.example.hogwartshouses.popoulate;

import com.example.hogwartshouses.model.*;
import com.example.hogwartshouses.service.RoomRepository;
import com.example.hogwartshouses.service.StudentRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
public class DatabasePopulation {

    @Bean
    ApplicationRunner populate(RoomRepository roomRepository, StudentRepository studentRepository) {
        return args -> {
            List<Room> rooms = getRooms();
            List<Student> students = getStudents();
            roomRepository.saveAll(rooms);
            studentRepository.saveAll(students);
            saveDefaultData(studentRepository);
        };
    }

    private static void saveDefaultData(StudentRepository studentRepository) {
       /* studentRepository.updateStudent(2, "Harry");
        studentRepository.updateStudent(2, "Neville");
        studentRepository.updateStudent(3, "Luna");*/
        studentRepository.findById(2,"Harry");
        studentRepository.findById(2, "Neville");
        studentRepository.findById(3, "Luna");

    }

    private List<Student> getStudents() {
        Student student1 = new Student("Harry", "Potter", HousePet.OWL, HouseType.GRYFFINDOR, true, Gender.MALE);
        Student student2 = new Student("Neville", "Longbottom", HousePet.TOAD, HouseType.GRYFFINDOR, true, Gender.MALE);
        Student student3 = new Student("Hermine", "Granger", HousePet.CAT, HouseType.GRYFFINDOR, false, Gender.FEMALE);
        Student student4 = new Student("Luna", "Lovegood", HousePet.CAT, HouseType.RAVENCLAW, true, Gender.FEMALE);
        Student student5 = new Student("Draco", "Melfoy", HousePet.OWL, HouseType.SLYTHERIN, false, Gender.MALE);
        Student student6 = new Student("Ron", "Weasly", HousePet.RAT, HouseType.GRYFFINDOR, false, Gender.MALE);
        return List.of(student1, student2, student3, student4, student5, student6);
    }

    private List<Room> getRooms() {
        Room room1 = new Room(4, HouseType.GRYFFINDOR, 2, Gender.FEMALE);
        Room room2 = new Room(6, HouseType.GRYFFINDOR, 3, Gender.MALE);
        Room room3 = new Room(4, HouseType.RAVENCLAW, 2, Gender.FEMALE);
        Room room4 = new Room(5, HouseType.RAVENCLAW, 2, Gender.MALE);
        Room room5 = new Room(3, HouseType.SLYTHERIN, 1, Gender.MALE);
        Room room6 = new Room(4, HouseType.HUFFLEPUFF, 1, Gender.MALE);
        return List.of(room1, room2, room3, room4, room5, room6);
    }
}
