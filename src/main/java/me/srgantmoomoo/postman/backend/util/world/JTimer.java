package me.srgantmoomoo.postman.backend.util.world;

public class JTimer{
	private long current;

	public JTimer(){
		this.current = System.currentTimeMillis();
	}

	public boolean hasReached(final long delay){
		return System.currentTimeMillis() - this.current >= delay;
	}

	public boolean hasReached(final long delay, boolean reset){
		if (reset)
			reset();
		return System.currentTimeMillis() - this.current >= delay;
	}

	public void reset(){
		this.current = System.currentTimeMillis();
	}

	public long getTimePassed(){
		return System.currentTimeMillis() - this.current;
	}

	public boolean sleep(final long time){
		if (time() >= time){
			reset();
			return true;
		}
		return false;
	}

	public long time() {
		return System.currentTimeMillis() - current;
	}
}
