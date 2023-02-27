# Jetpack Compose Cases 

It's an open source project includes very common **JetpackCompose Screens** that could be used in your live/self projects.

# Goals behind repository existance
- Share JetpackCompose Cases to make developers mission easier.
- Share knowledge of Jetpack Compose library
- Improve Jetpack compose learning curve for people who benefit from the repo and for the contributors.


# Rules of contribution

- Your contribution should include ***a single screen***  per pull request.
- You should use Compose only in your case.
- Your Jetpack compose case should be in a separate module created at the root directory of the project
- Your module/case should contains a README file using [this template](https://gist.github.com/MustafaKhaled/75c3d3e722fc60517744c247ffee30a6).

# How to contribute?

As previously mentioned, if you need to contribute you should add your Jetpack Compose Case by following the steps: 

 ### Project Clone
- Create a fork from this repository.
- Clone the fork to your computer.
- Create a branch from ``` develop``` branch.

### Create your module

- Create in your module at root directory of the project.

> **Note**
>
> File > New > New Module > Android Library 
>
> Your package name should start with **io.jetpack.compose.** followed by your module/library name

### Things must be added before start adding your case

- In your ``` build.gradle ``` of your module add the **```common library```** that include all Jetpack Compose dependencies needed. 
If you didn't find a library just add it to your ```build.gradle```

```gradle
implementation project(path: ':common')
```

- Add needed gradle Jetpack Compose configuration to your ```build.gradle``` file

```gradle
android {
    ....
    buildFeatures {
        compose true
    }
    composeOptions {
        kotlinCompilerExtensionVersion compose_version
    }
    
}

```

> **Warning**
>
> Don't replace **compose_version**, keep it as pasted. **compose_version** has a value in ```build.gradle``` project level.


### Things must be added after adding your case

- Add your module as dependency to App module. Open App ```build.gradle``` (module level), then add your dependency. For example: 

```gradle
implementation project(path: ':RequestPermissionScreen')
```

- To add your case as an item in the project as shown in the screenshots section you should: 

    * Open app> java> io> jetpack> cases> ui> casesList> navigation> NavRoutes.kt 
    * Add you Navigation Route. Since the project using Navigation Component with Compose you should add you NavigationRoute with your compose to NavHost


    ```kotlin
    sealed class NavRoutes(val route: String) {
    object SplashScreen: NavRoutes("splash_screen")
    object CasesListScreen: NavRoutes("cases_list_screen")
    object RequestPermissionScreen: NavRoutes("request_permission_screen")
    //Example
    object YourScreenRouteName: NavRoutes("your_route_key")
    }
    ```

    *   Open app> java> io> jetpack> cases> ui> navigation> Navigation.kt 

    ```kotlin
    NavHost(navController = navController, startDestination = NavRoutes.SplashScreen.route) {
        ...
        //Example
        composable(route = Your cases route) {
            SinglePermissionScreen()
        }
    }

    ```

    * Append your list item 

        Open app> java> io> jetpack> cases> ui> casesList> Cases.kt 

    ```kotlin 
    val casesList = listOf(
    CaseItem("Single Permission", "Mustafa Khaled", NavRoutes.RequestPermissionScreen.route),
    // Example
    CaseItem( your case name,  your name , The NavRoute you added)
    )
    ```

> **Note** 
>
> Don't add any UI to add your item, just add a new item to ```casesList```, and your item will be added as UI ListItem.

### Create README.md of your case

You should add your ```README.md``` file in your module from this template.

> **Note** 
>
> Take care of illustration of screenshots section in README.md [template](https://gist.github.com/MustafaKhaled/75c3d3e722fc60517744c247ffee30a6). To be able to upload screenshots of your case.


# Pull Request
After following steps of **How to contribute?**, create pull request from your forked repo to develop branch of the original repo.
Once your PR is accepted, your case would be listed in the project, and your name will be added as an author section below.

# Cases
[Single Permission](https://github.com/MustafaKhaled/JetpackComposeCases/tree/develop/RequestPermissionScreen)
[OTP](https://github.com/MustafaKhaled/JetpackComposeCases/tree/develop/otp)

# Authors

List of contributors: 

* [Mustafa Khaled](https://github.com/MustafaKhaled)
* [Mahmoud Eraqi](https://github.com/MahmoudEraqi95)

# License
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
