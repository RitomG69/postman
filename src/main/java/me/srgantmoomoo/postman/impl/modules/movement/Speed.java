package me.srgantmoomoo.postman.impl.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.events.PlayerMoveEvent;
import me.srgantmoomoo.postman.backend.util.world.EntityUtil;
import me.srgantmoomoo.postman.backend.util.world.JTimer;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.MobEffects;

/*
 * strafe is iffy rn, vanilla obvi doesn't work in most cases, strafe utils 
 */

public class Speed extends Module {
	public NumberSetting timerSpeed = new NumberSetting("timerSpeed", this, 1.15, 1, 1.5, 0.01);
	public NumberSetting jumpHeight = new NumberSetting("jumpHeight", this, 0.41, 0, 1, 0.01);
	public NumberSetting vanillaSpeed = new NumberSetting("vanillaSpeed", this, 1.0, 0.1, 4.0, 0.1);
	public NumberSetting strafeSpeed = new NumberSetting("strafeSpeed", this, 1.9, 0.0, 4.0, 0.1);
	public ModeSetting mode = new ModeSetting("mode", this, "strafe", "strafe", "vanilla");
	
	public Speed() {
		super ("speed", "speeeeeeeeddddyyy.", Keyboard.KEY_NONE, Category.MOVEMENT);
		this.addSettings(mode, vanillaSpeed, strafeSpeed, timerSpeed, jumpHeight);
	}
	private boolean slowDown;
	private double playerSpeed;
	private final JTimer timer = new JTimer();
	
	@Override
	public void onEnable() {
		playerSpeed = EntityUtil.getBaseMoveSpeed();
	}
	
	@Override
	public void onDisable() {
		timer.reset();
		EntityUtil.resetTimer();
	}
	
	@Override
	public void onUpdate() {
		if(mc.player == null || mc.world == null) {
			disable();
			return;
		}
		if(mode.is("vanilla")) {
			if(mc.player.moveForward > 0) {
				double direction = getDirection();
				double speed = vanillaSpeed.getValue();
				EntityUtil.setTimer((float)timerSpeed.getValue());
				mc.player.motionX = -Math.sin(direction) * speed; 
				mc.player.motionZ = Math.cos(direction) * speed;
			}
		}
	}
		
	@EventHandler
	private final Listener<PlayerMoveEvent> playerMoveEventListener = new Listener<>(event -> {
		if(mc.player.isInLava() || mc.player.isInWater() || mc.player.isOnLadder() || mc.player.isInWeb) {
			return;
		}
		if(mode.getMode().equalsIgnoreCase("strafe")) {
			double heightY = jumpHeight.getValue();
			if(mc.player.onGround && EntityUtil.isMoving(mc.player) && timer.hasReached(300)) {
				EntityUtil.setTimer((float)timerSpeed.getValue());
				if(mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
					heightY += (mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1f;
				}
				event.setY(mc.player.motionY = heightY);
				playerSpeed = EntityUtil.getBaseMoveSpeed() * (EntityUtil.isColliding(0, -0.5, 0) instanceof BlockLiquid && !EntityUtil.isInLiquid() ? 0.9 : strafeSpeed.getValue());
				slowDown = true;
				timer.reset();
			}else {
				EntityUtil.resetTimer();
				if(slowDown || mc.player.collidedHorizontally) {
					playerSpeed -= (EntityUtil.isColliding(0, -0.8, 0) instanceof BlockLiquid && !EntityUtil.isInLiquid()) ? 0.4 : 0.7 * (playerSpeed = EntityUtil.getBaseMoveSpeed());
					slowDown = false;
				}else {
					playerSpeed -= playerSpeed / 159.0;
				}
			}
			playerSpeed = Math.max(playerSpeed, EntityUtil.getBaseMoveSpeed());
			double[] dir = EntityUtil.forward(playerSpeed);
			event.setX(dir[0]);
			event.setZ(dir[1]);
		}
		
	});
	
	public static float getDirection() {
		float var1 = mc.player.rotationYaw;
		
		if(mc.player.moveForward < 0.0f) var1 += 180.0f;
		float forward = 1.0f;
		
		if(mc.player.moveForward < 0.0f) forward = -0.5f;
		else if(mc.player.moveForward > 0.0f) forward = 0.5f;
		
		if(mc.player.moveStrafing > 0.0f) var1 -= 90.f * forward;
		
		if(mc.player.moveStrafing < 0.0f) var1 += 90.0f * forward;
		
		var1 *= 0.017453292f;
		return var1;
	}
}
