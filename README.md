# Animatrix 

### Get instant wildlife insight with our AI tool! Simply snap a photo of any animal and learn its species, genus, endangerment status and fascinating facts in seconds.

![Animatrix 1](https://user-images.githubusercontent.com/48258080/216829205-199cae30-ea42-4271-a979-e359e49e68bf.png)

---

As Urbanization increases more and more animals around the world are going extinct due to habitat loss, pollution, deforestation, climate change, and more. The WWF found that humans are responsible for 99% of endangered species and that the current extinction rate is 1000 to 10000 times higher than the natural rate. Animatrix is a web application which uses ImageAI to classify animals based on pictures uploaded by the user. This application was developed to increase awareness for endangered species of animals around the world. Our goal is to provide easy access to information regarding a specific species, giving young students and teachers a platform to explore and learn about the animal kingdom. This application will provide the name of the animal, its genus, endangered status, and a little description of the animal when prompted with an image. 

With more awareness, the world can take action to preserve Earth’s biodiversity and natural beauty.


# Installation

## Python - ImageAI

Install python version 3.8.x (x being any number) if on Windows 10, other python versions will work if running WSL or other operating systems.

Install AI dependencies from the ai directory:

`pip install -r requirements.txt`

Run the AI locally from the ai directory:

`flask run`

## Backend - Java

Install openjdk-17 on your device. Change the permissions of gradlew in the backend directory:

`chmod +x /gradlew`

Run the backend server locally:

`./gradlew bootRun`

## frontend - HTML/JS/CSS

No dependencies or installations required. Run web application using live server or by opening index.html with a browser.
