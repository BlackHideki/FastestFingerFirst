package ready;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import main.Game;

/**
 * GameSkeleton
 * ゲームの骨組みを定義する為のクラス
 * @author Hideki Kudo
 * @since 2016/07/30
 * @version 1.0
 */
public class GameSkeleton extends JPanel implements Runnable {

	/**
	 * ウィンドウのサイズ
	 */
	private Dimension windowSize;

	/**
	 * スレッド
	 */
	private Thread thread;

	/**
	 * ゲーム
	 */
	private Game game;

	/**
	 * <p>コンストラクタ<br>
	 * 指定された幅と高さのパネルを新しく生成します。<br>
	 * </p>
	 * @param title
	 */
	public GameSkeleton(int width, int height) {
		windowSize = new Dimension(width, height);
		setSize(windowSize.width, windowSize.height);
		setBackground(Color.WHITE);
		game = new Game();

	    thread = null;
	}

	/**
	 * <p>ゲームスタートメソッド<br>
	 * ゲームを開始する事が出来ます。<br>
	 * </p>
	 */
	public void GameStart() {
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
	}

	/**
	 * <p>ゲームループメソッド<br>
	 * ゲームループをする事が出来ます。<br>
	 * 1秒間に60回呼び出されます。<br>
	 * </p>
	 */
	public void run() {
		while(true){
			action();
			repaint();
			try{
				Thread.sleep(1000/60);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * 処理
	 */
	public void action(){
		game.action();
	}

	/**
	 * グラフィックを描画
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		game.draw(g);
	}

	/**
	 * キーが押された時に呼び出される
	 * @param key 押されたキーの種類
	 */
	public void keyPressed(int key) {
		game.keyPressed(key);
	}

	/**
	 * キーが離された時に呼び出される
	 */
	public void keyReleased() {
		game.keyReleased();
	}

	/**
	 * マウスがクリックした時に呼び出される
	 */
	public void mouseClick(Point p) {
		game.mouseClick(p);
	}
}
