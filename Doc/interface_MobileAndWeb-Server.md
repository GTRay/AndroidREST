;; This buffer is for notes you don't want to save, and for Lisp evaluation.
;; If you want to create a file, visit that file with C-x C-f,
;; then enter the text in that file's own buffer.

# Mobile Client Application to Server Interface

All objects will be represented in JSON format unless otherwise noted.
`Optional<>` implies that the field is not guaranteed to be included in the object returned from the web server.
`Date` will be represented in JSON as the number of milliseconds since 1 January 1970 00:00:00 UTC.
REST API calls are all to be executed in the context of the currently authenticated user.
User authentication will be handled by Google Account's User service API.


## User

The `User` object represents a user of the App.

### User Object

|Name               |Type                       |Description                                                             |
|-------------------|---------------------------|------------------------------------------------------------------------|
|id                 |`String`                   |Unique identifier for a user (Provided by Google User Service API).   |
|first_name         |`String`                   |Display first name for the user.                                      |
|family_name	    |`String`			|Display family name for the user.                                     |
|username           |`String`     		|User name of a user.                                                  |
|email		    |`String`			|Email address of the user.					       |
|password	    |`String`        		|The password of the user.                         		       |
|access_token	    |`String`			|The access token of the user.					       |

### User REST API

|HTTP Method|URL						|Input		|Output         |Description                                           |
|-----------|---------------------------------------------------|---------------|---------------|------------------------------------------------------|
| GET       |/api/users/first_name     				|           	|		|Get the first name of the user.	               |
| GET 	    |/api/users/last_name				|		|		|Get the last name of the user.			       |
| GET       |/api/users   					|           	|      		|Get the user with the provided user ID.               |
| POST 	    |/api/users						|User		|		|Create the user account.	     		       |
| PUT       |/api/users/{userId}    				|User  		|User      	|Updates a user.                                       |
| GET	    |/api/users/login?email=value&password=value	|`String`	|`Token`   	|Login given username and password.		       |
| GET	    |/api/users/email					|User		|`String`	|Get the email of the user.			       |



## Song
### Song Object

The `Song` object represents a song

|Name               |Type                     |Description                                                             |
|-------------------|-------------------------|------------------------------------------------------------------------|
|id          |String                   |id: unique ID for this song    |
|title          |String                   |title: the title of song                   |
|artist           |String                   |Artist: the artist of song|
|genre           |String                   |genre: the genre of song|
|length           |String                   |length: the length of song|
|rating           |Integer                   |rating: users’ rating of song|

### Song REST API

|HTTP Method|URL                    |Input      |Output         |Description                                           |
|-----------|-----------------------|-----------|---------------|------------------------------------------------------|
| GET       |/api/song             |           |  |Get all songs|
| GET       |/api/song/{songId}    |           |Song      |Get the song with the provided song ID.               |
| GET       |/api/song/{title}    |           |Songs      |Get a list of songs with the provided title.              |
| GET       |/api/song/{artist}    |           |Songs      |Get a list of songs with the provided artist.               |
| GET       |/api/song/{genre}    |           |Songs      |Get a list of songs with the provided genre.               |
| PUT       |/api/song/{songId}    |Song  |Song      |Update an existing song                          |
| POST       |/api/song/{songId}    |Song  |Song      |Add a new song|


## Playlist

The `Playlist` object represents the metadata about a collection of songs.

### Playlist Object

|Name           |Type       |Description                                                                               |
|---------------|-----------|------------------------------------------------------------------------------------------|
|id             |`String`     |Unique identifier for a Playlist.                                                            |
|name           |`String`     |Display name for the Playlist.                                                               |
|hostId         |`String`     |Unique identifier of the user who owns the playlist.                                         |
|members        |`List<Song>`|List of all songs of the Playlist.                                                 |
|link		|`String`	| Link to access playlist					|



### Playlist REST API

|HTTP Method|URL                    |Input      |Output     |Description                                               |
|-----------|-----------------------|-----------|-----------|----------------------------------------------------------|
|GET        |/api/playlists            |           |`List<Playlist>`|Get all Playlists to which the current user has access.    |
|GET        |/api/playlists/{playlistId}  |           |`Playlist`      |Get the Playlist represented by the provided playlistId.        |
|POST       |/api/playlists/           |`Playlist`      |`Playlist`      |Create a new Playlist.                                       |
|PUT        |/api/playlists/{playlistId}  |`Playlist`      |`Playlist`      |Add song to an existing Playlist.                                 
|DELETE     |/api/playlists/{playlistId}/members/{songId}|       |`Playlist`|Remove song from playlist.      |
|GET        |/api/playlist/{playlistId}            |           |`Playlist`    |Get link to access playlist    |
|GET        |/api/playlists/{playlistLink}  |           |`Playlist`      |Get the Playlist represented by the provided playlistLink. |



## FriendsList

The `FriendsList` object represents the metadata about a collection of users that are friends of a user.

### FriendsList Object

|Name           |Type       |Description                                                                               |
|---------------|-----------|------------------------------------------------------------------------------------------|
|host		|User	|the host user of a friends list.                                                          |
|memebers       |User     	|User name of a friend of host.                                                               |

### FriendsList REST API

|HTTP Method|URL                    |Input		    |Output     |Description                                               |
|-----------|-----------------------|-----------	    |-----------|----------------------------------------------------------|
|GET        |/api/friends_list      |			    |`List<User>`		|Get all users that is a friend of the host.		|
|POST       |/api/friends_list/     |List<User>        |`List<User>`      	|Create a friends list.                                 |
|PUT        |/api/friends_list      |List<User>        |`List<User>`      	|Update an existing friends list.                       |



## PlaylistInvitation

The `PlaylistInvitation` object represents a request from a group member to another user to join their group.

### PlaylistInvitation Object

|Name      |Type             |Description                                                                              |
|----------|-----------------|-----------------------------------------------------------------------------------------|
|id        |`String`           |Unique identifier for a Group Invitation.                                                |
|playlist     |`Playlist`            |The group that can be joined.                                                            |
|invitedBy |`User`        |Person that sent the invitation.                                                         |
|recipient |`User`        |Person invited to join the Group.                                                        |
|inviteTime|`Date`          |Time the invitation was created.                                                         |
|accepted  |`Optional<Boolean>`|Response to the invitation. True if accepted, false if rejected, absent if outstanding.  |

### PlaylistInvitation REST API

|HTTP Method|URL                                  |Input          |Output               |Description                   |
|-----------|-------------------------------------|---------------|---------------------|------------------------------|
|GET        |/api/playlist-invitations               |               |`List<PlaylistInvitation>`|Invitations for current user. |
|GET        |/api/playlist-invitations/{invitationId}|               |`PlaylistInvitation`      |Get invitation by provided ID.|
|POST       |/api/playlist-invitations/{recepientId}            | `PlaylistInvitation`|`PlaylistInvitation`      | Send a playlist invitation |
|PUT        |/api/playlist-invitations/{playlistId}|`PlaylistInvitation`|`PlaylistInvitation`      |Accept/reject the invitation. |
