package me.srgantmoomoo.postman.impl.commands;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.command.Command;

public class Friend extends Command {
	
    public Friend() {
		super("friend", "manage your friends.", "friend list | friend add <name> | friend remove <name> | friend clear", "f");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 2) {
			if(args[0].equalsIgnoreCase("add")) {
				Main.INSTANCE.friendManager.addFriend(args[1]);
				Main.INSTANCE.commandManager.sendClientChatMessage("added friend " + WHITE + args[1].toUpperCase() + GRAY + ".", true);
			}else if(args[0].equalsIgnoreCase("remove")) {
				if(Main.INSTANCE.friendManager.isFriend(args[1])) {
					Main.INSTANCE.friendManager.removeFriend(args[1]);
					Main.INSTANCE.commandManager.sendClientChatMessage("removed friend: " + WHITE + args[1].toUpperCase() + GRAY + ".", true);
				}else
					Main.INSTANCE.commandManager.sendClientChatMessage("player " + WHITE + args[1] + GRAY + " is not on your friends list.", true);
			}else
				Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
		}else if(args.length == 1) {
			if(args[0].equalsIgnoreCase("list")) {
				Main.INSTANCE.commandManager.sendClientChatMessage("friends: " + WHITE + Main.INSTANCE.friendManager.getFriendsByName() + GRAY + ".", true);
			}else if(args[0].equalsIgnoreCase("clear")) {
				Main.INSTANCE.friendManager.clearFriends();
				Main.INSTANCE.commandManager.sendClientChatMessage("cleared all friends.", true);
			}else
				Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
		}else
			Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
	}

}