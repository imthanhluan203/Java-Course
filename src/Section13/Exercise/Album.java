package Section13.Exercise;

import java.util.ArrayList;
import java.util.LinkedList;

class Song{
    private String name;
    private double duration;
    private int track;
    public static int trackNumber = 1;
    public Song(String name,double duration) {
        this.name = name;
        this.duration = duration;
        track = trackNumber++;
    }

    public String getName() {
        return name;
    }

    public int getTrack() {
        return track;
    }

    public double getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "%s%n".formatted(name);
    }
}
public class Album {
    private String name;
    private String bank;
    private SongList songList = new SongList();
    public Album(String name, String bank) {
        this.name = name;
        this.bank = bank;
    }
    public void  addSong(String title, double duration){
        songList.Add(new Song(title,duration));
    }
    public void addToPlayList(int trackNumber, LinkedList<Song> playList){

    }
    private class SongList{
        private ArrayList<Song> songs = new ArrayList<>();
        private boolean Add(Song s){
            if(findSong(s.getName())==null){
                songs.add(s);
                return true;
            }
            return false;
        }
        private Song findSong(String title){
            for(var ob : songs){
                if(ob.getName().equalsIgnoreCase(title)){
                    return ob;
                }
            }
            return null;
        }
        private Song findSong(int track){
            for(var ob : songs){
                if(ob.getTrack() == track){
                    return ob;
                }
            }
            return null;
        }
    }
}
