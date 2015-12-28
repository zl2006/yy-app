package org.yy.monitor.core.entity;

/**
 * 监控插件
 * 
 * @author zhouliang
 *
 */
public class Plugin implements Comparable<Plugin> {

	// 名称
	private String name;

	// 英文名称
	private String ename;

	// 插件code,作为标识
	private String code;

	// 插件展示顺序
	private int order = 9999;

	// vm模板所在路径
	private String vmpath = "";

	// 视图展示bean名称
	private String view = "";

	private String test = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getVmpath() {
		return vmpath;
	}

	public void setVmpath(String vmpath) {
		this.vmpath = vmpath;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	@Override
	public String toString() {
		return "Plugin [name=" + name + ", ename=" + ename + ", code=" + code
				+ ", order=" + order + ", vmpath=" + vmpath + ", view=" + view
				+ ", test=" + test + "]";
	}

	@Override
	public int compareTo(Plugin o) {
		return this.order - o.order;
	}

	// public static void main(String[] args) {
	// ResourcePatternResolver resourcePatternResolver = new
	// PathMatchingResourcePatternResolver();
	// try {
	// Resource[] metaInfResources = resourcePatternResolver
	// .getResources("classpath*:**/META-INF/plugin.properties");
	// for(Resource r : metaInfResources){
	// Properties p = new Properties();
	// p.load(r.getInputStream());
	// System.out.println(p.getProperty("plugin.name"));
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
}
