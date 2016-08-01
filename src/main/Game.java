package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;
import java.util.Random;

import file.ImageFileReader;
import file.TextFileReader;
import ready.GameStart;

public class Game {

	/**
	 * フォント
	 */
	private Font font;

	/**
	 * ランダムクラス
	 */
	private Random random;

	/**
	 * テキストファイルリーダー
	 */
	private TextFileReader ranking;

	/**
	 * イメージファイルを格納
	 */
	private ImageFileReader titleBg;
	private ImageFileReader titleLogo;
	private ImageFileReader menuStart;
	private ImageFileReader menuRanking;
	private ImageFileReader cursor;
	private ImageFileReader rankingBg;
	private ImageFileReader rankingLogo;
	private ImageFileReader rankingOne;
	private ImageFileReader rankingTwo;
	private ImageFileReader rankingThree;
	private ImageFileReader menuBack;
	private ImageFileReader[] gameImages1;
	private ImageFileReader[] gameImages2;
	private ImageFileReader startForEnter;
	private ImageFileReader gameOverImage;

	/**
	 * 現在表示されているイメージ
	 */
	private int currentImage;

	/**
	 * 表示座標
	 */
	private Point[] cursorPoint;
	private Point gameImagesPoint1;
	private Point[] gameImagesPoint2;

	/**
	 * 現在のスコア
	 */
	private int currentScore;

	/**
	 * 最終スコア
	 */
	private int score;

	/**
	 * 制限時間
	 */
	private float timer;

	/**
	 * シーンフラグ
	 */
	private int sceneFlag;

	/**
	 * ゲームフラグ
	 */
	private int gameFlag;

	/**
	 * コンストラクタ
	 */
	public Game() {
		font = new Font("メイリオ", Font.BOLD, 32);

		random = new Random();

		ranking = new TextFileReader("text/ranking.txt");
		ranking.textReader();

		titleBg = new ImageFileReader("images/title_bg.png");
		titleLogo = new ImageFileReader("images/title_logo.png");
		menuStart = new ImageFileReader("images/menu_start.png", 120, 55);
		menuRanking = new ImageFileReader("images/menu_ranking.png", 165, 55);
		cursor = new ImageFileReader("images/cursor.png", 55, 55);
		rankingBg = new ImageFileReader("images/ranking_bg.png");
		rankingLogo = new ImageFileReader("images/menu_ranking.png");
		rankingOne = new ImageFileReader("images/ranking_one.png", 100, 100);
		rankingTwo = new ImageFileReader("images/ranking_two.png", 70, 70);
		rankingThree = new ImageFileReader("images/ranking_three.png", 50, 50);
		menuBack = new ImageFileReader("images/menu_back.png", 101, 55);
		gameImages1 = new ImageFileReader[3];
		gameImages2 = new ImageFileReader[3];
		String[] imagePath1 = new String[]{"images/zidane.jpg", "images/link.jpg", "images/riku.jng.jpg"};
		String[] imagePath2 = new String[]{"images/chokobo.jpg", "images/Hairia.jpg", "images/souleater.pg.jpg"};
		for(int i = 0; i < gameImages1.length; i++){
			gameImages1[i] = new ImageFileReader(imagePath1[i], 200, 200);
			gameImages2[i] = new ImageFileReader(imagePath2[i], 100, 100);
		}
		startForEnter = new ImageFileReader("images/start_for_enter.png");
		gameOverImage = new ImageFileReader("images/game_over.png", 800, 600);

		cursorPoint = new Point[2];
		cursorPoint[0] = new Point(GameStart.WINDOW_WIDTH / 2 - menuStart.getSize().width, GameStart.WINDOW_HEIGHT / 2);
		cursorPoint[1] = new Point(GameStart.WINDOW_WIDTH / 2 - menuRanking.getSize().width, GameStart.WINDOW_HEIGHT / 2 + menuStart.getSize().height);
		gameImagesPoint1 = new Point(GameStart.WINDOW_WIDTH / 2 - gameImages1[0].getSize().width / 2, GameStart.WINDOW_HEIGHT / 6);
		gameImagesPoint2 = new Point[gameImages2.length];
		gameImagesPoint2[0] = new Point(GameStart.WINDOW_WIDTH / 4 * 1 - gameImages2[0].getSize().width / 2, GameStart.WINDOW_HEIGHT / 2);
		gameImagesPoint2[1] = new Point(GameStart.WINDOW_WIDTH / 4 * 2 - gameImages2[1].getSize().width / 2, GameStart.WINDOW_HEIGHT / 2);
		gameImagesPoint2[2] = new Point(GameStart.WINDOW_WIDTH / 4 * 3 - gameImages2[2].getSize().width / 2, GameStart.WINDOW_HEIGHT / 2);

		cursor.setPosition(cursorPoint[0]);
		currentImage = random.nextInt(3);
		randomSetPosition();

		currentScore = 0;

		timer = 10.0f;

		sceneFlag = 0;

		gameFlag = 0;
	}

