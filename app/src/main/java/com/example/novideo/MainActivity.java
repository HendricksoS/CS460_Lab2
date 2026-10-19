package com.example.novideo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        final ViewPager2 videoViewPager = findViewById(R.id.videosViewPager);

        List<VideoItem> videoItemsList = new ArrayList<>();

        /* example code for adding a new video. This should be automated eventually.
        VideoItem videoNew = new VideoItem();
        videoNew.videoURL = "https://raw.githubusercontent.com/HendricksoS/CS460_Lab2_Videos/main/.mp4webm";
        videoNew.videoTitle = "Title";
        videoNew.videoDescription = "Description";
        videoNew.videoID = "00000";
        videoItemsList.add(videoNew);
         */

        VideoItem videoNot = new VideoItem();
        videoNot.videoURL = "https://raw.githubusercontent.com/HendricksoS/CS460_Lab2_Videos/main/noVideo.mp4";
        videoNot.videoTitle = "This is Not a Video";
        videoNot.videoDescription = "Why would it have a description if it isn't a video?";
        videoItemsList.add(videoNot);

        VideoItem videoDilly = new VideoItem();
        videoDilly.videoURL = "https://raw.githubusercontent.com/HendricksoS/CS460_Lab2_Videos/main/Dilly.mp4";
        videoDilly.videoTitle = "Kick it Dilly!";
        videoDilly.videoDescription = "He's really busting it down.";
        videoItemsList.add(videoDilly);

        VideoItem videoColumbo = new VideoItem();
        videoColumbo.videoURL = "https://raw.githubusercontent.com/HendricksoS/CS460_Lab2_Videos/main/Im_Snoopy.mp4";
        videoColumbo.videoTitle = "He's What Now???";
        videoColumbo.videoDescription = "We're all very proud of Columbo for sharing himself with us.";
        videoItemsList.add(videoColumbo);

        VideoItem videoDuc = new VideoItem();
        videoDuc.videoURL = "https://raw.githubusercontent.com/HendricksoS/CS460_Lab2_Videos/main/duc.webm";
        videoDuc.videoTitle = "Duc";
        videoDuc.videoDescription = "What is he doing in there? What is he plotting?";
        videoItemsList.add(videoDuc);

        videoViewPager.setAdapter(new VideoAdapter(videoItemsList));
    }
}