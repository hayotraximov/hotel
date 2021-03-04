package uz.raximov.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.hotel.Entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