	public void init(){
		currentImage = random.nextInt(3);
		randomSetPosition();
		currentScore = 0;
		timer = 10.0f;
	}

	/**
	 * 実行処理
	 */
	public void action() {
		switch(sceneFlag){
		case 0:
			break;

		case 1:
			if(gameFlag == 1) {
				timer -= 1 / 60.0f;
				if(timer < 0){
					score = currentScore;
					int[] tmp = new int[ranking.getText().size() + 1];
					for(int i = 0; i < tmp.length; i++){
						if(i < 3){
							tmp[i] = Integer.valueOf(ranking.getText().get(i));
						}else{
							tmp[i] = 0;
						}
					}

					for(int i : tmp){
						if(score > i){
							tmp[tmp.length - 1] = score;
						}
					}

					Arrays.sort(tmp);
					ranking.getText().clear();
					for(int i = 3; i > 0; i--){
						ranking.getText().add(String.valueOf(tmp[i]));
					}
					ranking.textWriter(ranking.getText());

					sceneFlag = 2;
				}
			}
			break;

		case 2:
			break;

		case 3:
			break;
		}
	}

	/**
	 * 描画
	 *
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setFont(font);
		switch(sceneFlag){
		case 0:
			g.drawImage(titleBg.getImage(), 0, -20, null);
			g.drawImage(titleLogo.getImage(), GameStart.WINDOW_WIDTH / 2 - titleLogo.getSize().width / 2, GameStart.WINDOW_HEIGHT / 8, null);
			g.drawImage(menuStart.getImage(), GameStart.WINDOW_WIDTH / 2 - menuStart.getSize().width / 2, GameStart.WINDOW_HEIGHT / 2, null);
			g.drawImage(menuRanking.getImage(), GameStart.WINDOW_WIDTH / 2 - menuRanking.getSize().width / 2, GameStart.WINDOW_HEIGHT / 2 + menuStart.getSize().height, null);
			g.drawImage(cursor.getImage(), cursor.getPosition().x, cursor.getPosition().y, null);
			break;

		case 1:
			g.setColor(Color.BLACK);
			g.drawString("SCORE : " + String.valueOf(currentScore), 32, 32);
			g.drawString("TIME : " + String.format("%.1f", timer), 32 * 8, 32);
			g.drawImage(gameImages1[currentImage].getImage(), gameImagesPoint1.x, gameImagesPoint1.y, null);
			g.drawImage(gameImages2[0].getImage(), gameImages2[0].getPosition().x, gameImages2[0].getPosition().y, null);
			g.drawImage(gameImages2[1].getImage(), gameImages2[1].getPosition().x, gameImages2[1].getPosition().y, null);
			g.drawImage(gameImages2[2].getImage(), gameImages2[2].getPosition().x, gameImages2[2].getPosition().y, null);

			if(gameFlag == 0){
				g.drawImage(startForEnter.getImage(), gameImagesPoint2[0].x - 40, gameImagesPoint2[0].y, null);
			}
			break;

		case 2:
			g.setColor(Color.WHITE);
			g.drawImage(gameOverImage.getImage(), 0, 0, null);
			g.drawString("SCORE : " + String.valueOf(currentScore), GameStart.WINDOW_WIDTH / 2 - 32 * 6 / 2, GameStart.WINDOW_HEIGHT / 4 * 3);
			break;

		case 3:
			g.setColor(Color.BLACK);
			g.drawImage(rankingBg.getImage(), 0, 0, null);
			g.drawImage(rankingLogo.getImage(), GameStart.WINDOW_WIDTH / 2 - rankingLogo.getSize().width / 2, GameStart.WINDOW_HEIGHT / 8, null);

			g.drawImage(rankingOne.getImage(), GameStart.WINDOW_WIDTH / 2 - rankingLogo.getSize().width, GameStart.WINDOW_HEIGHT / 8 + rankingLogo.getSize().height, null);
			g.setFont(new Font("メイリオ", Font.BOLD, 80));
			if(ranking.getText().get(0).length() > 1){
				g.drawString(ranking.getText().get(0), GameStart.WINDOW_WIDTH / 2 - rankingLogo.getSize().width / 2 + 60, GameStart.WINDOW_HEIGHT / 4 + rankingLogo.getSize().height + 5);
			}else{
				g.drawString(ranking.getText().get(0), GameStart.WINDOW_WIDTH / 2 - rankingLogo.getSize().width / 2 + 85, GameStart.WINDOW_HEIGHT / 4 + rankingLogo.getSize().height + 5);
			}

			g.drawImage(rankingTwo.getImage(), GameStart.WINDOW_WIDTH / 2 - rankingLogo.getSize().width + 15, GameStart.WINDOW_HEIGHT / 8 + rankingLogo.getSize().height + rankingOne.getSize().height + 20, null);
			g.setFont(new Font("メイリオ", Font.BOLD, 60));
			if(ranking.getText().get(1).length() > 1){
				g.drawString(ranking.getText().get(1), GameStart.WINDOW_WIDTH / 2 - rankingLogo.getSize().width / 2 + 73, GameStart.WINDOW_HEIGHT / 4 + rankingLogo.getSize().height + 110);
			}else{
				g.drawString(ranking.getText().get(1), GameStart.WINDOW_WIDTH / 2 - rankingLogo.getSize().width / 2 + 92, GameStart.WINDOW_HEIGHT / 4 + rankingLogo.getSize().height + 110);
			}

			g.drawImage(rankingThree.getImage(), GameStart.WINDOW_WIDTH / 2 - rankingLogo.getSize().width + 25, GameStart.WINDOW_HEIGHT / 8 + rankingLogo.getSize().height + rankingOne.getSize().height + rankingTwo.getSize().height + 40, null);
			g.setFont(new Font("メイリオ", Font.BOLD, 40));
			if(ranking.getText().get(2).length() > 1){
				g.drawString(ranking.getText().get(2), GameStart.WINDOW_WIDTH / 2 - rankingLogo.getSize().width / 2 + 84, GameStart.WINDOW_HEIGHT / 4 + rankingLogo.getSize().height + 180);
			}else{
				g.drawString(ranking.getText().get(2), GameStart.WINDOW_WIDTH / 2 - rankingLogo.getSize().width / 2 + 99, GameStart.WINDOW_HEIGHT / 4 + rankingLogo.getSize().height + 180);
			}

			g.drawImage(menuBack.getImage(), GameStart.WINDOW_WIDTH - menuBack.getSize().width - 20, GameStart.WINDOW_HEIGHT - menuBack.getSize().height * 2, null);
			g.drawImage(cursor.getImage(), GameStart.WINDOW_WIDTH - menuBack.getSize().width - 80, GameStart.WINDOW_HEIGHT - menuBack.getSize().height * 2, null);
			break;
		}
	}

	/**
	 * キーが押された時
	 *
	 * @param key
	 */
	public void keyPressed(int key) {
		switch(sceneFlag){
		case 0:
			switch(key){
			case 38:
				cursor.setPosition(cursorPoint[0]);
				break;

			case 40:
				cursor.setPosition(cursorPoint[1]);
				break;

			case 10:
				if(cursor.getPosition().equals(cursorPoint[0])){
					init();
					gameFlag = 0;
					sceneFlag = 1;
				}else{
					sceneFlag = 3;
				}
				break;
			}
			break;

		case 1:
			if(key == 10){
				gameFlag = 1;
			}

			if(gameFlag == 1){
				switch(currentImage){
				case 0:
					switch(drawWhereImage(gameImages2[0].getPosition().x)){
					case 0:
						if(key == 90){
							currentImage = random.nextInt(3);
							randomSetPosition();
							currentScore++;
						}else{
							if(currentScore > 0){
								currentScore--;
							}
						}
						break;

					case 1:
						if(key == 88){
							currentImage = random.nextInt(3);
							randomSetPosition();
							currentScore++;
						}else{
							if(currentScore > 0){
								currentScore--;
							}
						}
						break;

					case 2:
						if(key == 67){
							currentImage = random.nextInt(3);
							randomSetPosition();
							currentScore++;
						}else{
							if(currentScore > 0){
								currentScore--;
							}
						}
						break;
					}
					break;

				case 1:
					switch(drawWhereImage(gameImages2[1].getPosition().x)){
					case 0:
						if(key == 90){
							currentImage = random.nextInt(3);
							randomSetPosition();
							currentScore++;
						}else{
							if(currentScore > 0){
								currentScore--;
							}
						}
						break;

					case 1:
						if(key == 88){
							currentImage = random.nextInt(3);
							randomSetPosition();
							currentScore++;
						}else{
							if(currentScore > 0){
								currentScore--;
							}
						}
						break;

					case 2:
						if(key == 67){
							currentImage = random.nextInt(3);
							randomSetPosition();
							currentScore++;
						}else{
							if(currentScore > 0){
								currentScore--;
							}
						}
						break;
					}
					break;

				case 2:
					switch(drawWhereImage(gameImages2[2].getPosition().x)){
					case 0:
						if(key == 90){
							currentImage = random.nextInt(3);
							randomSetPosition();
							currentScore++;
						}else{
							if(currentScore > 0){
								currentScore--;
							}
						}
						break;

					case 1:
						if(key == 88){
							currentImage = random.nextInt(3);
							randomSetPosition();
							currentScore++;
						}else{
							if(currentScore > 0){
								currentScore--;
							}
						}
						break;

					case 2:
						if(key == 67){
							currentImage = random.nextInt(3);
							randomSetPosition();
							currentScore++;
						}else{
							if(currentScore > 0){
								currentScore--;
							}
						}
						break;
					}
					break;
				}
			}
			break;

		case 2:
			if(key == 10){
				cursor.setPosition(cursorPoint[0]);
				sceneFlag = 0;
			}
			break;

		case 3:
			if(key == 10){
				cursor.setPosition(cursorPoint[0]);
				sceneFlag = 0;
			}
			break;
		}
	}

