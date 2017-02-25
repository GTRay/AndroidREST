;; This buffer is for notes you don't want to save, and for Lisp evaluation.
;; If you want to create a file, visit that file with C-x C-f,
;; then enter the text in that file's own buffer.

# Web Client Application to Web Server Interface

All objects will be represented in JSON format unless otherwise noted.
`Optional<>` implies that the field is not guaranteed to be included in the object returned from the web server.
`Date` will be represented in JSON as the number of milliseconds since 1 January 1970 00:00:00 UTC.
REST API calls are all to be executed in the context of the currently authenticated user.
User authentication will be handled by Google Account's User service API.




## Location

### Location Object

The `Location` object represents a physical location.

|Name               |Type                     |Description                                                             |
|-------------------|-------------------------|------------------------------------------------------------------------|
|longitude          |double                   |Longitude: east-west coordinate in degrees.                             |
|latitude           |double                   |Latitude: north-south coordinate in degrees.                            |

### Location REST API

No Location REST API.  Object is nested within other objects.




############## THE PART I AM WORKING ON ###############################
## GtNowUser

The `GtNowUser` object represents a user of the GTNow system.

### GtNowUser Object

|Name               |Type                     |Description                                                             |
|-------------------|-------------------------|------------------------------------------------------------------------|
|id                 |`String`                   |Unique identifier for a user (Provided by Google User Service API).     |
|name               |`String`                   |Display name for the user.                                              |
|friends_list       |`Optional<GtNowUsers>`     |Friends list of a user.                                                |
|play_list	    |`Optional<Songs>  `        |Last time that the user location was reported.                          |

### GtNowUser REST API

|HTTP Method|URL				|Input		|Output         |Description                                           |
|-----------|-----------------------------------|---------------|---------------|------------------------------------------------------|
| GET       |/api/users/current     		|           	|GtNowUser      |Get the current user.				       |
| GET       |/api/users/{userId}    		|           	|GtNowUser      |Get the user with the provided user ID.               |
| POST 	    |/api/users				|GtNowUser	|		|Create the user account.	     		       |
| PUT       |/api/users/{userId}    		|GtNowUser  	|GtNowUser      |Updates a user.                                       |
| GET	    |/api/users/current/friends_list	|		|GtNowUser	|Get the friends of the current user.		       |
| GET	    |/api/users/current/play_list	|		|Songs		|Get the play list of the current user.		       |
| GET	    |/api/users/{userId}/friends_list	|		|GtNowUser	|Get the friends list of the user with the ID.	       |
| GET	    |/api/users/{userId}/play_list	|		|Songs		|Get the play list of the user with the ID.	       |




## FriendsList

The `FriendsList` object represents the metadata about a collection of users that are friends of a user.

### FriendsList Object

|Name           |Type       |Description                                                                               |
|---------------|-----------|------------------------------------------------------------------------------------------|
|host		|GtNowUser	|the host user of a friends list.                                                          |
|memebers       |GtNowUser     	|User name of a friend of host.                                                               |

### FriendsList REST API

|HTTP Method|URL                    |Input		    |Output     |Description                                               |
|-----------|-----------------------|-----------	    |-----------|----------------------------------------------------------|
|GET        |/api/friends_list      |			    |`List<GtNowUser>`		|Get all users that is a friend of the host.		|
|POST       |/api/friends_list/     |List<GtNowUser>        |`List<GtNowUser>`      	|Create a friends list.                                 |
|PUT        |/api/friends_list      |List<GtNowUser>        |`List<GtNowUser>`      	|Update an existing friends list.                       |





## GroupMembers

The `GroupMembers` object represents a single group and all of the users that are part of the group.

### GroupMembers Object

|Name           |Type           |Description                                                                           |
|---------------|---------------|--------------------------------------------------------------------------------------|
|group          |`Group`          |The group that this GroupMembers object is describing.                                |
|members        |`List<GtNowUser>`|List of all the members of the group.                                                 |

### GroupMembers REST API

|HTTP Method|URL                                   |Input  |Output      |Description                                   |
|-----------|--------------------------------------|-------|------------|----------------------------------------------|
|GET        |/api/groups/{groupId}/members         |       |`GroupMembers`|Get all GroupMembers for the provided groupId.|
|DELETE     |/api/groups/{groupId}/members/{userId}|       |`GroupMembers`|Remove user from group (may restrict to self).|





## GroupInvitation

The `GroupInvitation` object represents a request from a group member to another user to join their group.

### GroupInvitation Object

|Name      |Type             |Description                                                                              |
|----------|-----------------|-----------------------------------------------------------------------------------------|
|id        |`String`           |Unique identifier for a Group Invitation.                                                |
|group     |`Group`            |The group that can be joined.                                                            |
|invitedBy |`GtNowUser`        |Person that sent the invitation.                                                         |
|recipient |`GtNowUser`        |Person invited to join the Group.                                                        |
|inviteTime|`Date`          |Time the invitation was created.                                                         |
|accepted  |`Optional<Boolean>`|Response to the invitation. True if accepted, false if rejected, absent if outstanding.  |

### GroupInvitation REST API

