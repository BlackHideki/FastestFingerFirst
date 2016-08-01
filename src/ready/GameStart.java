package ready;

public class GameStart {

	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;

	public static void main(String[] args){
		GameWindow gw = new GameWindow("タイピングゲーム", WINDOW_WIDTH, WINDOW_HEIGHT);
		gw.setVisible(true);
	}
}
