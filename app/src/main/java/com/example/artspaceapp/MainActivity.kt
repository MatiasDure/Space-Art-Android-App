package com.example.artspaceapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpace(
                        Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(Color(242, 242, 242))
                    )
                }
            }
        }
    }
}

@Composable
fun TransitionButton(text: String, onClicked: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClicked, modifier = modifier.padding(horizontal = 24.dp)) {
        Text(text = text);
    }
}

@Composable
fun ArtDetails(title: String, author: String, year: String,modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(bottom = 16.dp)
            .background(Color.White)
            .border(BorderStroke(1.dp, Color.LightGray))
            .padding(20.dp)
    ) {
        Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.W300, modifier = Modifier.padding(bottom = 6.dp) )
        Row {
            Text(text = author, fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 8.dp))
            Text(text = "($year)")
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    val artPieces = remember {
        listOf(
            ArtPiece(R.drawable.annie_spratt_1, title = "Flower Bloom", author = "Annie Spratt", year = "2024"),
            ArtPiece(R.drawable.annie_spratt_2, title = "Blooming Field", author = "Annie Spratt", year = "2024"),
            ArtPiece(R.drawable.jennifer_kalenberg, title = "Hanging Chandeliers", author = "Jennifer Kalenberg", year = "2024"),
            ArtPiece(R.drawable.jimmy, title = "Through the Arch", author = "Jimmy Woo", year = "2024"),
            ArtPiece(R.drawable.lawrence_chismorie, title = "Chill Stop", author = "Lawrence Chismorie", year = "2024"),
            ArtPiece(R.drawable.richard_stachmann, title = "Shadows", author = "Richard Stachmann", year = "2024"),
            ArtPiece(R.drawable.taylor_flowe, title = "Sharing time", author = "Taylor Flowe", year = "2024")
        )
    }
    var artIndex by remember { mutableIntStateOf(0); }

    val previousClick = { artIndex = if((artIndex - 1) < 0) artPieces.size - 1 else artIndex - 1; }
    val nextClick = { artIndex = if((artIndex + 1) >= artPieces.size) 0 else artIndex + 1; }
    val currentArtPiece: ArtPiece = artPieces[artIndex];


    Column (modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = currentArtPiece.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp, vertical = 80.dp)
                .shadow(3.dp, RoundedCornerShape(24.dp))
                .background(Color.White)
                .scale(.85f)
        )
        ArtDetails(title = currentArtPiece.title, author = currentArtPiece.author, year = currentArtPiece.year , modifier = Modifier.padding(bottom = 20.dp));
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()) {
            TransitionButton(text = stringResource(R.string.previous), onClicked = previousClick , modifier = Modifier.weight(1f))
            TransitionButton(text = stringResource(R.string.next), onClicked = nextClick , modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpace(
            Modifier
                .fillMaxSize()
                .background(Color(242, 242, 242))
        )
    }
}