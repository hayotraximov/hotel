package uz.raximov.hotel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.raximov.hotel.Entity.Hotel;
import uz.raximov.hotel.Entity.Room;
import uz.raximov.hotel.Repository.HotelRepository;
import uz.raximov.hotel.Repository.RoomRepository;
import uz.raximov.hotel.layout.RoomDTO;

import java.util.Optional;

@RestController
@RequestMapping
public class RoomController {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/")
    public Page<Room> getHotel(@RequestParam Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        return roomRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Room getHotelById(@PathVariable Integer id){
        Optional<Room> roomOptional = roomRepository.findById(id);
        return roomOptional.orElseGet(Room::new);
    }

    @DeleteMapping("{id}")
    public String deleteHotelById(@PathVariable Integer id){
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()){
            roomRepository.delete(roomOptional.get());
            return "O'chirildi";
        }
        return "O'chirilmadi";
    }

    @PostMapping("/")
    public String addHotel(@RequestBody RoomDTO roomDTO){
        Optional<Hotel> hotelOptional = hotelRepository.findById(roomDTO.getHotelid());
        if (hotelOptional.isPresent()) {
            Room room = new Room();
            room.setHotel(hotelOptional.get());
            room.setFloor(roomDTO.getFloor());
            room.setNumber(roomDTO.getNumber());
            room.setSize(roomDTO.getSize());
            roomRepository.save(room);
            return "Qo'shildi";
        }
        return "Qo'shilmadi";
    }

    @PutMapping("/{id}")
    public String editHotel(@RequestBody RoomDTO roomDTO, @PathVariable Integer id){
        Optional<Hotel> hotelOptional = hotelRepository.findById(roomDTO.getHotelid());
        Optional<Room> byId = roomRepository.findById(id);
        if (hotelOptional.isPresent() && byId.isPresent()) {
            Room room = byId.get();
            room.setHotel(hotelOptional.get());
            room.setFloor(roomDTO.getFloor());
            room.setNumber(roomDTO.getNumber());
            room.setSize(roomDTO.getSize());
            roomRepository.save(room);
            return "Saqlandi!";
        }
        return "O'zgartirilmadi!";
    }

}
