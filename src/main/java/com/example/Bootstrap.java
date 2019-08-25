package com.example;

import java.lang.reflect.Method;
import java.net.URL;

import groovy.lang.Binding;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;

public class Bootstrap {
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(Bootstrap.class.getClassLoader());
		String userHome=System.getProperty("user.home");
		String fileSeparator=System.getProperty("file.separator");
		String target=String.join(fileSeparator, userHome,"tmp","groovy");
		System.setProperty("groovy.target.directory",target);
		URL root=Bootstrap.class.getResource("/groovy/");
		GroovyScriptEngine engine=new GroovyScriptEngine(new URL[] {root});
		while(true) {
			run(engine);
			script(engine);
			clazz(engine);
			Thread.sleep(5000);
		}
	}
	
	public static void run(GroovyScriptEngine engine) throws Exception {
		Binding binding=new Binding();
		Object object=engine.run("Run.groovy", binding);
		System.out.println(object);
	}
	
	public static void script(GroovyScriptEngine engine) throws Exception {
		Binding binding=new Binding();
		Script script=engine.createScript("Script.groovy", binding);
		Object object=script.invokeMethod("sayHello","lff");
		System.out.println(object);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void clazz(GroovyScriptEngine engine) throws Exception {
		Class clazz=engine.loadScriptByName("Greeter.groovy");
		System.out.println(clazz);
		System.out.println(clazz.getClassLoader());
		System.out.println(clazz.getClassLoader().getParent());
		System.out.println(clazz.getClassLoader().getParent().getParent());
		System.out.println(clazz.getClassLoader().getParent().getParent().getParent());
		Object instance=clazz.newInstance();
		Method method=clazz.getMethod("sayHello",Student.class);
		Object ret=method.invoke(instance,new Student("ssl", "dhu"));
		System.out.println(ret);
	}
	
}
