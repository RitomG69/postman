package me.srgantmoomoo.postman.impl.commands;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class Vanish extends Command {
	private static Entity ridden;
	
    public Vanish() {
		super("vanish", "vanish ridden entities.", "vanish", "v");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 0) {
			if (Minecraft.getMinecraft().player.getRidingEntity() != null && ridden == null) {
				ridden = Minecraft.getMinecraft().player.getRidingEntity();
				
			    Minecraft.getMinecraft().player.dismountRidingEntity();
			    Minecraft.getMinecraft().world.removeEntityFromWorld(ridden.getEntityId());
				Main.INSTANCE.commandManager.sendClientChatMessage("entity " + WHITE + ridden.getName() + GRAY + " removed.", true);
			}else {
			    if (ridden != null) {
			    	ridden.isDead = false;
			    	
			        Minecraft.getMinecraft().world.addEntityToWorld(ridden.getEntityId(), ridden);
			        Minecraft.getMinecraft().player.startRiding(ridden, true);
					Main.INSTANCE.commandManager.sendClientChatMessage("entity " + WHITE + ridden.getName() + GRAY + " created.", true);
			        ridden = null;
			    }else
					Main.INSTANCE.commandManager.sendClientChatMessage("no entity is being ridden", true);
			}
		}else
			Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
	}
}