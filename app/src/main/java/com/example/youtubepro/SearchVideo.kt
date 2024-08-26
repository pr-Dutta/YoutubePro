package com.example.youtubepro

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp

//@Composable
//fun SearchVideo(videos: SnapshotStateList<Video>, searchText: String) {
//
//    LazyColumn(
//        modifier = Modifier
//            .padding(16.dp)
//    ) {
//
//        items(videos) { video ->
//            if (video.title.contains(searchText)) {
//                VideoDisplay(title = video.title, likes = video.likes, videoId = video.videoId)
//            }
//        }
//    }
//}