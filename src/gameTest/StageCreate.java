package gameTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class StageCreate {
	// 舞台
	public Stage stage;
	
	button bt = new button();
	
	public void CreateStage() {
		// 使用伸展视口（StretchViewport）创建舞台
		stage = new Stage(new StretchViewport(1280, 720));
 
        // 将输入处理设置到舞台（必须设置, 否则点击按钮没效果）
        Gdx.input.setInputProcessor(stage);
		bt.button();
		bt.floor();
		
		//添加 button 到舞台
        stage.addActor(bt.button);
        stage.addActor(bt.floor);
	}
}
