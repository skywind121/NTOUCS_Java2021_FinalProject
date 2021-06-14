package gameTest;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * 游戏主程序的启动入口类
 */
public class MainGame extends ApplicationAdapter {

    private static final String TAG = MainGame.class.getSimpleName();
    public static final float WORLD_WIDTH = 1280;
	public static final float WORLD_HEIGHT = 720;

    // 纹理
    private Texture texture;
    // 演员
    private MyActor actor;
    // 舞台
    private Stage stage;
    
    public int checkClick = 0;	//當actor被選擇時為1，判斷是否有東西被選中
    public Texture upTexture;	// 按钮 弹起 状态的纹理
	public Texture downTexture;	// 按钮 按下 状态的纹理
	public Button button, floor, floor1;	// 按钮
	public Button.ButtonStyle style;		//按鈕風格

    @Override
    public void create() {
        // 设置 Log 输出级别
        Gdx.app.setLogLevel(Application.LOG_DEBUG);      
        ButtonCreate();
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
       
    }

    @Override
    public void dispose() {
    	// 应用退出时释放资源
		if (upTexture != null) 
			upTexture.dispose();
		if (downTexture != null) 
			downTexture.dispose();
		if (stage != null) 
			stage.dispose();
		if (texture != null) 
			texture.dispose();
    }
    
    public void ButtonCreate() {
    	//設置舞台
        stage = new Stage();
        // 将输入处理设置到舞台（必须设置, 否则点击按钮没效果）
        Gdx.input.setInputProcessor(stage);
		buttonStart();
		floor();
		floor1();
        /* 事件初始化 */
        // 首先必须注册输入处理器（stage）, 将输入的处理设置给 舞台（Stage 实现了 InputProcessor 接口）
        // 这样舞台才能接收到输入事件, 分发给相应的演员 或 自己处理。
        Gdx.input.setInputProcessor(stage);

        // 给舞台添加输入监听器（包括触屏, 鼠标点击, 键盘按键 的输入）
        stage.addListener(new MyInputListener());		
    }
    
    public void buttonStart(){
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
	    PlayButtonClick(button);
	  //添加 button 到舞台
        stage.addActor(button);
       
	}
    
    public void floor(){
		upTexture = new Texture(Gdx.files.internal("floor1.PNG"));
		downTexture = new Texture(Gdx.files.internal("floor2.PNG"));
		add(upTexture, downTexture, floor);
		floor = new Button(style);
		floor.setBounds(0,550,40, 40);
		FloorButtonClick(floor);
		 stage.addActor(floor);
	}
    
    public void floor1(){
		upTexture = new Texture(Gdx.files.internal("floor2.PNG"));
		downTexture = new Texture(Gdx.files.internal("floor1.PNG"));
		add(upTexture, downTexture, floor1);
		floor1 = new Button(style);
		floor1.setBounds(50,550,40, 40);
		FloorButtonClick1(floor1);
		stage.addActor(floor1);
	}
    
    public void add(Texture upTexture, Texture downTexture, Button button) {
		 //创建 ButtonStyle
		style = new Button.ButtonStyle();
		
		// 设置 style 的 弹起 和 按下 状态的纹理区域
		style.up = new TextureRegionDrawable(new TextureRegion(upTexture));
		style.down = new TextureRegionDrawable(new TextureRegion(downTexture));
	}
    
    public void PlayButtonClick(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.printf("Play\n");
			}
		});
	}
    
    public void FloorButtonClick(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.printf("Floor\n");
				CreateActor(1);
			}
		});
	}
    
    public void FloorButtonClick1(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.printf("Floor\n");
				CreateActor(2);
			}
		});
	}
    
    public void CreateActor(int actorNum) {
    	// 创建纹理 和 演员
    	if (actorNum == 1){
    		texture = new Texture(Gdx.files.internal("floor3.PNG"));
    		    	}
    	else if(actorNum == 2){
    		texture = new Texture(Gdx.files.internal("floor4.PNG"));
    	}
    	
        actor = new MyActor(new TextureRegion(texture));
        // 设置演员的位置
        actor.setBounds(80, 80, 80, 80);
        // 添加演员        
        //System.out.printf("%f,%f\n", stage.getWidth(), stage.getHeight());
        stage.addActor(actor);
        // 给演员添加一个 点击 监听器（只包括 手指点击 或 鼠标点击）
        actor.addListener(new MyClickListener());
    }

    /**
     * 输入事件监听器（包括触屏, 鼠标点击, 键盘按键 的输入）
     */
    private class MyInputListener extends InputListener {     
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            Gdx.app.log(TAG, "touchDown: " + x + ", " + y + "; pointer: " + pointer);                
            
            return true;
        }
        
         //手指/鼠标 按下后拖动时调用
        @Override
        public void touchDragged(InputEvent event, float x, float y, int pointer) {
        	Gdx.app.log(TAG, "touchDragged: " + x + ", " + y + "; pointer: " + pointer); 	
        	
        }

        
        //手指/鼠标 抬起时调用         
        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            Gdx.app.log(TAG, "touchUp: " + x + ", " + y + "; pointer: " + pointer);           
            
        }
               
        
        @Override
        public boolean mouseMoved(InputEvent event, float x, float y) {
        	//Gdx.app.log(TAG, "Mouse position: " + x + ", " + y );        
        	if(checkClick == 1) {
        		float xPos = x-(actor.getWidth()/2);
        		float yPos = y-(actor.getHeight()/2);
        		
        		float setPosX = ((int)(xPos/80))*80;
        		float setPosY = ((int)(yPos/80))*80;
        		actor.setPosition(setPosX,  setPosY);
        		Gdx.app.log(TAG, "mousePos: " + x + ", " + y + "; pointer: "); 
        	}
        	return true;
        }
    }

    /**
     * 点击 监听器（只包括 手指点击 或 鼠标点击）
     */
    private class MyClickListener extends ClickListener {    	
    	/**
    	 * 对象（演员/舞台）被点击时调用
    	 * 
    	 * @param x 
    	 * 		点击时（手指抬起时）的 X 轴坐标, 相对于被点击对象（监听器注册者）的左下角
    	 * 
    	 * @param y 
    	 * 		点击时（手指抬起时）的 Y 轴坐标, 相对于被点击对象（监听器注册者）的左下角
    	 */
        @Override
        public void clicked(InputEvent event, float x, float y) {
            // 获取响应这个点击事件的演员            
        	actor = (MyActor) event.getListenerActor();

            Gdx.app.log(TAG, "被点击: " + x + ", " + y + "; Actor: " + actor.getClass().getSimpleName());
            if(checkClick ==0) {
            	checkClick = 1;
            }
            else if(checkClick ==1) {
            	checkClick = 0;
            }
            System.out.printf("check:%d\n", checkClick);
        }
    }

}
