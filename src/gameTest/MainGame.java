package gameTest;
		
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
	
/**
 * 游戏主程序的启动入口类
 */
public class MainGame extends ApplicationAdapter {
	
	public static final String TAG = MainGame.class.getSimpleName();
	
	// 视口世界的宽高统使用 480 * 800, 并统一使用伸展视口（StretchViewport）
	public static final float WORLD_WIDTH = 1280;
	public static final float WORLD_HEIGHT = 720;
	
	// 纹理画布
	public SpriteBatch batch;
	// 纹理
	public Texture texture;
	// 自定义的演员
	public MyActor actor;
	
	// 舞台
	public Stage stage;
	
	//調用button function
	button buttonClick = new button();

	@Override
	public void create() {
		// 设置日志输出级别
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		// 使用伸展视口（StretchViewport）创建舞台
        stage = new Stage(new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT));
        
        // 将输入处理设置到舞台（必须设置, 否则点击按钮没效果）
        Gdx.input.setInputProcessor(stage);
		buttonClick.button();
		buttonClick.floor();
		
		 // 创建纹理画布
		batch = new SpriteBatch();
		// 创建纹理, badlogic.jpg 图片的宽高为 256 * 256
		texture = new Texture(Gdx.files.internal("badlogic.jpg"));
		// 创建演员
		actor = new MyActor(new TextureRegion(texture));
		/* 设置演员属性的值 */
		// 设置演员的坐标
		actor.setPosition(50, 100);		// 或者 setX(), setY() 分开设置
		// 设置演员 旋转和缩放支点 为演员的左下角
		actor.setOrigin(0, 0);

		System.out.printf("Test3\n");
		
		// 给按钮添加点击监听器
		buttonClick.button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(TAG, "add play");
				
				
				
				
				
				
				
			}
		});
		
		buttonClick.floor.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(TAG, "add floor");
			}
		});
		
		//第 4 步: 添加 button 到舞台
        stage.addActor(buttonClick.button);
        stage.addActor(buttonClick.floor);
        
        
        
	    
	}
	@Override
	public void render() {
		// 黑色清屏
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// 更新舞台逻辑
		stage.act();
		// 绘制舞台
		stage.draw();
		
		// 更新演员逻辑
		actor.act(Gdx.graphics.getDeltaTime());
		batch.begin();
		// 绘制演员（这里暂且先直接绘制, 位置相对于屏幕左下角）
		actor.draw(batch, 1.0F);
		batch.end();
		

	}

	@Override
	public void dispose() {
		// 应用退出时释放资源
		if (buttonClick.upTexture != null) {
			buttonClick.upTexture.dispose();
		}
		if (buttonClick.downTexture != null) {
			buttonClick.downTexture.dispose();
		}
		if (stage != null) {
			stage.dispose();
		}
		
		// 当应用退出时释放资源
		if (batch != null) {
			batch.dispose();
		}
		// 应用退出, 纹理不在需要用到, 释放纹理资源
		if (texture != null) {
			texture.dispose();
		}
	}

}