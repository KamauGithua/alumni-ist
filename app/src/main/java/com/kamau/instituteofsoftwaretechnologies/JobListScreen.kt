package com.kamau.instituteofsoftwaretechnologies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun JobListScreen(navController: NavController) {
    val jobTitle = "UI Designer"
    val companyName = "ChabokSoft"
    val location = "LosAngeles, USA"
    val salaryRange = "$38k - $46k"
    val jobType = "Full Time"
    val workingModel = "Remote"
    val level = "Internship"

    // Display the job list
    Column(modifier = Modifier.padding(16.dp)) {
        Card(modifier = Modifier.clickable {
            navController.navigate(
                "jobDetail/$jobTitle/$companyName/$location/$salaryRange/$jobType/$workingModel/$level"
            )
        }) {
            Text(text = jobTitle)
        }
    }
}
