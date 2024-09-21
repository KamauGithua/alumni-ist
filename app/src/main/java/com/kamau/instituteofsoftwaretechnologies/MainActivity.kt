package com.kamau.instituteofsoftwaretechnologies

import Home
import android.Manifest

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.rounded.Home

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState

//import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.BottomAppBar
//import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kamau.instituteofsoftwaretechnologies.ui.theme.InstituteOfSoftwareTechnologiesTheme
import com.kamau.instituteofsoftwaretechnologies.ui.theme.Purple80
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    val CHANNEL_ID = "ch1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InstituteOfSoftwareTechnologiesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavBotSheet()

                }
            }

        }


        createNotificationChannel()
        if (ActivityCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            return
        } else{
            showNotification()

        }

    }
    private val notificationPermissionLauncher =registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){result:Boolean->
        if (result){
            showNotification()
        }else{
            Toast.makeText(this@MainActivity,
                "Permission Not Granted",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    @SuppressLint("MissingPermission")
    private fun showNotification()
    {
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Title")
            .setContentText("Title Content")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)) {

            notify(1, builder.build())
        }

    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Channel"
            val descriptionText = "channel_description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBotSheet(){
    val navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current.applicationContext

    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    val  sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Box(modifier = Modifier
                    .background(Purple80)
                    .fillMaxWidth()
                    .height(150.dp)) {
                    Text(text = "IST ALUMNI APP")
                }

                Divider()

                NavigationDrawerItem(
                    icon = {
                        Icon(imageVector =Icons.Rounded.Home,
                            contentDescription = "home",
                            modifier = Modifier.size(27.dp)
                        )
                    },
                    label = { Text(
                        text = "Home",
                        fontSize = 17.sp,
                    ) },
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Screens.Home.screen){
                            popUpTo(0)
                        }
                    })
                //profile section
                NavigationDrawerItem(
                    icon = {
                        Icon(imageVector =Icons.Default.Person,
                            contentDescription = "Profile",
                            modifier = Modifier.size(27.dp)
                        )
                    },
                    label = { Text(
                        text = "Profile",
                        fontSize = 17.sp,
                    ) },
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Screens.Profile.screen){
                            popUpTo(0)
                        }
                    })

                //settings
                NavigationDrawerItem(
                    icon = {
                        Icon(imageVector =Icons.Default.Settings,
                            contentDescription = "settings",
                            modifier = Modifier.size(27.dp)
                        )
                    },
                    label = { Text(
                        text = "Settings",
                        fontSize = 17.sp,
                    ) },
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Screens.Settings.screen){
                            popUpTo(0)
                        }
                    })
                //LOGOUT
                NavigationDrawerItem(
                    icon = {
                        Icon(imageVector =Icons.Default.ExitToApp,
                            contentDescription = "logout",
                            modifier = Modifier.size(27.dp)
                        )
                    },
                    label = { Text(
                        text = "log Out",
                        fontSize = 17.sp,
                    ) },
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
                    })
            }
        },
        ) {
        Scaffold(topBar = {

            val coroutineScope = rememberCoroutineScope()
            TopAppBar(
                title = {
                    Text(text = "IST ALUMNI APP")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(0.6f),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            modifier = Modifier
                                .padding(start = 16.dp, end = 8.dp)
                                .size(28.dp)
                        )
                    }
                },
            )
        },
            bottomBar = {
                BottomAppBar(containerColor = Purple80) {
                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Home
                            navigationController.navigate(Screens.Home.screen) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
                            tint = if (selected.value == Icons.Default.Home) Color.White else Color.DarkGray
                        )
                    }

                    //search
                    IconButton(onClick = {
                        selected.value = Icons.Default.Search
                        navigationController.navigate(Screens.Search.screen) {
                            popUpTo(0)
                        }
                    }, modifier = Modifier.weight(1f)) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
                            tint = if (selected.value == Icons.Default.Search) Color.White else Color.DarkGray
                        )
                    }

                    //Floating Button

