package com.stackroute.MuzixCrudOperations.repository;

import com.stackroute.MuzixCrudOperations.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class TrackRepositoryTest {

    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setUp() throws Exception {
        track = new Track(10, "peeloon", "good");
    }

    @Test
    public void testSaveTrack() {
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(101, fetchTrack.getId());
    }

    @Test
    public void testSaveTrackFailure() {
        Track testTrack = new Track(11, "saathiya", "good");
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testTrack, track);
    }

    @Test
    public void testGetAllTrack() {
//        Track u = new Track(13,"raaz","good");
//        Track u1 = new Track(14,"dilwale","bad");

     //   trackRepository.save(track);
        //  trackRepository.save(u1);
        List<Track> list = trackRepository.findAll();
        System.out.println(list);
       // list.add(track);
        Assert.assertEquals("peeloon", list.get(0).getName());


    }
}
