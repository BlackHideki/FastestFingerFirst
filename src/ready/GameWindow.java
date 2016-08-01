package ready;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

	/**
	 * ゲームの骨組み
	 */
	private GameSkeleton game;

	/**
	 * キーフラグ
	 */
	private boolean keyFlg;

	/**
	 * 指定されたタイトル、幅、高さのウィンドウを新しく生成する
	 * @param title ウィンドウのタイトル
	 * @param width ウィンドウの幅
	 * @param height ウィンドウの高さ
	 */
	public GameWindow(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setResizable(false);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    keyFlg = false;
	    enableEvents(AWTEvent.KEY_EVENT_MASK);

	    enableEvents(AWTEvent.MOUSE_EVENT_MASK);

	    game = new GameSkeleton(width, height);
	    add(game, BorderLayout.CENTER);
	    game.GameStart();
	}

	/**
	 * キーイベント
	 */
	protected void processKeyEvent(KeyEvent e) {
		if(e.getID() == 400 || e.getID() == 401){
			if(!keyFlg){
				keyFlg = true;
				game.keyPressed(e.getKeyCode());
			}
		}else if(e.getID() == 402){
			if(keyFlg){
				keyFlg = false;
				game.keyReleased();
			}
		}
	}

	/**
	 * マウスイベント
	 */
	protected void processMouseEvent(MouseEvent e){
		if(e.getID() == 501){
			game.mouseClick(e.getPoint());
		}else if(e.getID() == 502){
		}
	}
}
