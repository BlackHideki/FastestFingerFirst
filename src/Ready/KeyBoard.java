package Ready;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Main.GameSkeleton;

public class KeyBoard extends KeyAdapter implements KeyListener {

	/**
	 * ゲームの骨組み
	 */
	private GameSkeleton gs;

	/**
	 * キーボードイベントのオブジェクトを新しく生成
	 * @param gs キーボードを対応させるクラス
	 */
	public KeyBoard(GameSkeleton gs){
		this.gs = gs;
	}

	/**
	 * キーが押さている間は呼び出される
	 */
	public void keyPressed(KeyEvent e){
	}

	public void keyReleased(KeyEvent e){

	}

	public void keyTyped(KeyEvent e){

	}

}
