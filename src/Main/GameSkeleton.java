package Main;

import javax.swing.JFrame;

public class GameSkeleton extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -204092683626212119L;

	/**
	 * <p>コンストラクタ<br>
	 * 引数にて指定されたタイトルをウィンドウのタイトルにする事が出来ます。<br>
	 * ウィンドウの可視状態にします。<br>
	 * </p>
	 * @param title
	 */
	public GameSkeleton(String title){
		setTitle(title);
		setVisible(true);
	}

	/**
	 * メインメソッド
	 * ゲームプログラムを実行させる事が出来ます
	 * @param args
	 */
	public static void main(String[] args) {
		GameSkeleton gs = new GameSkeleton("タイピングゲーム");
	}
}
