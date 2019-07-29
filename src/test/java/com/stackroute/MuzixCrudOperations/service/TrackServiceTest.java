package com.stackroute.MuzixCrudOperations.service;

import com.stackroute.MuzixCrudOperations.domain.Track;
import com.stackroute.MuzixCrudOperations.expections.TrackAlreadyExistsException;
import com.stackroute.MuzixCrudOperations.expections.TrackNotFoundException;
import com.stackroute.MuzixCrudOperations.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest
{
    Track track;
    @Mock
    TrackRepository trackRepository;
    @InjectMocks
    TrackServiceImpl trackService;
    private List<Track> trackList = new ArrayList<Track>();
    MockMvc mockMvc;

    @Before
    public void setUp()
    {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
       // mockMvc = MockMvcBuilders.standaloneSetup(trackService).build();
        track = new Track(15,"peeloon","fd");
        trackList.add(track);
    }
    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException
    {
        when(trackRepository.save((Track)any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track,savedTrack);
        verify(trackRepository,times(1)).save(track);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track) any())).thenReturn(null);
        Track savedTrack= trackService.saveTrack(track);
        System.out.println("savedTrack" + savedTrack);
    }
    @Test
    public void getAllTracks(){
        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(trackList);
        List<Track> tracks = trackService.getAllTracks();
        Assert.assertEquals(trackList,tracks);
    }
    @Test//(expected = TrackNotFoundException.class)
    public void getAllTracksFailure() throws TrackNotFoundException
    {
        trackRepository.save(track);
        when(trackRepository.findAll()).thenReturn(null);
        List<Track> tracks = trackService.getAllTracks();
        Assert.assertNotEquals(trackList,tracks);
    }
    @Test
    public void getTrackById() throws TrackNotFoundException {
      //  trackRepository.save(track);
        when(trackRepository.findById(anyInt())).thenReturn(Optional.ofNullable(trackList.get(0)));
        Track trackServiceTrackById = trackService.getTrackById(track.getId());
        Assert.assertEquals(trackList.get(0),trackServiceTrackById);
    }

    @Test
    public void getTrackByName() throws TrackNotFoundException{
        when(trackRepository.getTrackByName(anyString())).thenReturn(trackList);
        List<Track> trackServiceTrackByName = trackService.getTrackByName(track.getName());
        Assert.assertEquals(trackList.get(0).getName(),trackServiceTrackByName.get(0).getName());
    }
    @Test
    public void deleteById() throws TrackNotFoundException //throws TrackNotFoundException
    {
        when(trackRepository.findById(anyInt())).thenReturn(null);
        String trackServiceDeleteById = trackService.deleteTrack(track.getId());
        Assert.assertEquals("deleted",trackServiceDeleteById);
    }
    @Test
    public void updateId() throws TrackNotFoundException {
        when(trackRepository.save(any())).thenReturn(track);
        Track trackServiceUpdateById = trackService.UpdateTrack(track);
        Assert.assertEquals(track,trackServiceUpdateById);
    }


}