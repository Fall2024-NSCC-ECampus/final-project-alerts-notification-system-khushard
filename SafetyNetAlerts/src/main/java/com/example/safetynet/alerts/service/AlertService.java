package com.example.safetynet.alerts.service;

import com.example.safetynet.alerts.model.FireStation;
import com.example.safetynet.alerts.model.Person;
import com.example.safetynet.alerts.repositories.FireStationRepository;
import com.example.safetynet.alerts.repositories.PersonRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

// The service layer that handles the business logic for our emergency alerts.
@Service
public class AlertService {
    private final PersonRepository personRepository;
    private final FireStationRepository fireStationRepository;

    // Constructor dependency injection
    public AlertService(PersonRepository personRepository, FireStationRepository fireStationRepository) {
        this.personRepository = personRepository;
        this.fireStationRepository = fireStationRepository;
    }

    // Initializes sample data when the app starts up. Using H2 database so this gives us data to test with on boot.
    @PostConstruct
    public void loadSampleData() {
        if (personRepository.count() > 0) {
            return;
        }

        // Creating person sample data.
        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Smith");
        person1.setAddress("123 Main St");
        person1.setPhone("555-1234");
        person1.setAge(25);
        person1.setEmail("john@email.com");
        person1.setMedications(Arrays.asList("med1:100mg"));
        person1.setAllergies(Arrays.asList("pollen"));
        personRepository.save(person1);

        // Creating another person sample data.
        Person person2 = new Person();
        person2.setFirstName("Jane");
        person2.setLastName("Doe");
        person2.setAddress("123 Main St");
        person2.setPhone("555-5678");
        person2.setAge(12);
        person2.setEmail("jane@email.com");
        person2.setMedications(new ArrayList<>());
        person2.setAllergies(Arrays.asList("nuts"));
        personRepository.save(person2);

        // Creating firestation sample data.
        FireStation station1 = new FireStation();
        station1.setAddress("123 Main St");
        station1.setStation(1);
        fireStationRepository.save(station1);
    }

    // This function gets all the people that are serviced by a specific fire station.
    public Map<String, Object> getPeopleByStation(int stationNumber) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, String>> residents = new ArrayList<>();
        int adults = 0, children = 0;

        // Get the addresses serviced by the station.
        List<String> stationAddresses = fireStationRepository.findByStation(stationNumber)
                .stream()
                .map(FireStation::getAddress)
                .toList();

        // Process the info for each resident
        for (String address : stationAddresses) {
            for (Person person : personRepository.findByAddress(address)) {
                Map<String, String> personInfo = new HashMap<>();
                personInfo.put("firstName", person.getFirstName());
                personInfo.put("lastName", person.getLastName());
                personInfo.put("address", person.getAddress());
                personInfo.put("phone", person.getPhone());
                residents.add(personInfo);

                // Determine if children are present.
                if (person.getAge() <= 18) children++;
                else adults++;
            }
        }

        result.put("residents", residents);
        result.put("adultCount", adults);
        result.put("childCount", children);
        return result;
    }

    //Gets children and any other resident that are living at an address.
    // Returns a map that has two lists, one for children and one for adults
    public Map<String, Object> getChildrenAtAddress(String address) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> children = new ArrayList<>();
        List<Map<String, Object>> adults = new ArrayList<>();

        for (Person person : personRepository.findByAddress(address)) {
            Map<String, Object> personInfo = new HashMap<>();
            personInfo.put("firstName", person.getFirstName());
            personInfo.put("lastName", person.getLastName());
            personInfo.put("age", person.getAge());

            if (person.getAge() <= 18) {
                children.add(personInfo);
            } else {
                adults.add(personInfo);
            }
        }

        result.put("children", children);
        result.put("otherResidents", adults);
        return result;
    }

    // Gets all phone numbers for people serviced by a specific fire station.
    // Used for emergency text notifications
    public List<String> getPhonesByStation(int stationNumber) {
        List<String> phones = new ArrayList<>();

        //Gets all addresses served by the station
        List<String> addresses = fireStationRepository.findByStation(stationNumber)
                .stream()
                .map(FireStation::getAddress)
                .toList();

        // Collects and stores the phone numbers for each address in a list
        for (String address : addresses) {
            phones.addAll(personRepository.findByAddress(address)
                    .stream()
                    .map(Person::getPhone)
                    .toList());
        }

        return phones;
    }

    // Gets detailed information about a person, including their medical details.
    public Map<String, Object> getPersonInfo(String firstName, String lastName) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> personList = new ArrayList<>();

        for (Person person : personRepository.findByFirstNameAndLastName(firstName, lastName)) {
            Map<String, Object> personInfo = new HashMap<>();
            personInfo.put("name", person.getFirstName() + " " + person.getLastName());
            personInfo.put("address", person.getAddress());
            personInfo.put("age", person.getAge());
            personInfo.put("email", person.getEmail());
            personInfo.put("medications", person.getMedications());
            personInfo.put("allergies", person.getAllergies());
            personList.add(personInfo);
        }

        result.put("persons", personList);
        return result;
    }
}

