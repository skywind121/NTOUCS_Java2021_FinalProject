package gameTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class button{
	
	public Texture upTexture;	// 按钮 弹起 状态的纹理
	public Texture downTexture;	// 按钮 按下 状态的纹理
	public Button button, floor;	// 按钮
	public Button.ButtonStyle style;
	
	public int checkActor = 0;
	public int checkClick = 0;
	
	ButtonClick BC = new ButtonClick();
	
	public void button(){
		 //创建 弹起 和 按下 两种状态的纹理
		upTexture = new Texture(Gdx.files.internal("button_" + 1 +  ".PNG"));
		downTexture = new Texture(Gdx.files.internal("button_2.PNG"));
		//設定紋理
		add(upTexture, downTexture, button);
		//创建 Button
		button = new Button(style);
		// 设置按钮的位置
		button.setPosition(0, 650);
		//調用ButtonClick function
		BC.PlayButtonClick(button);
	}

	public void floor(){
		upTexture = new Texture(Gdx.files.internal("floor1.PNG"));
		downTexture = new Texture(Gdx.files.internal("floor2.PNG"));
		add(upTexture, downTexture, floor);
		floor = new Button(style);
		floor.setBounds(0,550,40, 40);
		BC.FloorButtonClick(floor);
	}
	
	public void add(Texture upTexture, Texture downTexture, Button button) {
		 //第 2 步: 创建 ButtonStyle
		style = new Button.ButtonStyle();
		
		// 设置 style 的 弹起 和 按下 状态的纹理区域
		style.up = new TextureRegionDrawable(new TextureRegion(upTexture));
		style.down = new TextureRegionDrawable(new TextureRegion(downTexture));
	}
}