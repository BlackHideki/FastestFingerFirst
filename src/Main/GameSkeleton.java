package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Ready.KeyBoard;

/**
 * GameSkeleton
 * ゲームの骨組みを定義する為のクラス
 * @author Hideki Kudo
 * @since 2016/07/30
 * @version 1.0
 */
public class GameSkeleton extends JPanel implements Runnable{

	/**
	 * ウィンドウのサイズ
	 */
	private Dimension windowSize;

	/**
	 *
	 */
	private KeyBoard keyboard;

	/**
	 * スレッド
	 */
	private Thread thread;

	/**
	 * <p>コンストラクタ<br>
	 * 指定された幅と高さのパネルを新しく生成します。<br>
	 * </p>
	 * @param title
	 */
	public GameSkeleton(int width, int height) {
		windowSize = new Dimension(width, height);
		setSize(windowSize.width, windowSize.height);

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
			repaint();
			try{
				Thread.sleep(1 / 60 * 1000);
			}catch(InterruptedException e){
			    e.printStackTrace();
			}
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawString("adwkdoawdia", windowSize.width / 2, windowSize.height / 2);
	}
}
