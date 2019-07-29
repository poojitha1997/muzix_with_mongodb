package com.stackroute.MuzixCrudOperations.service;

import com.stackroute.MuzixCrudOperations.domain.Track;
import com.stackroute.MuzixCrudOperations.expections.TrackAlreadyExistsException;
import com.stackroute.MuzixCrudOperations.expections.TrackNotFoundException;

import java.util.List;

public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public String deleteTrack(int id) throws TrackNotFoundException;

    public List<Track> getAllTracks();

    public Track getTrackById(int id) throws TrackNotFoundException;


    public Track UpdateTrack(Track track) throws TrackNotFoundException;
    public List<Track> getTrackByName(String name) throws TrackNotFoundException;



}
