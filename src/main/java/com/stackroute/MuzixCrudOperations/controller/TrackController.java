package com.stackroute.MuzixCrudOperations.controller;

import com.stackroute.MuzixCrudOperations.domain.Track;
import com.stackroute.MuzixCrudOperations.expections.TrackAlreadyExistsException;
import com.stackroute.MuzixCrudOperations.expections.TrackNotFoundException;
import com.stackroute.MuzixCrudOperations.service.TrackService;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.core.MongoTemplate;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController
{
    TrackService trackService;


    public TrackController(TrackService trackService)
    {
        this.trackService = trackService;
    }
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);

        } catch (TrackAlreadyExistsException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
           // ex.printStackTrace();
        }

        return responseEntity;
    }

    @PostMapping("tracksLists")
    public ResponseEntity<?> saveTracks(@RequestBody List<Track> tracks) throws TrackAlreadyExistsException
    {
        ResponseEntity responseEntity;
        for(Track t1 : tracks)
        {
            trackService.saveTrack(t1);
        }
        responseEntity = new ResponseEntity<String>("successfully created",HttpStatus.CREATED);
        return responseEntity;
    }
    @GetMapping("track")
    public ResponseEntity<?> getAllTracks()
    {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable(value = "id") Integer id)
    {
        ResponseEntity responseEntity;
        try {
            responseEntity=  new ResponseEntity<Track>(trackService.getTrackById(id),HttpStatus.OK);
        }
        catch(TrackNotFoundException ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteuserById(@PathVariable(value="id") Integer id) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        trackService.deleteTrack(id);
        responseEntity=new ResponseEntity<String>("Deleted",HttpStatus.FORBIDDEN);
        return responseEntity;
    }
    @PutMapping("track")
    public ResponseEntity<?> updateUser(@RequestBody Track track)  {
        ResponseEntity responseEntity;
        try {

            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Updated Successfully", HttpStatus.CREATED);
        }
        catch (TrackAlreadyExistsException ex) {

            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }

        return responseEntity;
    }


    @GetMapping("tracks/{name}")
    @Query("from Track where name=?1")
    public ResponseEntity<?> getAllTracksByName(@PathVariable(value="name") String name)
    {
        ResponseEntity responseEntity;
        try {
            responseEntity=new ResponseEntity<List<Track>>(trackService.getTrackByName(name),HttpStatus.OK);
        }
        catch (TrackNotFoundException e)
        {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}
