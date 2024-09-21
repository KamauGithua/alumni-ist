import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Home(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        TopBar()

        // Search Bar
        SearchBar()

        // Suggested Jobs Section
        SuggestedJobsSection(navController)

        // Recent Jobs Section
        RecentJobsSection(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Location",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier
                .background(Color(0xFF6A00FF)) // Purple background
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
        IconButton(onClick = { /*TODO: Add functionality*/ }) {

//            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

//        Icon(Icons.Default.Search, contentDescription = "Search Icon", modifier = Modifier.padding(8.dp))
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "Search anything") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun SuggestedJobsSection( navController : NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Suggested Jobs", fontWeight = FontWeight.Bold)
            TextButton(onClick = { /*TODO: Navigate to See All*/ }) {
                Text(text = "See All", color = Color.Blue)
            }
        }
        JobCard(
            title = "UI Designer",
            company = "ChatBoxSoft",
            location = "Los Angeles, USA",
            jobTypes = listOf("Full-Time", "Remote", "Internship"),
            salaryRange = "$38k - $46k",
            navController = navController

        )

    }
}

@Composable
fun RecentJobsSection( navController : NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Recent Jobs", fontWeight = FontWeight.Bold)
            TextButton(onClick = { /*TODO: Navigate to See All*/ }) {
                Text(text = "See All", color = Color.Blue)
            }
        }
        JobCard(
            title = "UI Designer",
            company = "ChatBoxSoft",
            location = "Los Angeles, USA",
            jobTypes = listOf("Full-Time", "Remote", "Internship"),
            salaryRange = "$38k - $46k",
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobCard(
    title: String,
    company: String,
    location: String,
    jobTypes: List<String>,
    salaryRange: String,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
//        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = company, color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
//                Icon(Icons.Default.LocationOn, contentDescription = "Location", tint = Color.Gray)
                Text(text = location, color = Color.Gray, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                jobTypes.forEach { type ->
                    JobTypeChip(type)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = salaryRange, color = Color.Blue, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                // Navigate to the JobDetailScreen and pass the job details
//                navController.navigate("jobDetail/$title/$company/$location/$salaryRange/${jobTypes.first()}") // Passing first job type as example
//
                            navController.navigate("jobDetail") // Passing first job type as example
            //                navController.navigate("notifications") // Passing first job type as example
            }) {
                Text("View Job")
            }
        }
    }
}

@Composable
fun JobTypeChip(text: String) {
    Box(
        modifier = Modifier
            .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = text, color = Color.DarkGray, fontSize = 12.sp)
    }
}
