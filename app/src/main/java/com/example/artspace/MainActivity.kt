package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    // Define a list of artworks with their details
    val artworks = listOf(
        Artwork("Baby Turkey", "Artist A", "2024", R.drawable.baby_turkey),
        Artwork("Baby Christmas", "Artist B", "2023", R.drawable.baby_christmas),
        Artwork("Baby Elf", "Artist C", "2022", R.drawable.baby_elf)
    )

    // State to track the current artwork index
    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the current artwork
        ArtworkSection(imageRes = artworks[currentIndex].imageRes)

        // Show the artwork details
        ArtworkInfoSection(
            title = artworks[currentIndex].title,
            artist = artworks[currentIndex].artist,
            year = artworks[currentIndex].year
        )

        // Provide navigation buttons
        NavigationSection(
            onPrevious = { currentIndex = if (currentIndex > 0) currentIndex - 1 else artworks.size - 1 },
            onNext = { currentIndex = (currentIndex + 1) % artworks.size }
        )
    }
}

// Data class for artwork details
data class Artwork(val title: String, val artist: String, val year: String, val imageRes: Int)

@Composable
fun ArtworkSection(imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Artwork Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
            .padding(top = 16.dp) // Add a margin of 16.dp above the image
    )
}


@Composable
fun ArtworkInfoSection(title: String, artist: String, year: String) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$artist ($year)",
            fontSize = 16.sp
        )
    }
}

@Composable
fun NavigationSection(onPrevious: () -> Unit, onNext: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = onPrevious) {
            Text(text = "Previous")
        }
        Button(onClick = onNext) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
