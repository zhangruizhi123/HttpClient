package com.telrob;

import java.util.Arrays;

/**
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年8月8日 下午12:43:04
 *
 */
public class Data {
	private String[] result;
	private int flag;
	public String[] getResult() {
		return result;
	}
	public void setResult(String[] result) {
		this.result = result;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Data [result=" + Arrays.toString(result) + ", flag=" + flag
				+ "]";
	}
	
	
}
