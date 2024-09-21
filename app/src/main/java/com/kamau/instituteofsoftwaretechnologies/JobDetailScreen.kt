package com.kamau.instituteofsoftwaretechnologies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun JobDetailScreen(
    navController: NavController,
    jobTitle: String,
    companyName: String,
    location: String,
    salaryRange: String,
    jobType: String,
    workingModel: String,
    level: String
) {
    // Build the details screen, where you'll show all the job info.
    Column(modifier = Modifier.padding(16.dp)) {
        // Job title and company
        Text(text = jobTitle, style = MaterialTheme.typography.h5)
        Text(text = companyName, style = MaterialTheme.typography.subtitle1)
        Text(text = location, style = MaterialTheme.typography.subtitle2)


            Spacer(modifier = Modifier.height(16.dp))

                    // Salary, job type, and other details
                    Row {
                Text(text = "Salary: $salaryRange")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Job Type: $jobType")
            }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                Text(text = "Working Model: $workingModel")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Level: $level")
            }

                    Spacer(modifier = Modifier.height(24.dp))

                    // This is where you explain the job (like in your job details screenshot)
                    Text(text = "About this Job", style = MaterialTheme.typography.h6)
                Text(text = "We are searching for a talented and motivated individual to join our team.")

                Spacer(modifier = Modifier.height(16.dp))

                // A button to apply for the job!
                Button(onClick = {
            // You can add logic here when someone applies for the job
        }) {
            Text("Apply for Job")
        }
    }
}

