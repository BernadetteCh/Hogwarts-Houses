package com.example.hogwartshouses.endpoint;

import com.example.hogwartshouses.model.*;
import com.example.hogwartshouses.service.HogwartsService;
import com.example.hogwartshouses.service.RoomRepository;
import com.example.hogwartshouses.service.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HogwartsEndpoints.class)
class HogwartsEndpointsTest {

    @MockBean
    HogwartsService hogwartsService;
    @MockBean
    RoomRepository roomRepository;
    @MockBean
    StudentRepository studentRepository;
    @Autowired
    MockMvc mockMvc;


    String allRooms = "/rooms";
    String createRoom = "/create-room";
    String createStudent = "/create-student";
    String assignStudent = "/assignStudent/Hermine";
    String ratOwners = "/rooms/rat-owners/Ron";

    @Test
    void getAllRooms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get(allRooms)
                        .contentType(APPLICATION_JSON))
                .andExpect(status()
                        .isOk());
    }

    @Test
    void saveRoom() throws Exception {
        Room room = new Room(4, HouseType.GRYFFINDOR, 2, Gender.FEMALE);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(room);

        when(hogwartsService.saveRoom(room)).thenReturn(room);

        mockMvc.perform(MockMvcRequestBuilders.post(createRoom).contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        verify(hogwartsService).saveRoom(room);

    }

    @Test
    void saveStudent() throws Exception {
        Room room = new Room(4, HouseType.GRYFFINDOR, 2, Gender.MALE);
        Student student = new Student("Harry", "Potter", HousePet.OWL, HouseType.GRYFFINDOR, false, Gender.MALE);
        student.setRoom(room);

        String json = """
                {"id":0,"firstName":"Harry",
                "lastName":"Potter",
                "housePet":"OWL",
                "houseType":"GRYFFINDOR",
                "gender":"MALE",
                "hasRoom":false,
                "room":{"id":0,"beds":4,"gender":"MALE","houseType":"GRYFFINDOR","availableBeds":2}}               
                 """;
        when(hogwartsService.saveStudent(student)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(createStudent)
                        .contentType(APPLICATION_JSON).content(json))
                .andExpect(status()
                        .isOk());
        verify(hogwartsService).saveStudent(student);
    }

    @Test
    void assignStudentToHouse() throws Exception {
        Room room = new Room(4, HouseType.GRYFFINDOR, 2, Gender.FEMALE);
        Student student = new Student("Hermine", "Granger", HousePet.OWL, HouseType.GRYFFINDOR, false, Gender.FEMALE);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(student);

        when(hogwartsService.assignStudent(student.getFirstName())).thenReturn((int) room.getId());

        mockMvc.perform(MockMvcRequestBuilders.put(assignStudent).contentType(APPLICATION_JSON).content(json)).andExpect(status().isOk());
        verify(hogwartsService).assignStudent(student.getFirstName());

    }

    @Test
    void assignRonToHouse() throws Exception {
        Room room = new Room(4, HouseType.GRYFFINDOR, 2, Gender.MALE);
        Student student = new Student("Ron", "Weasly", HousePet.RAT, HouseType.GRYFFINDOR, false, Gender.MALE);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(student);

        when(hogwartsService.findARoomForRatOwner(student.getFirstName())).thenReturn((int) room.getId());

        mockMvc.perform(MockMvcRequestBuilders.put(ratOwners).contentType(APPLICATION_JSON).content(json)).andExpect(status().isOk());

        verify(hogwartsService).findARoomForRatOwner(student.getFirstName());


    }
}