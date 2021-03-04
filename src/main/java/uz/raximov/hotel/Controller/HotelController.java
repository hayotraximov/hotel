package uz.raximov.hotel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.raximov.hotel.Entity.Hotel;
import uz.raximov.hotel.Repository.HotelRepository;

import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/")
    public Page<Hotel> getHotel(@RequestParam Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        return hotelRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Integer id){
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        return hotelOptional.orElseGet(Hotel::new);
    }

    @DeleteMapping("{id}")
    public String deleteHotelById(@PathVariable Integer id){
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()){
            hotelRepository.delete(hotelOptional.get());
            return "O'chirildi";
        }
        return "O'chirilmadi";
    }

    @PostMapping("/add")
    public String addHotel(@RequestBody Hotel hotel){
        hotelRepository.save(hotel);
        return "Qo'shildi";
    }

    @PutMapping("/{id}")
    public String editHotel(@RequestBody Hotel hotel, @PathVariable Integer id){
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()) {
            Hotel hotel1 = byId.get();
            hotel1.setName(hotel.getName());
            hotelRepository.save(hotel1);
            return "O'zgartirildi";
        }
        return "O'zgartirilmadi";
    }
}
