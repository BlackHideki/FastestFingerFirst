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
		if (isMac()) {
			System.out.println("Mac");
            // JFrameにメニューをつけるのではなく、一般的なOSXアプリ同様に画面上端のスクリーンメニューにする.
            System.setProperty("apple.laf.useScreenMenuBar", "true");

            // スクリーンメニュー左端に表記されるアプリケーション名を設定する
            // (何も設定しないとクラス名になる。)
            System.setProperty("com.apple.mrj.application.apple.menu.about.name","MacJavaSample");
        }else{
        	System.out.println("macじゃない");
        }

		GameSkeleton gs = new GameSkeleton("タイピングゲーム");
	}

    protected static boolean isMac() {
        // MacOSXで動作しているか?
        String lcOSName = System.getProperty("os.name").toLowerCase();
        return lcOSName.startsWith("mac os x");
    }

}
