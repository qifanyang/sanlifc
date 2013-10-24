package com.sanli.model;


import java.util.ArrayList;


/**
 * 通用的UI绑定类
 */
public class UIBinder<T> {
	private T binder;
	private String name;
	
	public UIBinder(T binder, String name){
		this.binder = binder;
		this.name = name;
	}
	
	public T getBinder() {
		return binder;
	}
	public void setBinder(T binder) {
		this.binder = binder;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		ITextField textField = new ITextField("hello");
		UIBinder<UIData> binder = new UIBinder<UIData>(textField, "text");
		
		ISpinner s = new ISpinner();
		UIBinder<UIData> binder1 = new UIBinder<UIData>(s, "ss");
		
		System.out.println(binder.getBinder().getString());
		
		ArrayList<UIBinder<UIData>> list = new ArrayList<UIBinder<UIData>>();
		
		list.add(binder);
		list.add(binder1);
		
		for(UIBinder<UIData> data : list){
			UIData uiData = data.getBinder();
			if(uiData instanceof ITextField){
				System.out.println("ITextField");
			}
			if(uiData instanceof ISpinner){
				System.out.println("ISpinner");
			}
			
			System.out.println();
		}
		
		
	}

}
