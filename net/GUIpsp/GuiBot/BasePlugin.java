package net.GUIpsp.GuiBot;

public abstract class BasePlugin {

	public abstract void main() throws Throwable;

	public abstract String version();

	public abstract String pluginName();

	public abstract String author();

	public boolean hidden() {
		return false;
	};

	public final void registerCmd(String cmd, Object toCall, Object that) {
		Main.cmdmap.put(cmd, toCall);
		Main.classmap.put(cmd, that);

		System.out
				.println("Hooked command \""
						+ cmd
						+ "\" to "
						+ toCall.toString().split(" ")[toCall.toString().split(
								" ").length - 1]);

	}
}
