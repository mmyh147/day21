package com.example.eventsystem.Controller;

import com.example.eventsystem.ApiResponse.ApiResponse;
import com.example.eventsystem.Model.EventSystem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/event")
public class EventController {


    ArrayList<EventSystem> events = new ArrayList<>();


    @PostMapping("post")
    public ApiResponse post(@RequestBody EventSystem event) {

        events.add(event);
        return new ApiResponse("the new event successfully added! ");
    }


    @GetMapping("events")
    public ArrayList<EventSystem> getEvents() {
        return events;
    }



    @PutMapping("update/{ID}")
    public ApiResponse updateProject(@PathVariable String ID, @RequestBody EventSystem updatedProject) {

        for (EventSystem event : events) {

            if (event.getID() != null && event.getID().equals(ID)) {

                events.set(events.indexOf(event), updatedProject);

                return new ApiResponse("the event has been updated successfully");
            }
        }
        return new ApiResponse("The ID not found!");
    }


    @DeleteMapping("delete/{ID}")
    public ApiResponse deleteProject(@PathVariable String ID) {

        for (EventSystem event : events) {
            if (event.getID() != null && event.getID().equals(ID)) {
                events.remove(event);
                return new ApiResponse("The event has been deleted!");

            }
        }
        return new ApiResponse("No event found with ID : " + ID);
    }


    @PostMapping("change/{ID}/{capacity}")
    public ApiResponse changeCapacity(@PathVariable String ID, @PathVariable int capacity){
        for (EventSystem event : events) {
            if (event.getID() != null && event.getID().equals(ID)) {
                event.setCapacity(capacity);
                return new ApiResponse("The event capacity has been changed!");

            }
        }
        return new ApiResponse("No event found with ID : " + ID);

    }

    @GetMapping("search/{ID}")
    public EventSystem getByTitle(@PathVariable String ID){
        return events.stream().filter(event -> event.getID().equals(ID))
                .findFirst()
                .orElse(null);
    }




}
