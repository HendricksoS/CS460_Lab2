package com.example.novideo;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * RecyclerView adapter that displays video items and their titles and descriptions.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<VideoItem> videoItems;

    /**
     * Creates an adapter for the supplied video items.
     *
     * @param videoItems the videos to display
     */
    public VideoAdapter(List<VideoItem> videoItems) {
        this.videoItems = videoItems;
    }

    /**
     * Creates a view holder for a video list item.
     *
     * @param parent the parent view that the new item view will be attached to
     * @param viewType the type of view being created
     * @return a holder containing the inflated video item view
     */
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_container_video, parent, false)
        );
    }

    /**
     * Binds the video at the given position to the supplied view holder.
     *
     * @param holder the view holder to update
     * @param position the position of the video in the data set
     */
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(videoItems.get(position));
    }

    /**
     * Returns the number of videos in the data set.
     *
     * @return the number of video items
     */
    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    /**
     * Holds the views for a video list item and binds video data to them.
     */
    static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView textVideoTitle1, textVideoDescription1, textVideoID1;
        VideoView videoView;
        ProgressBar progressBar;

        /**
         * Initializes the views contained in a video list item.
         *
         * @param itemView the inflated item view
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            textVideoTitle1 = itemView.findViewById(R.id.textVideoTitle);
            textVideoDescription1 = itemView.findViewById(R.id.textVideoDescription);
            textVideoID1 = itemView.findViewById(R.id.textVideoID);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
        }

        /**
         * Displays the video's title and description, prepares its player, and starts playback.
         * The progress indicator is hidden once the video is prepared, and playback restarts
         * when the video completes.
         *
         * @param videoItem the video data to display
         */
        void setVideoData(VideoItem videoItem) {
            textVideoTitle1.setText(videoItem.videoTitle);
            textVideoDescription1.setText(videoItem.videoDescription);
            textVideoID1.setText(videoItem.videoID);
            videoView.setVideoPath(videoItem.videoURL);

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressBar.setVisibility(View.GONE);
                    mp.start();

                    float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                    float screenRatio = videoView.getWidth() / (float) videoView.getHeight();

                    float scale = videoRatio / screenRatio;
                    if (scale >= 1f) {
                        videoView.setScaleX(scale);
                    } else {
                        videoView.setScaleY(scale);
                    }
                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
    }
}
