package com.makingiants.model.managers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/*
*
How to generate all the vk...
*
* private static final Map<String, Integer> keyCodes;
* static{
* keyCodes = new HashMap<String, Integer>(256);
* Field[] fields = KeyEvent.class.getDeclaredFields();
* 
* for (Field field : fields) {
* String name = field.getName();
* if (name.startsWith("VK_")) {
* try {
* System.out.println(String.format("else if(key.equals(\"%s\")){ return KeyEvent.%s;}", name.substring("VK_".length()).toUpperCase(), name));
* keyCodes.put(name.substring("VK_".length()).toUpperCase(),
* field.getInt(field));
* } catch (IllegalArgumentException ex) {
* Logger.getLogger(KeyboardManager.class.getName()).log(Level.SEVERE, null, ex);
* } catch (IllegalAccessException ex) {
* Logger.getLogger(KeyboardManager.class.getName()).log(Level.SEVERE, null, ex);
* }
* }
* }
* }*/
public class KeyboardManager {
	
	// ---------------------------------------------
	// Attributes
	// ---------------------------------------------
	
	private Robot robot;
	
	// ---------------------------------------------
	// Constructor
	// ---------------------------------------------
	
	public KeyboardManager() throws AWTException {
		this.robot = new Robot();
	}
	
	// ---------------------------------------------
	// Keyboard events management
	// ---------------------------------------------
	
	public void pressKey(String key) {
		
		int code = getKeyCode(key);
		
		System.out.println("SERVER Key '" + key + "'");
		
		robot.keyPress(code);
		robot.keyRelease(code);
		
	}
	
