# zeroAPI

Basic API Wrapper for HugoChat in Java Maven. Additional Dependencies:

- `org.json`
---
# Usage

First of all, you'll need to initialize your client.

### Client Login
If you don't run this first, you won't be able to communicate with the API.
```java
public static void main(String[]args)throws IOException{
    HugoChat client = new HugoChat("HugoBot");
    client.login();
}
```

> You can also set a specific room using `client.setRoom(roomId);`. Without specifying a room, it'll just use the default one.

### Sessions
In order to keep your current session running, it's recommended to call
```java
    client.sendActive();
```
frequently. API Sessions expire every 10 seconds.

### Messages
This will send a message into the current room.
```java
    client.sendMessage("Slowly dancing in the dark");
```

This will return an `JSONArray` of the amount of messages specified.
```java
    client.getMessageHistory(25);
```

If you want to retrieve the Message History from a specific time, use
```java
    client.getMessageHistoryBefore(messageId, 25);
```
or
```java
    client.getMessageHistoryAfter(messageId);
```

### Users
This will return an `JSONArray` of the currently active users in the current channel.
```java
    client.getUsers();
```

In order to change your **own** nickname, use
```java
    client.changeUsername("Porter Robinson");
```

### Rooms
This can be used, to create a Room. You can also specify the visiblity of your room by changing the `isListed` value.
This will also return information about the created room.
```java
    client.createRoom("do-re-mi-fa-so-la-ti-do", true);
```

To get a list of all currently listed rooms, you can use
```java
    client.getRooms();
```

---
## Outro
And that's it! If you found a Bug or want to improve the Documentation, feel free to open a [Pull Request](https://github.com/angelsflyinhell/zeroAPI/pulls) or an [Issue](https://github.com/angelsflyinhell/zeroAPI/issues). <3
