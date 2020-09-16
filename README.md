# <img src="https://myanimelist.net/images/mal-logo-xsmall.png" alt="MyAnimeList" width="200"/>
An unofficial My Anime List API, using Htmlparser.




## :paperclip: My Objective
`I created this wrapper, to use in my projects. I have no intention of keeping 100% updated.
If you want to use it, you will have a small example below, and for now it has no documentation.`

Sorry my english :3

## :dvd: Dependencias
The dependencies are inside build.gradle

## :newspaper: Add your dependencies!
[![](https://jitpack.io/v/Cristian-Sknz/MyAniList-API.svg)](https://jitpack.io/#Cristian-Sknz/MyAniList-API)
* Gradle

```groovy
repositories {
     maven { url 'https://jitpack.io' }
}

dependencies {
     compile 'com.github.Cristian-Sknz:MyAniList-API:VERSION'
}
```
* Maven
```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
}

<dependency>
    <groupId>com.github.Cristian-Sknz</groupId>
    <artifactId>MyAniList-API</artifactId>
    <version>VERSION</version>
</dependency>
}
```
## :man_teacher: Simple Use
I made it pretty simple.

```java
public static Main(String[] args) {
	MyAnimeList api = new MyAnimeList();
  //Request an anime ("By" can be used for search or id)
	Request<Anime> request = api.getAnime(By.search("naruto"));
   
  //When using .get () it will open a connection.
	Anime naruto = request.get();
		
  //Print the information obtained
	System.out.println(naruto.getName());
	System.out.println(naruto.getId());
	System.out.println(naruto.getCharacters());
	[...] // The same works for the rest (characters, profiles, anime lists and manga).
}
```

This project is not complete, I intend to update whenever I can.
Thanks for reading me :D