	private int getKeyCode(String key) {
		if (key.equals("ENTER")) {
			return KeyEvent.VK_ENTER;
		} else if (key.equals("BACK_SPACE")) {
			return KeyEvent.VK_BACK_SPACE;
		} else if (key.equals("TAB")) {
			return KeyEvent.VK_TAB;
		} else if (key.equals("CAPS_LOCK")) {
			return KeyEvent.VK_CAPS_LOCK;
		} else if (key.equals("LEFT")) {
			return KeyEvent.VK_LEFT;
		} else if (key.equals("UP")) {
			return KeyEvent.VK_UP;
		} else if (key.equals("RIGHT")) {
			return KeyEvent.VK_RIGHT;
		} else if (key.equals("DOWN")) {
			return KeyEvent.VK_DOWN;
		} else if (key.equals("DELETE")) {
			return KeyEvent.VK_DELETE;
		} else if (key.equals("EURO_SIGN¬")) {
			return KeyEvent.VK_EURO_SIGN;
		} else if (key.equals("SPACE")) {
			return KeyEvent.VK_SPACE;
		} else if (key.equals("@")) {
			return KeyEvent.VK_AT;
		} else if (key.equals(",")) {
			return KeyEvent.VK_COMMA;
		} else if (key.equals("-")) {
			return KeyEvent.VK_MINUS;
		} else if (key.equals(".")) {
			return KeyEvent.VK_PERIOD;
		} else if (key.equals(":")) {
			return KeyEvent.VK_COLON;
		} else if (key.equals(";")) {
			return KeyEvent.VK_SEMICOLON;
		} else if (key.equals("/")) {
			return KeyEvent.VK_SLASH;
		} else if (key.equals("=")) {
			return KeyEvent.VK_EQUALS;
		} else if (key.equals("[")) {
			return KeyEvent.VK_OPEN_BRACKET;
		} else if (key.equals("]")) {
			return KeyEvent.VK_CLOSE_BRACKET;
		} else if (key.equals("\\")) {
			return KeyEvent.VK_BACK_SLASH;
		} else if (key.equals("*")) {
			return KeyEvent.VK_ASTERISK;
		} else if (key.equals("+")) {
			return KeyEvent.VK_PLUS;
		} else if (key.equals("\"")) {
			return KeyEvent.VK_QUOTE;
		} else if (key.equals("&")) {
			return KeyEvent.VK_AMPERSAND;
		} else if (key.equals("<")) {
			return KeyEvent.VK_LESS;
		} else if (key.equals(">")) {
			return KeyEvent.VK_GREATER;
		} else if (key.equals("^")) {
			return KeyEvent.VK_CIRCUMFLEX;
		} else if (key.equals("$")) {
			return KeyEvent.VK_DOLLAR;
		} else if (key.equals("!")) {
			return KeyEvent.VK_EXCLAMATION_MARK;
		} else if (key.equals("Á")) {
			return KeyEvent.VK_INVERTED_EXCLAMATION_MARK;
		} else if (key.equals("(")) {
			return KeyEvent.VK_LEFT_PARENTHESIS;
		} else if (key.equals(")")) {
			return KeyEvent.VK_RIGHT_PARENTHESIS;
		} else if (key.equals("#")) {
			return KeyEvent.VK_NUMBER_SIGN;
		} else if (key.equals("_")) {
			return KeyEvent.VK_UNDERSCORE;
		} else if (key.equals("0")) {
			return KeyEvent.VK_0;
		} else if (key.equals("1")) {
			return KeyEvent.VK_1;
		} else if (key.equals("2")) {
			return KeyEvent.VK_2;
		} else if (key.equals("3")) {
			return KeyEvent.VK_3;
		} else if (key.equals("4")) {
			return KeyEvent.VK_4;
		} else if (key.equals("5")) {
			return KeyEvent.VK_5;
		} else if (key.equals("6")) {
			return KeyEvent.VK_6;
		} else if (key.equals("7")) {
			return KeyEvent.VK_7;
		} else if (key.equals("8")) {
			return KeyEvent.VK_8;
		} else if (key.equals("9")) {
			return KeyEvent.VK_9;
		} else if (key.equalsIgnoreCase("A")) {
			return KeyEvent.VK_A;
		} else if (key.equalsIgnoreCase("B")) {
			return KeyEvent.VK_B;
		} else if (key.equalsIgnoreCase("C")) {
			return KeyEvent.VK_C;
		} else if (key.equalsIgnoreCase("D")) {
			return KeyEvent.VK_D;
		} else if (key.equalsIgnoreCase("E")) {
			return KeyEvent.VK_E;
		} else if (key.equalsIgnoreCase("F")) {
			return KeyEvent.VK_F;
		} else if (key.equalsIgnoreCase("G")) {
			return KeyEvent.VK_G;
		} else if (key.equalsIgnoreCase("H")) {
			return KeyEvent.VK_H;
		} else if (key.equalsIgnoreCase("I")) {
			return KeyEvent.VK_I;
		} else if (key.equalsIgnoreCase("J")) {
			return KeyEvent.VK_J;
		} else if (key.equalsIgnoreCase("K")) {
			return KeyEvent.VK_K;
		} else if (key.equalsIgnoreCase("L")) {
			return KeyEvent.VK_L;
		} else if (key.equalsIgnoreCase("M")) {
			return KeyEvent.VK_M;
		} else if (key.equalsIgnoreCase("N")) {
			return KeyEvent.VK_N;
		} else if (key.equalsIgnoreCase("O")) {
			return KeyEvent.VK_O;
		} else if (key.equalsIgnoreCase("P")) {
			return KeyEvent.VK_P;
		} else if (key.equalsIgnoreCase("Q")) {
			return KeyEvent.VK_Q;
		} else if (key.equalsIgnoreCase("R")) {
			return KeyEvent.VK_R;
		} else if (key.equalsIgnoreCase("S")) {
			return KeyEvent.VK_S;
		} else if (key.equalsIgnoreCase("T")) {
			return KeyEvent.VK_T;
		} else if (key.equalsIgnoreCase("U")) {
			return KeyEvent.VK_U;
		} else if (key.equalsIgnoreCase("V")) {
			return KeyEvent.VK_V;
		} else if (key.equalsIgnoreCase("W")) {
			return KeyEvent.VK_W;
		} else if (key.equalsIgnoreCase("X")) {
			return KeyEvent.VK_X;
		} else if (key.equalsIgnoreCase("Y")) {
			return KeyEvent.VK_Y;
		} else if (key.equalsIgnoreCase("Z")) {
			return KeyEvent.VK_Z;
		}
		
		return -1;
	}
	
}