|HTTP Method|URL                                  |Input          |Output               |Description                   |
|-----------|-------------------------------------|---------------|---------------------|------------------------------|
|GET        |/api/group-invitations               |               |`List<GroupInvitation>`|Invitations for current user. |
|GET        |/api/group-invitations/{invitationId}|               |`GroupInvitation`      |Get invitation by provided ID.|
|POST       |/api/group-invitations               |`GroupInvitation`|`GroupInvitation`      |Create a new group invitation.|
|PUT        |/api/group-invitations/{invitationId}|`GroupInvitation`|`GroupInvitation`      |Accept/reject the invitation. |





## Building

The `Building` object represents a physical building on the Georgia Tech campus.

### Building Object

|Name      |Type             |Description                                                                              |
|----------|-----------------|-----------------------------------------------------------------------------------------|
|name      |`String`           |The name of the building.                                                                |
|location  |`Location`         |The physical location of the building.                                                   |
|address   |`String`           |The street address of the building.                                                      |

### Building REST API

|HTTP Method|URL           |Input |Output        |Description                                                          |
|-----------|--------------|------|--------------|---------------------------------------------------------------------|
| GET       |/api/buildings|      |`List<Building>`|Get List of all Buildings. Filter: `nameFilter` and `addressFilter` query params.|





## EventSchedule

The `EventSchedule` object represents a recurring event.

### EventSchedule Object

|Name          |Type         |Description                                                                              |
|--------------|-------------|-----------------------------------------------------------------------------------------|
|id            |`String`       |Unique ID for this event.                                                                |
|name          |`String`       |The name of the Event (e.g. "CS8803 Class").                                             |
|details       |`String`       |Any additional details.                                                                  |
|building      |`Building`     |Where the event will occur.                                                              |
|startTime     |`Date`      |When the first instance of the scheduled event begins.                                   |
|endTime       |`Date`      |When the first instance of the scheduled event ends.                                     |
|expirationTime|`Date`      |When the schedule is complete, after which no further instances will be created.         |
|daysOfWeek    |`List<Integer>`|List of days of the week per ISO8601; integers Monday (1) through Sunday (7).  |
|active        |`boolean`      |False if deleted, true otherwise.                                                        |

### EventSchedule REST API

|HTTP Method|URL                              |Input        |Output             |Description                           |
|-----------|---------------------------------|-------------|-------------------|--------------------------------------|
| GET       |/api/event-schedules             |             |`List<EventSchedule>`|All schedules for the current user.   |
| GET       |/api/event-schedules/{scheduleId}|             |`EventSchedule`      |Get schedule by its ID.               |
| POST      |/api/event-schedules             |`EventSchedule`|`EventSchedule`      |Create a new event schedule.          |
| PUT       |/api/event-schedules/{scheduleId}|`EventSchedule`|`EventSchedule`      |Update an existing event schedule.    |
| DELETE    |/api/event-schedules/{scheduleId}|             |`EventSchedule`      |Delete an existing event schedule.    |





## Event

The `Event` object represents an occasion that takes place at a specific place and time.

### Event Object

|Name          |Type            |Description                                                                           |
|--------------|----------------|--------------------------------------------------------------------------------------|
|id            |`String`          |Unique ID for this event.                                                             |
|name          |`String`          |The name of the Event (e.g. "CS8803 Class").                                          |
|details       |`String`          |Any additional details.                                                               |
|building      |`Building`        |Where the event will occur.                                                           |
|startTime     |`Date`         |When the event begins.                                                                |
|endTime       |`Date`         |When the event ends.                                                                  |
|scheduleId    |`Optional<String>`|The ID of the EventSchedule that created this event. Absent if non-recurring.                                  |
|active        |`boolean`         |False if deleted, true otherwise.                          |

### Event REST API

|HTTP Method|URL                  |Input|Output     |Description                                                       |
|-----------|---------------------|-----|-----------|------------------------------------------------------------------|
|GET        |/api/events          |     |`List<Event>`|User's events; before=Date query param, default: today @ 23:59.|
|GET        |/api/events/{eventId}|     |`Event`      |Get an event by its ID.                                           |
|POST       |/api/events          |`Event`|`Event`      |Create a new ad-hoc event (no schedule). Supported?               |
|PUT        |/api/events/{eventId}|`Event`|`Event`      |Update an existing event.                                         |
|DELETE     |/api/events/{eventId}|     |`Event`      |Delete an existing event.                                         |


## Route

The directions to get from one location to another.

### Route Object

Google Maps Directions API in JSON format. 

Web server will communicate with backend server to determine the `Event` location, and that of the current user. The Web server will then contact the Google Maps Directions API specifying the current user's location as the start, the event location as the destination, the mode of travel as "walking", and the arrival time as 5 minutes before the start of the `Event`.  This result will be sent unaltered to the client device.

### Route REST API

|HTTP Method|URL                        |Input|Output     |Description                                                 |
|-----------|---------------------------|-----|-----------|------------------------------------------------------------|
|GET        |/api/events/{eventId}/route|     |`Route`      |The route to the `Event` from the current user's last saved location.            |



