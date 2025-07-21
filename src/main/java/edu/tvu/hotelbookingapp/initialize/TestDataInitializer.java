package edu.tvu.hotelbookingapp.initialize;

import edu.tvu.hotelbookingapp.model.*;
import edu.tvu.hotelbookingapp.model.enums.RoleType;
import edu.tvu.hotelbookingapp.model.enums.RoomType;
import edu.tvu.hotelbookingapp.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final HotelManagerRepository hotelManagerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final HotelRepository hotelRepository;
    private final AvailabilityRepository availabilityRepository;

    @Override
    @Transactional
    public void run(String... args) {

        try {
            log.warn("Checking if test data persistence is required...");

            if (roleRepository.count() == 0 && userRepository.count() == 0) {
                log.info("Initiating test data persistence");

                Role adminRole = new Role(RoleType.ADMIN);
                Role customerRole = new Role(RoleType.CUSTOMER);
                Role hotelManagerRole = new Role(RoleType.HOTEL_MANAGER);

                roleRepository.save(adminRole);
                roleRepository.save(customerRole);
                roleRepository.save(hotelManagerRole);
                log.info("Role data persisted");

                User user1 = User.builder().username("admin@hotel.com").password(passwordEncoder.encode("1")).name("Admin").lastName("Admin").role(adminRole).build();
                User user2 = User.builder().username("customer1@hotel.com").password(passwordEncoder.encode("1")).name("Customer").lastName("1").role(customerRole).build();
                User user3 = User.builder().username("manager1@hotel.com").password(passwordEncoder.encode("1")).name("Manager").lastName("1").role(hotelManagerRole).build();
                User user4 = User.builder().username("manager2@hotel.com").password(passwordEncoder.encode("1")).name("Manager").lastName("2").role(hotelManagerRole).build();

                userRepository.save(user1);
                userRepository.save(user2);
                userRepository.save(user3);
                userRepository.save(user4);

                Admin admin1 = Admin.builder().user(user1).build();
                Customer c1 = Customer.builder().user(user2).build();
                HotelManager hm1 = HotelManager.builder().user(user3).build();
                HotelManager hm2 = HotelManager.builder().user(user4).build();

                adminRepository.save(admin1);
                customerRepository.save(c1);
                hotelManagerRepository.save(hm1);
                hotelManagerRepository.save(hm2);
                log.info("User data persisted");

                Address addressIst1 = Address.builder().addressLine("2A-4A Tôn Đức Thắng, Quận 1").city("Hồ Chí Minh")
                        .country("Việt Nam").build();
                Address addressIst2 = Address.builder().addressLine("127 Pasteur, Quận 3").city("Hồ Chí Minh")
                        .country("Việt Nam").build();
                Address addressIst3 = Address.builder().addressLine("201/1 Lý Tự Trọng, Quận 1").city("Hồ Chí Minh")
                        .country("Việt Nam").build();
                Address addressIst4 = Address.builder().addressLine("22 Lý Chính Thắng, Quận 3").city("Hồ Chí Minh")
                        .country("Việt Nam").build();
                Address addressIst5 = Address.builder().addressLine("6 Trương Định, Quận 3").city("Hồ Chí Minh")
                        .country("Việt Nam").build();
                Address addressIst6 = Address.builder().addressLine("22-36 Nguyễn Huệ, Quận 1").city("Hồ Chí Minh")
                        .country("Việt Nam").build();
                Address addressIst7 = Address.builder().addressLine("11 Lê Phụng Hiểu, Hoàn Kiếm").city("Hà Nội")
                        .country("Việt Nam").build();
                Address addressIst8 = Address.builder().addressLine("15 Ngô Quyền, Hoàn Kiếm").city("Hà Nội")
                        .country("Việt Nam").build();
                Address addressIst9 = Address.builder().addressLine("94 Mã Mây, Hoàn Kiếm").city("Hà Nội")
                        .country("Việt Nam").build();
                Address addressIst10 = Address.builder().addressLine("88 Lê Lợi, Phường 2").city("Trà Vinh")
                        .country("Việt Nam").build();
                Address addressIst11 = Address.builder().addressLine("38/2 Nguyễn Thị Minh Khai, Phường 2").city("Trà Vinh")
                        .country("Việt Nam").build();
                Address addressIst12 = Address.builder().addressLine("49 Lê Lợi, Phường 2").city("Trà Vinh")
                        .country("Việt Nam").build();

                addressRepository.save(addressIst1);
                addressRepository.save(addressIst2);
                addressRepository.save(addressIst3);
                addressRepository.save(addressIst4);
                addressRepository.save(addressIst5);
                addressRepository.save(addressIst6);
                addressRepository.save(addressIst7);
                addressRepository.save(addressIst8);
                addressRepository.save(addressIst9);
                addressRepository.save(addressIst10);
                addressRepository.save(addressIst11);
                addressRepository.save(addressIst12);

                Hotel hotelIst1 = Hotel.builder().name("Lotte Legend Hotel Saigon")
                        .address(addressIst1).hotelManager(hm1).build();
                Hotel hotelIst2 = Hotel.builder().name("Sherwood Residence")
                        .address(addressIst2).hotelManager(hm1).build();
                Hotel hotelIst3 = Hotel.builder().name("Cochin Sang Hotel")
                        .address(addressIst3).hotelManager(hm1).build();
                Hotel hotelIst4 = Hotel.builder().name("Hồng Hạc Hotel")
                        .address(addressIst4).hotelManager(hm2).build();
                Hotel hotelIst5 = Hotel.builder().name("A25 Hotel - Trương Định")
                        .address(addressIst5).hotelManager(hm2).build();
                Hotel hotelIst6 = Hotel.builder().name("The Reverie Saigon")
                        .address(addressIst6).hotelManager(hm2).build();
                Hotel hotelIst7 = Hotel.builder().name("Capella Hanoi")
                        .address(addressIst7).hotelManager(hm2).build();
                Hotel hotelIst8 = Hotel.builder().name("Sofitel Legend Metropole Hanoi")
                        .address(addressIst8).hotelManager(hm2).build();
                Hotel hotelIst9 = Hotel.builder().name("Hanoi La Siesta Hotel & Spa")
                        .address(addressIst9).hotelManager(hm2).build();
                Hotel hotelIst10 = Hotel.builder().name("Tra Vinh Lodge")
                        .address(addressIst10).hotelManager(hm2).build();
                Hotel hotelIst11 = Hotel.builder().name("Vila Basi")
                        .address(addressIst11).hotelManager(hm2).build();
                Hotel hotelIst12 = Hotel.builder().name("Bich Ngoan Hotel")
                        .address(addressIst12).hotelManager(hm2).build();

                Room singleRoomIst1 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(370).roomCount(35).hotel(hotelIst1).build();
                Room doubleRoomIst1 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(459).roomCount(45).hotel(hotelIst1).build();

                Room singleRoomIst2 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(700).roomCount(25).hotel(hotelIst2).build();
                Room doubleRoomIst2 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(890).roomCount(30).hotel(hotelIst2).build();

                Room singleRoomIst3 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(691).roomCount(30).hotel(hotelIst3).build();
                Room doubleRoomIst3 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(800).roomCount(75).hotel(hotelIst3).build();

                Room singleRoomIst4 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(120.0).roomCount(25).hotel(hotelIst4).build();
                Room doubleRoomIst4 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(250.0).roomCount(15).hotel(hotelIst4).build();

                Room singleRoomIst5 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(300).roomCount(50).hotel(hotelIst5).build();
                Room doubleRoomIst5 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(400).roomCount(50).hotel(hotelIst5).build();

                Room singleRoomIst6 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(179).roomCount(45).hotel(hotelIst6).build();
                Room doubleRoomIst6 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(256).roomCount(25).hotel(hotelIst6).build();

                Room singleRoomIst7 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(179).roomCount(45).hotel(hotelIst7).build();
                Room doubleRoomIst7 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(256).roomCount(25).hotel(hotelIst7).build();

                Room singleRoomIst8 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(179).roomCount(45).hotel(hotelIst8).build();
                Room doubleRoomIst8 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(256).roomCount(25).hotel(hotelIst8).build();

                Room singleRoomIst9 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(179).roomCount(45).hotel(hotelIst9).build();
                Room doubleRoomIst9 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(256).roomCount(25).hotel(hotelIst9).build();

                Room singleRoomIst10 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(179).roomCount(45).hotel(hotelIst10).build();
                Room doubleRoomIst10 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(256).roomCount(25).hotel(hotelIst10).build();

                Room singleRoomIst11 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(179).roomCount(45).hotel(hotelIst11).build();
                Room doubleRoomIst11 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(256).roomCount(25).hotel(hotelIst11).build();

                Room singleRoomIst12 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(179).roomCount(45).hotel(hotelIst12).build();
                Room doubleRoomIst12 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(256).roomCount(25).hotel(hotelIst12).build();

                hotelIst1.getRooms().addAll(Arrays.asList(singleRoomIst1,doubleRoomIst1));
                hotelIst2.getRooms().addAll(Arrays.asList(singleRoomIst2,doubleRoomIst2));
                hotelIst3.getRooms().addAll(Arrays.asList(singleRoomIst3,doubleRoomIst3));
                hotelIst4.getRooms().addAll(Arrays.asList(singleRoomIst4,doubleRoomIst4));
                hotelIst5.getRooms().addAll(Arrays.asList(singleRoomIst5,doubleRoomIst5));
                hotelIst6.getRooms().addAll(Arrays.asList(singleRoomIst6,doubleRoomIst6));
                hotelIst7.getRooms().addAll(Arrays.asList(singleRoomIst7,doubleRoomIst7));
                hotelIst8.getRooms().addAll(Arrays.asList(singleRoomIst8,doubleRoomIst8));
                hotelIst9.getRooms().addAll(Arrays.asList(singleRoomIst9,doubleRoomIst9));
                hotelIst10.getRooms().addAll(Arrays.asList(singleRoomIst10,doubleRoomIst10));
                hotelIst11.getRooms().addAll(Arrays.asList(singleRoomIst11,doubleRoomIst11));
                hotelIst12.getRooms().addAll(Arrays.asList(singleRoomIst12,doubleRoomIst12));



                hotelRepository.save(hotelIst1);
                hotelRepository.save(hotelIst2);
                hotelRepository.save(hotelIst3);
                hotelRepository.save(hotelIst4);
                hotelRepository.save(hotelIst5);
                hotelRepository.save(hotelIst6);
                hotelRepository.save(hotelIst7);
                hotelRepository.save(hotelIst8);
                hotelRepository.save(hotelIst9);
                hotelRepository.save(hotelIst10);
                hotelRepository.save(hotelIst11);
                hotelRepository.save(hotelIst12);

                log.info("Hotel data persisted");

                Availability av1Berlin1 = Availability.builder().hotel(hotelIst1)
                        .date(LocalDate.of(2026,9,1)).room(singleRoomIst1).availableRooms(20).build();
                Availability av2Berlin1 = Availability.builder().hotel(hotelIst1)
                        .date(LocalDate.of(2026,9,2)).room(doubleRoomIst1).availableRooms(25).build();
                Availability av1Berlin2 = Availability.builder().hotel(hotelIst2)
                        .date(LocalDate.of(2026,9,1)).room(singleRoomIst2).availableRooms(20).build();
                Availability av2Berlin2 = Availability.builder().hotel(hotelIst2)
                        .date(LocalDate.of(2026,9,2)).room(doubleRoomIst2).availableRooms(25).build();
                Availability av1Berlin3 = Availability.builder().hotel(hotelIst3)
                        .date(LocalDate.of(2026,9,1)).room(singleRoomIst3).availableRooms(20).build();
                Availability av2Berlin3 = Availability.builder().hotel(hotelIst3)
                        .date(LocalDate.of(2026,9,2)).room(doubleRoomIst3).availableRooms(25).build();
                Availability av1Berlin4 = Availability.builder().hotel(hotelIst4)
                        .date(LocalDate.of(2026,9,1)).room(singleRoomIst4).availableRooms(20).build();
                Availability av2Berlin4 = Availability.builder().hotel(hotelIst4)
                        .date(LocalDate.of(2026,9,2)).room(doubleRoomIst4).availableRooms(25).build();
                Availability av1Berlin5 = Availability.builder().hotel(hotelIst5)
                        .date(LocalDate.of(2026,9,1)).room(singleRoomIst5).availableRooms(20).build();
                Availability av2Berlin5 = Availability.builder().hotel(hotelIst5)
                        .date(LocalDate.of(2026,9,2)).room(doubleRoomIst5).availableRooms(25).build();
                Availability av1Berlin6 = Availability.builder().hotel(hotelIst6)
                        .date(LocalDate.of(2026,9,1)).room(singleRoomIst6).availableRooms(20).build();
                Availability av2Berlin6 = Availability.builder().hotel(hotelIst6)
                        .date(LocalDate.of(2026,9,2)).room(doubleRoomIst6).availableRooms(25).build();
                Availability av1Berlin7 = Availability.builder().hotel(hotelIst7)
                        .date(LocalDate.of(2026,9,1)).room(singleRoomIst7).availableRooms(20).build();
                Availability av2Berlin7 = Availability.builder().hotel(hotelIst7)
                        .date(LocalDate.of(2026,9,2)).room(doubleRoomIst7).availableRooms(25).build();
                Availability av1Berlin8 = Availability.builder().hotel(hotelIst8)
                        .date(LocalDate.of(2026,9,1)).room(singleRoomIst8).availableRooms(20).build();
                Availability av2Berlin8 = Availability.builder().hotel(hotelIst8)
                        .date(LocalDate.of(2026,9,2)).room(doubleRoomIst8).availableRooms(25).build();
                Availability av1Berlin9 = Availability.builder().hotel(hotelIst9)
                        .date(LocalDate.of(2026,9,1)).room(singleRoomIst9).availableRooms(20).build();
                Availability av2Berlin9 = Availability.builder().hotel(hotelIst9)
                        .date(LocalDate.of(2026,9,2)).room(doubleRoomIst9).availableRooms(25).build();
                Availability av1Berlin10 = Availability.builder().hotel(hotelIst10)
                        .date(LocalDate.of(2026,9,1)).room(singleRoomIst10).availableRooms(20).build();
                Availability av2Berlin10 = Availability.builder().hotel(hotelIst10)
                        .date(LocalDate.of(2026,9,2)).room(doubleRoomIst10).availableRooms(25).build();
                Availability av1Berlin11 = Availability.builder().hotel(hotelIst11)
                        .date(LocalDate.of(2026,9,1)).room(singleRoomIst11).availableRooms(20).build();
                Availability av2Berlin11 = Availability.builder().hotel(hotelIst11)
                        .date(LocalDate.of(2026,9,2)).room(doubleRoomIst11).availableRooms(25).build();
                Availability av1Berlin12 = Availability.builder().hotel(hotelIst12)
                        .date(LocalDate.of(2026,9,1)).room(singleRoomIst12).availableRooms(20).build();
                Availability av2Berlin12 = Availability.builder().hotel(hotelIst12)
                        .date(LocalDate.of(2026,9,2)).room(doubleRoomIst12).availableRooms(25).build();

                availabilityRepository.save(av1Berlin1);
                availabilityRepository.save(av2Berlin1);
                availabilityRepository.save(av1Berlin2);
                availabilityRepository.save(av2Berlin2);
                availabilityRepository.save(av1Berlin3);
                availabilityRepository.save(av2Berlin3);
                availabilityRepository.save(av1Berlin4);
                availabilityRepository.save(av2Berlin4);
                availabilityRepository.save(av1Berlin5);
                availabilityRepository.save(av2Berlin5);
                availabilityRepository.save(av1Berlin6);
                availabilityRepository.save(av2Berlin6);
                availabilityRepository.save(av1Berlin7);
                availabilityRepository.save(av2Berlin7);
                availabilityRepository.save(av1Berlin8);
                availabilityRepository.save(av2Berlin8);
                availabilityRepository.save(av1Berlin9);
                availabilityRepository.save(av2Berlin9);
                availabilityRepository.save(av1Berlin10);
                availabilityRepository.save(av2Berlin10);
                availabilityRepository.save(av1Berlin11);
                availabilityRepository.save(av2Berlin11);
                availabilityRepository.save(av1Berlin12);
                availabilityRepository.save(av2Berlin12);
                log.info("Availability data persisted");

            } else {
                log.info("Test data persistence is not required");
            }
            log.warn("App ready");
        } catch (DataAccessException e) {
            log.error("Exception occurred during data persistence: " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected exception occurred: " + e.getMessage());
        }
    }
}
