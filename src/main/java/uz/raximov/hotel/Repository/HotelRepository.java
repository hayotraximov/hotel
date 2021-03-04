package uz.raximov.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.hotel.Entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
