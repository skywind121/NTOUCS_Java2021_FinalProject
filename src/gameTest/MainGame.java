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

/**  游戏主程序的启动入口类 */
public class MainGame extends ApplicationAdapter {
	
	public static final String TAG = MainGame.class.getSimpleName();
	
	// 视口世界的宽高统使用 1280 * 720, 并统一使用伸展视口（StretchViewport）
	public static final float WORLD_WIDTH = 1280;
	public static final float WORLD_HEIGHT = 720;
	
	//調用button function
	button buttonClick = new button();
	StageCreate SC = new StageCreate();
	
	@Override
	public void create() {
		// 设置日志输出级别
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		SC.CreateStage();
	}
	
	@Override
	public void render() {
		// 黑色清屏
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// 更新舞台逻辑
		SC.stage.act();
		// 绘制舞台
		SC.stage.draw();
		
	}

	@Override
	public void dispose() {
		// 应用退出时释放资源
		if (buttonClick.upTexture != null) 
			buttonClick.upTexture.dispose();
		if (buttonClick.downTexture != null) 
			buttonClick.downTexture.dispose();
		if (SC.stage != null) 
			SC.stage.dispose();
	}

}