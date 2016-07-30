package Ready;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import Main.GameSkeleton;

public class GameWindow extends JFrame{

	/**
	 * 指定されたタイトル、幅、高さのウィンドウを新しく生成する
	 * @param title ウィンドウのタイトル
	 * @param width ウィンドウの幅
	 * @param height ウィンドウの高さ
	 */
	public GameWindow(String title, int width, int height){
		setTitle(title);
		setSize(width, height);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    GameSkeleton gs = new GameSkeleton(width, height);
	    add(gs, BorderLayout.CENTER);
	    gs.GameStart();
	}

}