//                    Box(
//                        modifier = Modifier
//                            .weight(1f)
//                            .padding(16.dp),
//                        contentAlignment = Alignment.Center
//                    )
                    FloatingActionButton(
                        onClick = { showBottomSheet = true },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = Purple80)
                    }
                    //Notifications

                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Notifications
                            navigationController.navigate(Screens.Notifications.screen) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
                            tint = if (selected.value == Icons.Default.Notifications) Color.White else Color.DarkGray
                        )
                    }
                    //JobList

                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Info
                            navigationController.navigate(Screens.JobListScreen.screen) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
                            tint = if (selected.value == Icons.Default.Info) Color.White else Color.DarkGray
                        )
                    }
                    //PROFILE
                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Person
                            navigationController.navigate(Screens.Profile.screen) {
                                popUpTo(0)
                            }
                        }, modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
                            tint = if (selected.value == Icons.Default.Person) Color.White else Color.DarkGray
                        )
                    }
                }
            }
        ) {


//                run {
//                    PaddingValues()
            NavHost(
                navController = navigationController,
//                startDestination = Screens.Home.screen
                startDestination = Screens.LoginScreen.screen
            ) {
                composable(Screens.LoginScreen.screen) { LoginScreen(navigationController) }
                composable(Screens.Home.screen) { Home(navigationController) }
                composable(Screens.Profile.screen) { Profile(navigationController) }
                composable(Screens.Settings.screen) { Settings() }
                composable(Screens.Search.screen) { Search() }
                composable(Screens.Notifications.screen) { Notification() }
                composable(Screens.Post.screen) { Post() }
                composable(Screens.JobListScreen.screen) {
                    JobListScreen(navigationController) }
                composable(
                    route = Screens.JobDetail.screen, // Use the dynamic route for job details
                    arguments = listOf(
                        navArgument("jobTitle") { type = NavType.StringType },
                        navArgument("companyName") { type = NavType.StringType },
                        navArgument("location") { type = NavType.StringType },
                        navArgument("salaryRange") { type = NavType.StringType },
                        navArgument("jobType") { type = NavType.StringType },
                        navArgument("workingModel") { type = NavType.StringType },
                        navArgument("level") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    JobDetailScreen(
                        navController = navigationController,
                        jobTitle = backStackEntry.arguments?.getString("jobTitle") ?: "",
                        companyName = backStackEntry.arguments?.getString("companyName") ?: "",
                        location = backStackEntry.arguments?.getString("location") ?: "",
                        salaryRange = backStackEntry.arguments?.getString("salaryRange") ?: "",
                        jobType = backStackEntry.arguments?.getString("jobType") ?: "",
                        workingModel = backStackEntry.arguments?.getString("workingModel") ?: "",
                        level = backStackEntry.arguments?.getString("level") ?: ""
                    )
                }
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    BottomSheetItem(icon = Icons.Default.ThumbUp, title = "Create a Post") {
                        showBottomSheet = false
                        navigationController.navigate(Screens.Post.screen) {
                            popUpTo(0)
                        }
                    }

                    BottomSheetItem(icon = Icons.Default.Star, title = "Add a Job") {
                        Toast.makeText(context, "Story", Toast.LENGTH_SHORT).show()
                    }

                    BottomSheetItem(
                        icon = Icons.Default.PlayArrow,
                        title = "Save a Job"
                    ) {
                        Toast.makeText(context, "Reels", Toast.LENGTH_SHORT).show()
                    }

                    BottomSheetItem(icon = Icons.Default.Favorite, title = "Go To My Network") {
                        Toast.makeText(context, "Live", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }
}
@Composable
fun BottomSheetItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.clickable { onClick() }
        ) {
            Icon(icon, contentDescription = null, tint = Purple80)
            Text(text = title, color = Purple80, fontSize = 22.sp)
        }

}







