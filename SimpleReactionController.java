public class SimpleReactionController implements Controller {

	private int time;
	private int start_time;
	private int state;
	private int reaction_time;
	//0 is initial state
	//1 is coininserted state
	//2 is random wait time state
	//3 is reaction test state
	//4 is results state
	
	private Gui g;
	private Random r;
	
	@Override
	public void connect(Gui gui, Random rng) {
		// TODO Auto-generated method stub
		g = gui;
		r = rng;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		state = 0;
		time = 0;
		start_time = 0;
		g.setDisplay("Insert Coin");
	}

	@Override
	public void coinInserted() {
		// TODO Auto-generated method stub
		state = 1;
		start_time = 0;
		time = 0;
		g.setDisplay("Press GO!");
	}

	@Override
	public void goStopPressed() {
		// TODO Auto-generated method stub
		if(state == 1) {
			start_time = r.getRandom(100, 250);
			g.setDisplay("Wait...");
			state = 2;
		}else if(state == 2) {
			this.init();
		}else if(state == 3) {
			reaction_time = time;
			state = 4;
		}else if(state == 4) {
			this.init();
		}

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(state == 2) {
			time++;
			if(time >= start_time - 1) {
				state = 3;
			}
		}else if(state == 3) {
			time++;
			if((time - start_time) >= 200) {
				reaction_time = time;
				state = 4;
			}
			int time_s = (time - start_time) / 100;
			int time_ms = ((time - start_time) - time_s*100);
			String out = Integer.toString(time_s) + "." + String.format("%02d", time_ms);
			g.setDisplay(out);
		}else if(state == 4) {
			time++;
			if(time >= (reaction_time + 300)) {
				this.init();
			}else {
				int time_s = (reaction_time - start_time) / 100;
				int time_ms = ((reaction_time - start_time) - time_s*100);
				String out = Integer.toString(time_s) + "." + String.format("%02d", time_ms);
				g.setDisplay(out);				
			}
		}

	}

}
