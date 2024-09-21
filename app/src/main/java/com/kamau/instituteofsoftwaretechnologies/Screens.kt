package com.kamau.instituteofsoftwaretechnologies

sealed class Screens (val screen: String) {
    data object LoginScreen: Screens("loginScreen")
    data object Home: Screens("home")
    data object Search: Screens("search")
    data object Notifications: Screens("notifications")
    data object Profile: Screens("profile")
    data object Post: Screens("post")
    data object Settings: Screens("settings")

    data object JobListScreen : Screens("jobListScreen")
    data object JobDetail : Screens("jobDetail/{jobTitle}/{companyName}/{location}/{salaryRange}/{jobType}/{workingModel}/{level}")
}