	/**
	 * キーが離された時
	 */
	public void keyReleased() {
	}

	/**
	 * マウスがクリックされた時
	 */
	public void mouseClick(Point p) {
	}

	/**
	 * 画像の表示座標を調べる
	 */
	private int drawWhereImage(int x){
		int result = -1;
		for(int i = 0; i < gameImagesPoint2.length; i++){
			if(x == gameImagesPoint2[i].x){
				result = i;
			}
		}
		return result;
	}

	/**
	 * 表示座標を3パターンからランダムに表示させる
	 */
	private void randomSetPosition() {
		Random rnd = new Random();
		int[] index = new int[]{-1, -1, -1};
		for(int i = 0; i < index.length; i++){
			int r = rnd.nextInt(3);
			while(arrayOverlap(r, index)){
				r = rnd.nextInt(3);
			}
			index[i] = r;
		}

		for(int i = 0; i < gameImages2.length; i++){
			gameImages2[i].setPosition(gameImagesPoint2[index[i]]);
		}
	}

	/**
	 * 指定された配列内の要素を調べる
	 * @param num
	 * @param array
	 * @return
	 */
	private boolean arrayOverlap(int num, int[] array) {
		boolean result = false;
		for(int i = 0; i < array.length - 1; i++){
			if(array[i] == num){
				result = true;
			}
		}
		return result;
	}
}
