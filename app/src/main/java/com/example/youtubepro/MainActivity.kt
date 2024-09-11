package com.example.youtubepro

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ComponentActivity
import com.example.youtubepro.ui.theme.YoutubeProTheme
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

val supabase = createSupabaseClient(
    supabaseUrl = "https://preqxjyjrjghxhhaeagx.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InByZXF4anlqcmpnaHhoaGFlYWd4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQ5ODg4ODEsImV4cCI6MjAzMDU2NDg4MX0.m2DvMBgsavmOke5VQGVEWKZki_5UGWr-lbdXPS_zYzc"
) {
    install(Postgrest)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YoutubeProTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    YoutubePro()
                }
            }
        }
    }
}

@Composable
fun YoutubePro() {

    val videos = remember { mutableStateListOf<Video>() }

//    LaunchedEffect(Unit) {
//        withContext(Dispatchers.IO) {
//            val results = supabase.from("videos").select().decodeList<Video>()
//            videos.addAll(results)
//        }
//    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Home",
            modifier = Modifier.padding(16.dp),
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 16.sp
        )

        val searchText = remember { mutableStateOf("") }
        val boolean = remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = searchText.value,
                onValueChange = { searchText.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                placeholder = { Text(text = "Search") },
                trailingIcon = {
                    Icon(Icons.Default.Search,
                        contentDescription = "Search Icon",
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(16.dp))
                            .clickable {
                                boolean.value = true        // something new happen
                            })
                },
                shape = RoundedCornerShape(16.dp),
            )
        }

        if (boolean.value) {
            SearchVideo(videos = videos, searchText = searchText.value)
        }else {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                items(videos) { video ->
                    ListItem(headlineContent = {
                        VideoDisplay(title = video.title, likes = video.likes, videoId = video.videoId)
                    })
                }
            }
        }
    }
}

@Composable
fun VideoDisplay(title: String, likes: Int, videoId: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        YouTubePlayer(
            youtubeVideoId = videoId,
            lifecycleOwner = LocalLifecycleOwner.current
        )
        Text(text = title)

        Row(
            modifier = Modifier.padding(top = 4.dp)
                .align(Alignment.Start),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                Icons.Default.ThumbUp,
                contentDescription = "Search Icon",
                modifier = Modifier.size(16.dp)
            )

            Text(
                text = " - $likes",
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
        }
    }
}