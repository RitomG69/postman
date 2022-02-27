package me.srgantmoomoo.postman.framework.friend;

import java.util.ArrayList;
import java.util.List;

import me.srgantmoomoo.Main;

public class FriendManager {

	public static List<Friend> friends;

	public FriendManager(){
		friends = new ArrayList<>();
	}

	public static List<String> getFriendsByName() {
		ArrayList<String> friendsName = new ArrayList<>();
		friends.forEach(friend -> friendsName.add(friend.getName()));

		return friendsName;
	}

	public static boolean isFriend(String name) {
		boolean b = false;
		for (Friend f : friends) {
			if (f.getName().equalsIgnoreCase(name)) {
				b = true;
				break;
			}
		}

		return b;
	}

	public static Friend getFriendByName(String name) {
		Friend fr = null;
		for (Friend f : friends) {
			if (f.getName().equalsIgnoreCase(name)) {
				fr = f;
			}
		}

		return fr;
	}

	public static void addFriend(String name) {
		friends.add(new Friend(name));
		
		if(Main.INSTANCE.saveLoad != null) {
			Main.INSTANCE.saveLoad.save();
		}
	}

	public static void removeFriend(String name) {
		friends.remove(getFriendByName(name));
	}
	
	public static void clearFriends() {
		friends.clear();
	}
}