<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li><a href="#contributors">Contributors</a></li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#stopping the containers">Stopping the Containers</a></li>
    <li><a href="#troubleshooting">Troubleshooting</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>


# Talee

<!-- ABOUT THE PROJECT -->
## About The Project
Talee is a website for searching and hosting events in Budapest.
You can invite your friends, add tags and search based on them on the website.
Talee is a Java Spring Boot project with a PostgreSQL database and a Vite React frontend. 
All three components are containerized using Docker for easy deployment and scalability.
We hope you will enjoy it!

### Built With
- Backend: 
  * [![SpringBoot][SpringBoot]][SpringBoot-url]
- Database:
  * [![PostgreSQL][PostgreSQL]][PostgreSQL-url]
- Frontend:
  * [![React][React.js]][React-url]
  * [![Vite][Vite]][Vite-url]
  * [![TailwindCSS][TailwindCSS]][TailwindCSS-url]
- Containerization: Docker
  * [![Docker][Docker]][Docker-url]

### Contributors:

- Molnár Mariann: https://github.com/MariannaMolnar
- Pojbics Máté: https://github.com/matet2001 - matet2001@gmail.com
- dr. Ditrói-Tóth Zsuzsa: https://github.com/DTZsuzsi
- Sárosdi Márton: https://github.com/mmarci96

<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

Before running the project, ensure you have the following installed:
- Docker, you can install it from this link: https://docs.docker.com/engine/install/
- Docker Compose, you can install it from this link: https://docs.docker.com/compose/install/

### Installation

Here you find the steps of the installation of our project:
1. Clone the repository
    ```
    git clone https://github.com/your-repo/talee.git
    cd talee
    ```

2. Configure environment variables

    If you have postgresql installed then please change the credentials for the project. 
    
    Create a .env file in the root directory, and add the following environment variables:
    ```
    - DB_PASSWORD=yourpassword
    - DB_USERNAME=yourusername
    - jwtSecret=======================CodeCool=Spring===========================
    - jwtExpirationMs=86400000
    ```
    
    Ensure that the values match your environment and security requirements.

3. Run the project with Docker

    Please enter in your terminal the following comment:
    ````
    docker-compose up --build
    ````

    This command will:
   - Build and start the PostgreSQL database.
   - Start the Spring Boot backend.
   - Start the Vite React frontend.

   The services will be available at:

   Frontend: http://localhost:5173

   Backend: http://localhost:8080

### Usage

Once the services are running, you can access the frontend to interact with the application and the backend API for examining purposes.

We have security on the website, not users just can see higlights from the website. Once you made the login, you can also:
- add new location,
- add new events (events are chained to locations, therefore you can find it there),
- modify locations, events, including adding or deleting tags
- invite friends.

This is the main page of the locations: 

![Website Location Screenshot](screenshots/ScreenshotLocations.png)


This is the main page of the events: 

![Website Event Screenshot](screenshots/ScreenshotEvents.png)

If you click to any box, you can see the details, you can edit and invite friends here too. 



### Stopping the Containers

To stop running  the containers enter the following command:
````
docker-compose down
````

### Troubleshooting

- Port Conflicts:
Ensure ports 5432, 8080, and 5173 are not in use by other processes.
- Docker Compose Issues:
Run docker-compose down and then docker-compose up --build to restart services.

<!-- ROADMAP -->
## Roadmap

- [x] Add Dockerize project
- [x] Add README
- [ ] Testing
    - [ ] Set up CI/CD pipeline
    - [ ] Increase coverage with more tests

<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

Use this space to list resources you find helpful and would like to give credit to. I've included a few of my favorites to kick things off!

* [React Icons](https://react-icons.github.io/react-icons/search)
* [Axios](https://axios-http.com/docs/intro)

[SpringBoot]: https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white
[SpringBoot-url]: https://spring.io/projects/spring-boot

[PostgreSQL]: https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white
[PostgreSQL-url]: https://www.postgresql.org/

[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/

[Vite]: https://img.shields.io/badge/Vite-646CFF?style=for-the-badge&logo=vite&logoColor=white
[Vite-url]: https://vitejs.dev/

[TailwindCSS]: https://img.shields.io/badge/TailwindCSS-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white
[TailwindCSS-url]: https://tailwindcss.com/

[Docker]: https://img.shields.io/badge/TailwindCSS-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white
[Docker-url]: https://tailwindcss.com/