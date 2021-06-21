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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * 游戏主程序的启动入口类
 */
public class MainGame extends ApplicationAdapter {

    private static final String TAG = MainGame.class.getSimpleName();
    public static final float WORLD_WIDTH = 1280;
	public static final float WORLD_HEIGHT = 720;
    
    private Texture texture;	// 纹理    
    private MyActor actor;	// 演员
    private Group floor, furniture;	//分組
    private Stage stage;		// 舞台
    private Image image; 	// 圖片
    
    public int checkClick = 0;					//當actor被選擇時為1，判斷是否有東西被選中
    public Texture upTexture;					// 按钮 弹起 状态的纹理
	public Texture downTexture;				// 按钮 按下 状态的纹理
	public Button button, button1, button2, button3, floor1, floor2, floor3, floor4;	// 按钮
	public Button.ButtonStyle style;			//按鈕風格

    @Override
    public void create() {
        // 设置 Log 输出级别
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        stage = new Stage();		//設置舞台        
        BackgroundCreate();
        
        floor = new Group();
        floor.setPosition(0, 0);
        stage.addActor(floor);       
        
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
    
    public void BackgroundCreate() {    	
        texture = new Texture(Gdx.files.internal("frame1.png"));               
        // 创建 Image
        image = new Image(new TextureRegion(texture));        
        // 设置 image 的相关属性        
        image.setBounds(16, 115, 240, 570);        
        // 添加 image 到舞台
        stage.addActor(image);
        
        texture = new Texture(Gdx.files.internal("frame2.png"));                
        image = new Image(new TextureRegion(texture));         
        image.setBounds(275, 115, 970, 570);
        stage.addActor(image);
        
        texture = new Texture(Gdx.files.internal("text.png"));                
        image = new Image(new TextureRegion(texture));                
        image.setPosition(972, 15);        
        stage.addActor(image);
        
    }
    
    public void ButtonCreate() {    	
    	Gdx.input.setInputProcessor(stage);		// 将输入处理设置到舞台（必须设置, 否则点击按钮没效果）
        
		//buttonStart();
		button1();button2();button3();floor1();floor2(); floor3(); floor4();		
			
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
		button.setPosition(30, 70);
		//調用ButtonClick function
	    PlayButtonClick(button);
	  //添加 button 到舞台
        stage.addActor(button);
       
	}
    
    public void button1(){
		upTexture = new Texture(Gdx.files.internal("button1_1.PNG"));
		downTexture = new Texture(Gdx.files.internal("button1_2.PNG"));
		add(upTexture, downTexture, button1);
		button1 = new Button(style);
		button1.setBounds(25,30,150, 60);		
		ButtonClick1(button1);
		stage.addActor(button1);
	}
    public void button2(){
		upTexture = new Texture(Gdx.files.internal("button2_1.PNG"));
		downTexture = new Texture(Gdx.files.internal("button2_2.PNG"));
		add(upTexture, downTexture, button2);
		button2 = new Button(style);
		button2.setBounds(200,30,150, 60);		
		ButtonClick2(button2);
		stage.addActor(button2);
	}
    public void button3(){
		upTexture = new Texture(Gdx.files.internal("button3_1.PNG"));
		downTexture = new Texture(Gdx.files.internal("button3_2.PNG"));
		add(upTexture, downTexture, button3);
		button3 = new Button(style);
		button3.setBounds(375,30,150, 60);		
		ButtonClick3(button3);
		stage.addActor(button3);
	}
    
    public void floor1(){
		upTexture = new Texture(Gdx.files.internal("floor1.PNG"));
		downTexture = new Texture(Gdx.files.internal("floor1.PNG"));		
		add(upTexture, downTexture, floor1);
		floor1 = new Button(style);
		floor1.setBounds(40,605,60, 60);
		FloorButtonClick1(floor1);
		 stage.addActor(floor1);
	}
    public void floor2(){
		upTexture = new Texture(Gdx.files.internal("floor2.PNG"));
		downTexture = new Texture(Gdx.files.internal("floor2.PNG"));
		add(upTexture, downTexture, floor2);
		floor2 = new Button(style);
		floor2.setBounds(120,605,60, 60);
		FloorButtonClick2(floor2);
		stage.addActor(floor2);
	}
    public void floor3(){
		upTexture = new Texture(Gdx.files.internal("floor3.PNG"));
		downTexture = new Texture(Gdx.files.internal("floor3.PNG"));
		add(upTexture, downTexture, floor3);
		floor3 = new Button(style);
		floor3.setBounds(40,530,60, 60);
		FloorButtonClick3(floor3);
		stage.addActor(floor3);
	}
    public void floor4(){
		upTexture = new Texture(Gdx.files.internal("floor4.PNG"));
		downTexture = new Texture(Gdx.files.internal("floor4.PNG"));
		add(upTexture, downTexture, floor4);
		floor4 = new Button(style);
		floor4.setBounds(120,530,60, 60);
		FloorButtonClick4(floor4);
		stage.addActor(floor4);
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
    
    public void ButtonClick1(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				floor.remove();	//清除所有地板
				
				//group被清除後需要被重新宣告
				floor = new Group();
		        floor.setPosition(0, 0);
		        stage.addActor(floor);       
			}
		});
	}
    public void ButtonClick2(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.printf("Floor2\n");				
			}
		});
	}
    public void ButtonClick3(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.printf("Floor3\n");				
			}
		});
	}
    
    public void FloorButtonClick1(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.printf("Floor\n");
				CreateActor(1);
			}
		});
	}
    
    public void FloorButtonClick2(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.printf("Floor\n");
				CreateActor(2);
			}
		});
	}
    
    public void FloorButtonClick3(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.printf("Floor\n");
				CreateActor(3);
			}
		});
	}
    
    public void FloorButtonClick4(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.printf("Floor\n");
				CreateActor(4);
			}
		});
	}
    
    public void CreateActor(int actorNum) {
    	int checkSort;
    	// 创建纹理 和 演员
    	switch(actorNum) {
    		case 1:
    			texture = new Texture(Gdx.files.internal("floor1.PNG"));
    			break;
    		case 2:
    			texture = new Texture(Gdx.files.internal("floor2.PNG"));
    			break;
    		case 3:
    			texture = new Texture(Gdx.files.internal("floor3.PNG"));
    			break;
    		case 4:
    			texture = new Texture(Gdx.files.internal("floor4.PNG"));
    			break;
    	}
    	
        actor = new MyActor(new TextureRegion(texture));
        
        // 设置演员的位置
        actor.setBounds(280, 600, 80, 80);
        // 添加演员                
        stage.addActor(actor);
        // 给演员添加一个 点击 监听器（只包括 手指点击 或 鼠标点击）
        actor.addListener(new MyClickListener());
        floor.addActor(actor);
    }

    /**
     * 输入事件监听器（包括触屏, 鼠标点击, 键盘按键 的输入）
     */
	    private class MyInputListener extends InputListener {
	    	@Override
	        public boolean keyDown(InputEvent event, int keycode) {
	            switch (keycode) {	               
	                case Input.Keys.Q: {
	                	if(checkClick == 1) {
	                		actor.remove();
	                		checkClick = 0;
	                	}	                    
	                    break;
	                }
	                case Input.Keys.R: {
	                	if(checkClick == 1) {	                		
	                	}	                    
	                    break;
	                }
	            }
	            return false;
	        }   	
    	
    	//手指/鼠标 按下时调用   
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
        
        //滑鼠移動時調用
        @Override
        public boolean mouseMoved(InputEvent event, float x, float y) {        	
        	if(checkClick == 1) {
        		//判斷actor的落點，每一格的大小為80px*80px
        		float xPos = x-(actor.getWidth()/2);
        		float yPos = y-(actor.getHeight()/2);        		
        		float setPosX = (((int)(xPos/80))*80)+40;
        		float setPosY = (((int)(yPos/80))*80)+40;
        		
        		//將actor限制在一個範圍内
        		if(setPosX < 280)actor.setPosition(280,  setPosY);        		
        		else if(setPosX > 1160)actor.setPosition(1160,  setPosY);        		
        		else if(setPosY > 600)actor.setPosition(setPosX,  600);        		
        		else if(setPosY < 120)actor.setPosition(setPosX,  120);        		       		
        		else actor.setPosition(setPosX,  setPosY);        		
        		
        		Gdx.app.log(TAG, "mousePos: " + x + ", " + y + "; pointer: "); 
        		Gdx.app.log(TAG, "graphPos: " + setPosX + ", " + setPosY + "; pointer: "); 
        	}
        	return true;
        }
    }
    
     //点击 监听器（只包括 手指点击 或 鼠标点击）     
    private class MyClickListener extends ClickListener {    	
    	 
    	//对象（演员/舞台）被点击时调用    	 
        @Override
        public void clicked(InputEvent event, float x, float y) {
            // 获取响应这个点击事件的演员            
        	actor = (MyActor) event.getListenerActor();
            
            if(checkClick ==0) checkClick = 1;            
            else if(checkClick ==1) checkClick = 0;        
        }
    }

}
