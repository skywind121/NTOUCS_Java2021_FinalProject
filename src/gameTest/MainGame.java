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
    public int moveMode = 0;					//當1時表示被選擇的是floor
    public int actorStage = 1;
    public float mousePosX, mousePosY;
    public Texture upTexture;					// 按钮 弹起 状态的纹理
	public Texture downTexture;				// 按钮 按下 状态的纹理
	public Button button, button1, button2, button3, floor1, floor2, floor3, floor4;	// 按钮
	public Button bed1, bed2, bigBed1, cabient1, cabient2, cabient3, chair1, sofa1, sofa2, table1, table2, table3, television;
	public Button.ButtonStyle style;			//按鈕風格

    @Override
    public void create() {
        // 设置 Log 输出级别
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        stage = new Stage();		//設置舞台        
        
        BackgroundCreate();	//創建背景
        GroupCreate();	//創建組別
        ButtonCreate();	//創建按鈕
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
    
    public void GroupCreate() {
    	//新增組別
        floor = new Group();
        floor.setPosition(0, 0);
        stage.addActor(floor);        
        furniture = new Group();
        furniture.setPosition(0, 0);
        stage.addActor(furniture);
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
		bed1(); bed2();bigBed1();cabient1(); cabient2(); cabient3();
		chair1(); sofa1(); sofa2(); table1(); table2(); table3(); television();
            
        Gdx.input.setInputProcessor(stage);	//事件初始化    

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
    
    public void bed1(){
		upTexture = new Texture(Gdx.files.internal("bed1_1.PNG"));
		downTexture = new Texture(Gdx.files.internal("bed1_1.PNG"));
		add(upTexture, downTexture, bed1);
		bed1 = new Button(style);
		bed1.setBounds(45,440,40, 80);		
		BedButtonClick1(bed1);
		stage.addActor(bed1);
	}
    public void bed2(){
		upTexture = new Texture(Gdx.files.internal("bed2_1.PNG"));
		downTexture = new Texture(Gdx.files.internal("bed2_1.PNG"));
		add(upTexture, downTexture, bed2);
		bed2= new Button(style);
		bed2.setBounds(100,440,40, 80);		
		BedButtonClick2(bed2);
		stage.addActor(bed2);
	}    
    public void bigBed1(){
		upTexture = new Texture(Gdx.files.internal("bigBed1_1.PNG"));
		downTexture = new Texture(Gdx.files.internal("bigBed1_1.PNG"));
		add(upTexture, downTexture, bigBed1);
		bigBed1= new Button(style);
		bigBed1.setBounds(160,440, 55, 80);		
		BedButtonClick3(bigBed1);
		stage.addActor(bigBed1);
	}
    public void cabient1(){
		upTexture = new Texture(Gdx.files.internal("cabient1.PNG"));
		downTexture = new Texture(Gdx.files.internal("cabient1.PNG"));
		add(upTexture, downTexture, cabient1);
		cabient1= new Button(style);
		cabient1.setBounds(25,360, 80, 70);		
		CabientButtonClick1(cabient1);
		stage.addActor(cabient1);
	}
    public void cabient2(){
		upTexture = new Texture(Gdx.files.internal("cabient2.PNG"));
		downTexture = new Texture(Gdx.files.internal("cabient2.PNG"));
		add(upTexture, downTexture, cabient2);
		cabient2= new Button(style);
		cabient2.setBounds(110,360, 50, 60);		
		CabientButtonClick2(cabient2);
		stage.addActor(cabient2);
	}
    public void cabient3(){
		upTexture = new Texture(Gdx.files.internal("cabient3.PNG"));
		downTexture = new Texture(Gdx.files.internal("cabient3.PNG"));
		add(upTexture, downTexture, cabient3);
		cabient3= new Button(style);
		cabient3.setBounds(165,360, 75, 30);		
		CabientButtonClick3(cabient3);
		stage.addActor(cabient3);
	}
    public void chair1(){
		upTexture = new Texture(Gdx.files.internal("chair1_1.PNG"));
		downTexture = new Texture(Gdx.files.internal("chair1_1.PNG"));
		add(upTexture, downTexture, chair1);
		chair1= new Button(style);
		chair1.setBounds(40,300, 30, 30);	
		
		ChairButtonClick1(chair1);
		stage.addActor(chair1);
	}
    public void sofa1(){
		upTexture = new Texture(Gdx.files.internal("sofa1_1.PNG"));
		downTexture = new Texture(Gdx.files.internal("sofa1_1.PNG"));
		add(upTexture, downTexture, sofa1);
		sofa1= new Button(style);
		sofa1.setBounds(85,300, 75, 40);		
		SofaButtonClick1(sofa1);
		stage.addActor(sofa1);
	}
    public void sofa2(){
		upTexture = new Texture(Gdx.files.internal("sofa2_1.PNG"));
		downTexture = new Texture(Gdx.files.internal("sofa2_1.PNG"));
		add(upTexture, downTexture, sofa2);
		sofa2= new Button(style);
		sofa2.setBounds(170,300, 75, 50);		
		SofaButtonClick2(sofa2);
		stage.addActor(sofa2);
	}
    public void table1(){
		upTexture = new Texture(Gdx.files.internal("table1.PNG"));
		downTexture = new Texture(Gdx.files.internal("table1.PNG"));
		add(upTexture, downTexture, table1);
		table1= new Button(style);
		table1.setBounds(25,230, 70, 45);		
		TableButtonClick1(table1);
		stage.addActor(table1);
	}
    public void table2(){
		upTexture = new Texture(Gdx.files.internal("table2.PNG"));
		downTexture = new Texture(Gdx.files.internal("table2.PNG"));
		add(upTexture, downTexture, table2);
		table2= new Button(style);
		table2.setBounds(105,230, 70, 45);		
		TableButtonClick2(table2);
		stage.addActor(table2);
	}
    public void table3(){
		upTexture = new Texture(Gdx.files.internal("table3.PNG"));
		downTexture = new Texture(Gdx.files.internal("table3.PNG"));
		add(upTexture, downTexture, table3);
		table3= new Button(style);
		table3.setBounds(190,230, 50, 35);		
		TableButtonClick3(table3);
		stage.addActor(table3);
	}
    public void television(){
		upTexture = new Texture(Gdx.files.internal("television.PNG"));
		downTexture = new Texture(Gdx.files.internal("television.PNG"));
		add(upTexture, downTexture, television);
		television= new Button(style);
		television.setBounds(35,170, 65, 40);		
		TelevisionButtonClick(television);
		stage.addActor(television);
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
				button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				furniture.remove();					
				furniture = new Group();
				furniture.setPosition(0, 0);
		        stage.addActor(furniture);       				
			}
		});
	}
    public void ButtonClick3(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				floor.remove();					
				floor = new Group();
		        floor.setPosition(0, 0);
		        stage.addActor(floor);
		        furniture.remove();					
				furniture = new Group();
				furniture.setPosition(0, 0);
		        stage.addActor(furniture);  
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
    public void BedButtonClick1(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.printf("Bed1\n");
				CreateActor(5);
			}
		});
	}
    public void BedButtonClick2(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.printf("Bed2\n");
				CreateActor(6);
			}
		});
	}
    public void BedButtonClick3(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.printf("Bed2\n");
				CreateActor(7);
			}
		});
	}
    public void CabientButtonClick1(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.printf("Bed2\n");
				CreateActor(8);
			}
		});
	}    
    public void CabientButtonClick2(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.printf("Bed2\n");
				CreateActor(9);
			}
		});
	}
    public void CabientButtonClick3(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.printf("Bed2\n");
				CreateActor(10);
			}
		});
	}
    public void ChairButtonClick1(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {				
				CreateActor(11);
			}
		});
	}
    public void SofaButtonClick1(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {				
				CreateActor(12);
			}
		});
	}
    public void SofaButtonClick2(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {				
				CreateActor(13);
			}
		});
	}
    public void TableButtonClick1(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {				
				CreateActor(14);
			}
		});
	}
    public void TableButtonClick2(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {				
				CreateActor(15);
			}
		});
	}
    public void TableButtonClick3(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {				
				CreateActor(16);
			}
		});
	}
    public void TelevisionButtonClick(Button button) {
		// 给按钮添加点击监听器
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {				
				CreateActor(17);
			}
		});
	}
    
    public void CreateActor(int actorNum) {
    	int checkSort=0;	//0=floor  1=other;
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
    		case 5:
    			texture = new Texture(Gdx.files.internal("bed1_1.PNG"));    			
    			checkSort = 1;    			
    			break;
    		case 6:
    			texture = new Texture(Gdx.files.internal("bed2_1.PNG"));    			
    			checkSort = 1;
    			break;
    		case 7:
    			texture = new Texture(Gdx.files.internal("bigBed1_1.PNG"));    			
    			checkSort = 1;
    			break;
    		case 8:
    			texture = new Texture(Gdx.files.internal("cabient1.PNG"));    			
    			checkSort = 1;
    			break;
    		case 9:
    			texture = new Texture(Gdx.files.internal("cabient2.PNG"));    			
    			checkSort = 1;
    			break;
    		case 10:
    			texture = new Texture(Gdx.files.internal("cabient3.PNG"));    			
    			checkSort = 1;
    			break;
    		case 11:	
    			texture = new Texture(Gdx.files.internal("chair1_1.PNG"));    			
    			checkSort = 1;
    			break;
    		case 12:
    			texture = new Texture(Gdx.files.internal("sofa1_1.PNG"));    			
    			checkSort = 1;
    			break;
    		case 13:
    			texture = new Texture(Gdx.files.internal("sofa2_1.PNG"));    			
    			checkSort = 1;
    			break;
    		case 14:
    			texture = new Texture(Gdx.files.internal("table1.PNG"));    			
    			checkSort = 1;
    			break;
    		case 15:
    			texture = new Texture(Gdx.files.internal("table2.PNG"));    			
    			checkSort = 1;
    			break;
    		case 16:
    			texture = new Texture(Gdx.files.internal("table3.PNG"));    			
    			checkSort = 1;
    			break;
    		case 17:
    			texture = new Texture(Gdx.files.internal("television.PNG"));    			
    			checkSort = 1;
    			break;    			
    	}    	
    	AddActor(checkSort, actorNum);	    
    }
    
    public void AddActor(int check, int checkNum) {
	    	// 添加演员
	        actor = new MyActor(new TextureRegion(texture));                        
	        stage.addActor(actor);	        
	        // 给演员添加一个 点击 监听器（只包括 手指点击 或 鼠标点击）
	        actor.addListener(new MyClickListener());
	        
	        if(check==0) {
	        	// 设置演员的位置        	
	        	actor.setBounds(280, 600, 80, 80);
	        }
	        else {        	
	        	actor.setPosition(300, 560);        	
	        }
	        
	        switch(checkNum) {    		
    		case 5:
    			actor.setName("bed1");
    			break;
    		case 7:
    			actor.setName("bigBed1");
    			break;
    		case 11:
    			actor.setName("chair1");
    			break;
    		case 12:
    			actor.setName("sofa1");
    			break;
    		case 13:
    			actor.setName("sofa2");
    			break;
	        } 
	        
	        //為actor設置組別
	        if(check==0) floor.addActor(actor);	        
	        else 	furniture.addActor(actor);
	        
    }
    
    	// 输入事件监听器（包括触屏, 鼠标点击, 键盘按键 的输入）     
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
	                		if(actor.getName()=="bed1" || actor.getName()=="bigBed1" || actor.getName()=="chair1" || actor.getName()=="sofa1" || actor.getName()=="sofa2") {
	                			String saveName = actor.getName();
	                			actor.remove();
	                			if(actorStage==1) actorStage=2;	                			
	                			else if(actorStage==2) actorStage=3;   
	                			else if(actorStage==3) actorStage=4;	
	                			else if(actorStage==4) actorStage=1;	
	                	
	                			texture = new Texture(Gdx.files.internal( actor.getName() + "_" + actorStage + ".png" ));	
	                			actor = new MyActor(new TextureRegion(texture));                        
                		        stage.addActor(actor);
                		        actor.addListener(new MyClickListener());                		        
                		        actor.setPosition(mousePosX, mousePosY); 
                		        furniture.addActor(actor);
                		        actor.setName(saveName);	                			
	                		}
	                	}	                    
	                    
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
        	//Gdx.app.log(TAG, "touchDragged: " + x + ", " + y + "; pointer: " + pointer);        	
        }
        
        //手指/鼠标 抬起时调用         
        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            //Gdx.app.log(TAG, "touchUp: " + x + ", " + y + "; pointer: " + pointer);            
        }               
        
        //滑鼠移動時調用
        @Override
        public boolean mouseMoved(InputEvent event, float x, float y) {        	
        	if(checkClick == 1) {
        		float setPosX;
        		float setPosY;
        		
        		//當moveMode為1，表示actor是floor，所以移動範圍要大一點
        		if(moveMode ==1) {
        			//判斷actor的落點，每一格的大小為80px*80px
	        		float xPos = x-(actor.getWidth()/2);
	        		float yPos = y-(actor.getHeight()/2);        		
	        		setPosX = (((int)(xPos/80))*80)+40;
	        		setPosY = (((int)(yPos/80))*80)+40;
	        		
	        		//將actor限制在一個範圍内
	        		if(setPosX < 280)actor.setPosition(280,  setPosY);        		
	        		else if(setPosX > 1160)actor.setPosition(1160,  setPosY);        		
	        		else if(setPosY > 600)actor.setPosition(setPosX,  600);        		
	        		else if(setPosY < 120)actor.setPosition(setPosX,  120);        		       		
	        		else actor.setPosition(setPosX,  setPosY);
        		}
        		
        		//當moveMode為0，表示actor是家具，所以移動範圍要小一點
        		else {
        			float xPos = x-(actor.getWidth()/2);
	        		float yPos = y-(actor.getHeight()/2);        		
	        		setPosX = (((int)(xPos/4))*4)+ (float)2;
	        		setPosY = (((int)(yPos/4))*4)+ (float)2;
	        		actor.setPosition(setPosX,  setPosY);            		
        		}
        		mousePosX = setPosX;
        		mousePosY = setPosY;
        		        		 
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
        	System.out.printf("%s\n", actor.getName());
        	
        	moveMode = 0;
            float checkHeight = actor.getHeight();
            float checkWeight = actor.getWidth();
            if(checkHeight == 80.0 && checkWeight == 80.0) {
            	moveMode = 1;
            }        	
        	
            if(checkClick ==0) {
            	checkClick = 1;
            	actorStage = 1;
            }
            else if(checkClick ==1) {
            	checkClick = 0;
            	actorStage = 1;
            }
        }
    }

}
