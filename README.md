<div id="top"></div>

<!-- PROJECT SHIELDS -->
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/bpedryc/Foodity">
    <img src="./foodity_logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Foodity</h3>

  <p align="center">
    An Android application for writing down all sorts of cooking </br> notes or original recipes and sharing them with friends
    </br>
    </br>
    <b><a href="#demo">View Demo</a></b>
  </p>
</div>


<!-- ABOUT THE PROJECT -->
## About The Project

The project provides its users with flexible tools for defining cooking notes - they can be anything: recipes, tea brewing instructions, favourite snack, fancy knife collection - whatever your heart desires! Notes are grouped into categories. The application lets you browse through other users' notes. You can follow other users to see their latest activity. Additionaly users are provided with built-in kitchen tools like a timer or a unit converter to help with cooking.

The project has been implemented as a part of the engineering thesis "Foodity - A Social Kitchen Assistant".

<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

* [Kotlin](https://developer.android.com/kotlin)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Hibernate](https://hibernate.org/)
* [SQLite](https://www.sqlite.org/index.html)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Retrofit](https://square.github.io/retrofit/)
* [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines)
* [Android View Binding](https://developer.android.com/topic/libraries/view-binding)
* [Android Data Binding](https://developer.android.com/topic/libraries/data-binding)
* [Espresso](https://developer.android.com/training/testing/espresso)

<p align="right">(<a href="#top">back to top</a>)</p>


## Getting started

To get a local copy up and running on Windows follow these simple example steps.

### Prerequisites
1. Android Studio to run the mobile app (https://developer.android.com/studio)
2. IntelliJ IDEA to run the server app (https://www.jetbrains.com/idea/download)
3. Git for Windows to run Keycloak shell script (https://gitforwindows.org/)

### Installation
1. Clone the repository
  ```sh
  git clone https://github.com/bpedryc/Foodity.git
  ```
2. Download Keycloak server (WildFly distribution) from the official website https://www.keycloak.org/downloads 
3. Place the contents of downloaded "keycloak-..." directory to "\**cloned repository path\**/FoodityKeycloak/" 
4. In IntelliJ select File>Open, then select directory "\**cloned repository path\**/FoodityServer" 
5. On C:\ drive create a new directory called "uploads" (C:\uploads\)
6. In Android Studio select File>Open, then select directory "\**cloned repository path\**/Foodity" (the selected path should look like "....../Foodity/Foodity") 
7. Navigate to the file: assets>config.properties. In that file replace server_url value with a local IP address (with port 8080) of the machine that you will be running the server on. (The Android emulator or device needs to be in the same network as the server machine)

### Running
1. First run Keycloak with the prepared bash script "\**cloned repository path\**/FoodityKeycloak/run_keycloak.sh"
  ```sh
  sh run_keycloak.sh
  ```
2. Then in IntelliJ run the server - navigate to the class "FoodityServerApplication" (you can double-press shift key to search for a specific class in IntelliJ) and click on the small green button to the left of the function "main(args: Array<String>)"
3. Lastly run the mobile application in Android Studio - the configuration should be generated automatically
4. In the application click "Sign Up", enter any username, email and password for your user (email doesn't have to be real), for example: 
  Username: abc
  Email: abc@gmail.com
  Password: abc
5. Click "Sign Up" and wait for feedback about your account being created
6. On the login page enter created account username and password, then sign in and try out the app! :)
  
  
### Issues
  
If an error occurs when starting either the server or the mobile app:
  - In IntelliJ, set Project SDK to version 11 in project settings (File>Project Structure)
  - In Android Studio, set Gradle JDK to version 11 (File>Settings>Build,Execution,Deployment>Build Tools>Gradle)
  
<p align="right">(<a href="#top">back to top</a>)</p>  

<!-- DEMO -->
## Demo

<img src="./1_showcase_edit.gif" align="left" alt="showcase_edit" width="300" height="660" > 
Edit your cooking notes in any way you want. A cooking note can be made out of three types of elements: text, list and image. You can upload any image from your device's memory.
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>


<img src="./2_showcase_browse.gif" align="left" alt="showcase_browse" width="300" height="660">
Follow your friends' accounts, watch their activity and browse through their notes collection.
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>


<img src="./3_showcase_tools.gif" align="left" alt="showcase_tools" width="300" height="660">
Use built-in tools for converting different types of units from all over the world. Spare yourself some kitchen tools and use a scale only!
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- CONTACT -->
## Contact

Bartłomiej Pedryc - b.pedryc11@gmail.com

<p align="right">(<a href="#top">back to top</a>)</p>




<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->


[contributors-shield]: https://img.shields.io/github/contributors/github_username/repo_name.svg?style=for-the-badge
[contributors-url]: https://github.com/github_username/repo_name/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/github_username/repo_name.svg?style=for-the-badge
[forks-url]: https://github.com/github_username/repo_name/network/members
[stars-shield]: https://img.shields.io/github/stars/github_username/repo_name.svg?style=for-the-badge
[stars-url]: https://github.com/github_username/repo_name/stargazers
[issues-shield]: https://img.shields.io/github/issues/github_username/repo_name.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo_name/issues
[license-shield]: https://img.shields.io/github/license/github_username/repo_name.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/bart%C5%82omiej-pedryc-944800191
[product-screenshot]: ./note_preview.png
