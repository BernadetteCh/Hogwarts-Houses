# Hogwarts_Houses

Hogwarts School of Witchcraft 
and Wizardry needs a little bit of my
Muggle technology, to make the 
management of this enormous castle 
less of a headache. Dumbledore asked me to create an API 
to assign Hogwarts students to a room of a specific house. 

### Built with
- Spring boot
- h2 Database
- Java

### Code Snippets

Hermine and Draco came too late and I have to assign them to there room of their Hogwarts house. I also have to consider that in Hogwarts the rooms are seperated between female students and male students. 

*add the name of the student as path of the endpoint to the url*
![assign Hermine](./img/assignHermineapipostman.png)
![assign Draco](./img/assignDracoapiPostman.png)

*Put request to assign Student to their correct house*
```js 
@PutMapping("assignStudent/{name}"){
    Student student = studentRepository.findStudentByFirstNameAndHasRoomFalse(name);
    Optional<Room> room = roomRepository.findRoomByGenderAndHouseType(student.getGender(), student.getHouseType());
return room.isPresent() ? studentRepository.assignStudentToRoom(room.get().getId(),name) : null;
    }
```

*In Roomrepository interface which extends the JpaRepository, 
methods and queries are responsible to find a room of their Hogwarts house,
which also has enough space for them.
So is Draco in Slytherin house and Hermine in a house of 
Gryffindor.*
```js
    Optional<Room> findRoomByGenderAndHouseType(Gender gender, HouseType houseType);

```
*At same time the amount of available 
beds of the room gets also updated*
```js
   @Modifying
   @Transactional
   @Query(value="UPDATE Room r SET r.availableBeds = r.availableBeds-1  WHERE r.id = :roomId")
   Integer updateRoom(@Param("roomId") long roomId);
```
Ron has a rat as house animal. 
So he can't stay in a room where one of the students has a cat.

Ron can be assigned as path of the url
![assign Ron](./img/ronapi.png)

Luckily neither Harry and Neville have a cat as house animal so Ron can stay with them

Before put request
![Ron assigned](./img/ron.png)
After put request
![Ron assigned](./img/ronassigned.png)

Ron is now assigned to the same room as Harry and Neville and his status of has_room is also updated.  

To connect the entity Student and entity Room with each other , the bidirectional association is used
```js
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue
    private long id;
    private int beds;
    private Gender gender;
    private HouseType houseType;
    private int availableBeds;

    @OneToMany(mappedBy = "room")
    private Set<Student> students;
```
```js
@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private HousePet housePet;
    private HouseType houseType;
    private Gender gender;

    public Gender getGender() {
        return gender;
    }

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;
```

### Tests
## Tests for the enpoints codesnippet
```js
  @Test
    void getAllRooms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get(allRooms)
                        .contentType(APPLICATION_JSON))
                .andExpect(status()
                        .isOk());
    }
 ```


```js
 @Test
    void saveStudent() throws Exception {
        Room room = new Room(4, HouseType.GRYFFINDOR,2, Gender.FEMALE);
        Student student = new Student("Harry", "Potter", HousePet.OWL, HouseType.GRYFFINDOR, true, Gender.MALE);
        student.setRoom(room);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(student);

        when(hogwartsService.saveStudent(student)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(createStudent)
                        .contentType(APPLICATION_JSON).content(json))
                .andExpect(status()
                        .isOk());
        verify(hogwartsService).saveStudent(student);
    }
```
## Test for the HogwartsService class codesnippet
@Test
```js
    @Test
    void assignStudent() {
        Room room = new Room(4, HouseType.GRYFFINDOR, 2, Gender.FEMALE);
        Student student = new Student("Hermine", "Granger", HousePet.CAT, HouseType.GRYFFINDOR, false, Gender.FEMALE);
        room.setStudents(Set.of(student));
        when(studentRepository.findStudentByFirstNameAndHasRoomFalse(student.getFirstName())).thenReturn(student);
        when(roomRepository.findRoomByGenderAndHouseType(student.getGender(), student.getHouseType())).thenReturn(Optional.of(room));
        assertEquals((int) room.getId(), hogwartsService.assignStudent(student.getFirstName()));
    }
```