package gameTest;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {

	public static void main(String[] args) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1280;			// 窗口宽度
		config.height = 720;		// 窗口高度

		config.resizable = false;	// 窗口设置为大小不可改变

		// 创建LibGDX启动入口 MainGame 对象, 传入配置 config, 启动游戏程序
		new LwjglApplication(new MainGame(), config);
	}
}
